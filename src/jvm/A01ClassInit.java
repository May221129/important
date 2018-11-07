package jvm;

import org.junit.Test;

/**
 * 类的初始化——被动引用时，是不会引发类的初始化的。
 */
public class A01ClassInit {
	@Test
	public void testString(){
		String s4 = "Hel" + new String("lo");
		System.out.println(s4);
	}
	
	/**
	 * 被动引用案例3. 
	 * 在main方法中读取被final修饰、已在编译期把结果放入常量池的静态字段，也不会引发该静态字段所属的类的初始化.
	 */
	@Test
	public void testStaticFinalField(){
		System.out.println(MySupClass.say);
	}
	
	/**
	 * 被动引用案例2. 
	 * 创建一个长度为10的数组，里面放SubClass对象。
	 * new出数组对象的时候该类不会被初始化。但是会初始化一个由虚拟机自动生成的，直接继承于java.lang.Object的子类
	 */
	@Test
	public void testArray(){
		MySubClass[] subClassArray = new MySubClass[10];
	}
	
	/**
	 * 被动引用案例1. 
	 * Super类有一个静态变量A(或者静态方法)。Sub类继承Super类。在main方法里：Sub.A。 结果只是初始化了父类，子类并没有被初始化。
	 * 总结：只有直接在类里面定义静态字段，该类才会被初始化。
	 */
	@Test
	public void testSuperClassField(){
		System.out.println(MySubClass.A);
	}
}

class MySupClass{
	static{
		System.out.println("I'm MySupClass.");
	}
	public static String A = "A";
	public static final String say = "I Love You";
}

class MySubClass extends MySupClass{
	static{
		System.out.println("I'm MySubClass...");
	}
	public MySubClass(){}
}

interface A{
	
}

interface B extends A{
	
}