package Algorithm;

import java.util.Stack;

/**
 * 数组的反转，如：{1,2,3}反转为{3,2,1}.
 * 	1. 实现方式：1.栈：压栈、弹栈；  2.首尾交换，i++, j--
 * 	2. 两种实现方式的效率对比：
 * 		（时间单位：毫秒）		栈			首尾交换
 * 			10万					19			1
 * 			100万				147			5
 * 			1000万				3040		11
 * 	3. 结论：“首尾交换”的方式来完成数组的反转，明显优于“栈”的方式。
 * 
 * @author May
 * 2019年7月16日
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
	 * 用栈
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
		System.out.println("用栈，耗时： " + (end - start));
	}
	
	/**
	 * 看stack中元素的顺序
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
	 * 首尾交换
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
		System.out.println("首尾交换，耗时： " + (end - start));
	}
	
	/**
	 * 每50输出一次，如：输出第0个元素、第49个元素、……
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
