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

import java.util.HashMap;
import exception.InvalidCommandException;
import shell.ReturnObject;
import shell.State;

/**
 * An abstract shell command
 */
public abstract class Command {
  /*
       ______   Hey, no
      /      \  /
     |  o  o |   |
     |       |  |" "|
      \  --  /  /  /
       |    |  /  /
       |    | /  /
    ___|    |/  /
   |  _      __/
   | | |    |
   | | |    |
   |_| |  | |
       |  | |
       |  | |
       |  | |
       |__|_|
   */

  // The name of the command for the documentation
  protected String name;

  // The description for the documentation
  protected String description;

  /*
   * The command parameters for the documentation, mapping from parameter to
   * description
   */
  protected HashMap<String, String> parameters = new HashMap<>();

  // The list of all valid commands
  private static final HashMap<String, Command> validCommands =
      new HashMap<String, Command>() {
        {
          put("exit", new ExitCommand());
          put("mkdir", new MkdirCommand());
          put("cd", new CdCommand());
          put("ls", new LsCommand());
          put("pwd", new PwdCommand());
          put("pushd", new PushdCommand());
          put("popd", new PopdCommand());
          put("history", new HistoryCommand());
          put("cat", new CatCommand());
          put("echo", new EchoCommand());
          put("man", new ManCommand());
          put("mv", new MvCommand());
          put("cp", new CpCommand());
          put("get", new GetCommand());
          put("save", new SaveCommand());
          put("load", new LoadCommand());
          put("tree", new TreeCommand());
          put("find", new FindCommand());
        }
      };

  /**
   * Returns the name of the command
   *
   * @return The name of the command
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the description of the command
   *
   * @return The description of the command
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the parameters for the documentation of the command
   *
   * @return The parameters for the documentation of the command in a HashMap
   * mapping from parameter name to parameter description
   */
  public HashMap<String, String> getParameters() {
    return parameters;
  }

  /**
   * Returns the documentation for the command
   */
  @Override
  public String toString() {
    // The documentation to be returned
    String doc = "";

    // Add the name, descriptions and parameter header to the documentation
    doc += "\nNAME\n\n" + getName() + "\n\nDESCRIPTION\n\n" + getDescription()
        + "\n\nPARAMETERS\n";

    if (getParameters().size() > 0) {
      doc += "\n";

      // Loop through all the parameters in the HashMap and add them
      for (String parameter : getParameters().keySet()) {
        doc += parameter + " - " + getParameters().get(parameter) + "\n";
      }
    }

    // Get rid of the last new line character
    return doc.substring(0, doc.length() - 1);
  }

  /**
   * Returns the Command object associated with the string command given
   *
   * @param cmd The command to return the Command object for
   * @return The command class for the given string command
   * @throws InvalidCommandException when the command given is not in the
   * HashMap of valid commands
   */
  public static Command getCommand(String cmd) throws InvalidCommandException {
    Command command = validCommands.get(cmd);

    if (command == null) {
      throw new InvalidCommandException(cmd);
    }

    return command;
  }

  /**
   * Will run the command and return either the command output, or an empty
   * string if no output is required
   *
   * @param state The state of the JShell
   * @throws Exception if the command finds an error in runtime
   */
  public abstract void run(State state) throws Exception;
}
