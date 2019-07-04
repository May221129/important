package sort;

import java.util.Random;

import org.junit.Test;

/**
 * 1.冒泡排序、选择排序、插入排序。
 * 2.冒泡排序和选择排序的效率对比。
 * 笔记见：G:\mystudy\studynotes\algorithm\sort.xmind
 */
public class A03ThreeTypesOfBaseSort {
//	========================== 冒泡排序和选择排序的效率对比 ============================
	public static void main(String[] args) {
		A03ThreeTypesOfBaseSort sort = new A03ThreeTypesOfBaseSort();
		
		// 测试百万级别的数组排序，看冒泡排序和选择排序的效率差别：
		int number = 50000;
		int[] array = new int[number];
		int[] array1 = new int[number];
		int[] array2 = new int[number];
		for(int i = 0; i < array.length; i++) {
			int temp = new Random().nextInt(number);
			array[i] = temp;
			array1[i] = temp;
			array2[i] = temp;
		}
		System.out.println("数组准备完毕~");
		
		long start1 = System.currentTimeMillis();
		sort.bubbleSort(array);
		long end1 = System.currentTimeMillis();
		System.out.println("bubbleSort 用时：" + (end1 - start1));//5万：4157。50万：430255。100万：1644079
		
		long start2 = System.currentTimeMillis();
		sort.selectionSort(array1);
		long end2 = System.currentTimeMillis();
		System.out.println("selectSort 用时：" + (end2 - start2));//5万：727。50万：74253。100万：281276 ==》选择排序比冒泡快了5.7倍。
		
		long start3 = System.currentTimeMillis();
		sort.insertionSort(array2);
		long end3 = System.currentTimeMillis();
		System.out.println("insertionSort 用时：" + (end3 - start3));//5万：827。50万：84644。 ==》插入排序比选择排序稍慢一点。
	}
	
//	========================== BubbleSort ============================
	/**
	 * 冒泡排序：相邻的两个数进行比较，较大的那个数放后，每次都要交换。
	 * 1.数组是个特殊对象；
	 * 2.数组的声明：
	 * 	① int[] array = new int[3];
	 * 	  array[0] = 1;
	 * 	  array[1] = 2;
	 * 	  array[2] = 3;
	 * 	② int[] array = new int[]{1,2,3};
	 * 3.冒泡排序是否需要返回值：不需要；
	 * 4.数组的遍历：
	 * 5.冒泡排序的优化思路：
	 * 	① 判空；
	 * 	② 元素的个数很特殊时：个数为0、1时；
	 * 	③ 数组已经是有序的，或者是数组里所有元素都相同：
	 * 		--> boolean初始值为true？还是false？
	 * 		--> boolean放在哪儿进行判断？
	 * 		--> 哪里需要改变boolean的值？
	 * 	④ 比较的次数；
	 * 6.普通for循环和增强型for循环的区别：
	 * 7.什么时候对象可能为null：
	 * 	① 声明了，但没指向任何堆空间的对象实体。
	 * 	② 声明了，也指向了堆空间的实体，但后面又将null赋值给它了：String str = "abc"; str = null;
	 */
	@Test
	public void testBubbleSort(){
//		int[] target = new int[]{3,1,7,2,4,3};
		int[] target = new int[]{4};
//		int[] target = new int[]{5,4,3,2,1};
//		int[] target = new int[]{1,4,1,1,3,1};
//		int[] target = null;
		bubbleSort(target);
		for(int i = 0; i < target.length; i++){
			System.out.println(target[i]);
		}
		
		System.out.println("用增强型for循环再输出一次：");
		for(int i : target){
			System.out.println(i);
		}
	}
	
	public void bubbleSort(int[] target){
		if(target == null){
			return;
		}
		boolean tag = true;//如果数组是有序的，则不需要再进行交换。
		for(int i = 0; i < target.length -1 && tag; i++){//外层循环：控制比较的组数：（元素个数-1）次。
			tag = false;
			for(int j = 0; j < target.length - i - 1; j++){//内层循环：控制每组比较的次数：每组比较都从第一个元素开始比较，把每次比较时的max移到后面去，每组的比较次数=target.length-1-i。
				if(target[j] > target[j + 1]){
					int temp = target[j];
					target[j] = target[j + 1];
					target[j + 1] = temp;
					tag = true;
				}
			}
		}
	}
	
//	======================= SelectionSort ============================
	
	@Test
	public void testSelectionSort(){
//		int[] target = new int[]{5,4,3,2,1};
		int[] target = new int[]{6,3,7,2,9};
		selectionSort(target);
		for(int element : target){
			System.out.println(element);
		}
	}
	/**
	 * 选择排序：
	 * 第一趟从n个元素的数据序列中选出关键字最小/大的元素并放在最前位置，
	 * 下一趟从n-1个元素中选出最小/大的元素并放在未排好序元素的最前位置。以此类推，经过n-1趟完成排序。
	 */
	public void selectionSort(int[] target){
		if(target == null){
			return;
		}
		for(int i = 0; i < target.length - 1; i++){
			int tempIndex = i;
			for(int j = i + 1; j < target.length; j++){
				if(target[tempIndex] > target[j]){
					tempIndex = j;
				}
			}
			int temp = target[tempIndex];
			target[tempIndex] = target[i];
			target[i] = temp;
		}
	}
	
//	===================== insertSort ====================================
	
	@Test
	public void testInsertSort(){
//		int[] target = new int[]{5,4,3,2,1};
		int[] target = new int[]{3,3,3,3,1};
//		insertionSort(target);
		for(int element : target){
			System.out.println(element);
		}
	}
	/**
	 * 插入排序：将一个数据插入到已经排好序的有序数据中（一般默认第一个元素是有序的，比较从第二个元素开始），
	 * 从而得到一个新的、个数加一的有序数据，算法适用于少量数据的排序。
	 */
	public void insertionSort(int[] target){
		if(target == null){
			return;
		}
		//外层循环控制比较的组数；
		for(int i = 0; i < target.length - 1; i++){
			//两层循环：外层控制比较的组数；内层负责找出该组比较中，在已排好序数组之后的第一个无序的元素，并通过比较将这个无序元素插入到有序数组中。
			for(int j = i + 1; j > 0; j--){
				if(target[j - 1] > target[j]){
					int temp = target[j];
					target[j] = target[j - 1];
					target[j - 1] = temp;
				}else{
					break;
				}
			}
		}
	}
}
