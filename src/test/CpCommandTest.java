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

import command.CpCommand;
import command.MvCommand;
import driver.JShell;
import org.junit.Test;
import shell.Interpreter;

public class CpCommandTest {

  private CpCommand cp = new CpCommand();

  @Test
  public void test01GetNameTest() {
    assertEquals(cp.getName(), "cp");
  }

  @Test
  public void test02GetDescriptionTest() {
    assertEquals(cp.getDescription(), "Makes a copy of a Directory or a "
        + "File at a new location");
  }

  @Test
  public void test03MoveAbsolutePathDirectoryTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "mkdir /b");
    Interpreter.run(shell.getState(), "mkdir /a/c");
    String actual = Interpreter
        .run(shell.getState(), "cp /a/c/ /");
    String expected = "Copied /a/c/ to /";
    assertEquals(expected, actual);
  }

  @Test
  public void test04MoveRelativePathDirectoryTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "mkdir /b");
    Interpreter.run(shell.getState(), "mkdir /a/c");
    String actual = Interpreter
        .run(shell.getState(), "cp a/c/ /");
    String expected = "Copied a/c/ to /";
    assertEquals(expected, actual);
  }

  @Test
  public void test05MoveFileTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "echo \"test\" > file1");
    String actual = Interpreter
        .run(shell.getState(), "cp file1 a");
    String expected = "Copied file1 to a/";
    assertEquals(expected, actual);
  }

  @Test
  public void test06ItemAlreadyExistsTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /a");
    Interpreter.run(shell.getState(), "mkdir /b");
    Interpreter.run(shell.getState(), "mkdir /a/b");
    String actual = Interpreter
        .run(shell.getState(), "cp a/b /");
    String expected = "cp: The name b/ already exists in / this is illegal. "
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
        .run(shell.getState(), "cp a a/b");
    String expected = "cp: Given path \"a\" does not exist or is not valid";
    assertEquals(expected, actual);
  }
}
