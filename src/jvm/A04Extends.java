package jvm;

/**
 * 子类没有重写父类的属性和方法的时候，通过子类来调用父类的属性或方法，子类是不会被初始化的。
 */
public class A04Extends{
	public static void main(String[] args){
		System.out.println(Children2.a);
		Children2.doSomething();
	}
}

class Parent2{
	static int a = 3;
	static{
		System.out.println("父类初始化了");
	}
	static void doSomething(){
		System.out.println("执行了父类的静态方法");
	}
}
class Children2 extends Parent2{
	static{
		System.out.println("子类被初始化了");
	}
}
