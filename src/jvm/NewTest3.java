package jvm;
/**
 * 可以继承父类的静态方法、静态变量。
 * 如果子类没有去重写父类的静态方法，在另一个main方法中通过子类调用父类的这个静态方法，子类是不会被加载的（也就是说不糊初始化子类）。
 * 怎么看一个类是否被初始化了：看这个类的静态代码块是否被执行了。
 */
public class NewTest3 {
	public static void main(String[] args) {
		Son.han();
	}
}

class Baba {
	public static void han() {
		System.out.println("sadasda");
	}
}

class Son extends Baba {
	static{
		System.out.println("Son类的加载");
	}
}