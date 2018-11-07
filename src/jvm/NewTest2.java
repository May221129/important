package jvm;
/**
 * 父类的动态代码块中，调用了 静态的p1()方法和非静态的p2()方法，且这两个方法都是子类重写了的。
 * 此时，实际调用的是父类中的静态方法p1()，子类中的非静态方法p2().
 */
/*
 * 运行结果：
 * 静态 方法Parent-a: 2
 * 静态方法Child-a: 21
 * 静态 方法Parent-a: 2
 * Child-a: 21
 * Child-b: 0
 * 执行了父类的构造方法
 * 静态方法Child-a: 21
 * Child-a: 21
 * Child-b: 41
 * 执行了子类的构造方法
 */
public class NewTest2 {
	public static void main(String[] args) {
		new Child();
	}
}

class Parent5 {
	public static int a = 1;
	static {
		a = 2;
		p1();
	}
	public int b = 3;
	{
		b = 4;
		p1();
		p2();
	}

	public Parent5() {
		System.out.println("执行了父类的构造方法");
	}

	public static void p1() {
		System.out.println("静态 方法Parent-a: " + a);
	}

	public void p2() {
		System.out.println("Parent-a: " + a);
		System.out.println("Parent-b: " + b);
	}
}

class Child extends Parent5 {
	public static int a = 11;
	static {
		a = 21;
		p1();
	}

	public Child() {
		System.out.println("执行了子类的构造方法");
	}

	public int b = 31;
	{
		b = 41;
		p1();
		p2();
	}

	public static void p1() {
		System.out.println("静态方法Child-a: " + a);
	}

	public void p2() {
		System.out.println("Child-a: " + a);
		System.out.println("Child-b: " + b);
	}
}