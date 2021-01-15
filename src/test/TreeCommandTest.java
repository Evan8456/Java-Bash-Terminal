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
import shell.Interpreter;
import command.TreeCommand;

public class TreeCommandTest {

  @Test
  public void test01GetNameTest() {
    TreeCommand tree = new TreeCommand();
    String actual = "tree";
    String expected = tree.getName();
    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescriptionTest() {
    TreeCommand tree = new TreeCommand();
    String actual =
        "This returns a String representation of the current file system tree.";
    String expected = tree.getDescription();
    assertEquals(actual, expected);
  }

  @Test
  public void test03GetEmptyTreeTest() {
    JShell shell = new JShell();
    String actual = "/";
    String expected = Interpreter.run(shell.getState(), "tree");
    assertEquals(actual, expected);
  }

  @Test
  public void test04GetChildTest() {
    JShell shell = new JShell();
    String actual = "/" + "\n\t" + "a" + "\n\t" + "b";
    Interpreter.run(shell.getState(), "mkdir a");
    Interpreter.run(shell.getState(), "mkdir b");
    String expected = Interpreter.run(shell.getState(), "tree");
    assertEquals(actual, expected);
  }

  @Test
  public void test05ChildrenOfChildrenTest() {
    JShell shell = new JShell();

    Interpreter.run(shell.getState(), "mkdir a");
    Interpreter.run(shell.getState(), "mkdir b");
    Interpreter.run(shell.getState(), "mkdir b/c");
    Interpreter.run(shell.getState(), "mkdir a/d");
    String actual =
        "/" + "\n\t" + "a" + "\n\t\t" + "d" + "\n\t" + "b" + "\n\t\t" + "c";
    String expected = Interpreter.run(shell.getState(), "tree");
    assertEquals(actual, expected);
  }

  @Test
  public void test05ContainsFilesTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir a");
    Interpreter.run(shell.getState(), "mkdir b");
    Interpreter.run(shell.getState(), "echo " + "\"Hello\"" + " > file");
    Interpreter.run(shell.getState(), "mkdir b/c");
    Interpreter.run(shell.getState(), "echo " + "\"Hello2\"" + " > a/file2");
    Interpreter.run(shell.getState(), "mkdir a/d");
    String actual =
        "/" + "\n\t" + "a" + "\n\t\t" + "d" + "\n\t" + "b" + "\n\t\t" + "c";
    String expected = Interpreter.run(shell.getState(), "tree");
    assertEquals(actual, expected);
  }

}
