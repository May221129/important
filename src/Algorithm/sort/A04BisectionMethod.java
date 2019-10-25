package Algorithm.sort;

import java.util.ArrayList;
import org.junit.Test;

/**
 * �Լ�ʵ�ֶ��ַ�����
 * 
 * @author May 2019��10��20��
 */
public class A04BisectionMethod {
	/**
	 * 1. ���ԣ������Լ�ʵ�ֵĶ��ַ�����newNumber���뵽���������У�
	 * 2. ���Խ����
	 * 	��length==100������������в�����Ԫ�أ�3���룻
	 * 	��length==1000������������в�����Ԫ�أ�31���룻
	 */
	@Test
	public void testBisectionMethodSortUsedTime() {
		A04BisectionMethod bisectionMethod = new A04BisectionMethod();
		int arrayLength = 10000000;
		ArrayList<Integer> array = bisectionMethod.getOrderlyArray(arrayLength);
		long start = System.currentTimeMillis();
		bisectionMethod.insertByBisectionMethod(array, 0, (array.size() - 1), 9);
		long end = System.currentTimeMillis();
		System.out.println("����Ԫ�ز��뵽����Ϊ " + arrayLength + " �����������У���ʱ��" + (end - start));
	}
	/**
	 * �����Լ�ʵ�ֵĶ��ַ������Ƿ���ȷ
	 */
	@Test
	public void testBisectionMethodSortIsTrue() {
		A04BisectionMethod bisectionMethod = new A04BisectionMethod();
		int arrayLength = 20;
		ArrayList<Integer> array = bisectionMethod.getOrderlyArray(arrayLength);
		bisectionMethod.showArray(array);
		System.out.println("----------------------");
		bisectionMethod.insertByBisectionMethod(array, 0, (array.size() - 1), 9);
		bisectionMethod.showArray(array);
	}
	
	/**
	 * ��������
	 */
	protected void showArray(ArrayList<Integer> target) {
		for (Integer element : target) {
			System.out.println(element);
		}
	}
	
	/**
	 * ׼��һ����������
	 * 
	 * @param arrayLength
	 * @return ArrayList<Integer>
	 */
	protected ArrayList<Integer> getOrderlyArray(int arrayLength) {
		ArrayList<Integer> array = new ArrayList<>(arrayLength);
		for (int i = 0; i < arrayLength; i++) {
			array.add(i);
		}
		return array;
	}

	/**
	 * ���ö��ַ�����Ԫ�ز��뵽����������
	 */
	public void insertByBisectionMethod(ArrayList<Integer> targetArray, int left, int right, Integer newNumber) {
		// ���ݴ���
		if ((right - left) < 0) {
			return;
		}
		// ֻ��һ����
		if ((right - left) == 0) {
			if (newNumber < targetArray.get(left)) {
				targetArray.add(left, newNumber);
				return;
			}
			targetArray.add(left + 1, newNumber);
			return;
		}
		
		// ֻ��������
		if ((right - left) == 1) {
			if (newNumber < targetArray.get(left)) {
				targetArray.add(left, newNumber);// ����left��ǰ��
				return;
			}
			if (newNumber > targetArray.get(right)) {
				targetArray.add(right + 1, newNumber);// ����right�ĺ���
				return;
			}
			targetArray.add(right, newNumber);// ����left��right���м�
			return;
		}
		
		// >= 3����ʱ�����ֲ���
		int middleIndex = (left + right) / 2;
		if (newNumber == targetArray.get(middleIndex)) {
			targetArray.add(middleIndex, newNumber);// ����middleIndexλ�ã�middleIndex������������Ԫ�ض�����һλ
			return;
		}
		if (newNumber < targetArray.get(middleIndex)) {// newNumber�����
			insertByBisectionMethod(targetArray, left, middleIndex - 1, newNumber);
			return;
		} else {// newNumber���ұ�
			insertByBisectionMethod(targetArray, middleIndex + 1, right, newNumber);
			return;
		}
	}
}
