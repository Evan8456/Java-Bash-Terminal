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
import exception.*;
import shell.Interpreter;
import command.HistoryCommand;

public class HistoryCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetNameTest() {
    HistoryCommand history = new HistoryCommand();

    String actual = "history";
    String expected = history.getName();

    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescriptionTest() {
    HistoryCommand history = new HistoryCommand();

    String actual = "Command returns all previous commands the user has done";
    String expected = history.getDescription();

    assertEquals(actual, expected);
  }

  @Test
  public void test03HistoryFirstCommandTest() throws InvalidCommandException {
    JShell shell = new JShell();

    String actual = "1. history";
    String expected = Interpreter.run(shell.getState(), "history");
    assertEquals(actual, expected);

  }

  @Test
  public void test04HistoryAfterMultipleCommandsTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir home");
    Interpreter.run(shell.getState(), "ls");
    Interpreter.run(shell.getState(), "cd home");
    String actual =
        "1. mkdir home\n" + "2. ls\n" + "3. cd home\n" + "4. history";
    String expected = Interpreter.run(shell.getState(), "history");
    assertEquals(actual, expected);
  }

  @Test
  public void test05HistoryWithOneParamerterTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir home");
    Interpreter.run(shell.getState(), "ls");
    Interpreter.run(shell.getState(), "cd home");
    String actual = "2. ls\n" + "3. cd home\n" + "4. history 3";
    String expected = Interpreter.run(shell.getState(), "history 3");
    assertEquals(actual, expected);

  }

  @Test
  public void test06HistoryWithMoreThanOneParamerterTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    exception.expect(IllegalNumberOfArgumentsException.class);
    Interpreter.run(shell.getState(), "history 3 1");
  }

  @Test
  public void test07HistoryLetterParameterTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    exception.expect(InvalidCommandArgumentException.class);
    Interpreter.run(shell.getState(), "history j");
  }


  @Test
  public void test08HistoryParamerterToBigTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir home");
    Interpreter.run(shell.getState(), "ls");
    Interpreter.run(shell.getState(), "cd home");
    String actual =
        "1. mkdir home\n" + "2. ls\n" + "3. cd home\n" + "4. history 44";
    String expected = Interpreter.run(shell.getState(), "history 44");
    assertEquals(actual, expected);
  }

  @Test
  public void test09HistoryNegativeNumberParameterTest()
      throws InvalidCommandException {
    JShell shell = new JShell();
    exception.expect(InvalidCommandArgumentException.class);
    Interpreter.run(shell.getState(), "history -3");
  }
}
