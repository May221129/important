package jvm;

public class A03ChuShiHua2 extends SuperClass{
	static{
		System.out.println("����main���������ʼ����");
	}
	public static void main(String[] args){
		System.out.println("main����ִ����");
	}
}

class SuperClass{
	static{
		System.out.println("����main��������ĸ����ʼ����");
	}
}