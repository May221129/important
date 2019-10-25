package Algorithm.sort;

import java.util.ArrayList;
import org.junit.Test;

/**
 * 自己实现二分法排序
 * 
 * @author May 2019年10月20日
 */
public class A04BisectionMethod {
	/**
	 * 1. 测试：利用自己实现的二分法排序将newNumber插入到有序数组中：
	 * 2. 测试结果：
	 * 	往length==100万的有序数组中插入新元素：3毫秒；
	 * 	往length==1000万的有序数组中插入新元素：31毫秒；
	 */
	@Test
	public void testBisectionMethodSortUsedTime() {
		A04BisectionMethod bisectionMethod = new A04BisectionMethod();
		int arrayLength = 10000000;
		ArrayList<Integer> array = bisectionMethod.getOrderlyArray(arrayLength);
		long start = System.currentTimeMillis();
		bisectionMethod.insertByBisectionMethod(array, 0, (array.size() - 1), 9);
		long end = System.currentTimeMillis();
		System.out.println("将新元素插入到长度为 " + arrayLength + " 的有序数组中，耗时：" + (end - start));
	}
	/**
	 * 测试自己实现的二分法排序是否正确
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
	 * 遍历数组
	 */
	protected void showArray(ArrayList<Integer> target) {
		for (Integer element : target) {
			System.out.println(element);
		}
	}
	
	/**
	 * 准备一个有序数组
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
	 * 利用二分法将新元素插入到有序数组中
	 */
	public void insertByBisectionMethod(ArrayList<Integer> targetArray, int left, int right, Integer newNumber) {
		// 数据错误
		if ((right - left) < 0) {
			return;
		}
		// 只有一个数
		if ((right - left) == 0) {
			if (newNumber < targetArray.get(left)) {
				targetArray.add(left, newNumber);
				return;
			}
			targetArray.add(left + 1, newNumber);
			return;
		}
		
		// 只有两个数
		if ((right - left) == 1) {
			if (newNumber < targetArray.get(left)) {
				targetArray.add(left, newNumber);// 放在left的前面
				return;
			}
			if (newNumber > targetArray.get(right)) {
				targetArray.add(right + 1, newNumber);// 放在right的后面
				return;
			}
			targetArray.add(right, newNumber);// 放在left和right的中间
			return;
		}
		
		// >= 3个数时：二分查找
		int middleIndex = (left + right) / 2;
		if (newNumber == targetArray.get(middleIndex)) {
			targetArray.add(middleIndex, newNumber);// 放在middleIndex位置，middleIndex及其后面的所有元素都后移一位
			return;
		}
		if (newNumber < targetArray.get(middleIndex)) {// newNumber在左边
			insertByBisectionMethod(targetArray, left, middleIndex - 1, newNumber);
			return;
		} else {// newNumber在右边
			insertByBisectionMethod(targetArray, middleIndex + 1, right, newNumber);
			return;
		}
	}
}
