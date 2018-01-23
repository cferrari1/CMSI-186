/**
 *  File name     :  CountTheDays.java
 *  Purpose       :  Provides a class to count the days between two dates
 *  Author        :  Christopher Ferrari
 *  Date          :  2018-01-23
 *  Description   :  This file will provide a count for the days between dates given six arguments.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date         Modified by:     Reason for change/modification
 *           -----  ----------  -------------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-28  Christopher Ferarri  Initial writing and release
 */

public class CountTheDays {

  /**
   * the main method which calls all of the test methods in the class
   * @param args String[] array containing command line parameters
   * @return void
   */
   public static void main ( String [] args ) {
      long[] argsLong = new long[6];

      for (int i = 0; i < argsLong.length; i++)
         argsLong[i] = Long.parseLong(args[i]);

      long count = CalendarStuff.daysBetween(argsLong[0], argsLong[1], argsLong[2], argsLong[3], argsLong[4], argsLong[5]);

      if (1 == count)
         System.out.println("\n" + count + " day");
      else
         System.out.println("\n" + count + " days");

   }

}