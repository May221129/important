package jvm;
/**
 * ��̬������¡���ʵ�ͷǶ�̬�����һ���ġ�
 * 1. ����Ķ�̬��������������������ˡ�������д�˵ġ���̬�ķ���������ʱִ�е������Ǹ���ĸþ�̬������
 * 2. ����Ķ�̬��������������������ˡ�������д�˵ġ��Ǿ�̬�ķ���������ʱִ�е�������ĸ÷Ǿ�̬������
 * 3. ����Ķ�̬������������������ʡ�������д�˵�����(���۾�̬���Ǿ�̬)������ʱ���ʵ������Ǹ���ġ�
 * 4. ��̬����������������е��÷������÷�����˭�ģ���ô�����з��ʵġ�������д�˵�����(���۾�̬���Ǿ�̬)������˭�ġ�
 * ���Ϲ��ıʼǣ��ڸ���Ķ�̬������ڻ����ڹ��췽����Ҫ�ǵ�������������д�˵ķ�������ô���ǵ�������ķ�����Ҫ�Ƿ�����������д�ı��������ʽ���Ǹ����Լ��ı�������
 */
public class A01Jvm4 {
	public static void main(String[] args) {
		SuperClass2 s = new SubClass2();
	}
}

class SuperClass2 {
	static int a = 9;

	public void a() {
		System.out.println("ִ�и����a����");
	}

	public SuperClass2() {
		System.out.println(a);
		a();
	}

	{
		System.out.println("���ද̬�����ִ��" + a);
		a();
	}
}

class SubClass2 extends SuperClass2 {
	static int a = 5;

	public void a() {
		System.out.println("ִ���������a����" + a);
	}

	public SubClass2() {
		a();
	}

	{
		System.out.println("���ද̬�����ִ��" + a);
	}
}