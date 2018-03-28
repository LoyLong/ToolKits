
package org.loy.xsl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>
 * <b> Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class HSSFReader extends XSLReader {
    
    @Override
    public void readXSL() {

        String separator = "";
        Map<String, String> acctDescMap = new LinkedMap();

        try {
            HSSFWorkbook xlsFileWorkBook = new HSSFWorkbook(new FileInputStream(filePatch));
            HSSFSheet sheet = xlsFileWorkBook.getSheetAt(tab);

            int total = sheet.getLastRowNum();

            String categoryTitle = "";
            List<String> criteriaEntryList = new ArrayList<String>();

            for (int count = startRow; count <= total; count++) {

                String acctCriteria = "";

                HSSFRow row = sheet.getRow(count);
                
                int lastCellNum = row.getLastCellNum();

                String title = null;
                try {
                	title = row.getCell((short) 0).getStringCellValue();
                } catch (Throwable e){
                }

                if (StringUtils.isNotBlank(title)) {
                    if (StringUtils.isNotBlank(categoryTitle)) {

                        String categoryAcctSearchCriteria = "";

                        for (String tmp : criteriaEntryList) {
                            categoryAcctSearchCriteria = categoryAcctSearchCriteria + separator + tmp;
                        }

                        if (StringUtils.isNotBlank(categoryAcctSearchCriteria) && categoryAcctSearchCriteria.length() > 1) {
                            System.out.println(categoryTitle + " : " + categoryAcctSearchCriteria.substring(1));
                        }
                    }

                    categoryTitle = title;
                    criteriaEntryList.clear();
                }

                if (lastCellNum < acctProductTypeCodeCellIndex) {
                    continue;
                }

                String acctTypeCode = row.getCell(acctTypeCodeCellIndex).getStringCellValue();
                String acctProductTypeCode = row.getCell(acctProductTypeCodeCellIndex).getStringCellValue();
                String acctGroupMember = row.getCell(groupMemberCellIndex).getStringCellValue();
                String acctDesc = row.getCell(acctDescIndex).getStringCellValue();

                String AcctWDTypeCode = acctProductTypeCode;
                
                if (combinedAcctType) {
                	AcctWDTypeCode = StringUtils.isBlank(acctProductTypeCode)?acctTypeCode:acctTypeCode + "_" + acctProductTypeCode;
                }
                
                if (StringUtils.isBlank(AcctWDTypeCode)) {
                    continue;
                }

                if (StringUtils.isNotBlank(acctDesc)) {
                    acctDescMap.put(AcctWDTypeCode, acctDesc);
                }

                while (acctGroupMember.length() < 4) {
                    acctGroupMember = acctGroupMember + " ";
                }

                acctCriteria = "/" + AcctWDTypeCode.trim() + "|";

                if (!criteriaEntryList.contains(acctCriteria)) {
                    criteriaEntryList.add(acctCriteria);
                }

            }

            String categoryAcctSearchCriteria = "";

            for (String tmp : criteriaEntryList) {
                categoryAcctSearchCriteria = categoryAcctSearchCriteria + separator + tmp;
            }

            System.out.println(categoryTitle + " : " + categoryAcctSearchCriteria);

            System.out.println("\r");
            for (String acctType : acctDescMap.keySet()) {
                String acctDescption = acctDescMap.get(acctType);
                System.out.println(acctType.trim() + "=" + acctDescption.trim());
            }

            xlsFileWorkBook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
