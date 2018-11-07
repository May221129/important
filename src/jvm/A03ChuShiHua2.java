package jvm;

public class A03ChuShiHua2 extends SuperClass{
	static{
		System.out.println("包含main方法的类初始化了");
	}
	public static void main(String[] args){
		System.out.println("main方法执行了");
	}
}

class SuperClass{
	static{
		System.out.println("包含main方法的类的父类初始化了");
	}
}