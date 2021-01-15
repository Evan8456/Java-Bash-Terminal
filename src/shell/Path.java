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
package shell;

/**
 * An interface for any item in a file system with a location path
 */
public interface Path {

  /**
   * Retrieves the parent Directory of this item
   *
   * @return this item's parent Directory
   */
  Directory getParentDirectory();

  /**
   * Retrieves the path of this item
   *
   * @return this item's path
   */
  String getPath();

  /**
   * Retrieves the name of this item
   *
   * @return this item's name
   */
  String getName();

  /**
   * Change the name of this item
   *
   * @param name the new name
   */
  void setName(String name);

}
