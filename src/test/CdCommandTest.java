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
package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import driver.JShell;
import exception.InvalidCommandException;
import exception.InvalidPathException;
import shell.Directory;
import shell.Interpreter;
import command.CdCommand;
import shell.Navigate;

public class CdCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetNameTest() {

    CdCommand changeDir = new CdCommand();

    String actual = "cd";
    String expected = changeDir.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescriptionTest() {
    CdCommand changeDir = new CdCommand();

    String actual = "Will change the current directory to the specified path";
    String expected = changeDir.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03ChangeDirectoryToCurrentDirectoryTest()
      throws InvalidPathException, InvalidCommandException {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir home");
    String actual = "Changed directory to /home/";
    Directory intention =
        Navigate.navigateToDirectory(shell.getState(), "/home");

    shell.getState().setWorkingDirectory(
        Navigate.navigateToDirectory(shell.getState(), "/home"));
    String expected = Interpreter.run(shell.getState(), "cd .");
    Directory current = shell.getState().getWorkingDirectory();
    assertEquals(actual, expected);
    assertEquals(intention, current);
  }

  @Test
  public void test04ChangeToDirectoryParentDirectoryTest()
      throws InvalidPathException, InvalidCommandException {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir home");
    shell.getState().setWorkingDirectory(
        Navigate.navigateToDirectory(shell.getState(), "/home"));

    Directory intention = Navigate.navigateToDirectory(shell.getState(), "/");
    Interpreter.run(shell.getState(), "cd ..");
    Directory current = shell.getState().getWorkingDirectory();
    assertEquals(intention, current);
  }

  @Test
  public void test05AttemptToGetParentOfRootTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    exception.expect(InvalidPathException.class);
    Interpreter.run(shell.getState(), "cd ..");
  }

  @Test
  public void test06ChangeDirectoryToNonExistentDirectoryTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    exception.expect(InvalidPathException.class);
    Interpreter.run(shell.getState(), "cd /home");
  }

  @Test
  public void test07ChangeDirectoryToGivenPathTest()
      throws InvalidPathException, InvalidCommandException {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir home");
    shell.getState().setWorkingDirectory(
        Navigate.navigateToDirectory(shell.getState(), "/home"));
    Interpreter.run(shell.getState(), "cd /home");
    assertEquals(Navigate.navigateToDirectory(shell.getState(), "/home"),
        shell.getState().getWorkingDirectory());
  }

}
