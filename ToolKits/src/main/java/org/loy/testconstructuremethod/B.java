package org.loy.testconstructuremethod;


public class B extends A{

    public B(){
        System.out.println("B1");
    }
    public B(String t){
    	super(t);
        System.out.println("B2" + t);
    }

    public static void main(String[] args){
        A a = new B();
        System.out.println("-----------------------------------");
        a = new B("t");
    }
}
