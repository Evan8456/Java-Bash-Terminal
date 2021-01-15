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

import static org.junit.Assert.assertEquals;

import command.MkdirCommand;
import command.MvCommand;
import driver.JShell;
import exception.InvalidCommandException;
import org.junit.Test;
import shell.Interpreter;

public class MvCommandTest {

  private MvCommand mv = new MvCommand();

  @Test
  public void test01GetNameTest() {
    assertEquals(mv.getName(), "mv");
  }

  @Test
  public void test02GetDescriptionTest() {
    assertEquals(mv.getDescription(), "Moves a Directory or File to a"
        + " new location");
  }

  @Test
  public void test03MoveAbsolutePathDirectoryTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "mkdir /b");
    Interpreter.run(shell.getState(), "mkdir /a/c");
    String actual = Interpreter
        .run(shell.getState(), "mv /a/c/ /");
    String expected = "Moved /a/c/ to /";
    assertEquals(expected, actual);
  }

  @Test
  public void test04MoveRelativePathDirectoryTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "mkdir /b");
    Interpreter.run(shell.getState(), "mkdir /a/c");
    String actual = Interpreter
        .run(shell.getState(), "mv a/c/ /");
    String expected = "Moved a/c/ to /";
    assertEquals(expected, actual);
  }

  @Test
  public void test05MoveFileTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "echo \"test\" > file1");
    String actual = Interpreter
        .run(shell.getState(), "mv file1 a");
    String expected = "Moved file1 to a/";
    assertEquals(expected, actual);
  }

  @Test
  public void test06ItemAlreadyExistsTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "mkdir /b");
    Interpreter.run(shell.getState(), "mkdir /a/b");
    String actual = Interpreter
        .run(shell.getState(), "mv a/b /");
    String expected = "mv: The name b/ already exists in / this is illegal. "
        + "Names of files and directories cannot contain spaces or the "
        + "characters /.!@#$%^&*(){}~|<>?";
    assertEquals(expected, actual);
  }

  @Test
  public void test07AncestorIntoDescendantTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "mkdir /a/b");
    String actual = Interpreter
        .run(shell.getState(), "mv a a/b");
    String expected = "mv: Given path \"a\" does not exist or is not valid";
    assertEquals(expected, actual);
  }
}
