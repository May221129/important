package jvm;

/**
 * jvmִ��˳���ȳ�ʼ��˭��
 * 1.����������ڣ���main�������࣬���и�����ȳ�ʼ�����࣬���໹�и��࣬�����ȳ�ʼ������ĸ��ࣻû�и�������ȳ�ʼ������
 * 2.��main�����еĳ���ִ��˳�򣺡�new,ͨ�������õ�ĳ����,���ʻ����ĳ����ľ�̬������̬�������������������������ĳ�ʼ������Ȼ��������ѭ�����ȳ�ʼ�����ࡱ��һԭ��
 */
public class A03ChuShiHua1{
	static{
		System.out.println("�����������౻��ʼ����");
	}
	public static void main(String[] args){
		System.out.println(Children.a);
	}
}

class Parent{
	static{
		System.out.println("���౻��ʼ����");
	}
}
class Children extends Parent{
	public static int a = 5;
	
	static{
		System.out.println("���౻��ʼ����");
	}
}