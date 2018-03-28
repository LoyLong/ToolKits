package org.loy.teststatic;

public class A2 extends A{
	static {
		System.out.println("A2 static");
	}
	{
		System.out.println("A2 block");
	}
	public A2(){
		System.out.println("A2 this");
	}
}
