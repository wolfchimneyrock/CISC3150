/********************************************************
 * Robert Wagner
 * CISC 3150 HW #8
 * 2017-11-13
 *
 * Tree.java:
 *   In which a directory tree is traversed
 *
 ********************************************************/

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.io.*;
import static java.nio.file.FileVisitResult.*;

public class Tree extends SimpleFileVisitor<Path> {
    private String format;
    private int indent;
    public Tree() {
        this.indent = 0;
        this.makeFormat();
    }

    private static String repeatChar(char s, int count) {
        if (count > 0) {
            char[] buf = new char[count];
            Arrays.fill(buf, s);
            return new String(buf);
        } else
            return "";
    }

    private void makeFormat() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.indent - 1; i++) {
            sb.append("|   ");
        }
        if (this.indent > 0) {
            sb.append("+-- ");
        }
        sb.append("%s\n");
        this.format = sb.toString();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        File f = file.toFile();
        if (attr.isRegularFile() && !f.isHidden()) {
            // print it out if not hidden
            System.out.printf(this.format, file.getFileName());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
        File f = dir.toFile();
        if (!f.isHidden()) {
            // print it out
            System.out.printf(this.format, dir.getFileName());
            // increment the indentation
            this.indent++;
            this.makeFormat();
            return CONTINUE;
        } else {
           return SKIP_SUBTREE;
        } 
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        // decrement the indention
        this.indent--;
        this.makeFormat();
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {

        String output = "Failed to read " + file.getFileName();
        System.out.printf(this.format, output);
        return CONTINUE;
    }

    public static void main(String[] args) {
        String root = Paths.get(".").toAbsolutePath().normalize().toString();
        if (args.length > 0) 
            root = Paths.get(args[0]).toAbsolutePath().normalize().toString();
        Tree t = new Tree();
        try {
            Files.walkFileTree(Paths.get(root), t);
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }
}
