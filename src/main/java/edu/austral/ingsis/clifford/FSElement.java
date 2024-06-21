package edu.austral.ingsis.clifford;

public interface FSElement {
    String getName();
    String getPath();
    void setParent(Directory parent);
    Directory getParent();
}
