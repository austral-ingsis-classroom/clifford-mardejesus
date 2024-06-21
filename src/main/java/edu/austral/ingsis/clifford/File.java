package edu.austral.ingsis.clifford;

public class File implements FSElement {

  private final String name;
  private Directory parent;

  public File(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getPath() {
    return (parent == null) ? name : parent.getPath() + "/" + name;
  }

  @Override
  public void setParent(Directory parent) {
    this.parent = parent;
  }

  @Override
  public Directory getParent() {
    return this.parent;
  }
}
