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

public class InvalidURLException extends Exception {

  /**
   * InvalidURLException, the exception to be used if an invalid URL is given
   */
  public InvalidURLException() {
    super();
  }

  /**
   * Creates the InvalidURLException with a more detailed message when given the
   * URL
   *
   * @param url The URL to make a detailed string out of
   */
  public InvalidURLException(String url) {
    super("The URL \"" + url + "\" does not exist or is not valid");
  }
}
