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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import driver.JShell;
import exception.IllegalNameException;
import org.junit.Test;
import shell.Directory;
import shell.File;

public class FileTest {

  @Test
  public void test01CreateFileNoContentsTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    File file1 = new File(root, "file1");
    assertEquals("file1", file1.getName());
    assertEquals("", file1.getContents());
  }

  @Test
  public void test02CreateFileWithContentsTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    File file1 = new File(root, "file1", "contents");
    assertEquals("file1", file1.getName());
    assertEquals("contents", file1.getContents());
  }

  @Test
  public void test03SetNameTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    File file1 = new File(root, "file1");
    file1.setName("new_name");
    assertEquals("new_name", file1.getName());
  }

  @Test
  public void test04SetContentsTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    File file1 = new File(root, "file1", "contents");
    file1.setContents("new_contents");
    assertEquals("new_contents", file1.getContents());
  }

  @Test
  public void test05SetParentTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir = root.addChild("f/");
    File file1 = new File(root, "file1s");
    file1.setParent(dir);
    assertEquals("/f/", file1.getParentDirectory().getPath());
  }
}
