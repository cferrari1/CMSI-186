/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
   /**
   *  Class field definintions go here
   */
   private static double timeSlice;
   private static double angle;
   private static final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private static final double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons

   /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
   }

   /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public static void handleInitialArguments( String args[] ) {
      // args[0] specifies the angle for which you are looking
      //  your simulation will find all the angles in the 12-hour day at which those angles occur
      // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
      Clock clock    = new Clock();


      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }

      if( 3 <= args.length ) {
         System.out.println( "   Sorry you entered too many arguments\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }

      try {
         angle = clock.validateAngleArg(args[0]);
      } catch (Exception e) {
         System.out.println( "   Sorry you did not enter a valid angle value\n" +
                             "   Use an angle that is non-negative and less than or equal to 360\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }



      if ( 1 == args.length ) {
         timeSlice = DEFAULT_TIME_SLICE_SECONDS;
      } else {

         try {
            timeSlice = clock.validateTimeSliceArg(args[1]);
         } catch (Exception e) {
            System.out.println( "   Sorry you did not enter a valid time slice value\n" +
                                "   Use a time slice that is non-negative and less than or equal to 1800\n" +
                                "   Please try again..........." );
            System.exit( 1 );
      }

   }

   }

   /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      handleInitialArguments(args);
      System.out.println("   Your simulation is running,\n"  +   
                         "   looking for angles of " + angle + " degrees\n" +
                         "    with a time slice of " + timeSlice + " seconds.\n\n");

      Clock clock2    = new Clock(timeSlice);

      while (clock2.getTotalSeconds() <= 43200) {
         if ( Math.abs(angle - clock2.getHandAngle()) <= EPSILON_VALUE  ) {
            System.out.println("Found target angle of " + angle + " at time: " + clock2.toString());
         }
         clock2.tick();
      }

      System.exit( 0 );
   }
}
