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

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b> Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class ReadProperties {

    public static void main(final String[] args) {
    	readProperties();
    }
    
    public static void validateProductpath(){

        try {
            BufferedReader reader = null;
            String filePath = null;
            String line  = null;
            
            filePath = "C:\\Workspace\\WDV2.0Dev\\group-wealthdashboard-portlet\\src\\main\\resources\\group\\wealthdashboard\\portfdetlsevlt\\product-path.properties";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            
            List<String> feProductTypeList = new ArrayList<String>();
            
            do {
                line = reader.readLine();
                
                if(StringUtils.isNotEmpty(line) && !line.startsWith("#") && line.contains("=")){
                	String[] arr = line.split("=");
                	String[] tmp = arr[0].split("_");
                	feProductTypeList.add(tmp[0]);
                }

            } while (null != line);
            
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            
            filePath = "C:\\Workspace\\WDV2.1INTG\\group-wealthdashboard-content\\src\\main\\resources\\content\\group\\wealthdashboard\\common\\TW\\product-type-mapping.properties";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));

            do {
                line = reader.readLine();
                
                if(StringUtils.isNotEmpty(line) && !line.startsWith("#") && line.contains("=")){
                	String[] arr = line.split("=");
                	
                	if(arr.length == 1){
                		continue;
                	}
                	if(!feProductTypeList.contains(arr[1])){
                		System.out.println(line);
                	}
                }

            } while (null != line);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        }

    
    	
    }
    
    
    public static void validateDescription(){

        try {
            BufferedReader reader = null;
            String filePath = null;
            String line  = null;
            
            filePath = "C:\\Workspace\\WDV2.1INTG\\group-wealthdashboard-content_customer\\src\\main\\resources\\content\\group\\wealthdashboard\\txnhistory\\nls\\SG\\txn_type_description.properties";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            
            Map<String,String> transactionDescriptionMap = new HashMap<String,String>();
            
            do {
                line = reader.readLine();
                
                if(StringUtils.isNotEmpty(line) && !line.startsWith("#") && line.contains("=")){
                	String[] arr = line.split("=");
                	transactionDescriptionMap.put(arr[0], arr[1]);
                }

            } while (null != line);
            
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            
            filePath = "C:\\Workspace\\WDV2.1INTG\\group-wealthdashboard-content_customer\\src\\main\\resources\\content\\group\\wealthdashboard\\txnhistory\\SG\\txnhistory-config.properties";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));

            List<String> transactionTypesList = new ArrayList<String>();
            transactionTypesList.add("SEC");transactionTypesList.add("UT");transactionTypesList.add("BOND");
            transactionTypesList.add("ELI");transactionTypesList.add("SN");transactionTypesList.add("SID");
            transactionTypesList.add("DPS");transactionTypesList.add("OPTS");transactionTypesList.add("WRTS");
            transactionTypesList.add("LCBB");transactionTypesList.add("ETF");transactionTypesList.add("MAP");
            transactionTypesList.add("ADR");
            
            int lineCount = 0;
            
            do {
                line = reader.readLine();
                lineCount ++;
                
                if(lineCount < 72){
                	continue;
                }
                
                if(lineCount > 147){
                	break;
                }
                
                if(StringUtils.isNotEmpty(line) && !line.startsWith("#") && line.contains("=")){
                	String[] arr = line.split("=");
                	
                	if(arr.length == 1){
                		continue;
                	}
                	
                	boolean isAllowedTxn = false;
                	String txnType = null;
                	for(String tmp : transactionTypesList){
                		if(arr[0].startsWith(tmp)){
                			txnType = tmp;
                			isAllowedTxn = true;
                			break;
                		}
                	}
                	
                	if(!isAllowedTxn){
                		continue;
                	}

                	String[] patternArr = arr[1].split(",");
                	
                	for(String pattern : patternArr){
                    	if(!transactionDescriptionMap.containsKey(txnType + "_" + pattern)){
                    		System.out.println(txnType + "_" + pattern);
                    	}
                	}
                	
                }

            } while (null != line);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
        }

    }

    public static void readProperties(){

        try {
            BufferedReader reader = null;
            String filePatch = "C:\\Workspace\\WDv2.1Dev\\group-wealthdashboard-content\\src\\main\\resources\\content\\group\\wealthdashboard\\common\\nls\\currencyfilter-labels.properties";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePatch)));

            String line;
            do {
                line = reader.readLine();
                
                if(StringUtils.isEmpty(line)){
                	continue;
                }
                
                if(line.startsWith("#")){
                	continue;
                }
                
                if(!line.contains("=")){
                	continue;
                }
                
                String tmp[] = line.split("=");
                
                System.out.println(tmp[0] + "	" + tmp[1]);
                

            } while (null != line);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    
    	
    }
    
}
