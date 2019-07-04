package sort;

/**
 * 快排优化
 * 
 * 一、为什么要对快排进行优化：
 * 	A01Quicksort中的快排算法可以说是最基本的快速排序，因为它并没有考虑任何输入数据。
 * 	但是，我们很容易发现这个算法的缺陷：这就是在我们输入数据基本有序甚至完全有序的时候，
 * 	这算法退化为冒泡排序，不再是O(n㏒n)(N*logN,而不能表述为N*log2 N，因为很多时候，快排都不能达到理想的每次平均分为2份)，而是O(n^2)了。
 * 
 * 二、怎么进行优化：
 * 	1.优化选取枢轴：
 * 		三数取中，即取三个元素先进行排序，将中间数作为枢轴， 一般是取左端、右端和中间三个数， 也可以随机选取。 
 *		对于非常大的待排序的序列来说还是不足以保证能够选择出一个好的pivot， 因此还有个办法是所谓的九数取中，
 *		先从数组中分三次取样，每次取三个数，三个样品各取出中数，然后从这三个中数当中再取出一个中数作为枢轴 。
 */
public class A02QuickSort {
	public static void main(String[] args) {
		int[] array = {181, 181, 187, 181};
		quicksort(array, 0, array.length-1);
		for (int element : array) {
			System.out.print(element + " ");
		}
	}

	/**
	 * 选择一个关键字，想尽办法将它放到一个位置，使得它左边的值都比它小，右边的值都比它大，我们称这个关键字叫枢轴。
	 */
	public static void quicksort(int[] array, int start, int end) {
		if (array == null || start < 0 || end >= array.length) {
			return;
		}
		
		int middle = getPivot(array, start, end);// 选取枢轴值
		int pivot = array[middle];
		
		//因为在getPivot()中，就已经对start、middle和end这三个值进行过排序了，所以这里不许重复排序
		int left = start + 1, right = end - 1;
		
		// 从表的两端向中间扫描
		while (left < right){
			while (left < right && array[right] >= pivot) {// 如果大于枢轴值，则下标减一，否则，跳出循环。
				right--;
			}
			while (left < right && array[left] < pivot) {// 如果小于枢轴值，则下标加一，否则，跳出循环。
				left++;
			}
			swap(array, left, right);
		}
		
		if (left - start > 1) {
			quicksort(array, 0, left-1);
		}
		if (end - right > 1) {
			quicksort(array, right + 1, end);
		}
	}
	
	/**
	 * 交换两个数
	 */
	public static void swap(int[] array, int first, int second) {
		int temp = array[first];
		array[first] = array[second];
		array[second] = temp;
	}
	
	/**
	 * 在 第一个元素、中间一个元素、最后一个元素，这三个元素中，找出中间值作为基准点，并放到中间位置
	 */
	public static int getPivot(int[] array, int start, int end) {
		int middle = start + (int) (end - start) / 2;
		
		if (array[start] > array[end]) {
			swap(array, start, end);
		}
		
		if (array[middle] > array[end]) {
			swap(array, middle, end);
		}
		
		if (array[start] > array[middle]) {
			swap(array, start, middle);
		}
		
		return middle;
	}
}
