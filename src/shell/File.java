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

import exception.IllegalNameException;

/**
 * Files contain data and can be a child of a Directory
 */
public class File implements Path {

  // name and contents of the file
  private String name, contents;
  // the file's parent directory
  private Directory parent;

  /**
   * Create a new File
   *
   * @param parent a Directory to place this file
   * @param name the new file's name
   * @param contents the new file's contents
   * @throws IllegalNameException if name is invalid
   */
  public File(Directory parent, String name, String contents)
      throws IllegalNameException {
    if (name.matches(State.ILLEGAL_CHARACTERS) || name.contains("/")) {
      throw new IllegalNameException(name);
    }
    this.parent = parent;
    this.name = name;
    this.contents = contents;
  }

  /**
   * Create a new File with empty contents
   *
   * @param parent a Directory to place this file
   * @param name the new file's name
   * @throws IllegalNameException if name is invalid
   */
  public File(Directory parent, String name) throws IllegalNameException {
    this(parent, name, "");
  }

  /**
   * Retrieves the name of this File
   *
   * @return this File's name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Change the name of this File
   *
   * @param name the new name
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the contents of this file
   *
   * @return th contents
   */
  public String getContents() {
    return contents;
  }

  /**
   * Change the contents of this File
   *
   * @param contents the new contents
   */
  public void setContents(String contents) {
    this.contents = contents;
  }

  /**
   * Change the parent Directory of this File
   *
   * @param newParent the new parent Directory
   * @throws IllegalNameException if this file has invalid data
   */
  public void setParent(Directory newParent) throws IllegalNameException {
    // remove from current parent
    parent.removeChildFile(name);
    // add to new parent
    newParent.addFile(this);
    parent = newParent;
  }

  /**
   * Retrieves the path of this File
   *
   * @return this File's path
   */
  @Override
  public String getPath() {
    return parent.getPath() + name;
  }

  /**
   * Retrieves the parent Directory of this File
   *
   * @return this File's parent Directory
   */
  @Override
  public Directory getParentDirectory() {
    return parent;
  }

  /**
   * Returns whether or not a given object is equal to this File. Two Files are
   * equal iff they have the same path and contents.
   *
   * @param obj An object to compare to
   * @return true iff the object is equal to this File
   */
  @Override
  public boolean equals(Object obj) {
    // check for null
    if (obj == null) {
      return false;
    }
    // check that these objects are both files
    if (getClass() != obj.getClass()) {
      return false;
    }
    // cast to File
    File other = (File) obj;
    // check if these have different parents
    if (!getPath().equals(other.getPath())) {
      return false;
    }
    // check if these have the same contents
    return (contents.equals(other.contents));
  }
}
