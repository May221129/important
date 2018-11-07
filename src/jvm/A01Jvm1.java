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
	 * static�������ȸ��ڴ�ռ䣬����Ĭ��ֵ�� Ȼ���static���������ڴ�ռ䣬�����������Ǹ��ϳ�ʼֵ��
	 * �����������ӣ���ֵ��ʱ����ȫ������1(���췽��)�� Ȼ���������number1��Ϊû���ٴγ�ʼ��������Ȼ��1��
	 * number2�ָ���ʼ������0 ���������Ľ���� number1 = 1 number2 = 0;
	 */
	
	//��ʼ����ʱ���������д����ִ���ˣ�Ҳ����˵singleton�Ѿ�new���ˡ�
	private static Singleton singleton = new Singleton();//��������д���ŵ�number�����档��ô���Ƕ���1
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
