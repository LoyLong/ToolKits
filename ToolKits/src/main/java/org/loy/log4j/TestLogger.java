package org.loy.log4j;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLogger {

    public static void main(String[] args) {
        try {
            Properties p = new Properties();
            p.load(TestLogger.class.getClassLoader().getResourceAsStream("GRLog4j.properties"));
            p.put("username", "LeonLong");
            PropertyConfigurator.configure(p);

            // set this date "01/31/1998"
//            String newLastModified = "01/31/1998";
//            updateFileTime("L:\\GlobalResearchConfigHelper.log", newLastModified);
//            updateFileTime("L:\\GlobalResearchConfigHelper_LeonLong.log", newLastModified);

            Logger singleLog = Logger.getLogger("singleLogger");
            Logger monthlyLog = Logger.getLogger("configDiffLogger");

            singleLog.error("Hello debugLog message");
            monthlyLog.error("Hello reportsLog message");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateFileTime(String fileName, String time) {
        try {

            File file = new File(fileName);

            if (!file.exists()) {
                return;
            }

            // print the original last modified date
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            System.out.println("Original Last Modified Date : " + sdf.format(file.lastModified()));

            // need convert the above date to milliseconds in long value
            Date newDate = sdf.parse(time);
            file.setLastModified(newDate.getTime());

            // print the latest last modified date
            System.out.println("Lastest Last Modified Date : " + sdf.format(file.lastModified()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

