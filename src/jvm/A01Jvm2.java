package jvm;

/**
 * 这个例子是为了明白new之后都做了是那么事情，还有就是多态
 * 运行结果：
 *  jvm.A01Jvm2
	Target=> 0
	Target=> 30
	Depend=> 20
	jvm.Depend
	Depend=> 10

	jvm.A01Jvm2
	Target=> 0
	Target=> 30
	Depend=> 20
	20
 */
public class A01Jvm2 extends Depend{
	public static void main(String[] args){
		new A01Jvm2();
		new Depend();
		
		System.out.println();
		
		Depend d = new A01Jvm2();//多态的情况下，子类重写了父类的print()方法，那么d.print()调用的就是子类A01Jvm2的。
		System.out.println(d.i);//多态的情况下，此时d.i访问的是父类Depend的属性。
	}
	
	int i = 30;

	public A01Jvm2(){
		print();
		super.print();
		i = 40;
	}
	void print(){
		System.out.println("Target=> " + i);
	}
}

class Depend{
	int i = 10;

	public Depend(){
		System.out.println(this.getClass().getName());
		print();//在实例化子类对象时，来到这里实例化父类，此时这里是个多态。问，这里的print()调用的是谁的？i又是谁的？
		i = 20;
	}
	
	void print(){
		System.out.println("Depend=> " + i);
	}
}
