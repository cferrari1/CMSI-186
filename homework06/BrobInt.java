/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
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

        if (byteVersionOutput == "0") {
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

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to subtract the value of a BrobInt passed as argument to this BrobInt using bytes
    *  @param  g2         BrobInt to subtract from this
    *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt subtract( BrobInt g2 ) {

        // 18 - 18 = 0, -18 - (-18) = 0
        if (this.equals(g2)) {
            return ZERO;
        }

        // - 18 - 17 = -35, -17 - 18 = -35
        if (this.sign == 1 && g2.sign == 0) {
            return this.add(new BrobInt("-" + g2));
        }

        // 18 - (-17) = 35       
        if (this.sign == 0 && g2.sign == 1) {
            return this.add( new BrobInt( g2.internalValue.substring(1) ) );
        }





        return ZERO;

        // this - g2

        // 18-17 
        // 17-18
        // -18 - (-19)
        // -18 - (-17)

    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
    *  @param  g2         BrobInt to multiply by this
    *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt multiply( BrobInt g2 ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
    *  @param  g2         BrobInt to divide this by
    *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt divide( BrobInt g2 ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to get the remainder of division of this BrobInt by the one passed as argument
    *  @param  g2         BrobInt to divide this one by
    *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public BrobInt remainder( BrobInt g2 ) {
        throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    }

    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to compare a BrobInt passed as argument to this BrobInt
    *  @param  g2  BrobInt to add to this
    *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
    *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
    *        THAT was easy.....
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    public int compareTo( BrobInt g2 ) {
        return (toString().compareTo( g2.toString() ));
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
