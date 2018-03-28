package org.loy.test2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

public class UpdateDummyMsg {

	public static void main(String[] args) {
		readProperties();
	}

    public static void readProperties(){

        try {
            BufferedReader reader = null;
            String filePatch = "C:\\Workspace\\Temp\\RegionHostMSGTemplate.txt";
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePatch)));

            String line;
            do {
                line = reader.readLine();
                
                if(StringUtils.isEmpty(line)){
                	continue;
                }

                if(line.contains("productHoldingPercent")){
                	int dotPoint = line.indexOf(".");
                	
                	String preStr = line.substring(0, dotPoint - 2);
                	String subStr = line.substring(dotPoint);
                	
                	line = preStr + subStr;
                	
                	line = line.replaceAll("1.000", "4");
               
                }
                
                System.out.println(line);
                
            } while (null != line);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    
    	
    }

}
