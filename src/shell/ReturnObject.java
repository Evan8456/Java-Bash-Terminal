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
package shell;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a data class that holds information about the results and errors to
 * print to the user
 */
public class ReturnObject implements Serializable {

  //default serialVersion id
  private static final long serialVersionUID = 1L;
  // Array list with a command's result
  private ArrayList<String> results;
  //Array list with a command's messages to user, including non-thrown errors
  private ArrayList<Boolean> messages;

  /**
   * Create a new ReturnObject
   */
  public ReturnObject() {
    this.results = new ArrayList<>();
    this.messages = new ArrayList<>();
  }

  /**
   * Clear all data in this ReturnObject
   */
  public void clear() {
    results.clear();
    messages.clear();
  }

  /**
   * Add a shell output message to the ReturnObject
   *
   * @param message the new error message
   */
  public void addMessage(String message) {
    results.add(message);
    messages.add(false);
  }

  /**
   * Add a command result to the ReturnObject
   *
   * @param message the new non-error message
   */
  public void addResult(String message) {
    results.add(message);
    messages.add(true);
  }

  /**
   * Return full shell output message including results
   *
   * @return the full message
   */
  public String getResults() {
    StringBuilder result = new StringBuilder();
    for (String str : results) {
      result.append(str);
    }
    return result.toString();
  }

  /**
   * Return the command output results without shell messages
   *
   * @return the non-error message
   */
  public String getResultsWithoutMessages() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < results.size(); i++) {
      if (messages.get(i)) {
        result.append(results.get(i));
      }
    }
    return result.toString();
  }

}
