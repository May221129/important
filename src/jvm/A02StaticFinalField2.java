package jvm;

import java.util.Random;

public class A02StaticFinalField2{
	public static void main(String[] args){
		System.out.println(FinalSample2.b);
	}
}

class FinalSample2{
	public static final int b = new Random().nextInt(100);
	
	static {
		System.out.println("静态代码块执行了，也就是说初始化了");
	}
}