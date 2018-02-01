/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  Christopher Ferrari
 *  Date          :  2018-02-01
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date         Modified by:     Reason for change/modification
 *           -----  ----------  -------------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-01  Christopher Ferrari  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for "vowel-ness";
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {

      // Loops through the lower-case string and finds if any characters are equal to vowels, which returns true. Otherwise returns false.

      s = s.toLowerCase();

      for (int i = 0; i < s.length(); i++) {
        switch (s.charAt(i)) {
            case 'a': return true;
            case 'e': return true;
            case 'i': return true;
            case 'o': return true;
            case 'u': return true;
            case 'y': return true;
        }
      }

      return false;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {

      // Uses the .reverse method to see if the String and its reverse are equal. 

      if (StringStuff.reverse(s).equals(s)){
         return true;
      }
      return false;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {

      // Loops through the lower-case string and adds the original character to a new string if it is an even letter.

      String evens = new String();
      String lower = s.toLowerCase();

      for (int i = 0; i < lower.length(); i++){
         switch (lower.charAt(i)){
            case 'b': evens += s.charAt(i);
                      break;  
            case 'd': evens += s.charAt(i);
                      break;  
            case 'f': evens += s.charAt(i);
                      break;          
            case 'h': evens += s.charAt(i);
                      break; 
            case 'j': evens += s.charAt(i);
                      break;
            case 'l': evens += s.charAt(i);
                      break;
            case 'n': evens += s.charAt(i);
                      break;
            case 'p': evens += s.charAt(i);
                      break;
            case 'r': evens += s.charAt(i);
                      break;
            case 't': evens += s.charAt(i);
                      break;
            case 'v': evens += s.charAt(i);
                      break;
            case 'x': evens += s.charAt(i);
                      break;
            case 'z': evens += s.charAt(i);
                      break;
         }

      }

      return evens;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {

      // Loops through the lower-case string and adds the original character to a new string if it is an odd letter.

      String odds = new String();
      String lower = s.toLowerCase();

      for (int i = 0; i < lower.length(); i++){
         switch (lower.charAt(i)){
            case 'a': odds += s.charAt(i);
                      break;  
            case 'c': odds += s.charAt(i);
                      break;  
            case 'e': odds += s.charAt(i);
                      break;          
            case 'g': odds += s.charAt(i);
                      break; 
            case 'i': odds += s.charAt(i);
                      break;
            case 'k': odds += s.charAt(i);
                      break;
            case 'm': odds += s.charAt(i);
                      break;
            case 'o': odds += s.charAt(i);
                      break;
            case 'q': odds += s.charAt(i);
                      break;
            case 's': odds += s.charAt(i);
                      break;
            case 'u': odds += s.charAt(i);
                      break;
            case 'w': odds += s.charAt(i);
                      break;
            case 'y': odds += s.charAt(i);
                      break;
         }

      }
      
      return odds;   
  }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {

      // Takes the .evensOnly string, loops through it, and adds characters to a new string after looping through the .evensOnly string again to find no duplicate characters.

      s = StringStuff.evensOnly(s);
      String noDupes = new String();
      boolean check;

      for (int i = 0; i < s.length(); i++){
         check = true;
         for (int j = 0; j < i; j++)
            if (s.charAt(i) == s.charAt(j))
               check = false;
         if (check)
            noDupes += s.charAt(i);
      }

      return noDupes;

   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {

      // Takes the .oddsOnly string, loops through it, and adds characters to a new string after looping through the .oddsOnly string again to find no duplicate characters.


      s = StringStuff.oddsOnly(s);
      String noDupes = new String();
      boolean check;

      for (int i = 0; i < s.length(); i++){
         check = true;
         for (int j = 0; j < i; j++)
            if (s.charAt(i) == s.charAt(j))
               check = false;
         if (check)
            noDupes += s.charAt(i);
      }

      return noDupes;
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {

      // Reverse loops through the characters on the string argument and adds them to a new string.

      String rString = new String();

      for (int i = s.length() - 1; i >= 0; i--) {
         rString += s.charAt(i);
      }

      return rString;
   }
}