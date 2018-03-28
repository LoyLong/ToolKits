package org.loy.teststatic;

public class B{
	static {
		System.out.println("B static");
	}
	{
		System.out.println("B block");
	}
	public B(){
		System.out.println("B this");
	}

	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
}
