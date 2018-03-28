package org.loy.test2;

import org.loy.xsl.HSSFReader;
import org.loy.xsl.XSLReader;
import org.loy.xsl.XSSFReader;


/**
 * <p>
 * <b> Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class AccountTypeMappingReader {

	// Row, Column and Tab start from 0
    public static boolean combinedAcctType = true;
    public static int startRow = 1;
    public static short groupMemberCellIndex = 4;
    public static short acctDescIndex = 2;
    public static short acctTypeCodeCellIndex = 4;
    public static short acctProductTypeCodeCellIndex = 5;
    public static short tab = 100;
    public static String filePatch = "C:\\WorkInfo\\WealthDashboard\\v2eur\\UK Wealth Platform - WD Account Type Mapping v1.3.xlsx";
    
    public static void main(final String[] args) {
    	
    	XSLReader reader;
    	
    	if (filePatch.contains("xlsx")) {
			reader = new XSSFReader();
    	} else {
			reader = new HSSFReader();
    	}
    	
    	reader.tab = tab;
    	reader.startRow = startRow;
    	reader.groupMemberCellIndex = groupMemberCellIndex;
    	reader.acctDescIndex = acctDescIndex;
    	reader.acctTypeCodeCellIndex = acctTypeCodeCellIndex;
    	reader.acctProductTypeCodeCellIndex = acctProductTypeCodeCellIndex;
    	reader.filePatch = filePatch;
    	reader.combinedAcctType = combinedAcctType;
    	
    	reader.readXSL();
    }
    
}
