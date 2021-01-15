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
package command;

import java.util.*;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidCommandArgumentException;
import shell.*;

/**
 * The History class
 */

public class HistoryCommand extends Command {

  /*
          _______       /\
        _/ _   _ \_     ||
      _/  / \ / \  \_   ||
     {__  \_/ \_/  __}  ||
       |          |     ||
       |   o   o  |   __||_
       |          |  |_   _|
       |   -----  |    / /
       \____  ____/   / /
           |  |______/ /
          /    _______/
         //|  |
        // |  |
   */

  /**
   * default Constructor
   */
  public HistoryCommand() {
    name = "history";
    description = "Command returns all previous commands the user has done";

  }

  /**
   * Function returns list of all commands user has done
   *
   * @throws IllegalNumberOfArgumentsException if user inputs to many arguments
   * @throws InvalidCommandArgumentException if the user inputed a paramerter
   * that isn't a number
   */
  public void run(State state) throws IllegalNumberOfArgumentsException,
      InvalidCommandArgumentException {
    // Initializes a local commandHistory arraylist
    ArrayList<String> commandHistory = state.getCommandHistory();
    // if number of arguments is greater than 1, throw
    // IllegalNumberOfArguementsExceptionS
    if (state.getParameters().length > 1) {
      throw new IllegalNumberOfArgumentsException();
    }
    // if user input exactly 1 parameter
    if (state.getParameters().length > 0) {
      // converts the String to an int
      int num;
      // try catch to see if user input is a valid and throws
      // InvalidCommandArgumentsException
      try {
        num = Integer.parseInt(state.getParameters()[0]);
      } catch (Exception e) {
        throw new InvalidCommandArgumentException();
      }
      if (num <= 0) {
        throw new InvalidCommandArgumentException();
      }
      // if the number the user inputed is bigger than the currently size of the
      // command history arraylist, history returns all commands.
      if (num > state.getCommandHistory().size()) {
        num = state.getCommandHistory().size();
      }
      state.addResult(getCommands(commandHistory, num));
      return;
    }
    state.addResult(getCommand(commandHistory));
  }

  /**
   * function accesses and returns the nth last command and all commands
   * following it
   *
   * @param n number of commands you want go back by
   * @return the nth last command and everything following
   */
  private String getCommands(ArrayList<String> commandHistory, int n) {
    StringBuilder out = new StringBuilder();

    // loops through arrayList to get the nth last command and all following it
    for (int i = commandHistory.size() - n; i < commandHistory.size(); i++) {
      // formatting
      out.append(i + 1).append(". ").append(commandHistory.get(i)).append("\n");
    }
    // removes last extra u need character space and returns the string
    return out.substring(0, out.length() - 1);
  }

  /**
   * This function returns a string that shows all the commands the user last
   * used
   *
   * @return A String representation of all the commands the user used
   */
  private String getCommand(ArrayList<String> commandHistory) {
    // calls the other getCommands method.
    return getCommands(commandHistory, commandHistory.size());
  }
}
