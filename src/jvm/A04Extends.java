package jvm;

/**
 * ����û����д��������Ժͷ�����ʱ��ͨ�����������ø�������Ի򷽷��������ǲ��ᱻ��ʼ���ġ�
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
		System.out.println("�����ʼ����");
	}
	static void doSomething(){
		System.out.println("ִ���˸���ľ�̬����");
	}
}
class Children2 extends Parent2{
	static{
		System.out.println("���౻��ʼ����");
	}
}
