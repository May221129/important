package jvm;

public class MethodTest{
	public static void main(String[] args){
		Demo d = new Demo();
	}
}

class Demo{
	{
		a();//ִ�з���a����
		//System.out.println(i);����a�Ͳ��С�
	}
	int i = 5;
	public void a(){
		System.out.println("ִ���˷���a");
	}
}