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
import static org.junit.Assert.assertTrue;

import exception.IllegalNameException;
import org.junit.Test;
import shell.Tree;

public class TreeTest {

  @Test
  public void test01CreateTreeTest() {
    Tree<Integer> tree = new Tree<>(1);
    assertEquals(1, (int) tree.getData());
    assertEquals(null, tree.getParent());
  }

  @Test
  public void test02IsRootTest() throws IllegalNameException {
    Tree<Integer> tree = new Tree<>(1);
    Tree<Integer> sub1 = tree.addChild(2);
    assertTrue(tree.isRoot());
    assertFalse(sub1.isRoot());
  }

  @Test
  public void test03GetChildTest() throws IllegalNameException {
    Tree<Integer> tree = new Tree<>(1);
    Tree<Integer> sub1 = tree.addChild(2);
    assertEquals(sub1, tree.getChild(2));
    assertEquals(null, tree.getChild(7));
  }

  @Test
  public void test04IteratorTest() throws IllegalNameException {
    Tree<Integer> tree = new Tree<>(1);
    Tree<Integer> sub1 = tree.addChild(2);
    Tree<Integer> sub2 = tree.addChild(3);
    sub1.addChild(4);
    sub1.addChild(5);
    sub2.addChild(6);
    sub2.addChild(7);
    StringBuilder nums = new StringBuilder();
    for (int i : tree) {
      nums.append(i);
    }
    assertEquals("1376254", nums.toString());
  }
}
