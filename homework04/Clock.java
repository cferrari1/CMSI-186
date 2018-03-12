/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  C. Ferrari
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Clock {
   /**
   *  Class field definintions go here
   */
   private double hours;
   private double totalMinutes;
   private double minuteHand;
   private double totalSeconds;
   private double secondHand;
   private double timeSlice;
   private double angle;
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

   /**
   *  Constructor goes here
   */
   public Clock() {
      hours = 0;
      totalMinutes = 0;
      minuteHand = 0;
      totalSeconds = 0;
      secondHand = 0;
      angle = 0;
      timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
   }

   public Clock(double slice) {
      hours = 0;
      totalMinutes = 0;
      minuteHand = 0;
      totalSeconds = 0;
      secondHand = 0;
      angle = 0;
      timeSlice = slice;
   }


   /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      totalSeconds += timeSlice;
      secondHand = totalSeconds - (int)(totalSeconds / 60) * 60;
      totalMinutes = totalSeconds / 60;
      minuteHand = totalMinutes - (int)(totalMinutes/60) * 60; 
      hours = totalSeconds / 3600;
      return totalSeconds;
   }

   /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException, IllegalArgumentException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException, IllegalArgumentException {
      try {
         Double.parseDouble(argValue);
      }
      catch (Exception e) {
          throw new NumberFormatException();
      } 

      double val = Double.parseDouble(argValue);

      if ( (val > 360) || (val < 0) )
         throw new IllegalArgumentException();
      else
         return val;
   }

   /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument
   *  @throws NumberFormatException, IllegalArgumentException
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) throws NumberFormatException, IllegalArgumentException {
      try {
         Double.parseDouble(argValue);
      }
      catch (Exception e) {
          throw new NumberFormatException();
      }

      double val = Double.parseDouble(argValue);

      if (val <= 0 || val >= 1800)
        throw new IllegalArgumentException();

      return val;
   }

   /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      
      return 0.0;
   }

   /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return 0.0;
   }

   /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      if (getHourHandAngle() < getMinuteHandAngle())
         angle = getMinuteHandAngle() - getHourHandAngle();
      else {
         angle = getHourHandAngle() - getMinuteHandAngle();
      }

      return angle;
   }

   /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

   /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      DecimalFormat deciFormat = new DecimalFormat("#00.0");
      return (deciFormat.format(hours) + " hours, " + deciFormat.format(minuteHand) + " minutes, " + deciFormat.format(secondHand) + " seconds");
   }

   /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}