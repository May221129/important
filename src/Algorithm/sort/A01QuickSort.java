package Algorithm.sort;

/**
 * 排序之快速排序【该类中写的都是没有优化的版本。优化之后的在A02Quicksort】
 * 1. 实现：【博客：https://blog.csdn.net/shujuelin/article/details/82423852】
 * 	①找基准点：第一个元素；
 * 	②right：从数组的最后一个元素开始，从右往左，直到找到小于或等于基准点的元素；每次都要right比left先走；
 * 	③left：从基准点的后一个元素开始，从左往右，直到找到大于或等于基准点的元素；
 * 	④交换left和right所在位置的元素；
 * 	⑥left继续往右走，right继续往左走，直到两个人相遇；
 * 	⑦将相遇点所在位置的元素和基准点所在位置的元素做交换，基准点到了中间位置；
 * 	⑧【递归】将基准点左边的所有元素当成一个数组，重复①~⑦步骤；基准点右边的所有元素也是如此；
 * 
 * 2. 快排的效率为什么高：空间复杂度和时间复杂度；用概念来套冒泡和快排。
 * 	（1）一般情况下，算法中基本操作重复执行的次数是问题规模n的某个函数，用T(n)表示
 * 	（2）在计算算法复杂度时一般只用到大O符号
 * 	（3）在各种不同算法中，若算法中语句执行次数为一个常数，则时间复杂度为O(1)
 * 	（4）一个算法中的语句执行次数称为语句频度或时间频度。记为T(n)
 * 	（5）常见的算法时间复杂度由小到大依次为：Ο(1)＜Ο(log2n)＜Ο(n)＜Ο(nlog2n)＜Ο(n2)＜Ο(n3)＜…＜Ο(2n)＜Ο(n!)
 * 	（6）表示基本语句的执行次数是一个常数，一般来说，只要算法中不存在循环语句，其时间复杂度就是Ο(1)。
 * 		其中Ο(log2n)、Ο(n)、 Ο(nlog2n)、Ο(n2)和Ο(n3)称为多项式时间，而Ο(2n)和Ο(n!)称为指数时间。
 * 		计算机科学家普遍认为前者（即多项式时间复杂度的算法）是有效算法
 * 
 * 3. 快排的时间复杂度：O（n×log（n））
 * 	什么情况下，快排的时间复杂度会降低到"N的平方"：通过快排将一个从大到小的数组，排序为从小到大时，其效率为"N的平方"。
 * 	【博客：https://www.cnblogs.com/fengty90/p/3768827.html】
 */
public class A01QuickSort {
	public static void main(String[] args) {
		A01QuickSort quickSort = new A01QuickSort();
		// 测试快排的效率：
//		int number = 1000000;
//		int[] array = new int[number];
//		for (int i = 0; i < array.length; i++) {
//			array[i] = new Random().nextInt(number);
//		}
		
		//配合后面的元素输出，测试快排是否排序准确：
		int[] array = new int[] {181,181,187,181};
//		int[] array = new int[] {18,10};
		System.out.println("数组准备完毕~");

		long start = System.currentTimeMillis();
		quickSort.quickSort(array, 0, array.length - 1);
		long end = System.currentTimeMillis();
		System.out.println("quickSort 用时：" + (end - start));// 5万：11毫秒。50万：66毫秒。100万：136毫秒
		
		//遍历输出数组元素：
		quickSort.traverseArray(array);
	}
	
	/**
	 * 快排的实现
	 * @param target
	 * @param left
	 * @param right
	 */
	public void quickSort(int[] target, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = target[left];// 基准点
		int temp;
		int i = left;
		int j = right;//为什么要声明i和j，因为后面做迭代的时候还需要用到最初的left和right
		while (i < j) {//验证array数组至少有2个元素，才要做排序
			/**
			 * 提问：
			 * 	为什么是  while里的判断，为什么是 “target[j] >= pivot”，而不是“target[j] > pivot”？？？
			 * 	答: 数组[181,181,187,181]，分别用上面两种while去测试：
			 *		如果是">="时，因为 181 >= 181 成立，所以right就会从右往左移；
			 *		如果是">"时，因为 181 > 181 成立，所以right就不会左移。
			 * 	重点！！！right或left，必须有一方得是移动的！！！否则程序就会进入死循环！！！
			 */
			// 如果right一直都大于或等于pivot，则继续走，直到找到比key小的：
			while (target[j] >= pivot && i < j) {
				j--;
			}
			// 如果left一直都小于等于pivot，则继续走，直到找到比key大的：
			while (target[i] <= pivot && i < j) {
				i++;
			}
			// 此时right < key, left > key，将i和j做交换：
			if (i < j) {//这里做判断是为了right到了left位置时，不用再将执行下面这三行代码了：
				temp = target[i];
				target[i] = target[j];
				target[j] = temp;
			}
		}
		// left和right相遇了：
		// ①将相遇点的元素和key做交换：
		target[left] = target[j];
		target[j] = pivot;
		// ②基准点两边的元素的分别再做排序：
		quickSort(target, left, j - 1);
		quickSort(target, j + 1, right);
	}
	
	//遍历数组
	public void traverseArray(int[] array) {
		for(int element : array) {
			System.out.println(element);
		}
	}
}
