/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  C. Ferrari
 *  Date written  :  2018-03-15
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *                   for Homework 5, part 1.  Includes the following:
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  NumberFormatException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-15  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Ball {

    /**
    *  Class field definitions go here
    */
    private double x_velocity;
    private double y_velocity;
    private double x_location;
    private double y_location;
    private double radius;
    private boolean ballIn;
    private static final double STANDARD_BALL_RADIUS = 4.45 / 12;

    /**
    *  Constructor goes here
    */
    public Ball(double x_loc,double y_loc,double x_vel,double y_vel) {
        x_velocity = x_vel;
        y_velocity = y_vel;
        x_location = x_loc;
        y_location = y_loc;
        ballIn = true;
        radius = STANDARD_BALL_RADIUS;
    }

    /**
    *  Methods go here
    *
    *  @return double-precision value of the ball's current velocity in the x-direction
    */
    public double getXVelocity() {
        return x_velocity;
    }

    /**
    *  @return double-precision value of the ball's current velocity in the y-direction
    */
    public double getYVelocity() {
        return y_velocity;
    }

    /**
    *  @return double-precision value of the ball's current x-coordinate
    */
    public double getXLocation() {
        return x_location;
    }

    /**
    *  @return double-precision value of the ball's current y-coordinate
    */
    public double getYLocation() {
        return y_location;
    }

    /**
    *  @return double-precision value of the ball's radius
    */
    public double getRadius() {
        return radius;
    }


    /**
    *  Method to move the ball over a certain time slice
    * 
    *  @param slice    time slice to move the ball over
    */
    public void move(double slice) {
        x_location = x_location + (x_velocity * slice);
        y_location = y_location + (y_velocity * slice);      
    }

    /**
    *  Method to validate the optional time slice argument
    *  @param  argValue  String from the main programs args[1 – 4n] input, with n as the number of balls
    *  @return double-precision value of the argument
    *  @throws NumberFormatException
    */
    public static double validateBallArgs( String argValue ) throws NumberFormatException {
        try {
            Double.parseDouble(argValue);
        }
        catch (Exception e) {
             throw new NumberFormatException();
        }

        return Double.parseDouble(argValue);
    }


    /**
    *  Method to put the ball out of the running in the sim
    */
    public void putOut() {
        x_velocity = 0;
        y_velocity = 0;
        ballIn = false;
    }


    /**
    *  Method to check if the ball is actively participating in the sim
    * 
    *  @return true or false depending on whether the ball is active or not
    */
    public boolean checkIfIn() {
        return ballIn;
    }

    public void slow(double percent) {
        x_velocity *= 1 - (percent / 100);
        y_velocity *= 1 - (percent / 100);
        if (calcSpeed() < (1.0 / 12) ){
            x_velocity = 0;
            y_velocity = 0;
        }

    }

    /**
    *  Method to calculate the speed (not velocity) of the ball
    * 
    *  @return double value of the ball's speed
    */
    private double calcSpeed() {
        return Math.sqrt( (x_velocity * x_velocity) + (y_velocity * y_velocity) );
    }



    public boolean checkCollision(Ball ball1) {
        double dist = Math.sqrt( ((x_location - ball1.x_location) * (x_location - ball1.x_location)) + ((y_location - ball1.y_location) * (y_location - ball1.y_location)) );
        if (radius > dist/2)
            return true;
        return false;
    }

    /**
    *  @return String representation of the ball
    */
    public String toString() {
        DecimalFormat deciFormat = new DecimalFormat("#0.0000");

        if (x_velocity != 0 || y_velocity != 0)
            return ("position: (" + deciFormat.format(x_location) + ", " + deciFormat.format(y_location) + ") \t velocity: <" + deciFormat.format(x_velocity) + ", " + deciFormat.format(y_velocity) + "> ft/sec");
        return ("position: (" + deciFormat.format(x_location) + ", " + deciFormat.format(y_location) + ") \t <at rest>");
    }

    /**
    *  Test program goes here
    */
    public static void main( String args[] ) {
        System.out.println( "\nBALL CLASS TESTER PROGRAM\n" +
                            "--------------------------\n" );
        int n = 0;
        Ball balls[] = new Ball[5];

        balls[n] = new Ball(300, 300, -1, -2);
        System.out.println( "    Creating a new ball: \n    " + balls[n].toString() );
        n++;

        balls[n] = new Ball(-20, 100, 1, 22.1);
        System.out.println( "    Creating a new ball: \n    " + balls[n].toString() );
        n++;

        balls[n] = new Ball(1000 + 8.9/12, 100, 0.084, 0);
        System.out.println( "    Creating a new ball: \n    " + balls[n].toString() );
        n++;

        balls[n] = new Ball(1000, 100 + 8.89/12, 0.4, 0);
        System.out.println( "    Creating a new ball: \n    " + balls[n].toString() );
        n++;


        balls[n] = new Ball(1000,100, 0, 0);
        System.out.println( "    Creating a new ball: \n    " + balls[n].toString() );
        n++;

        System.out.println( "\n    Checking for collisions:");
        for (int i = 0; i < balls.length - 1; i++)
            for (int j = i + 1; j < balls.length; j++)
                System.out.println( "    Ball " + (i+1)  + " & Ball " + (j+1) + ": " + balls[i].checkCollision(balls[j]) );

        System.out.println( "\n    Moving balls w/ time slice of 1 second:");
        for (int i = 0; i < balls.length; i++) {
            balls[i].move(1);
            System.out.println("    " + balls[i].toString());
        }

        System.out.println( "\n    Slowing balls by 1 percent:");
        for (int i = 0; i < balls.length; i++) {
            balls[i].slow(1);
            System.out.println("    " + balls[i].toString());
        }

    } 

}
