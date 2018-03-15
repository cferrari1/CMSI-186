/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  C. Ferrari
 *  Date written  :  2017-03-15
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
 *  @version 1.0.0  2017-03-15  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Ball {

    /**
    *  Class field definintions
    */
    private double x_velocity;
    private double y_velocity;
    private double x_location;
    private double y_location;
    private double radius;
    private static final double STANDARD_BALL_RADIUS = 4.45 / 12;

    /**
    *  Constructor
    */
    public Ball(double x_vel,double y_vel,double x_loc,double y_loc){
        x_velocity = x_vel;
        y_velocity = y_vel;
        x_location = x_loc;
        y_location = y_loc;
        radius = STANDARD_BALL_RADIUS;
    }

    /**
    *  Methods go here
    *
    *  @return double-precision value of the ball's current velocity in the x-direction
    */
    private double getVelocityX() {
        return x_velocity;
    }

    /**
    *  @return double-precision value of the ball's current velocity in the y-direction
    */
    private double getVelocityY() {
        return y_velocity;
    }

    /**
    *  @return double-precision value of the ball's current x-coordinate
    */
    private double getLocationX() {
        return x_location;
    }

    /**
    *  @return double-precision value of the ball's current y-coordinate
    */
    private double getLocationY() {
        return y_location;
    }


    public void move() {

    }

}