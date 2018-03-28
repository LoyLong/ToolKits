package org.loy.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class FileUtility {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

    public static void main(String[] args) {
        /*
        List<String> fileList = new ArrayList<String>();
        sortFileList(fileList, "", new File("C:\\WorkSpace\\temp\\NTT2G\\teamsite_20170803\\content"));
        for (String fileRecord : fileList) {
            System.out.println(fileRecord);
        }
        */
        List<String> fileList = new ArrayList<String>();
        sortFileList(fileList, "C:\\WorkSpace\\temp\\ap524", new File("C:\\WorkSpace\\temp\\ap524\\content"));
        for (String filePath : fileList) {
            // listFileSizeAndTime(filePath);
            System.out.println(filePath);
        }
    }

    public static void sortFileList(List<String> fileList, String parent, File file) {
        if (!file.exists()) {
            System.out.println(file.getPath() + " doesn't exist!");
            return;
        }

        if (file.isFile()) {
            fileList.add(parent + File.separator + file.getName());
        } else if (file.isDirectory()) {
            String current = parent + File.separator + file.getName();
            File[] subFiles = file.listFiles();
            Arrays.sort(subFiles);
            for (File child : subFiles) {
                sortFileList(fileList, current, child);
            }
        }
    }

    public static void listFileSizeAndTime(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(filePath + " doesn't exist!");
            return;
        }

        if (file.isFile()) {
            System.out.println(file.getName() + "\t" + calFileSize(filePath) + "\t" + df.format(new Date(file.lastModified())));
        }

    }

    public static String calFileSize(String inputFilePath) {

        if (StringUtils.isBlank(inputFilePath)) {
            return null;
        }

        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            return null;
        }

        long fileSizeL = FileUtils.sizeOf(inputFile);

        String unit = "Bytes";
        double fileSizeD = fileSizeL;
        if (fileSizeD > 1024) {
            fileSizeD = fileSizeL / 1024;
            unit = "KB";
        }
        if (fileSizeD > 1024) {
            fileSizeD = fileSizeD / 1024;
            unit = "MB";
        }
        if (fileSizeD > 1024) {
            fileSizeD = fileSizeD / 1024;
            unit = "GB";
        }

        fileSizeL = (long) (fileSizeD * 100);
        fileSizeD = ((double) fileSizeL) / 100;

        return fileSizeD + unit;
    }
    
    public static void deleteFile(File targetFile) {
        try {
            if (targetFile.isDirectory()) {
                for (File child : targetFile.listFiles()) {
                    deleteFile(child);
                }
            }
            targetFile.delete();
        } catch (Exception x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }

    public static void test() {
        int hashResult = 1;
        hashResult = hashResult + "CompanyReport00005HK20160701".hashCode();
        System.out.println(" hashCode:" + hashResult);
    }

}
