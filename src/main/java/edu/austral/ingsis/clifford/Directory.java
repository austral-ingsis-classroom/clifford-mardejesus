package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FSElement {

  private final String name;
  private Directory parent;
  private final List<FSElement> elements;

  Directory(String name) {
    this.name = name;
    this.elements = new ArrayList<>();
  }

  public FSElement getSubDirectory(String name) {
    for (FSElement element : elements) {
      if (element.getName().equals(name)) {
        return element;
      }
      if (element instanceof Directory) {
        FSElement subElement = ((Directory) element).getSubDirectory(name);
        if (subElement != null) {
          return subElement;
        }
      }
    }
    return null;
  }

  public void add(FSElement element) {
    element.setParent(this);
    elements.add(element);
  }

  public void remove(String name, boolean recursive) {
    FSElement element = getSubDirectory(name);
    if (element instanceof File) {
      elements.remove(element);
    }

    if (element instanceof Directory) {
      Directory directory = (Directory) element;
      if (!directory.elements.isEmpty() && !recursive) {
        throw new IllegalArgumentException("Directory is not empty");
      } else if (!directory.elements.isEmpty()) {
        for (FSElement e : new ArrayList<>(directory.elements)) {
          if (e instanceof Directory) {
            directory.remove(e.getName(), true);
          } else {
            directory.elements.remove(e);
          }
        }
      }
    }
    elements.removeIf(e -> e.getName().equals(name));
  }

  public List<String> list() {
    List<String> names = new ArrayList<>();
    for (FSElement element : elements) {
      names.add(element.getName());
    }
    return names;
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
    return parent;
  }
}
