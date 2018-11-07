package jvm;

/**
 * jvm执行顺序：先初始化谁？
 * 1.看程序主入口：含main方法的类，它有父类就先初始化父类，父类还有父类，依旧先初始化父类的父类；没有父类就最先初始化它。
 * 2.看main方法中的程序执行顺序：“new,通过反射用到某个类,访问或调用某个类的静态变量或静态方法”这三种情况都会引发类的初始化。当然，依旧遵循“最先初始化父类”这一原则。
 */
public class A03ChuShiHua1{
	static{
		System.out.println("有主函数的类被初始化了");
	}
	public static void main(String[] args){
		System.out.println(Children.a);
	}
}

class Parent{
	static{
		System.out.println("父类被初始化了");
	}
}
class Children extends Parent{
	public static int a = 5;
	
	static{
		System.out.println("子类被初始化了");
	}
}