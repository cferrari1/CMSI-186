/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Christopher Ferrari
 * Date       :  2018-04-18
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-18  C. Ferrari    Initial writing and begin coding
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.Arrays;

public class BrobInt {

    public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
    public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
    public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
    public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
    public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
    public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
    public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
    public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
    public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
    public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
    public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

    /// Some constants for other intrinsic data types
    ///  these can help speed up the math if they fit into the proper memory space
    public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
    public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
    public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
    public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

    /// These are the internal fields
    private String internalValue = "";        // internal String representation of this BrobInt
    private byte   sign          = 0;         // "0" is positive, "1" is negative
    private String reversed      = "";        // the backwards version of the internal String representation
    private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string

    /**
    *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
    *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
    *   for later use
    *  @param  value  String value to make into a BrobInt
    */
    public BrobInt( String value ) {
        // Checks if digits are valid
        validateDigits(value);

        // Checks for sign
        if (value.charAt(0) == '-') {
            sign = 1;
        }
        else {
            sign = 0;
        }

        // Reverses string for byteVersion
        reversed = reverseValue( value );

        // Makes reversed array of bytes for value
        byteVersion = new byte[reversed.length()];
        for (int i = 0; i < reversed.length(); i++) {
            byteVersion[i] = (byte)(reversed.charAt(i) - '0');
        }

        // Truncates any trailing zeros in byteVersion
        if ( (byteVersion.length > 1) && (byteVersion[byteVersion.length - 1] == 0) ) {
            int zeroCount = 0;
            for (int i = byteVersion.length - 1; i > 0; i--) {
                if (byteVersion[i] == 0) {
                    zeroCount++;
                } else {
                    break;
                }

            }

            byteVersion = Arrays.copyOf(byteVersion, byteVersion.length - zeroCount);
        }

        // Creates string version of the BronInt and stores it in internalValue
        String byteVersionOutput = "";
        for( int i = 0; i < byteVersion.length; i++ ) {
            byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
        }
        byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );

        if (byteVersionOutput.equals("0")) {
            sign = 0;
        }

        if (sign == 0) {
            internalValue = byteVersionOutput;
        } else {
            internalValue = "-" + byteVersionOutput;
        }


    }

    /**
    *  Method to validate that all the characters in the value are valid decimal digits
    *  @throws  IllegalArgumentException if something is hinky
    *  note that there is no return false, because of throwing the exception
    *  note also that this must check for the '+' and '-' sign digits
    */
    private static void validateDigits( String value ) throws IllegalArgumentException {
        if (value.length() == 0) {
            throw new IllegalArgumentException();
        }

        if ( value.equals("+") || value.equals("-") ) {
            throw new IllegalArgumentException();
        }

        if (value.charAt(0) != '-' && value.charAt(0) != '+' && value.charAt(0) != '0' && value.charAt(0) != '1' && value.charAt(0) != '2' && value.charAt(0) != '3' && value.charAt(0) != '4' && value.charAt(0) != '5' && value.charAt(0) != '6' && value.charAt(0) != '7' && value.charAt(0) != '8' && value.charAt(0) != '9') {
            throw new IllegalArgumentException();
        }


        for (int i = 1; i < value.length(); i++) {
            if (value.charAt(i) != '0' && value.charAt(i) != '1' && value.charAt(i) != '2' && value.charAt(i) != '3' && value.charAt(i) != '4' && value.charAt(i) != '5' && value.charAt(i) != '6' && value.charAt(i) != '7' && value.charAt(i) != '8' && value.charAt(i) != '9') {
                throw new IllegalArgumentException();
            }
        }

    }

    private static String reverseValue( String value ) {
        String reversed = "";
        if (value.charAt(0) == '+' || value.charAt(0) == '-') {
            for (int i = value.length() - 1; i > 0; i--) {
                reversed = reversed + value.charAt(i);
            }
        } else {
            for (int i = value.length() - 1; i > -1; i--) {
                reversed = reversed + value.charAt(i);
            }
        }

        return reversed;
    }

    /** 
    *  Method to add the value of a BrobInt passed as argument to this BrobInt using byte array
    *  @param  g2         BrobInt to add to this
    *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
    */
    public BrobInt add( BrobInt g2 ) {

        // Check if one BrobInt is positive and if the other one is negative
        if (sign == 0 && g2.sign == 1) {
            return this.subtract(g2);
        } else if (sign == 1 && g2.sign == 0) {
            return g2.subtract(this);
        }

        byte carry = 0;
        byte digitArray[];
        byte g1array[];
        byte g2array[];
        String stringVer = "";


        // Create new arrays for adding
        if (byteVersion.length > g2.byteVersion.length || (byteVersion.length == g2.byteVersion.length) ) {
            g1array = new byte[byteVersion.length + 1];
            g2array = new byte[byteVersion.length + 1];
            digitArray = new byte[byteVersion.length + 1];
        } else {
            g1array = new byte[g2.byteVersion.length + 1];
            g2array = new byte[g2.byteVersion.length + 1];
            digitArray = new byte[g2.byteVersion.length + 1];
        }

        // Fill padded g1 and g2 arrays
        for (int i = 0; i < byteVersion.length; i++) {
            g1array[i] = byteVersion[i];
        }

        for (int i = 0; i < g2.byteVersion.length; i++) {
            g2array[i] = g2.byteVersion[i];
        }

        // Adding g1 and g2 arrays to make digitArray
        for (int i = 0; i < digitArray.length; i++) {

            digitArray[i] = (byte)( (g1array[i] + g2array[i] + carry) % 10);
            
            if ( (g1array[i] + g2array[i] + carry) > 9) {
                carry = 1;
            } else {
                carry = 0;
            }
        }


        for( int i = 0; i < digitArray.length; i++ ) {
            stringVer = stringVer.concat( Byte.toString( digitArray[i] ) );
        }
        stringVer = new String( new StringBuffer( stringVer ).reverse() );

        if (sign == 1) {
            stringVer =  "-" + stringVer;
        }


        return new BrobInt(stringVer);

    }

    /** 
    *  Method to subtract the value of a BrobInt passed as argument to this BrobInt using bytes
    *  @param  g2         BrobInt to subtract from this
    *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
    */
    public BrobInt subtract( BrobInt g2 ) {

        // 18 - 18 = 0, -18 - (-18) = 0
        if (this.equals(g2)) {
            return ZERO;
        }

        // - 18 - 17 = -35, -17 - 18 = -35
        if (this.sign == 1 && g2.sign == 0) {
            return this.add(new BrobInt("-" + g2.toString() ));
        }

        // 18 - (-17) = 35       
        if (this.sign == 0 && g2.sign == 1) {
            return this.add( new BrobInt( g2.toString().substring(1) ) );
        }


        byte topArray[];
        byte botArray[];
        byte subbedArray[];
        byte carry = 0;
        String stringVer = "";
        BrobInt top;
        BrobInt bot;

        if ( this.sign == 0 && g2.sign == 0 ) {
            if (this.compareTo(g2) > 0) {
                top = this;
                bot = g2;
            } else {
                top = g2;
                bot = this;
            }
        } else {

            if (this.compareTo(g2) > 0) {
                top = g2;
                bot = this;
            } else {
                top = this;
                bot = g2;
            }


        }

        topArray = new byte[top.byteVersion.length];
        botArray = new byte[top.byteVersion.length];
        subbedArray = new byte[top.byteVersion.length];

        for (int i = 0; i < top.byteVersion.length; i++) {
            topArray[i] = top.byteVersion[i];
        }

        for (int i = 0; i < bot.byteVersion.length; i++) {
            botArray[i] = bot.byteVersion[i];
        }


        for (int i = 0; i < subbedArray.length; i++) {
            
            if ( (topArray[i] - botArray[i] + carry) < 0) {
                subbedArray[i] = (byte)((topArray[i] + 10) - botArray[i] + carry);
                carry = -1;

            } else {
                subbedArray[i] = (byte)(topArray[i] - botArray[i] + carry);
                carry = 0;

            }

        }


        for( int i = 0; i < subbedArray.length; i++ ) {
            stringVer = stringVer.concat( Byte.toString( subbedArray[i] ) );
        }
        stringVer = new String( new StringBuffer( stringVer ).reverse() );

        if (this.compareTo(g2) < 0) {
            stringVer = "-" + stringVer;
        }

        return new BrobInt(stringVer);
    }

    /**
    *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
    *  @param  g2         BrobInt to multiply by this
    *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
    */
    public BrobInt multiply( BrobInt g2 ) {
        if (this.equals(ZERO) || g2.equals(ZERO)) {
            return ZERO;
        }

        BrobInt posInt1;
        BrobInt posInt2;
        String g1array[] = new String[(int)(byteVersion.length * ( Math.log(10) / Math.log(2) ) + 2)];
        BrobInt g2array[] = new BrobInt[(int)(byteVersion.length * ( Math.log(10) / Math.log(2) ) + 2)];
        BrobInt added = ZERO;

        if (this.sign == 0) {
            posInt1 = new BrobInt(this.internalValue);
        } else {
            posInt1 = new BrobInt(this.internalValue.substring(1));
        }

        if (g2.sign == 0) {
            posInt2 = new BrobInt(g2.internalValue);
        } else {
            posInt2 = new BrobInt(g2.internalValue.substring(1));
        }

        g1array[0] = posInt1.toString();
        g2array[0] = posInt2;

        for (int i = 1; i < g1array.length; i++) {
            g1array[i] = Halver.halve( g1array[i-1].toString() );
            if (g1array[i].equals("")) {
                break;
            }
        }


        for (int i = 1; i < g2array.length; i++) {
            g2array[i] = g2array[i-1].add( g2array[i-1] );
            if (g1array[i+1].equals("")) {
                break;
            }
        }

        char lastIndex = '0';

        for (int i = 0; i < g1array.length; i++) {
            if (g1array[i].equals("")) {
                break;
            }

            lastIndex =  g1array[i].charAt(g1array[i].length() - 1) ;
            if ( lastIndex == '1' || lastIndex == '3' || lastIndex == '5' || lastIndex == '7' || lastIndex == '9' ) {
                added = added.add(g2array[i]);
            }
        }

        if (this.sign == 0 && g2.sign == 1) {
            added = new BrobInt("-" + added.toString());
        } else if (this.sign == 1 && g2.sign == 0) {
            added = new BrobInt("-" + added.toString());
        }

        return added;
    }

    /** 
    *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
    *  @param  g2         BrobInt to divide this by
    *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
    */
    public BrobInt divide( BrobInt g2 ) {
        if (g2.equals(ZERO)) {
            throw new ArithmeticException( "\n         You cannot divide by zero." );
        } else if (this.equals(ZERO)) {
            return ZERO;
        }

        BrobInt counter = ZERO;
        BrobInt track1;
        BrobInt track2; 

        if (this.sign == 0) {
            track1 = new BrobInt( this.toString() );
        } else {
            track1 = new BrobInt( this.toString().substring(1) );
        }

        if (g2.sign == 0) {
            track2 = new BrobInt( g2.toString() );
        } else {
            track2 = new BrobInt( g2.toString().substring(1) );

        }

        while (true) {
            if (track1.compareTo(track2) < 0) {
                break;
            }

            track1 = track1.subtract(track2);
            counter = counter.add(ONE);
        }

        if (this.sign == 0 && g2.sign == 1) {
            counter = new BrobInt("-" + counter.internalValue);
        } else if (this.sign == 1 && g2.sign == 0) {
            counter = new BrobInt("-" + counter.internalValue);
        }

        return counter;
    }

    /**
    *  Method to get the remainder of division of this BrobInt by the one passed as argument
    *  NOTE: This method will only return positive values for the remainder
    *
    *  @param  g2         BrobInt to divide this one by
    *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
    *
    */
    public BrobInt remainder( BrobInt g2 ) {
        if (this.divide(g2).equals(ZERO)) {
            return ZERO;
        }

        BrobInt first;
        BrobInt second;

        if (this.sign == 0) {
            first = this;
        } else {
            first = new BrobInt(this.internalValue.substring(1));
        }

        if (g2.sign == 0) {
            second = g2;
        } else {
            second = new BrobInt(g2.internalValue.substring(1));
        }

        return first.subtract( second.multiply( first.divide(second) ) );


    }

    /**
    *  Method to compare a BrobInt passed as argument to this BrobInt
    *  @param  g2  BrobInt to add to this
    *  @return int   that is one of -1/0/1 if this BrobInt precedes/equals/follows the argument
    */
    public int compareTo( BrobInt g2 ) {
        if ( this.sign == 0 && g2.sign == 1 ) {
            return 1;
        } else if ( this.sign == 1 && g2.sign == 0 ) {
            return (-1);
        }

        int val = 0;

        if ( this.byteVersion.length > g2.byteVersion.length ) {
            val = 1;
        } else if ( this.byteVersion.length < g2.byteVersion.length ) {
            val = (-1);
        } else {
            for (int i = this.byteVersion.length - 1; i >= 0; i--) {
                if ( this.byteVersion[i] > g2.byteVersion[i] ) {
                    val = 1;
                    break;
                } else if ( this.byteVersion[i] < g2.byteVersion[i] ) {
                    val = (-1);
                    break;
                }
            }
        }

        if (this.sign == 1 && g2.sign == 1) {
            val *= (-1);
        }

        return val;
    }

    /**
    *  Method to check if a BrobInt passed as argument is equal to this BrobInt
    *  @param  g2     BrobInt to compare to this
    *  @return boolean  that is true if they are equal and false otherwise
    *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
    *        also using the java String "equals()" method -- THAT was easy, too..........
    */
    public boolean equals( BrobInt g2 ) {
        if ( !toString().equals( g2.toString() ) ) {
            return false;
        }            

        return true;

    }

    /**
    *  Method to return a BrobInt given a long value passed as argument
    *  @param  value         long type number to make into a BrobInt
    *  @return BrobInt  which is the BrobInt representation of the long
    */
    public static BrobInt valueOf( long value ) throws NumberFormatException {
        BrobInt gi = null;
        try {
            gi = new BrobInt( new Long( value ).toString() );
        }
        catch( NumberFormatException nfe ) {
            System.out.println( "\n  Sorry, the value must be numeric of type long." );
        }
        return gi;
    }

    /**
    *  Method to return a String representation of this BrobInt
    *  @return String  which is the String representation of this BrobInt
    */
    public String toString() {
        return internalValue;
    }

    /**
    *  Method to display an Array representation of this BrobInt as its bytes
    *  @param  d  byte array to be shown as a String representation
    */
    public void toArray( byte[] d ) {
        System.out.println( Arrays.toString( d ) );
    }

    /**
    *  the main method redirects the user to the test class
    *  @param  args  String array which contains command line arguments
    *  note:  we don't really care about these
    */
    public static void main( String[] args ) {
        System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
        System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

        System.exit( 0 );
    }
}
