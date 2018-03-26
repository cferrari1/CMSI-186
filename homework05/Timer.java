/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  C. Ferrari
 *  Date written  :  2018-03-15
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 5, part 1.  Includes the following:
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException & NumberFormatException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-15  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Timer {

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
    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

    /**
    *  Constructors go here
    */
    public Timer() {
        hours = 0;
        totalHours = 0;
        totalMinutes = 0;
        minuteHand = 0;
        totalSeconds = 0;
        secondHand = 0;
        timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
    }

    public Timer(double slice) {
        hours = 0;
        totalHours = 0;
        totalMinutes = 0;
        minuteHand = 0;
        totalSeconds = 0;
        secondHand = 0;
        timeSlice = slice;
    }


    /**
    *  Methods go here
    *
    *  Method to calculate the next tick from the time increment
    *  @return double-precision value of the current timer tick
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
    *  Method to validate the optional time slice argument
    *  @param  argValue  String from the main programs args[4n + 1] input, with n as the number of balls
    *  @return double-precision value of the argument
    *  @throws NumberFormatException, IllegalArgumentException
    */
    public static double validateTimeSliceArg( String argValue ) throws NumberFormatException, IllegalArgumentException {
        try {
            Double.parseDouble(argValue);
        }
        catch (Exception e) {
             throw new NumberFormatException();
        }

        double val = Double.parseDouble(argValue);

        if (val <= 0)
        {
             throw new IllegalArgumentException();
        }

        return val;
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
        DecimalFormat deciFormat = new DecimalFormat("#00");
        DecimalFormat secondsDeciFormat = new DecimalFormat("#00.0000");

        return ( deciFormat.format(hours) + ":" + deciFormat.format(minuteHand) + ":" + secondsDeciFormat.format(secondHand) );
    }

    /**
    *  The main program starts here
    */
    public static void main( String args[] ) {
        System.out.println( "\nTIMER CLASS TESTER PROGRAM\n" +
                            "--------------------------\n" );
        System.out.println( "  Creating a new timer: " );
        Timer timer1 = new Timer();
        System.out.println( "    New timer created: " + timer1.toString() );

        System.out.println( "  Creating a new timer with default time slice of 1.0 seconds: " );
        Timer timer2 = new Timer();
        for (int i = 0; i < 10; i++) {
            System.out.println("    " + timer2.toString());
            timer2.tick();
        }

        System.out.println( "  Creating a new timer with time slice 101.38 seconds: " );
        Timer timer3 = new Timer(101.38);
        for (int i = 0; i < 10; i++) {
            System.out.println("    " + timer3.toString());
            timer3.tick();
        }

        try { System.out.println( "\n" + Timer.validateTimeSliceArg("1")); }
        catch (Exception e) { System.out.println(e); }

        try { System.out.println( Timer.validateTimeSliceArg("1-")); }
        catch (Exception e) { System.out.println(e); }

        try { System.out.println( Timer.validateTimeSliceArg("-87")); }
        catch (Exception e) { System.out.println(e); }
        
        try { System.out.println( Timer.validateTimeSliceArg("hi")); }
        catch (Exception e) { System.out.println(e); }
        
        try { System.out.println( Timer.validateTimeSliceArg("9892829")); }
        catch (Exception e) { System.out.println(e); }


    }

}