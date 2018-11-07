package sort;

import org.junit.Test;

/**
 * 冒泡排序、选择排序、插入排序。
 */
public class A03BubbleSort {
	
//	========================== BubbleSort ============================
	/**
	 * 1.数组是个特殊对象；
	 * 2.数组的声明：
	 * 	① int[] array = new int[3];
	 * 	  array[0] = 1;
	 * 	  array[1] = 2;
	 * 	  array[2] = 3;
	 * 	② int[] array = new int[]{1,2,3};
	 * 3.冒泡排序是否需要返回值：不需要，因为
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
//		int[] target = new int[]{4,3,5,6,2};
		int[] target = new int[]{5,4,3,2,1};
		bubbleSort(target);
//		for(int i = 0; i < target.length; i++){
//			System.out.println(target[i]);
//		}
		for(int i : target){
			System.out.println(i);
		}
	}
	
	public void bubbleSort(int[] target){
		if(target == null){
			return;
		}
		boolean tag = true;//如果数组是有序的，则不需要再进行交换。
		for(int i = 0; i < target.length -1 && tag; i++){
			tag = false;
			for(int j = 0; j < target.length - i - 1; j++){
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
	 * 第一趟从n个元素的数据序列中选出关键字最小/大的元素并放在最前/后位置，
	 * 下一趟从n-1个元素中选出最小/大的元素并放在最前/后位置。以此类推，经过n-1趟完成排序。
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
		insertSort(target);
		for(int element : target){
			System.out.println(element);
		}
	}
	/**
	 * 插入排序
	 */
	public void insertSort(int[] target){
		if(target == null){
			return;
		}
		for(int i = 0; i < target.length - 1; i++){
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
