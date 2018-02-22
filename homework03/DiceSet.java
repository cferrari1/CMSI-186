/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Christopher Ferrari
 *  Date          :  2018-02-21
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-21  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {

      //Check if sides/count is valid, sets instance data, then constructs an array of Die classes


      if (count < 1 || sides < 1)
         throw new IllegalArgumentException();

      this.count = count;
      this.sides = sides;


      ds = new Die[count];
      for (int i = 0; i < ds.length; i++){
         ds[i] = new Die(sides);
      }     

   }

  /**
   * @return the count of dice in the set
   */
   public int getCount(){
      return count;
   }

  /**
   * @return the sides on each die in the set
   */
   public int getSides() {
      return sides;
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int add = 0;
      for (int i = 0; i < ds.length; i++)
         add += ds[i].getValue();
      return add;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
      for (int i = 0; i < ds.length; i++)
         ds[i].roll();
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @throws IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      if (dieIndex > (ds.length - 1))
      {
         throw new IllegalArgumentException();
      }

      return ds[dieIndex].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @throws IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      if (ds.length > (count - 1))
      {
         throw new IllegalArgumentException();
      }

      return ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      // Concatenates all the toStrings() of Die in the dice set

      String result = "";
      for (int i = 0; i < ds.length; i++)
         result += ds[i].toString();

      return result;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  true if this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds2 ) {

      //Checks if count and sides are equal
      if ((this.ds.length != ds2.ds.length) || (this.sides != ds2.sides))
          return false;

      // Creates new array of dice that will be sorted
      Die[] sortedSet = this.ds;


      // Sort first set
      for (int i = 0; i < sortedSet.length - 1; i++){
         for (int j = i + 1; j < sortedSet.length; j++){
            if (sortedSet[i].getValue() > sortedSet[j].getValue())
            {
               Die temp = sortedSet[i];
               sortedSet[i] = sortedSet[j];
               sortedSet[j] = temp;
            }
         }
      }

      // Sort second set   
      for (int i = 0; i < ds2.ds.length - 1; i++){
         for (int j = i + 1; j < ds2.ds.length; j++){
            if (ds2.ds[i].getValue() > ds2.ds[j].getValue())
            {
               Die temp = ds2.ds[i];
               ds2.ds[i] = ds2.ds[j];
               ds2.ds[j] = temp;
            }
         }
      }

      // Goes through each index and checks if they are equal
      for (int i = 0, j = 0; i < ds2.ds.length; i++, j++)
         if (sortedSet[i].getValue() != ds2.ds[i].getValue())  
            return false;

      return true;
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      DiceSet d = null;
      DiceSet d2 = null;
      DiceSet d3 = null;
      DiceSet d4 = null;
      DiceSet d5 = null;

      System.out.println();
      try {d = new DiceSet(0,3);}
      catch (IllegalArgumentException e) {System.out.println("Invalid count or sides requested to constructor...");}

      System.out.println();
      try {d = new DiceSet(6,3);}
      catch (IllegalArgumentException e) {System.out.println("Invalid count or sides requested to constructor...");}

      System.out.println();
      try {d = new DiceSet(0,5);}
      catch (IllegalArgumentException e) {System.out.println("Invalid count or sides requested to constructor...");}

      System.out.println();
      try {d = new DiceSet(5,17);}
      catch (IllegalArgumentException e) {System.out.println("Invalid count or sides requested to constructor...");}
      System.out.println("toString() test: " + d);
      System.out.println("static toString() test" + DiceSet.toString(d));
      System.out.println("Sum: " + d.sum());
      System.out.println("Rolling...");
      System.out.println("Rolling 3rd index die, new value is " + d.rollIndividual(3));
      System.out.println("toString() test: " + d);
      System.out.println("static toString() test: " + DiceSet.toString(d));
      System.out.println("Sum: " + d.sum());

      System.out.println();
      try {d2 = new DiceSet(3,4);}
      catch (IllegalArgumentException e) {System.out.println("Invalid count or sides requested to constructor...");}
      System.out.println("Set 2: " + d2);

      try {d3 = new DiceSet(3,4);}
      catch (IllegalArgumentException e) {System.out.println("Invalid count or sides requested to constructor...");}
      System.out.println("Set 3 : " + d3);

      try {d4 = new DiceSet(2,5);}
      catch (IllegalArgumentException e) {System.out.println("Invalid count or sides requested to constructor...");}
      System.out.println("Set 4 : " + d4);

      try {d5 = new DiceSet(2,5);}
      catch (IllegalArgumentException e) {System.out.println("Invalid count or sides requested to constructor...");}
      System.out.println("Set 5 : " + d5);

      System.out.println("Indentical test (Set 1 & 2): " + d.isIdentical(d2));
      System.out.println("Indentical test (Set 2 & 3): " + d2.isIdentical(d3));
      System.out.println("Indentical test (Set 3 & 4): " + d3.isIdentical(d4));
      System.out.println("Indentical test (Set 4 & 5): " + d4.isIdentical(d5));
      

   }

}