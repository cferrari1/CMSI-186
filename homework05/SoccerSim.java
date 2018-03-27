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
    private static double fieldWidth = 1000;
    private static double fieldLength = 1000;
    private static double leftField = (fieldWidth * -1) / 2;
    private static double rightField = fieldWidth / 2;
    private static double lowerField = (fieldLength * -1) / 2;
    private static double upperField = fieldLength / 2;
    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

    /**
    *  Method to handle all the input arguments from the command line
    *   this sets up the variables for the simulation
    */
    private static void handleInitialArguments( String args[] ) {
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
    
    private static boolean checkInField(Ball ball) {
        if ( (Math.abs(ball.getXLocation()) <= Math.abs(fieldWidth / 2)) && (Math.abs(ball.getYLocation()) <= Math.abs(fieldLength / 2)) )
            return true;
        else if ( (Math.abs(ball.getXLocation()) >= Math.abs(fieldWidth / 2)) && (Math.abs(ball.getYLocation()) >= Math.abs(fieldLength / 2)) ) {
            double dist = Math.sqrt( ((Math.abs(ball.getXLocation()) - rightField) * (Math.abs(ball.getXLocation()) - rightField)) + ((Math.abs(ball.getYLocation()) - upperField) * (Math.abs(ball.getYLocation()) - upperField)) );
            if (ball.getRadius() > dist)
                return true;
        } else if ( (Math.abs(ball.getXLocation()) >= Math.abs(fieldWidth / 2)) ) {
            if (ball.getRadius() > (Math.abs(ball.getXLocation()) - rightField) )
                return true;
        } else {
            if (ball.getRadius() > (Math.abs(ball.getYLocation()) - upperField) )
            return true;
        }

        return false;
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
        handleInitialArguments(args);
        System.out.println("FIELD SIZE IS " + fieldWidth + " BY " + fieldLength);
        Ball pole = new Ball(226, 72, 0, 0);
        System.out.println("POLE LOCATION @ (" + pole.getXLocation() + ", " + pole.getYLocation() + ")");
        if (timeSlice != 1)
            System.out.println("TIMESLICE VALUE IS " + timeSlice + " seconds");
        else
            System.out.println("TIMESLICE VALUE IS " + timeSlice + " second");
        Timer timer = new Timer(timeSlice);

        System.out.println("\nINITIAL REPORT at " + timer.toString() );
        boolean initial = true;

        while (true) {
            if (!initial)
                System.out.println("\nPROGRESS REPORT at " + timer.toString() );

            for (int i = 0; i < balls.length; i++) {
                if ( !checkInField(balls[i]) )
                    balls[i].putOut();

                System.out.println("Ball " + (i + 1) + ": " + balls[i].toString());
            }

            for (int i = 0; i < balls.length - 1; i++) {
                if ( balls[i].checkIfIn() ) {                
                    for (int j = i+1; j < balls.length; j++)
                        if ( balls[j].checkIfIn() )
                            if (balls[i].checkCollision(balls[j])) {
                                System.out.println("\nCollision occurred between ball " + (i + 1) + " and ball " + (j+1));
                                System.exit(1);
                            }

                }
            }

            for (int i = 0; i < balls.length; i++) {
                if ( balls[i].checkIfIn() ) {
                    if ( balls[i].checkCollision(pole) ){
                        System.out.println("\nCollision occurred between ball " + (i + 1) + " and pole");
                        System.exit(1);
                    }
                }
            }


            for (int i = 0; i < balls.length; i++) {
                if ( balls[i].getXVelocity() != 0 || balls[i].getYVelocity() != 0 )
                    break;
                if (i == balls.length - 1) {
                    System.out.println("\nNO COLLISION IS POSSIBLE");
                    System.exit(1);
                }
            }

            for (int i = 0; i < balls.length; i++) {
                balls[i].move(timeSlice);
            }
            timer.tick();


            for (int i = 0; i < balls.length; i++){
                balls[i].slow(timeSlice);
            }

            initial = false;

        }
    }

}
