package jvm;
/**
 * 非多态的情况下-下面程序的执行顺序：
 * 1. 程序入口：初始化“含main()方法的这个类”，初始化前先检查这个类是否有父类，父类先于子类进行初始化；【这里的初始化，指的是类的加载。】
 * 2. 类的加载，会将这个类的类变量(即静态变量)进行初始化，并执行静态代码块中的代码。【注意，静态方法是不执行的。】
 * 3.  类变量也称为共享变量，所有的实例都能拿到。
 * 4. SubClass s = new SubClass(); ==> new出SubClass类的实例，有父类先new父类的实例；
 * 5. new是从顶级父类Object逐层往下的。
 * 6. new这个动作会做什么：初始化类的实例变量(非静态变量)==>执行动态代码块中的代码==>执行构造方法(构造器)。
 * 7.1 父类的动态代码块或构造器中如果调用了“子类重写了的、静态的方法”，此时执行的依旧是父类的该静态方法。
 * 7.2 父类的动态代码块或构造器中如果调用了“子类重写了的、非静态的方法”，此时执行的是子类的该非静态方法。
 * 8. 父类的动态代码块或构造器中如果访问“子类重写了的、非静态的属性”，此时访问的属性是父类的。
 * 9. 动态代码块或构造器中如果有调用方法，该方法是谁的，那么方法中访问的“子类重写了的成员变量(静态或非静态都包括)”就是谁的。
 * 10. 执行构造器。
 */
public class A01Jvm3 {
	public static void main(String[] args) {
		SubClass s = new SubClass();
	}
}

class SuperClass1 {
	int a = 9;

	public void a() {
		System.out.println("执行父类的a方法");
	}

	public SuperClass1() {
		System.out.println(a);
		a();
	}

	{
		System.out.println("父类动态代码块执行" + a);
		a();//在new子类时，先来初始化父类的实例，此时这里相当于有多态
	}
}

class SubClass extends SuperClass1 {
	int a = 5;

	public void a() {
		System.out.println("执行了子类的a方法" + a);
	}

	public SubClass() {
		a();
	}

	{
		System.out.println("子类动态代码块执行" + a);
	}
}