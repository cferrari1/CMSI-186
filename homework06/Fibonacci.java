/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Fibonacci.java
 * Purpose    :  Find the "nth" Fibonacci number given an argument, using BrobInt class
 * @author    :  B.J. Johnson
 * Date       :  2017-04-17
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-17  B.J. Johnson  Initial writing and begin coding
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Fibonacci {

    private static final String usageMessage = "\n  You must enter an integer number....." +
                                               "\n    Please try again!" +
                                               "\n  USAGE: java Fibonacci <required_integer>\n\n";
    private static int    maxCount    = 0;
    private static int    working     = 15000;
    private static String end1        = "st";
    private static String end2        = "nd";
    private static String end3        = "rd";
    private static String endRest     = "th";
    private static String cardinality = "";

    private static final  int WRONG_NUM_CMD_LINE_ARGS = -1;
    private static final  int BAD_CMD_LINE_ARG = -2;

    public Fibonacci() {
        super();
    }

    public static void main( String[] args ) {
        System.out.println( "\n\n   Welcome to the Fibonacci sequence number finder!\n" );
        if( 1 != args.length ) {
            System.out.println( usageMessage );
            System.exit( WRONG_NUM_CMD_LINE_ARGS );
        }
        try {
            maxCount = Integer.parseInt( args[0] );
        }
        catch( NumberFormatException nfe ) {
            System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
            System.exit( BAD_CMD_LINE_ARG );
        }

        // this is just for making the output pretty... no real "fibonacci" functionality
        if (maxCount % 100 != 11 && maxCount % 100 != 12 && maxCount % 100 != 13) {
            int lastIndex = maxCount % 10;
            switch( lastIndex ) {
                case 1: cardinality = end1;
                          break;
                case 2: cardinality = end2;
                          break;
                case 3: cardinality = end3;
                          break;
                default : cardinality = endRest;
                          break;
            }
        } else {
            cardinality = endRest;
        }
      

        System.out.println( "\n\n   Starting from zero, the " + maxCount + cardinality + " Fibonacci number is: " );

        // NOTE: this section is just a happy notification that lets the user know to be patient.......
        if( maxCount > working ) {
            System.out.println( "\n                This may take me a while; please be patient!!\n\n" );
        }

        BrobInt[] fibArray = new BrobInt[maxCount];
        fibArray[0] = new BrobInt("0");



        if (maxCount > 1) {
            fibArray[1] = new BrobInt("1");
        }

        if (maxCount > 2) {
            for (int i = 2; i < fibArray.length; i++) {
                fibArray[i] = fibArray[i - 1].add(fibArray[i - 2]);
            }
        }

        System.out.println(fibArray[fibArray.length - 1].toString());
    }
}