package jvm;

/**
 * ���������Ϊ������new֮����������ô���飬���о��Ƕ�̬
 * ���н����
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
		
		Depend d = new A01Jvm2();//��̬������£�������д�˸����print()��������ôd.print()���õľ�������A01Jvm2�ġ�
		System.out.println(d.i);//��̬������£���ʱd.i���ʵ��Ǹ���Depend�����ԡ�
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
		print();//��ʵ�����������ʱ����������ʵ�������࣬��ʱ�����Ǹ���̬���ʣ������print()���õ���˭�ģ�i����˭�ģ�
		i = 20;
	}
	
	void print(){
		System.out.println("Depend=> " + i);
	}
}
