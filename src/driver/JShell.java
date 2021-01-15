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
package driver;

import exception.InvalidCommandException;
import java.util.Scanner;
import shell.*;

/*
 * A class for a running JShell
 */
public class JShell {

  /*
                   _
                  \ \    _
                 _| |_  | |
         __    /      \/ /
       /O   \/         \
      |            J    =--
       \O__ /\         /
               \ _   _/\ \
                  | |   |_|
                  /_/
   */

  private State state;

  /**
   * Creates the the JShell with the appropriate State
   */
  public JShell() {
    state = new State();
  }

  /**
   * Returns the state of the JShell
   *
   * @return The state of the JShell
   */
  public State getState() {
    return state;
  }

  public static void main(String[] args) throws InvalidCommandException {

    JShell jShell = new JShell();
    //JShell jShell = new JShell();
    Directory root = jShell.state.getRoot();

    jShell.getState().setWorkingDirectory(root);

    // Initialize a string that represents the raw user input
    String input;

    // Initialize a scanner for user input
    Scanner scan = new Scanner(System.in);

    while (!jShell.getState().getExit()) {
      System.out.print(jShell.getState().getWorkingDirectory().getData()
          + "> ");
      input = scan.nextLine();

      System.out.println(Interpreter.run(jShell.getState(), input));

      //jShell.printTree();
    }
    scan.close();
  }

  public void printTree() {
    Directory root = state.getRoot();
    System.out.println("----------------------------------------");
    for (String path : root) {
      System.out.println(path);
    }
    System.out.println("----------------------------------------");
  }
}
