package jvm;

/**
 * 当编译的时候静态常量是确定的,比如6/3.注意是静态常量，是常量！ 那么就算有FinalSample.a。该类也不会被初始化【看这个类的静态代码块是否被执行了。 】
 * 在Test2中int b = new Random().nextInt(100) ，这个是在编译的时候无法确定值的常量，该常量所属的类就会被初始化。
 */
public class A02StaticFinalField1{
	public static void main(String[] args){
		System.out.println(FinalSample.a);
	}
}

class FinalSample{
	
	public static final int a = 6 / 3;
	
	static{
		System.out.println("静态代码块执行了，也就是说初始化了");
	}
}