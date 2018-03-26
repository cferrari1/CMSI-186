/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the SoccerSim class
 *  @author       :  C. Ferrari
 *  Date written  :  2018-03-15
 *  Description   :  TODO
 *
 *  Notes         :  None 
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-15  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class SoccerSim {

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
    
    /**
    *  The main program starts here
    *  @param  args  String array of the arguments from the command line, where n is the number of balls
    *                args[1 + 4n] is the x location of the ball
    *                args[2 + 4n] is the y location of the ball
    *                args[3 + 4n] is the x velocity of the ball
    *                args[4 + 4n] is the y velocity of the ball
    *                args[4n + 1] is the time slice; this is optional and defaults to 1 second
    */
    public static void main( String args[] ) {
        SoccerSim.handleInit
        //handle args
        //create field
        //create pole
        //check if any balls are out
        //if ball is in then check collision w/ all balls & pole
        //if collision then end program
        //friction
    }

}