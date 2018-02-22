/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  Christopher Ferrari
 *  Date          :  2018-02-21
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-21  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Random;

public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int pips;
   private final int MINIMUM_SIDES = 4;
   private Random rand = new Random();


   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) {
      if (nSides < MINIMUM_SIDES)
         throw new IllegalArgumentException();
      sides = nSides;
      pips = rand.nextInt(sides) + 1;
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {

      //Generates random number between 1 and sides
      pips = rand.nextInt(sides) + 1;
      return pips;
   }

  /**
   * Get the value of THIS die to return to the caller
   * @return the pip count of THIS die instance
   */
   public int getValue() {
      return pips;
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public int setSides( int nSides ) {

      //Checks if there are more sides than the minimum number of sides and then sets the sides

      if (nSides < MINIMUM_SIDES)
         throw new IllegalArgumentException();
      sides = nSides;
      pips = rand.nextInt(sides) + 1;
      return sides;
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
      return "[" + pips + "]";
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public static String toString( Die d ) {
      return "[" + d.getValue() + "]";
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      Die d = null;

      System.out.println();
      try {d = new Die(1);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}

      System.out.println();
      try {d = new Die(2);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}

      System.out.println();
      try {d = new Die(3);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}

      System.out.println();
      try {d = new Die(4);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}
      System.out.println("roll() test for a 4 sided die: " + d.roll());
      System.out.println("roll() test for a 4 sided die: " + d.roll());
      System.out.println("roll() test for a 4 sided die: " + d.roll());
      System.out.println("getValue() test for a 4 sided die: " + d.getValue());
      System.out.println("Setting die to " + d.setSides(5) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("Resetting die to " + d.setSides(4) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("toString() test:");
      System.out.println(d.toString());
      System.out.println("static toString() test:");
      System.out.println(Die.toString(d));

      System.out.println();
      try {d = new Die(5);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}
      System.out.println("roll() test for a 5 sided die: " + d.roll());
      System.out.println("roll() test for a 5 sided die: " + d.roll());
      System.out.println("roll() test for a 5 sided die: " + d.roll());
      System.out.println("getValue() test for a 5 sided die: " + d.getValue());
      System.out.println("Setting die to " + d.setSides(6) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("Resetting die to " + d.setSides(5) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("toString() test:");
      System.out.println(d.toString());
      System.out.println("static toString() test:");
      System.out.println(Die.toString(d));

      System.out.println();
      try {d = new Die(6);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}
      System.out.println("roll() test for a 6 sided die: " + d.roll());
      System.out.println("roll() test for a 6 sided die: " + d.roll());
      System.out.println("roll() test for a 6 sided die: " + d.roll());
      System.out.println("getValue() test for a 6 sided die: " + d.getValue());
      System.out.println("Setting die to " + d.setSides(7) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("Resetting die to " + d.setSides(6) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("toString() test:");
      System.out.println(d.toString());
      System.out.println("static toString() test:");
      System.out.println(Die.toString(d));

      System.out.println();
      try {d = new Die(7);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}
      System.out.println("roll() test for a 7 sided die: " + d.roll());
      System.out.println("roll() test for a 7 sided die: " + d.roll());
      System.out.println("roll() test for a 7 sided die: " + d.roll());
      System.out.println("getValue() test for a 7 sided die: " + d.getValue());
      System.out.println("Setting die to " + d.setSides(8) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("Resetting die to " + d.setSides(7) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("toString() test:");
      System.out.println(d.toString());
      System.out.println("static toString() test:");
      System.out.println(Die.toString(d));

      System.out.println();
      try {d = new Die(18);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}
      System.out.println("roll() test for an 18 sided die: " + d.roll());
      System.out.println("roll() test for an 18 sided die: " + d.roll());
      System.out.println("roll() test for an 18 sided die: " + d.roll());
      System.out.println("getValue() test for an 18 sided die: " + d.getValue());
      System.out.println("Setting die to " + d.setSides(23) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("Resetting die to " + d.setSides(18) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("toString() test:");
      System.out.println(d.toString());
      System.out.println("static toString() test:");
      System.out.println(Die.toString(d));

      System.out.println();
      try {d = new Die(245);}
      catch (IllegalArgumentException e) {System.out.println("Too few sides requested to constructor...");}
      System.out.println("roll() test for an 245 sided die: " + d.roll());
      System.out.println("roll() test for an 245 sided die: " + d.roll());
      System.out.println("roll() test for an 245 sided die: " + d.roll());
      System.out.println("getValue() test for a 245 sided die: " + d.getValue());
      System.out.println("Setting die to " + d.setSides(6) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("Resetting die to " + d.setSides(245) + " sides, its value is now " + d.getValue() + ".");
      System.out.println("toString() test:");
      System.out.println(d.toString());
      System.out.println("static toString() test:");
      System.out.println(Die.toString(d));
   }

}