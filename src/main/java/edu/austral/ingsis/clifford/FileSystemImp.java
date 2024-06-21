package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystemImp implements FileSystem{

    private final Directory root;
    private Directory current;

    public FileSystemImp() {
        root = new Directory("");
        current = root;
    }

    @Override
    public void mkdir(String name) {
        current.add(new Directory(name)); // crear nuevo directorio en el directorio actual
    }

    @Override
    public void touch(String name) {
        current.add(new File(name)); // crear nuevo archivo en el directorio actual
    }

    @Override
    public void cd(String path) {
        if (path.equals("..")) {
            current = root; // Go to root
            if (current.getParent() != null) {
                current = current.getParent();
            }
        } else if (path.equals(".")) {
        } else {
            String parts[] = path.split("/");

            Directory newCurrent = current;
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                FSElement newElement = newCurrent.getSubDirectory(part);
                if (newElement instanceof Directory) {
                    newCurrent = (Directory) newElement;
                } else {
                    throw new IllegalArgumentException("Directory does not exist");
                }
            }
            current = newCurrent;
        }
    }

    @Override
    public void rm(String name, boolean recursive) {
        FSElement element = current.getSubDirectory(name);
        if (element == null) {
            throw new IllegalArgumentException("File or directory does not exist");
        }
        if (element instanceof Directory && !recursive) {
            throw new IllegalArgumentException("Cannot remove directory without --recursive option");
        }
        current.remove(name, recursive);
    }

    @Override
    public List<String> ls(boolean ascending) {
        List<String> names = new ArrayList<>(current.list());
        if (ascending) {
            Collections.sort(names);
        }
        return names;
    }

    @Override
    public String pwd() {
        return current.getPath();
    }
}
