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
import command.LsCommand;

public class LsCommandTest {

  private ExpectedException exception = ExpectedException.none();

  @Test
  public void test01GetNameTest() {
    LsCommand ls = new LsCommand();
    String actual = "ls";
    String expected = ls.getName();
    assertEquals(actual, expected);
  }

  @Test
  public void test02GetDescriptionTest() {
    LsCommand ls = new LsCommand();
    String actual = "This returns the contents of given path";
    String expected = ls.getDescription();
    assertEquals(actual, expected);
  }

  @Test
  public void test03HasNoFilesOrDirectoriesTest() {
    JShell shell = new JShell();
    String actual = "";
    String expected = Interpreter.run(shell.getState(), "ls");
    assertEquals(actual, expected);
  }

  @Test
  public void test04HasDirectoryTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir home");
    String actual = "home/\n";
    String expected = Interpreter.run(shell.getState(), "ls");
    assertEquals(actual, expected);
  }

  @Test
  public void test05HasFileTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "echo " + "\"Hello\"" + " > file");
    String actual = "file\n";
    String expected = Interpreter.run(shell.getState(), "ls");
    assertEquals(actual, expected);
  }

  @Test
  public void test06FileDirectoryTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "echo " + "\"Hello\"" + " > file");
    String actual = "file\n";
    String expected = Interpreter.run(shell.getState(), "ls file");
    assertEquals(actual, expected);
  }

  @Test
  public void test07DirectoryContentsTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /home");
    Interpreter.run(shell.getState(), "cd /home");
    Interpreter.run(shell.getState(), "echo " + "\"Hello\"" + " > file");
    String actual = "/home/: \n" + "file\n\n";
    String expected = Interpreter.run(shell.getState(), "ls /home");
    assertEquals(actual, expected);
  }

  @Test
  public void test08MultipleParametersTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "echo " + "\"hi\"" + " > file1");
    Interpreter.run(shell.getState(), "mkdir /home");
    Interpreter.run(shell.getState(), "cd /home");
    Interpreter.run(shell.getState(), "echo " + "\"Hello\"" + " > file2");
    Interpreter.run(shell.getState(), "cd ..");
    assertEquals("/home/: \n" + "file2\n\n" + "file1\n",
        Interpreter.run(shell.getState(), "ls /home file1"));
  }

  @Test
  public void test09InvalidPathTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /home");

    String actual =
        "The path: " + "/home2" + " is not valid or doesn't exist\n";
    String expected = Interpreter.run(shell.getState(), "ls /home2");
    assertEquals(actual, expected);
  }

  @Test
  public void test10DashRTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /home");
    String expected = Interpreter.run(shell.getState(), "ls -R /");
    String actual = "/: \n" + "home/\n\n" + "/home/: \n\n";
    assertEquals(actual, expected);
  }

  @Test
  public void test11DashRFileTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "echo " + "\"Hello\"" + " > file");
    String expected = Interpreter.run(shell.getState(), "ls -R /file");
    String actual = "file\n";
    assertEquals(actual, expected);
  }

  @Test
  public void test12InvalidPathTestDashRTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "mkdir /home");
    String actual =
        "The path: " + "/home2" + " is not valid or doesn't exist\n";
    String expected = Interpreter.run(shell.getState(), "ls -R /home2");
    assertEquals(actual, expected);
  }

  @Test
  public void test13DashRRootTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "echo " + "\"Hello\"" + " > file");
    String expected = Interpreter.run(shell.getState(), "ls -R /");
    String actual = "/: \nfile\n\n";
    assertEquals(actual, expected);
  }

  @Test
  public void test14DashRNoParamsTest() {
    JShell shell = new JShell();
    Interpreter.run(shell.getState(), "echo " + "\"Hello\"" + " > file");
    String expected = Interpreter.run(shell.getState(), "ls -R");
    String actual = "/: \nfile\n\n";
    assertEquals(actual, expected);
  }

}
