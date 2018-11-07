package jvm;

public class A01Jvm1{
	public static void main(String[] args){
		
		Singleton singleton = Singleton.getInstance();
		
		System.out.println("number1 :  " + singleton.number1);
		System.out.println("number2 :  " + singleton.number2);
	}
}

class Singleton{
	
	/**
	 * static属性首先给内存空间，附上默认值， 然后等static都被给了内存空间，接下来给他们附上初始值。
	 * 对于以下例子，赋值的时候先全部附成1(构造方法)， 然后接下来，number1因为没有再次初始化所以依然是1，
	 * number2又给初始化成了0 ，所以最后的结果是 number1 = 1 number2 = 0;
	 */
	
	//初始化类时，下面这行代码就执行了，也就是说singleton已经new好了。
	private static Singleton singleton = new Singleton();//如果把这行代码放到number的下面。那么它们都是1
	public static int number1;
	public static int number2 = 0;
	
	static{
		System.out.println(singleton.toString());
	}
	
	private Singleton(){
		number1++;
		number2++;
	}
	
	public static Singleton getInstance(){
		return singleton;
	}
}
