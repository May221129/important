package jvm;
/**
 * ���Լ̳и���ľ�̬��������̬������
 * �������û��ȥ��д����ľ�̬����������һ��main������ͨ��������ø���������̬�����������ǲ��ᱻ���صģ�Ҳ����˵������ʼ�����ࣩ��
 * ��ô��һ�����Ƿ񱻳�ʼ���ˣ��������ľ�̬������Ƿ�ִ���ˡ�
 */
public class NewTest3 {
	public static void main(String[] args) {
		Son.han();
	}
}

class Baba {
	public static void han() {
		System.out.println("sadasda");
	}
}

class Son extends Baba {
	static{
		System.out.println("Son��ļ���");
	}
}