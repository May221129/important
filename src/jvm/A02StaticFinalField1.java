package jvm;

/**
 * �������ʱ��̬������ȷ����,����6/3.ע���Ǿ�̬�������ǳ����� ��ô������FinalSample.a������Ҳ���ᱻ��ʼ�����������ľ�̬������Ƿ�ִ���ˡ� ��
 * ��Test2��int b = new Random().nextInt(100) ��������ڱ����ʱ���޷�ȷ��ֵ�ĳ������ó�����������ͻᱻ��ʼ����
 */
public class A02StaticFinalField1{
	public static void main(String[] args){
		System.out.println(FinalSample.a);
	}
}

class FinalSample{
	
	public static final int a = 6 / 3;
	
	static{
		System.out.println("��̬�����ִ���ˣ�Ҳ����˵��ʼ����");
	}
}