package org.loy.test2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TransactionTypeReader {

	public enum site{
		SGH,UAE,TWN
	}
	
	public static String[][] PRODUCT_ARRAY = {
		{"2", "EQ","Equity, Warrant, Right",},
		{"3","opts ","Options"},
		{"4","UT","Fund"},
		{"5","BOND","Bond&CD"},
		{"6","ELI","EquityLinkedInvestment"},
		{"7","SN","StructuredNote"},
		{"8","DCD","DualCurrencyDeposit"},
		{"9","SID","StructuredInvestmentDeposit"}
		};
	
    public static void main(final String[] args) {

        try {
            String filePatch = "C:\\WorkInfo\\SGH WD v1 - Transaction Type Mapping (20121018).xls";
            
            HSSFWorkbook xlsFileWorkBook = new HSSFWorkbook(new FileInputStream(filePatch));
//            System.out.println("xlsFileWorkBook.getNumberOfSheets() = " + xlsFileWorkBook.getNumberOfSheets());
        	for(int i=1; i<xlsFileWorkBook.getNumberOfSheets(); i ++ ){
            	readSpreadSheet(xlsFileWorkBook, i);
        	}

            System.out.println("/////////////////////////////////////////////////////////////////");
            validateDesc();
            System.out.println("///////////////  validat descripton key start  //////////////////////");
        	for(Entry<String, Map<String, String>> entry : totalTxnTypeDecMap.entrySet()){
            	Map<String, String> patten = entry.getValue();
            	for(Entry<String, String> subEntry : patten.entrySet()){
                	if(!txnDecInProMap.containsKey(subEntry.getKey())){
                    	System.out.println(subEntry.getKey() + "=" + subEntry.getValue());
                	}
    	        }
        	}
            System.out.println("///////////////  validat descripton key end  //////////////////////");
            System.out.println("///////////////  validat descripton value start  //////////////////////");
        	for(Entry<String, Map<String, String>> entry : totalTxnTypeDecMap.entrySet()){
            	Map<String, String> patten = entry.getValue();
            	for(Entry<String, String> subEntry : patten.entrySet()){
            		String key = subEntry.getKey();
            		String value = subEntry.getValue();
                	if(txnDecInProMap.containsKey(key)){
                		
                		String proValue = txnDecInProMap.get(key);
                		if(!StringUtils.equalsIgnoreCase(value, proValue)){
                        	System.out.println(key + ": Old="+ proValue + " | New=" +value.toUpperCase());
                		}
                	}
    	        }
        	}
            System.out.println("///////////////  validat descripton value end  //////////////////////");
        	
        	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readSpreadSheet(HSSFWorkbook xlsFileWorkBook, int sheetIndex){

        int startRow = 1;
        int txnActInx = 1;
        int txnTypeInx = 2;
        int txnDecInx = 4;
        String sheetName;
        
        Map<String, String> txnTypeDecMap = new HashMap<String, String>();
        Map<String, List<String>> txnTypeActMap = new HashMap<String, List<String>>();
        Map<String, List<String>> txnTypeClrMap = new HashMap<String, List<String>>();
        Map<String, List<String>> txnTypeClrMapTmp = new HashMap<String, List<String>>();
        Map<String, String> txnClrMap = new HashMap<String, String>();
        
        HSSFSheet sheet = xlsFileWorkBook.getSheetAt(sheetIndex);
        
        sheetName = sheet.getSheetName().trim();
        System.out.println("/////////////////////////////////////////////////////////////////");
//        System.out.println("sheetName = " + sheetName);
        String productType = getMapKey(productKeyMap, sheetName);
//        System.out.println("product type code = " + productType);

        int txnColorInx = productClrInxMap.get(productType);
        
        int total = sheet.getLastRowNum();
        int readRow = startRow;
        int readCell= -1;

    	try {
	        for (; readRow < total; readRow++) {
	
	            HSSFRow row = sheet.getRow(readRow);
	            
	            if(null == row || row.getLastCellNum() < 2 || 
	        		((null == row.getCell(txnTypeInx) || StringUtils.isEmpty(row.getCell(txnTypeInx).getStringCellValue())) 
	        				&& (null == row.getCell(txnColorInx) || StringUtils.isEmpty(row.getCell(txnColorInx).getStringCellValue())))){
	            	continue;
	            } else if((null == row.getCell(txnTypeInx) || StringUtils.isEmpty(row.getCell(txnTypeInx).getStringCellValue())) 
	    				&& (null != row.getCell(txnColorInx) || StringUtils.isNotEmpty(row.getCell(txnColorInx).getStringCellValue()))){
	            	readCell = txnColorInx;
	                HSSFCell keyCell = row.getCell(txnColorInx);
	                readCell = txnColorInx + 1;
	                HSSFCell colorCell = row.getCell(txnColorInx + 1);
	                
	                txnClrMap.put(keyCell.getStringCellValue().trim(), String.valueOf(colorCell.getCellStyle().getFillForegroundColor()));

	            } else {
	            	readCell = txnActInx;
		            String txnAct = row.getCell(txnActInx).getStringCellValue().trim();
	            	readCell = txnTypeInx;
		            String txnType = row.getCell(txnTypeInx).getStringCellValue().trim();
	            	readCell = txnDecInx;
		            String txnDec = row.getCell(txnDecInx).getStringCellValue().trim();
	            	readCell = txnColorInx + 1;
		            int txnClr = row.getCell(txnColorInx + 1).getCellStyle().getFillForegroundColor();
		            //System.out.println("txnCate = " + txnAct);
		            //System.out.println("txnType = " + txnType);
		            //System.out.println("txnClr = " + txnClr);
		
		            txnType = txnType.replaceAll(" ", "");
		            String txnTypeArr[] = null;
		            if(txnType.contains("\n")){
		            	txnTypeArr = txnType.split("\n");
		            }
		            
		            txnDec = txnDec.replace("  ", " ");
		            
		            if (null == txnTypeArr){
			            txnTypeDecMap.put(productType + "_" + txnType, txnDec);
			            putTxnRecord(txnTypeActMap, productType + "_" + getMapKey(actKeyMap, txnAct), txnType);
			            putTxnRecord(txnTypeClrMap, String.valueOf(txnClr), txnType);
		            	
		            } else {
		            	for(String temp : txnTypeArr){
				            txnTypeDecMap.put(productType + "_" + temp, txnDec);
				            putTxnRecord(txnTypeActMap, productType + "_" + getMapKey(actKeyMap, txnAct), temp);
				            putTxnRecord(txnTypeClrMap, String.valueOf(txnClr), temp);
		            	}
		            }
	            }
	        }

	        System.out.println("txnTypeClrMap = " + txnTypeClrMap);
	        for(Entry<String, List<String>> entry : txnTypeClrMap.entrySet()){
	        	String clrInt = entry.getKey();
	        	String clrKey = productType + "_" + getMapKey(txnClrMap, clrInt) + "_TXN";
	        	txnTypeClrMapTmp.put(clrKey, entry.getValue());
	        }
	        
	        //System.out.println("txnTypeDecMap = " + txnTypeDecMap);
	        //System.out.println("txnTypeActMap = " + txnTypeActMap);
	        //System.out.println("txnTypeClrMap = " + txnTypeClrMap);
	        totalTxnTypeDecMap.put(productType, txnTypeDecMap);
	        printMap(txnTypeActMap);
	        printMap(txnTypeClrMapTmp);
	        
	    } catch (Exception e) {
	        System.err.println("///////////////////////////////////");
	        System.err.println("error occurs in \nsheetName : " + sheetName);
	        System.err.println("row : " + (readRow + 1));
	        System.err.println("cell : " + (readCell + 1));
	        e.printStackTrace();
	        System.err.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
	    } finally {
	    }
    }
    
    public static void printMap(Map<String, List<String>> map){
    	for(Entry<String, List<String>> entry : map.entrySet()){
	        System.out.print(entry.getKey() + "=");
	    	boolean isFirst = true;
        	for(String patten : entry.getValue()){
        		if(isFirst){
            		System.out.print(patten);
            		isFirst = false;
        		} else {
            		System.out.print("," + patten);
        		}
	        }
    		System.out.print("\n");
    	}
    	
    }
    
    public static void putTxnRecord(Map<String, List<String>> txnTypeMap, String key, String value){
    	
    	if(!txnTypeMap.containsKey(key)){
    		txnTypeMap.put(key, new ArrayList<String>());
    	}
    	
    	if(!txnTypeMap.get(key).contains(value)){
    		txnTypeMap.get(key).add(value);
    	}
    	
    }
    
    public static String getMapKey(Map<String, String> keyMap, String keyInExl){
    	
    	if(!keyMap.containsValue(keyInExl)){
    		System.out.println("keyMap: " + keyMap);
    		System.out.println("Can't map key: " + keyInExl);
    	}
    	
    	for(Entry<String, String> entry : keyMap.entrySet()){
    		if(StringUtils.equals(entry.getValue(), keyInExl)){
    			return entry.getKey();
    		}
    	}

		System.out.println("Can't map key: " + keyInExl);
    	return null;
    }
    
	public static Map<String, Map<String, String>> totalTxnTypeDecMap;
	public static Map<String, String> productKeyMap;
	public static Map<String, String> actKeyMap;
	public static Map<String, Integer> productClrInxMap;
	public static Map<String, String> txnDecInProMap;
	
	static {
		totalTxnTypeDecMap = new HashMap<String, Map<String, String>>();
		
		productKeyMap = new HashMap<String, String>();
		productKeyMap.put("UT", "Fund");
		productKeyMap.put("BOND", "Bond&CD");
		productKeyMap.put("SN", "StructuredNotes");
		productKeyMap.put("EQ", "MAP");
		productKeyMap.put("DPS", "DualCurrencyDeposit");
		productKeyMap.put("SID", "StructuredInvestmentDeposit");
		
		actKeyMap = new HashMap<String, String>();
		actKeyMap.put("BUY", "Bought");
		actKeyMap.put("DIVIDEND_INTEREST", "Dividends & Interest");
		actKeyMap.put("SELL", "Sold");
		actKeyMap.put("SWITCH", "Switch");
		actKeyMap.put("OTHERS", "Other");

		productClrInxMap = new HashMap<String, Integer>();
		productClrInxMap.put("UT", new Integer(20));
		productClrInxMap.put("BOND", new Integer(20));
		productClrInxMap.put("SN", new Integer(20));
		productClrInxMap.put("EQ", new Integer(20));
		productClrInxMap.put("DPS", new Integer(19));
		productClrInxMap.put("SID", new Integer(19));
		
		txnDecInProMap = new HashMap<String, String>();
	}
	
	public static void validateDesc(){
        try {
            BufferedReader reader = null;
            String filePatch = "C:\\Workspace\\WDV2.1INTG\\group-wealthdashboard-content_customer\\src\\main\\resources\\content\\group\\wealthdashboard\\txnhistory\\nls\\SG\\txn_type_description.properties";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePatch)));

            String line;
            do {
                line = reader.readLine();
                
                if(StringUtils.isNotEmpty(line) && line.contains("=")){
                	String[] tmp = line.split("=");
                	if(StringUtils.isNotBlank(tmp[1])){
                		txnDecInProMap.put(tmp[0], tmp[1]);
                	} else {
                		txnDecInProMap.put(tmp[0], null);
                	}
                }

            } while (null != line);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
