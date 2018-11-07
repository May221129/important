package jvm;
/**
 * ����Ķ�̬������У������� ��̬��p1()�����ͷǾ�̬��p2()����������������������������д�˵ġ�
 * ��ʱ��ʵ�ʵ��õ��Ǹ����еľ�̬����p1()�������еķǾ�̬����p2().
 */
/*
 * ���н����
 * ��̬ ����Parent-a: 2
 * ��̬����Child-a: 21
 * ��̬ ����Parent-a: 2
 * Child-a: 21
 * Child-b: 0
 * ִ���˸���Ĺ��췽��
 * ��̬����Child-a: 21
 * Child-a: 21
 * Child-b: 41
 * ִ��������Ĺ��췽��
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
		System.out.println("ִ���˸���Ĺ��췽��");
	}

	public static void p1() {
		System.out.println("��̬ ����Parent-a: " + a);
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
		System.out.println("ִ��������Ĺ��췽��");
	}

	public int b = 31;
	{
		b = 41;
		p1();
		p2();
	}

	public static void p1() {
		System.out.println("��̬����Child-a: " + a);
	}

	public void p2() {
		System.out.println("Child-a: " + a);
		System.out.println("Child-b: " + b);
	}
}