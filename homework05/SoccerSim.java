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
    *  Class field definintions go here
    */
    private static double timeSlice;
    private static Ball balls[];
    private static double x_field = 1000 / 2;
    private static double y_field = 1000 / 2;
    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

    /**
    *  Method to handle all the input arguments from the command line
    *   this sets up the variables for the simulation
    */
    public static void handleInitialArguments( String args[] ) {
        // args[0] specifies the angle for which you are looking
        //  your simulation will find all the angles in the 12-hour day at which those angles occur
        // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds

        System.out.println( "\n    Hello world, from the SoccerSim program!!\n\n" ) ;

        if( args.length < 4 ) {
            System.out.println( "    Sorry, you enter too few arguments\n" +
                                "    Usage: java SoccerSim [x location] [y location] <x velocity> <y velocity> ... [time slice]\n" +
                                "    Please try again..........." );
            System.exit( 1 );
        }

        if( (args.length % 4 != 0) && (args.length % 4 != 1) ) {
            System.out.println( "    Sorry, you entered an incorrect number of arguments\n" +
                                "    Usage: java SoccerSim [x location] [y location] <x velocity> <y velocity> ... [time slice]\n" +
                                "    Please try again..........." );
            System.exit( 1 );
        }

        try {
            balls = new Ball[args.length / 4];
            double ballArgs[] = new double[4];

            for (int i = 0, j = 0; i < balls.length; i++) {
                for (int k = 0; k < 4; k++) {
                    ballArgs[k] = Ball.validateBallArgs(args[j]);
                    j++;
                }

                balls[i] = new Ball(ballArgs[0],ballArgs[1],ballArgs[2],ballArgs[3]);
                   
            }

        } catch (Exception e) {
            System.out.println( "    Sorry, you did not enter a valid value for at least one of your ball arguments\n" +
                                "    Use a number argument\n" +
                                "    Please try again..........." );
            System.exit( 1 );
        }

        if (args.length % 4 == 0)
            timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
        else {
            try { 
                timeSlice = Timer.validateTimeSliceArg(args[args.length - 1]); 
            } catch (Exception e) {  
                System.out.println( "    Sorry, you did not enter a valid value for your time slice argument\n" +
                                    "    Use a time slice that is non-negative\n" +
                                    "    Please try again..........." );
                System.exit( 1 );
            }
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
        SoccerSim.handleInitialArguments(args);
        //create field
        //create pole
        //check if any balls are out
        //if ball is in then check collision w/ all balls & pole
        //if collision then end program
        //friction
    }

}
