package org.loy.test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * <p>
 * <b> Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class ReadLog {

    public static void main(final String[] args) {

        try {
            String filePatch = "C:\\Workspace\\Temp\\cdmmsg";

            File logFolder = new File(filePatch);
            readFolder(logFolder);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void readFolder(final File filePatch) throws Exception{

    	if(filePatch.isDirectory()){
            for (File logFile : filePatch.listFiles()) {
            	readFolder(logFile);
            }
    	} else {
    		readFile(filePatch);
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

            if (line.contains("001550631") && line.contains("RTRVPRDARRSUM")) {
            	//String holdSmryCount = line.substring(line.indexOf("HLDGSUMINF") + 10, line.indexOf("HLDGSUMINF") + 14);
            	//if(StringUtils.isNotBlank(holdSmryCount) && Integer.parseInt(holdSmryCount) > 1)
                // System.out.println("line : " + index + ", " + line);
                System.out.println(line);
            }

        } while (null != line);

    }

}
