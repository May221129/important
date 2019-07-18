package Algorithm;

import java.util.Stack;

/**
 * ����ķ�ת���磺{1,2,3}��תΪ{3,2,1}.
 * 	1. ʵ�ַ�ʽ��1.ջ��ѹջ����ջ��  2.��β������i++, j--
 * 	2. ����ʵ�ַ�ʽ��Ч�ʶԱȣ�
 * 		��ʱ�䵥λ�����룩		ջ			��β����
 * 			10��					19			1
 * 			100��				147			5
 * 			1000��				3040		11
 * 	3. ���ۣ�����β�������ķ�ʽ���������ķ�ת���������ڡ�ջ���ķ�ʽ��
 * 
 * @author May
 * 2019��7��16��
 */
public class ArrayReversal {
	public static void main(String[] args) {
		ArrayReversal test = new ArrayReversal();
		int number = 10000 * 1000;
		int[] array1 = new int[number];
		int[] array2 = new int[number];
		for(int i = 0; i < number; i++) {
			array1[i] = i;
			array2[i] = i;
		}
		
//		test.printElement(array1);
		
		test.arrayReversal(array1);
//		test.printElement(array1);
		System.out.println("--------------------------");
		test.arrayReversalByStack(array2);
//		test.printElement(array2);
	}
	
	/**
	 * ��ջ
	 */
	public void arrayReversalByStack(int[] target) {
		long start = System.currentTimeMillis();
		Stack<Integer> stack = new Stack<>();
		for(Integer element : target) {
			stack.push(element);
		}
		for(int i = 0; stack.size() > 0; i++) {
			target[i] = stack.pop();
		}
//		int i = 0;
//		while(stack.size() > 0) {
//			target[i] = stack.pop();
//			i++;
//		}
		long end = System.currentTimeMillis();
		System.out.println("��ջ����ʱ�� " + (end - start));
	}
	
	/**
	 * ��stack��Ԫ�ص�˳��
	 */
	public void printStackElement(int[] target) {
		Stack<Integer> stack = new Stack<>();
		for(Integer element : target) {
			stack.push(element);
		}
		for(int element : stack) {
			System.out.println(element);
		}
	}
	
	/**
	 * ��β����
	 */
	private void arrayReversal(int[] target) {
		long start = System.currentTimeMillis();
		int temp;
		for(int i = 0,j = target.length-1; i < j; j--,i++ ) {
			temp = target[i];
			target[i] = target[j];
			target[j] = temp;
		}
		long end = System.currentTimeMillis();
		System.out.println("��β��������ʱ�� " + (end - start));
	}
	
	/**
	 * ÿ50���һ�Σ��磺�����0��Ԫ�ء���49��Ԫ�ء�����
	 * @param target
	 */
	private void printElement(int[] target) {
		for(int i = 0; i < target.length; i++) {
			if(i % 50 == 0) {
				System.out.println(target[i]);
			}
		}
	}
}
