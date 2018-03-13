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
   private double totalHours;
   private double totalMinutes;
   private double minuteHand;
   private double totalSeconds;
   private double secondHand;
   private double timeSlice;
   private double angle;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;


   /**
   *  Constructor goes here
   */
   public Clock() {
      hours = 0;
      totalHours = 0;
      totalMinutes = 0;
      minuteHand = 0;
      totalSeconds = 0;
      secondHand = 0;
      angle = 0;
      timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
   }

   public Clock(double slice) {
      hours = 0;
      totalHours = 0;
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
      totalHours = totalSeconds / 3600;
      hours = (int)(totalSeconds / 3600);
      totalMinutes = totalSeconds / 60;
      minuteHand = (int)(totalMinutes - hours*60);
      secondHand = totalSeconds - (int)(totalMinutes) * 60;
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

      if ( (val > MAXIMUM_DEGREE_VALUE) || (val < 0) )
         throw new IllegalArgumentException();

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

      if (val <= 0 || val > 1800)
      {
         throw new IllegalArgumentException();
      }

      return val;
   }

   /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return ((totalSeconds / 3600) / 12) * 360; 
   }

   /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return ((totalMinutes - hours*60) / 60) * 360;
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
      DecimalFormat deciFormat = new DecimalFormat("#0.0");
      DecimalFormat secondsDeciFormat = new DecimalFormat("#0.0000");

      return (deciFormat.format(hours) + " hours, " + deciFormat.format(minuteHand) + " minutes, " + secondsDeciFormat.format(secondHand) + " seconds");
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

      System.out.print("      sending ' 30 degrees', expecting double value  30.0");
      try { System.out.println(30.0D == clock.validateAngleArg("30.0") ? " - got  30.0" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 32.4 degrees', expecting double value  32.4");
      try { System.out.println(32.4D == clock.validateAngleArg("32.4") ? " - got  32.4" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 180 degrees', expecting double value  180.0");
      try { System.out.println(180.0D == clock.validateAngleArg("180.0") ? " - got  180.0" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 359.9 degrees', expecting double value  359.9");
      try { System.out.println(359.9D == clock.validateAngleArg("359.9") ? " - got  359.9" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 360 degrees', expecting double value  360.0");
      try { System.out.println(360.0D == clock.validateAngleArg("360.0") ? " - got  360.0" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 365 degrees', expecting ERROR");
      try { System.out.println(-1.0D == clock.validateAngleArg("365.0") ? " - got  ERROR" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' HELLO degrees', expecting ERROR");
      try { System.out.println(-1.0D == clock.validateAngleArg("HELLO") ? " - got  ERROR" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.println( "    Testing validateTimeSliceArg()....");
      System.out.print( "      sending '  0 seconds', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateTimeSliceArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.print("      sending ' 10 seconds', expecting double value  10.0");
      try { System.out.println(10.0D == clock.validateTimeSliceArg("10.0") ? " - got  10.0" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 100 seconds', expecting double value  100.0");
      try { System.out.println(100.0D == clock.validateTimeSliceArg("100.0") ? " - got  100.0" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 400.3 seconds', expecting double value  400.3");
      try { System.out.println(400.3D == clock.validateTimeSliceArg("400.3") ? " - got  400.3" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 1001.3 seconds', expecting double value  1001.3");
      try { System.out.println(1001.3D == clock.validateTimeSliceArg("1001.3") ? " - got  1001.3" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 1800 seconds', expecting double value  1800.0");
      try { System.out.println(1800.0D == clock.validateTimeSliceArg("1800.0") ? " - got  1800.0" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' 1810 seconds', expecting ERROR");
      try { System.out.println(-1.0D == clock.validateTimeSliceArg("1810.0") ? " - got  ERROR" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.print("      sending ' HELLO seconds', expecting ERROR");
      try { System.out.println(-1.0D == clock.validateTimeSliceArg("HELLO") ? " - got  ERROR" : " - no joy"); }
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString());}

      System.out.println("\n    Testing toString()...");
      System.out.print("      expecting string equal to 0.0 hours, 0.0 minutes, 0.0000 seconds");
      try { System.out.println(clock.toString().equals("0.0 hours, 0.0 minutes, 0.0000 seconds") ? " - got true" : " - no joy"); } 
      catch (Exception e) { System.out.println(" - Exception thrown: " + e.toString()); }  

      System.out.println( "  Creating a new clock with time slice 10.0 seconds: " );
      Clock clock2 = new Clock(10.0);
      for (int i = 0; i < 10; i++) {
         System.out.println("\n" + clock2.toString());
         System.out.println("Hour hand angle is " + clock2.getHourHandAngle());
         System.out.println("Minute hand angle is " + clock2.getMinuteHandAngle());
         System.out.println("Angle between hour and minute hand angle is " + clock2.getHandAngle());
         clock2.tick();
      }

      System.out.println( "  Creating a new clock with time slice 100.0 seconds: " );
      Clock clock3 = new Clock(100.0);
      for (int i = 0; i < 10; i++) {
         System.out.println("\n" + clock3.toString());
         System.out.println("Hour hand angle is " + clock3.getHourHandAngle());
         System.out.println("Minute hand angle is " + clock3.getMinuteHandAngle());
         System.out.println("Angle between hour and minute hand angle is " + clock3.getHandAngle());
         clock3.tick();
      }

      System.out.println( "  Creating a new clock with time slice 0.00001 seconds: " );
      Clock clock4 = new Clock(0.00001);
      for (int i = 0; i < 10; i++) {
         System.out.println("\n" + clock4.toString());
         System.out.println("Hour hand angle is " + clock4.getHourHandAngle());
         System.out.println("Minute hand angle is " + clock4.getMinuteHandAngle());
         System.out.println("Angle between hour and minute hand angle is " + clock4.getHandAngle());
         clock4.tick();
      }

      System.out.println( "  Creating a new clock with time slice 0.0625 seconds: " );
      Clock clock5 = new Clock(0.0625);
      for (int i = 0; i < 10; i++) {
         System.out.println("\n" + clock5.toString());
         System.out.println("Hour hand angle is " + clock5.getHourHandAngle());
         System.out.println("Minute hand angle is " + clock5.getMinuteHandAngle());
         System.out.println("Angle between hour and minute hand angle is " + clock5.getHandAngle());
         clock5.tick();
      }

      System.out.println( "  Creating a new clock with time slice 1737.12 seconds: " );
      Clock clock6 = new Clock(1737.12);
      for (int i = 0; i < 10; i++) {
         System.out.println("\n" + clock6.toString());
         System.out.println("Hour hand angle is " + clock6.getHourHandAngle());
         System.out.println("Minute hand angle is " + clock6.getMinuteHandAngle());
         System.out.println("Angle between hour and minute hand angle is " + clock6.getHandAngle());
         clock6.tick();
      }
   }
}
