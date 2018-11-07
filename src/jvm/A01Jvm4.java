package jvm;
/**
 * 多态的情况下【其实和非多态情况是一样的】
 * 1. 父类的动态代码块或构造器中如果调用了“子类重写了的、静态的方法”，此时执行的依旧是父类的该静态方法。
 * 2. 父类的动态代码块或构造器中如果调用了“子类重写了的、非静态的方法”，此时执行的是子类的该非静态方法。
 * 3. 父类的动态代码块或构造器中如果访问“子类重写了的属性(不论静态、非静态)”，此时访问的属性是父类的。
 * 4. 动态代码块或构造器中如果有调用方法，该方法是谁的，那么方法中访问的“子类重写了的属性(不论静态、非静态)”就是谁的。
 * 【老公的笔记：在父类的动态代码块内或是在构造方法内要是调用了子类有重写了的方法，那么就是调用子类的方法。要是访问了子类重写的变量，访问结果是父类自己的变量。】
 */
public class A01Jvm4 {
	public static void main(String[] args) {
		SuperClass2 s = new SubClass2();
	}
}

class SuperClass2 {
	static int a = 9;

	public void a() {
		System.out.println("执行父类的a方法");
	}

	public SuperClass2() {
		System.out.println(a);
		a();
	}

	{
		System.out.println("父类动态代码块执行" + a);
		a();
	}
}

class SubClass2 extends SuperClass2 {
	static int a = 5;

	public void a() {
		System.out.println("执行了子类的a方法" + a);
	}

	public SubClass2() {
		a();
	}

	{
		System.out.println("子类动态代码块执行" + a);
	}
}