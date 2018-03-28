package org.loy.test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ValidateTrField {


    public static void main(final String[] args) {

        try {
            String filePatch = "C:\\Workspace\\WDV2.1INTG\\group-wealthdashboard-content\\src\\main\\resources\\content\\group\\wealthdashboard\\common\\trwidgetsevlt\\resources";
            
            List<String> keyList = new ArrayList<String>();
            
            File logFolder = new File(filePatch);

            for (File logFile : logFolder.listFiles()) {
                if (logFile.getName().contains("trtmpl")) {
                    readFile(logFile);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void readFile(final File filePatch) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePatch)));

        System.out.println("Read file : " + filePatch);
        String line;
        int index = 0;
        do {
            line = reader.readLine();
            index++;
            if (null == line) {
                return;
            }

            if (line.contains("003227162")) {
                // System.out.println("line : " + index + ", " + line);
                System.out.println(line);
            }

        } while (null != line);

    }
}
