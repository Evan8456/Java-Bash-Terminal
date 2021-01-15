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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import exception.IllegalNameException;
import exception.IllegalNumberOfArgumentsException;
import exception.InvalidPathException;
import exception.InvalidURLException;
import shell.State;

public class GetCommand extends Command {

  /**
   * Creates the get command with the appropriate documentation
   */
  public GetCommand() {
    // The name of the get command
    name = "get";

    // The description of the get command
    description = "Will retrieve the file on a server at a given web address";

    // The parameters for the get command and their description
    parameters.put("URL", "The web address of the file to retrieve");
  }

  /**
   * Will save the contents of the given InputStream to a new file created in
   * the current working directory with the given name
   *
   * @param state The state of the JShell
   * @param in The input stream of the contents in the file at the URL
   * @param urlPath The path of the URL the input stream came from
   * @throws IOException If the input stream given cannot be read from
   * @throws IllegalNameException If the file name at the URL is invalid
   * @throws InvalidURLException If the path given is empty
   */
  public void saveURLToFile(State state, InputStream in, String urlPath)
      throws IOException, IllegalNameException, InvalidURLException {
    // Make sure the path is valid
    if (urlPath.equals("")) {
      throw new InvalidURLException(urlPath);
    }

    int afterSlashIndex = urlPath.lastIndexOf("/") + 1;
    int extensionIndex = urlPath.indexOf(".", afterSlashIndex);

    // Set the extension to the end of the file if it doesn't exist
    if (extensionIndex == -1) {
      extensionIndex = urlPath.length();
    }

    // Make sure the file name isn't empty
    if (afterSlashIndex == extensionIndex) {
      throw new IllegalNameException("");
    }

    // Get the file name
    String fileName = urlPath.substring(afterSlashIndex, extensionIndex);

    try {
      // The BufferedReader to get the file contents
      BufferedReader br = new BufferedReader(new InputStreamReader(in));

      // The string builder for the file contents
      StringBuilder fileContents = new StringBuilder();

      // Read the URL contents line by line
      String line;
      while ((line = br.readLine()) != null) {
        fileContents.append(line).append("\n");
      }

      // Close the InputStream and BufferedReader
      br.close();
      in.close();

      // Create the file with the name of the URL file and the contents
      state.getWorkingDirectory().addFile(fileName, fileContents.toString());
    } catch (IOException e) {
      throw new IOException(
          "Failed to read the contents of the file at the given URL");
    }
  }

  /**
   * Given a URL, will retrieve the file at that web address
   *
   * @param state The current state of the JShell
   * @throws IllegalNumberOfArgumentsException If less or more than 1 argument
   * is given
   * @throws IOException If an error occurs when retrieving the file
   * @throws InvalidURLException If the URL given is invalid
   * @throws InvalidPathException If the current working directory is invalid
   * @throws IllegalNameException If the file at the given URL has an illegal
   * name
   */
  @Override
  public void run(State state) throws IllegalNumberOfArgumentsException,
      IOException, InvalidURLException, IllegalNameException {
    // Get the parameters
    String[] commandParams = state.getParameters();

    // Make sure the length is only 1
    if (commandParams.length != 1) {
      throw new IllegalNumberOfArgumentsException(commandParams.length, 1);
    }

    // Get the URL address
    String urlAddress = commandParams[0];

    try {
      if (urlAddress.endsWith("/")) {
        urlAddress = urlAddress.substring(0, urlAddress.length() - 1);
      }

      // Get the URL for the given web address
      URL url = new URL(urlAddress);

      // Get the path, the / index and the index of the . for the extension
      String urlPath = url.getPath();

      // Get the InputStream for the URL
      InputStream urlStream = url.openStream();

      saveURLToFile(state, urlStream, urlPath);
    } catch (IOException e) {
      // Given an appropriate message if an IOException is thrown
      throw new IOException("Failed to retrieve the file at the given URL");
    }
  }
}
