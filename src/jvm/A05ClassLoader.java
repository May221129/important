package jvm;

/**
 * ���÷����ȡĳ�����Class
 */
public class A05ClassLoader{
	public static void main(String[] args) throws Exception{
		/**
		 * �ֶ�����ĳ����
		 * ��Ȼ������û���Լ��ֶ�ȥ������C,jvmҲ���Զ�������ȥ���ء�
		 * ���о���Ҫ�õ�classLoader��ʱ��Ӧ���Ƕ�̬����һ�����ʱ��Ȼ����Ҫ�����أ��õ��ĵط����Ƕ�̬����-���ɴ����ࡿ
		 */
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();//�������.�õ�ϵͳ�������()
		//������(��ȷ��ֻ�����أ�������г�ʼ��)
		Class<?> clazz = classLoader.loadClass("jvm.C");//ȫ����
		clazz = Class.forName("jvm.C");//forName()��������г�ʼ���������Ҫ�����롣
	}
}

class C{
	static{
		System.out.println("��ʼ����C");
	}
}