package org.loy.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

public class CleanSmsMain {
    private static String fileName = "1_SMS_reconciliation_report_20180302.csv";
    private static String srcFile = "C:\\WorkInfo\\BSD\\ProdSupport\\20180307_SMS\\backup\\" + fileName;
    private static String newFile = "C:\\WorkInfo\\BSD\\ProdSupport\\20180307_SMS\\" + fileName;

	private static final String LB = "\r\n";
	private static String DELIVERED = "Delivered";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader srcReader = new BufferedReader(new FileReader(srcFile));
		BufferedWriter newFileWriter = new BufferedWriter(new FileWriter(new File(newFile)));
		
        try {
            // skip the 1st line
            String line = srcReader.readLine();
            newFileWriter.write(line + LB);

            while ((line = srcReader.readLine()) != null) {
                if (!isDelivered(line)) {
                    newFileWriter.write(line + LB);
                }
            }

            if (newFileWriter != null) {
                newFileWriter.flush();
            }

        } finally {
            srcReader.close();
            newFileWriter.close();
		}
		
	}
	
	public static boolean isDataRow(String line) {
		if (line.indexOf("Delivered") >= 0) {
			return true;
		} else if (line.indexOf("Other") >= 0) {
			return true;
		} else if (line.indexOf("Undelivered") >= 0) {
			return true;
		} else if (line.indexOf("Expired") >= 0) {
			return true;
		}
		
		System.out.println("Non data row: " + line);
		
		return false;
	}

    private static boolean isDelivered(String line) throws IOException {
        if (StringUtils.isBlank(line)) {
            return false;
		}

        return line.indexOf(DELIVERED) > 0;
	}
}
