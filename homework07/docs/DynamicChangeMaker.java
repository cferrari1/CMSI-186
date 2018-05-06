/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Learning exercise to implement tuples to find the optimal amount of change
 * @author    :  Christopher Ferrari
 * Date       :  2018-04-26
 * Description:  @see <a href='https://bjohnson.lmu.build/cmsi186web/homework07.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-26  C. Ferrari    Initial writing and begin coding
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;


public class DynamicChangeMaker {


    /** 
    *  Method to calculate the optimal solution of a target sum using certain denominations
    *  @param  denoms         int[] of denomination values
    *  @param  target         int to find the optimal solution for
    *  @return Tuple containing the optimal solution or an IMPOSSIBLE Tuple
    */

    public static Tuple makeChangeWithDynamicProgramming(int[] denoms, int target) {
        if ( !validateDenoms(denoms,target) ) {
            return Tuple.IMPOSSIBLE;
        }

        Tuple arr[][] = new Tuple[denoms.length][target + 1];
        
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {

                    if ( ( c == 0 ) || ( c - denoms[r] >= 0  && ( !arr[r][ c - denoms[r] ].isImpossible() ) ) ) {
                        arr[r][c] = new Tuple (denoms.length);
                        if (c != 0) {
                            arr[r][c].setElement(r, 1);
                        }

                        if ( c - denoms[r] >= 0 ) {
                            arr[r][c] = arr[r][c].add( arr[r][ c-denoms[r] ] );
                        }

                    } else {
                        arr[r][c] = Tuple.IMPOSSIBLE;
                    }

                    if ( r != 0 && arr[r][c].isImpossible() && !arr[r-1][c].isImpossible() ) {
                        arr[r][c] = arr[r-1][c];
                    }

                    if ( r != 0 && !arr[r-1][c].isImpossible() ) {
                        if (arr[r][c].total() > arr[r-1][c].total()) {
                            arr[r][c] = arr[r-1][c];
                        }
                    } 

            }
        }

        return arr[arr.length - 1][arr[0].length - 1];
    }

    /** 
    *  Method to verify if all the arguments are valid
    *  @param  denoms         int[] of denomination values
    *  @param  target         int to find the optimal solution for
    *  @return true if all arguments are valid, false if not
    */

    public static boolean validateDenoms(int[] denoms, int target) {
        for (int i = 0; i < denoms.length; i++) {
            if (denoms[i] < 1) {
                System.out.println("BAD DATA: You cannot have any denominations fewer than one cent");
                return false;
            }
        }

        for (int i = 0; i < denoms.length; i++) {
            for (int j = i + 1; j < denoms.length; j++) {
                if (denoms [i] == denoms[j]) {
                    System.out.println("BAD DATA: You cannot have any duplicate denominations");
                    return false;
                }
            }
        }

        if (target < 1) {
            System.out.println("BAD DATA: You cannot have a target fewer than one cent");
            return false;
        }

        return true;

    }

}
