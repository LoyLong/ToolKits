package org.loy.teststatic;

public class A {
	static {
		//System.out.println("A static");
	}
	{
		//System.out.println("A block");
	}
	public A(){
		//System.out.println("A this");
	}

	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}
