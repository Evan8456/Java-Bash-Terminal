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

import java.util.ArrayList;
import org.junit.Test;
import shell.ReturnObject;

public class ReturnObjectTest {

  @Test
  public void test01CreateReturnObjectTest() {
    ReturnObject ro = new ReturnObject();
    assertEquals(ro.getResults(), "");
    assertEquals(ro.getResultsWithoutMessages(), "");
  }

  @Test
  public void test02CreateMessageNoResultsTest() {
    ReturnObject ro = new ReturnObject();
    ro.addMessage("message here");
    assertEquals(ro.getResults(), "message here");
    assertEquals(ro.getResultsWithoutMessages(), "");
  }

  @Test
  public void test03CreateResultNoMessagesTest() {
    ReturnObject ro = new ReturnObject();
    ro.addResult("result here");
    assertEquals(ro.getResults(), "result here");
    assertEquals(ro.getResultsWithoutMessages(), "result here");
  }

  @Test
  public void test04CreateResultAndMessageTest() {
    ReturnObject ro = new ReturnObject();
    ro.addResult("result here");
    ro.addMessage("message here");
    assertEquals(ro.getResults(), "result heremessage here");
    assertEquals(ro.getResultsWithoutMessages(), "result here");
  }

  @Test
  public void test05CreateResultsAndMessagesTest() {
    ReturnObject ro = new ReturnObject();
    ro.addResult("result 1 here");
    ro.addMessage("message 1 here");
    ro.addMessage("message 2 here");
    ro.addResult("result 2 here");
    assertEquals(ro.getResultsWithoutMessages(), "result 1 hereresult 2 here");
    assertEquals(ro.getResults(),
        "result 1 heremessage 1 heremessage 2 hereresult 2 here");
  }

  @Test
  public void test06CreateResultsAndMessagesClearTest() {
    ReturnObject ro = new ReturnObject();
    ro.addResult("result 1 here");
    ro.addMessage("message 1 here");
    ro.addMessage("message 2 here");
    ro.addResult("result 2 here");
    ro.clear();
    assertEquals(ro.getResultsWithoutMessages(), "");
    assertEquals(ro.getResults(), "");
  }
}

