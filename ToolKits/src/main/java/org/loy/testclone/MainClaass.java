package org.loy.testclone;

import java.util.ArrayList;
import java.util.List;

public class MainClaass {

	public static void main(String[] args){
		List<ChildClassOne> dataList = new ArrayList<ChildClassOne>();
		
		DataClass data1 = new DataClass();
		data1.setData("data1");
		
		ChildClassOne class1 = new ChildClassOne();
		class1.setClassKey("ClassOne1");
		class1.setClassOneAtt("OneAttOne1");
		class1.setData(data1);
		dataList.add(class1);
		
		try {
			ChildClassOne class2 = (ChildClassOne)class1.getClone();
			class2.setClassKey("ClassOne2");
			class2.setClassOneAtt("OneAttOne2");
			dataList.add(class2);
			
			ChildClassOne class3 = (ChildClassOne)class1.getClone();
			class3.setClassKey("ClassOne3");
			class3.setClassOneAtt("OneAttOne3");
			dataList.add(class3);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		System.out.println(dataList);
		
	}
	
}
