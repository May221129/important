package jvm;

import org.junit.Test;

/**
 * ��ĳ�ʼ��������������ʱ���ǲ���������ĳ�ʼ���ġ�
 */
public class A01ClassInit {
	@Test
	public void testString(){
		String s4 = "Hel" + new String("lo");
		System.out.println(s4);
	}
	
	/**
	 * �������ð���3. 
	 * ��main�����ж�ȡ��final���Ρ����ڱ����ڰѽ�����볣���صľ�̬�ֶΣ�Ҳ���������þ�̬�ֶ���������ĳ�ʼ��.
	 */
	@Test
	public void testStaticFinalField(){
		System.out.println(MySupClass.say);
	}
	
	/**
	 * �������ð���2. 
	 * ����һ������Ϊ10�����飬�����SubClass����
	 * new����������ʱ����಻�ᱻ��ʼ�������ǻ��ʼ��һ����������Զ����ɵģ�ֱ�Ӽ̳���java.lang.Object������
	 */
	@Test
	public void testArray(){
		MySubClass[] subClassArray = new MySubClass[10];
	}
	
	/**
	 * �������ð���1. 
	 * Super����һ����̬����A(���߾�̬����)��Sub��̳�Super�ࡣ��main�����Sub.A�� ���ֻ�ǳ�ʼ���˸��࣬���ಢû�б���ʼ����
	 * �ܽ᣺ֻ��ֱ���������涨�徲̬�ֶΣ�����Żᱻ��ʼ����
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