// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: bryanch3
// UT Student #: 1004015683
// Author: Chanzo Bryan
//
// Student2:
// UTORID user_name: gaylejoe
// UT Student #: 1004029268
// Author: Joey Lakerdas-Gayle
//
// Student3:
// UTORID user_name: appleb16
// UT Student #: 1004432205
// Author: Sean Applebaum
//
// Student4:
// UTORID user_name: ngevan1
// UT Student #:1004166662
// Author: Evan Kar Long Ng
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package exception;

public class IllegalNameException extends Exception {

  /**
   * Creates the IllegalNameException, which an illegal name is given for a File
   * or Directory
   */

  public IllegalNameException() {
    super("Names of files and Directories cannot contain spaces or the "
        + "characters /.!@#$%^&*(){}~|<>?");
  }


  /**
   * Creates the IllegalNameException with the given message
   *
   * @param illegalName The name that is illegal
   */
  public IllegalNameException(String illegalName) {
    super("The name " + illegalName + " is illegal. Names of files and "
        + "directories cannot contain spaces or the characters "
        + "/.!@#$%^&*(){}~|<>?");
  }

}
