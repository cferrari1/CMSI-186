/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Christopher Ferrari
 *  Date          :  2018-01-23
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date         Modified by:     Reason for change/modification
 *           -----  ----------  -------------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-23  Christopher Ferrari  Initial writing and release
 */
public class CalendarStuff {

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Default Constructor called, object created" );  // replace this with the actual code
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {

      // Checks if divisible by 400 (true), then 100 (false), then 4 (true), otherwise false

    if (year % 400 == 0)
      return true;
    else if (year % 100 == 0)
      return false;
    else if (year % 4 == 0)
      return true;
    else
      return false;
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {

      // Returns value in days array, unless it is february in a leap year which returns 29

    if (month == 2 && isLeapYear(year))
      return 29;
    else
      return (days[(int)month-1]);
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      
      // Simply checks if month, day, and year are all the same

      if (month1 == month2 && day1 == day2 && year1 == year2)
        return true;
      return false;
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {

      // First checks year, then checks month, then checks day to compare dates

      if (year2 > year1)
        return -1;
     else if (year1 > year2)
        return 1;

     if (month2 > month1)
        return -1;
     else if (month1 > month2)
        return 1;

     if (day2 > day1)
        return -1;
     else if (day1 > day2)
        return 1;

    return 0;
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {

      // Checks if months between 1 and 12, day is >0, then if the day is less than the value on the days array

     if (month < 1 || month > 12 || day < 1)
        return false;
     else if (day <= days[(int)month-1] || (day == 29 && month == 2 && isLeapYear(year)))
        return true;
     return false;
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {

      // Uses a switch statement starting at 0, throws exception if argument is fewer than 1 or greater than 12

      switch( month - 1 ) {
         case 0        : return "January";
         case 1        : return "February";
         case 2        : return "March";
         case 3        : return "April";
         case 4        : return "May";
         case 5        : return "June";
         case 6        : return "July";
         case 7        : return "August";
         case 8        : return "September";
         case 9        : return "October";
         case 10       : return "November";
         case 11       : return "December";
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {

      // Uses a switch statement starting at 0, throws exception if argument is fewer than 1 or greater than 7

      switch( day - 1 ) {
         case 0        : return "Sunday";
         case 1        : return "Monday";
         case 2        : return "Tuesday";
         case 3        : return "Wednesday";
         case 4        : return "Thursday";
         case 5        : return "Friday";
         case 6        : return "Saturday";
         default       : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {

      // Checks if date is valid, otherwise throws Illegal Argument error
      if (!isValidDate(month1,day1,year1) || !isValidDate(month2,day2,year2))
         throw new IllegalArgumentException( "Invalid dates" );

      long dayCount = 0;

      // Checks if date1 is before date2, otherwise switches them
      if (CalendarStuff.compareDate(month1, day1, year1, month2, day2, year2) == 1) {
         long tempMonth = month2;
         long tempDay = day2;
         long tempYear = year2;
         
         month2 = month1;
         day2 = day1;
         year2 = year1;

         month1 = tempMonth;
         day1 = tempDay;
         year1 = tempYear;
      }
      

      // Uses seperate procedures depending on if years are equal or not
      if (year1 != year2){


         // Adds 365 (366 if leap) days for every year in between
         for (long i = year1 + 1; i < year2; i++)
         {
             if (CalendarStuff.isLeapYear(i))
                dayCount += 366;
             else
                dayCount += 365;
         }

         //Count days remaining in first year
         for (long i = 12; i > month1; i--)
         {
            if (i == 2 && isLeapYear(year1))
                dayCount += 29;
            else
                dayCount += days[(int)i - 1];
         }

         if (month1 == 2 && isLeapYear(year1))
            dayCount += 29-day1;
         else
            dayCount += days[(int)month1-1] - day1;

         //Count days in second year
         for (long i = 1; i < month2; i++)
            if (i == 2 && isLeapYear(year2))
                dayCount += 29;
            else
                dayCount += days[(int)i - 1];

         dayCount += day2;
      } else
      {  
          
          // If years are equal, counts days in year of first date, then counts days in year of second date, then returns the subtraction
          
          long daysCount1 = 0;
          long daysCount2 = 0;

         for (long i = 1; i < month1; i++)
         {
            if (i == 2 && isLeapYear(year1))
                daysCount1 += 29;
            else
                daysCount1 += days[(int)i - 1];
        }

         daysCount1 += day1;

         for (long i = 1; i < month2; i++)
         {
            if (i == 2 && isLeapYear(year2))
                daysCount2 += 29;
            else
                daysCount2 += days[(int)i - 1];
        }

         daysCount2 += day2;

         dayCount = daysCount2-daysCount1;
      }


      return dayCount;

   }

}