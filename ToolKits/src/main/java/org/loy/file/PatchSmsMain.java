package org.loy.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

public class PatchSmsMain {
    private static String fileName = "1_SMS_reconciliation_report_20171102.csv";
    private static String srcFile = "C:\\WorkInfo\\BSD\\ProdSupport\\20171107_SMS\\backup\\" + fileName;
    private static String patchFile = "C:\\WorkInfo\\BSD\\ProdSupport\\20171107_SMS\\fromICCM\\" + fileName;
    private static String newFile = "C:\\WorkInfo\\BSD\\ProdSupport\\20171107_SMS\\firefight\\" + fileName;

	private static final String LB = "\r\n";
	private static String DELIVERED = "Delivered";
	private static int deliveredFieldStartIndex = -1;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader srcReader = new BufferedReader(new FileReader(srcFile));

		BufferedWriter newFileWriter = new BufferedWriter(new FileWriter(new File(newFile)));
		getPatchFileStatusFieldStartIndex();
		
		int totalOtherCount = 0;
		int statusNoUpdateCount = 0;
		int statusUpdateCount = 0;

		String line = "";
		while((line = srcReader.readLine()) != null) {
			if (isDataRow(line)) {
				if (line.indexOf("Other") > 0) {
					totalOtherCount++;
					
					String[] arr = line.split(",");
                    String msgId = arr[3]; // 4th column
                    String status = getStatusByMsgIdFromPatchFile(msgId);
					if ("".equals(status)) {	// status not find in patch file
						newFileWriter.write(line + LB);
						System.out.println("Other status not update: " + line);
						
						statusNoUpdateCount++;
					} else {
						if ("Expired".equals(status)) {
							status = "SMS Expired";
						}
						String newLine = line.replace("Other", status);
						newFileWriter.write(newLine + LB);
						
						statusUpdateCount++;
					}
				}
				// normal data row
				else {
					newFileWriter.write(line + LB);
				}
			}
			// write header
			else {
				newFileWriter.write(line + LB);
			}
		}
		
		if (newFileWriter != null) {
			newFileWriter.flush();
			newFileWriter.close();
		}
		
		System.out.println("Total Other count = " + totalOtherCount);
		System.out.println("Status Not Update Count = " + statusNoUpdateCount);
		System.out.println("Status Update Count = " + statusUpdateCount);
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

    public static String getStatusByMsgIdFromPatchFile(String msgIdInOld) throws IOException {
		BufferedReader patchReader = new BufferedReader(new FileReader(patchFile));
		
		try {
            String line = "";
			while((line = patchReader.readLine()) != null) {
                String[] arr = line.split(",");
                String msgIdInNew = arr[3];
                if (StringUtils.equalsIgnoreCase(msgIdInOld, msgIdInNew)) {
                    return arr[8];
				}
			}
		} finally {
			patchReader.close();
		}
		
		return "";
	}
	
	private static void getPatchFileStatusFieldStartIndex() throws IOException {
		int index = -1;
		BufferedReader patchReader = new BufferedReader(new FileReader(patchFile));
		String line = "";
		while((line = patchReader.readLine()) != null) {
			index = line.indexOf(DELIVERED);
			if (index > 0) {
				break;
			}
		}
		patchReader.close();
		
		deliveredFieldStartIndex = index;
	}
}
