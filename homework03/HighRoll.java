/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Provides a class to play a dice game
 *  Author        :  Christopher Ferrari
 *  Date          :  2018-02-21
 *  Description   :  This class provides everything needed to play with a dice set and try to obtain the
 *                   high score. Includes: 
 * 
 *                   public static void main( String[] args );        // The built-in main program for this class
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-21  C. Ferrari    Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Scanner;

public class HighRoll {


      private static Scanner scan = new Scanner(System.in);

      public static void main( String[] args ) {
         //private data
         DiceSet d = null;
         int count = 0;
         int sides = 0;
         int index = 0;
         int sum = 0;
         int high = 0;
         String option = "";
         char optionChar = '0';
         boolean rolledOnce = false;


         //Take in args and convert to int if command line is used, check if valid
         if (args.length == 0) {
            System.out.print("How many dice would you like to play with? ");
            count = scan.nextInt();
            System.out.print("How many sides would you like each die to have? ");
            sides = scan.nextInt();
         } else if (args.length == 2) {
            count = Integer.parseInt(args[0]);
            sides = Integer.parseInt(args[1]);
         } else {
            System.out.println("Please enter only 0 or 2 arguments when running this program.");
            return;
         }

         System.out.println();
         try {d = new DiceSet(count, sides);}
         catch (IllegalArgumentException e) {
            System.out.println("Invalid count or sides requested. Please rerun the program and enter 4 or more sides and have at least one die.");
            return;
         }

         System.out.println("You have " + count + " dice with " + sides + " sides on each die.");
         
         if (args.length == 0)
            scan.nextLine();

         while (optionChar != 'Q') {
            
            //Display options
            System.out.println("\n");
            System.out.println("OPTION 1: ROLL ALL THE DICE");
            System.out.println("OPTION 2: ROLL A SINGLE DIE");
            System.out.println("OPTION 3: CALCULATE THE SCORE FOR THIS SET");
            System.out.println("OPTION 4: SAVE THIS SCORE AS HIGH SCORE");
            System.out.println("OPTION 5: DISPLAY THE HIGH SCORE");
            System.out.println("OPTION Q: ENTER 'Q' TO QUIT THE PROGRAM (CASE-SENSITIVE)");

            System.out.print("\nPICK AN OPTION: ");

            option = scan.nextLine();

            if (option.length() != 1){
               System.out.println("Invalid option.");
               continue;
            }

            optionChar = option.charAt(0);

            System.out.println();
            
            switch (optionChar){
               case '1': 
                  // Rolls all dice in set, prints out set, and calculates new sum
                  rolledOnce = true;
                  d.roll();
                  System.out.println("Your dice set is now: ");
                  System.out.println(d);
                  sum = d.sum();
                  break;
               case '2': 
                  // Checks if all dice have been rolled once, checks if index is valid, rolls die, prints new set, and calculates new sum

                  if (!rolledOnce){
                     System.out.println("Please pick option 1 at least one time before picking this option.");
                     break;
                  }
                  System.out.print("Which index die would you like to roll? ");
                  index = scan.nextInt();
                  while (index > d.getCount() - 1 || index < 0){
                     System.out.print("Please enter a valid index: ");
                     index = scan.nextInt();
                  }
                  d.rollIndividual(index);
                  System.out.println("Your dice set is now: ");
                  System.out.println(d); 
                  sum = d.sum(); 
                  scan.nextLine();
                  break;
               case '3': 
                  // Checks if all dice have been rolled once, then calculates and prints sum

                  if (!rolledOnce){
                     System.out.println("Please pick option 1 at least one time before picking this option.");
                     break;
                  }

                  System.out.println("Your dice set is: ");
                  System.out.println(d); 
                  System.out.println("Your sum for this set is " + sum);
                  break;
               case '4': 
                  // Checks if all dice have been rolled once, checks if the current sum is high than the saved high score, then sets and displays the new high score

                  if (!rolledOnce){
                     System.out.println("Please pick option 1 at least one time before picking this option.");
                     break;
                  }

                  if (sum >= high){
                     System.out.println("Your old saved high score was " + high);
                     high = sum;
                     System.out.println("Your new saved high score is " + high);
                  } else
                     System.out.println("Your current score is less than your previously saved high score. This score was not saved.");

                  break;
               case '5': 
                  // Checks if all dice have been rolled once, then displays new high score


                  if (!rolledOnce){
                     System.out.println("Please pick option 1 at least one time before picking this option.");
                     break;
                  }

                  System.out.println("Your last saved high score was " + high); 
                  break;
               case 'Q': 
                  System.out.println("\nExiting program. Thanks for playing!");
                  break;
               default:
                  System.out.println("Invalid option.");
                  break;
            }
            
         }
      }

}