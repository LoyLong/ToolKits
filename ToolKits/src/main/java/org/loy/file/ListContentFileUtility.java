package org.loy.file;

import java.io.File;

public class ListContentFileUtility {

    public static void main(String[] args) {
        File contentRoot = new File("C:\\WorkSpace\\temp\\ap550\\content");
        listSubFile(contentRoot, "content");
    }

    private static void listSubFile(File currentFile, String parentPath) {

        if (currentFile.isFile()) {
            System.out.println(parentPath + File.separator + currentFile.getName());
        } else if (currentFile.isDirectory()) {
            for (File subfile : currentFile.listFiles()) {
                listSubFile(subfile, parentPath + File.separator + currentFile.getName());
            }
        }
    }

}
