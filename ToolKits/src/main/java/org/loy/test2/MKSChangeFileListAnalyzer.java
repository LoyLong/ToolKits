package org.loy.test2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>
 * <b> Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class MKSChangeFileListAnalyzer {

    public static void main(final String[] args) {

        String filePatch = "D:\\temp\\MKS_ChangeFileListTemplate.xls";

        try {

            HSSFWorkbook xlsFileWorkBook = new HSSFWorkbook(new FileInputStream(filePatch));

            HSSFSheet sheet = xlsFileWorkBook.getSheetAt(0);

            int total = sheet.getLastRowNum();
            System.out.println("total = " + total);
            short parentPathColumn = 5;
            short fileNameColumn = 1;

            for (int count = 0; count < total; count++) {
                HSSFRow row = sheet.getRow(count);

                String parentPath = row.getCell(parentPathColumn).getStringCellValue();
                String fileName = row.getCell(fileNameColumn).getStringCellValue();

                if (StringUtils.isBlank(parentPath)) {
                    continue;
                }

                parentPath = parentPath.replaceAll("project.pj", "");
                parentPath = parentPath.substring(parentPath.indexOf("modules/") + 7);

                System.out.println(parentPath + fileName);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
