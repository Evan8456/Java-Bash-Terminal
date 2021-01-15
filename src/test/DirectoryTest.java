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

public class DirectoryTest {

  @Test
  public void test01CreateRootTest() {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    assertEquals("/", root.getPath());
    assertEquals(null, root.getName());
    assertEquals(null, root.getParent());
  }

  @Test
  public void test02GetPathTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir = root.addChild("f1/").addChild("f2/").addChild("f3/");
    assertEquals("/f1/f2/f3/", dir.getPath());
  }

  @Test
  public void test03GetParentDirectoryTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir = root.addChild("f1/").addChild("f2/").addChild("f3/");
    assertEquals("/f1/f2/", dir.getParentDirectory().getPath());
  }

  @Test
  public void test04HasChildDirectoryTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir2 = root.addChild("f1/").addChild("f2/");
    dir2.addChild("f3/");
    assertTrue(dir2.hasChildDirectory("f3/"));
  }

  @Test
  public void test05HasChildFileTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir = root.addChild("f1/");
    dir.addFile("file1");
    assertTrue(dir.hasChildFile("file1"));
    assertFalse(dir.hasChildFile("anotherFile"));
  }

  @Test
  public void test06IsAncestorOfTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir1 = root.addChild("f1/");
    Directory dir2 = dir1.addChild("f2/");
    Directory dir3 = dir2.addChild("f3/");
    assertTrue(root.isAncestorOf(dir1));
    assertFalse(dir1.isAncestorOf(root));
    assertTrue(root.isAncestorOf(dir3));
    assertFalse(dir3.isAncestorOf(root));
    assertTrue(dir2.isAncestorOf(dir3));
    assertFalse(dir3.isAncestorOf(dir2));
  }

  @Test
  public void test07AddDuplicateTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir1 = root.addChild("f1/");
    Directory dir2 = dir1.addChild("f2/");
    Directory dir3 = root.addChild("f3/");
    Directory duplicateDir3 = dir2.addDuplicate(dir3);
    assertNotEquals(dir3, duplicateDir3);
    assertTrue(dir2.hasChildDirectory("f3/"));
  }

  @Test
  public void test08GetChildFileTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir1 = root.addChild("f1/");
    File file1 = dir1.addFile("file1");
    assertEquals(file1, dir1.getChildFile("file1"));
    assertEquals(null, dir1.getChildFile("anotherFile"));
  }

  @Test
  public void test09RemoveChildFileTest() throws IllegalNameException {
    JShell shell = new JShell();
    Directory root = shell.getState().getRoot();
    Directory dir1 = root.addChild("f1/");
    File file1 = dir1.addFile("file1");
    File removedFile = dir1.removeChildFile("file1");
    assertFalse(dir1.hasChildFile("file1"));
    assertEquals(file1, removedFile);
  }
}
