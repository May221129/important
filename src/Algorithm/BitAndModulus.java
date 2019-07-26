package Algorithm;

/**
 * ̽����λ�����ȡģ���������Ч�ʶԱȡ�
 * 	��˵��������ó�����λ����ģ���ô�������ж��ó��أ�
 * 
 * �������ʱ�䵥λ�����룩
 * 	�������			λ����			ȡģ����			������λ���㣺ȡģ���㣩
 * 	10��			734				20489			27
 * 	100��			742				20544			27
 * 	1000��			735				20408			27
 * 	1�ڣ�			712				19545			27
 * 
 * @author May
 * 2019��7��26��
 */
public class BitAndModulus {
	public static void main(String[] args) {
		int number = 10000 * 10;
		int a = 1, b = 1;
		
		long start = System.currentTimeMillis();
		for(int i = number; i > 0 ; i++) {
			a &= i;
		}
		long end = System.currentTimeMillis();
		System.out.println("λ�����ʱ�� " + (end - start));
		
		System.out.println("---------------------------------");
		
		start = System.currentTimeMillis();
		for(int i = number; i > 0; i++) {
			b %= i;
		}
		end = System.currentTimeMillis();
		System.out.println("ȡģ�����ʱ�� " + (end - start));
	}
}
