package jvm;

/**
 * ������-Xss128k
 * 
 * ջ����쳣��StackOverflowError
 * 1.ջ��ͨ��ָjava�����ջ��
 * 2.ջ�������������̣߳�
 * 3.ջ֡�����ŷ�����ִ�ж���������
 * 4.���ŷ���������ֱ��ִ�н����Ĺ��̣���Ӧ��һ��ջ֡��Java�����ջ�д���ջ����ջ�Ĺ��̣�
 * 5.����ͨ����עջ���ǹ�עջ֡�еľֲ�������
 * 
 * ջһ��ᱨ�����쳣��
 * 	��StackOverflowError���߳������ջ��ȴ������������������ȡ�
 * 	��OutofMemoryError�����������չջʱ�޷����뵽�㹻���ڴ�ռ䡣
 */
public class C02StackOverflowError {
	private int i = 0;
	
	/**
	 * �������������γ���һ��û�г��ڵĵݹ飬ÿ����һ���������ͻ���������д���һ��ջ֡����ջ�������ջ�С�
	 */
	public void show(){
		i++;
		show();//ջ���
	}
	
	public static void main(String[] args) throws Throwable {
		C02StackOverflowError c2 = new C02StackOverflowError();
		try {
			c2.show();
		} catch (Throwable e) {
			System.out.println("==================> i = " + c2.i);
			throw e;
		}
	}
}
