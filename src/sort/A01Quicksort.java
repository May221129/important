package sort;


/**
 * 排序之快速排序【该类中写的都是没有优化的版本。优化之后的在A02Quicksort】
 * 两种不同的实现方式：
 * 1.先 从右往左找比基准点小的数，一旦找到，就先将这个数放到第0个位置了。https://www.cnblogs.com/coderising/p/5708801.html
 * 2.先 从右往左找比基准点小的数A，找到之后先不动这个数；再 从左往右找比基准点大的数B，找到了之后，再将A和B这两个数进行交换；等最后left和right碰到一起来，再和基准点换。
 * 	http://developer.51cto.com/art/201403/430986.htm
 * 
 * 快排的效率为什么高：空间复杂度和时间复杂度；用概念来套冒泡和快排。
 * 	1. 一般情况下，算法中基本操作重复执行的次数是问题规模n的某个函数，用T(n)表示
 * 	2.在计算算法复杂度时一般只用到大O符号
 * 	3.在各种不同算法中，若算法中语句执行次数为一个常数，则时间复杂度为O(1)
 * 	4.一个算法中的语句执行次数称为语句频度或时间频度。记为T(n)
 * 	5. 常见的算法时间复杂度由小到大依次为：Ο(1)＜Ο(log2n)＜Ο(n)＜Ο(nlog2n)＜Ο(n2)＜Ο(n3)＜…＜Ο(2n)＜Ο(n!)
 * 	6.Ο(1)表示基本语句的执行次数是一个常数，一般来说，只要算法中不存在循环语句，其时间复杂度就是Ο(1)。
 * 		其中Ο(log2n)、Ο(n)、 Ο(nlog2n)、Ο(n2)和Ο(n3)称为多项式时间，而Ο(2n)和Ο(n!)称为指数时间。
 * 		计算机科学家普遍认为前者（即多项式时间复杂度的算法）是有效算法
 * 
 * 快排的时间复杂度：O（n×log（n））
 * 什么情况下，快排的时间复杂度会降低到"N的平方"：通过快排将一个从大到小的数组，排序为从小到大时，其效率为"N的平方"。
 * https://www.cnblogs.com/fengty90/p/3768827.html
 */
public class A01Quicksort {
	
	// 主方法
	public static void main(String[] args) {
		
//		int[] heights = { 181, 187, 181};
		int[] heights = { 181, 181, 187, 181};
//		int[] heights = { 189,188,187,186};
		
		// =====网上找的方法：使用快速排序法对表示8名运动员身高的数组heights进行从低到高的排序=====
//		quickSort(heights, 0, heights.length - 1);
//		for (int height : heights) {
//			System.out.println(height);
//		}
		
		System.out.println("-------------------------------------------------");
		
		// =====自己写的方法=====
//		quicksort(heights, 0, heights.length - 1);
//		for (int height : heights) {
//			System.out.println(height);
//		}
		
		quicksort2(heights, 0, heights.length-1);
		for (int height : heights) {
			System.out.println(height);
		}
	}
	
	/**
	 * 先 从右往左找比基准点小的数，一旦找到，就先将这个数放到第0个位置了.
	 * 这个是网上找到的，而非自己写的。
	 */
	public static final void quickSort(int[] array, int start, int end) {
		// i相当于助手1的位置，j相当于助手2的位置
		int i = start, j = end;
		int pivot = array[i]; // 取第1个元素为基准元素
		int temp =  i; // 用于交换i和j位置的元素
		// 如果需要排序的元素个数不止1个，就进入快速排序(只要i和j不同，就表明至少有2个数组元素需要排序)
		while (i < j) {
			// 助手2开始从右向左一个个地查找小于基准元素的元素
			while (i < j && pivot <= array[j]){
				j--;
			}
			if (i < j) {//没有这层判断，则会陷入死循环
				/**
				 * 如果助手2在遇到助手1之前就找到了对应的元素，就将该元素给助手1的"空位"，j成了空位
				 * 用j位置上的值，覆盖temp位置上的值；把下标j作为temp，即此时j位置被闲置出来了。
				 * 这里是“从右往左找比基准点小的数”，一旦找到就用这个数去覆盖temp位置上的数。而不是等“从左往右找比基准点大的数”再来将这两个数进行交换。
				 */
				array[temp] = array[j];
				temp = j;
			}

			// 助手1开始从左向右一个个地查找大于基准元素的元素
			while (i < j && array[i] <= pivot)
				i++;
			if (i < j) {
				// 如果助手1在遇到助手2之前就找到了对应的元素，就将该元素给助手2的"空位"，i成了空位
//				array[temp] = array[temp = i];//这样的写法很容易出歧义，不能这么写！！！
				array[temp] = array[i];
				temp = i;
			}
		}
		// 助手1和助手2相遇后会停止循环，将最初取出的基准值给最后的空位
		array[temp] = pivot;

		// =====本轮快速排序完成=====

		// 如果分割点i左侧有2个以上的元素，递归调用继续对其进行快速排序
		if (i - start > 1) {
			quickSort(array, 0, i - 1);
		}
		// 如果分割点j右侧有2个以上的元素，递归调用继续对其进行快速排序
		if (end - j > 1) {
			quickSort(array, j + 1, end);
		}
	}
	
	/**
	 * 先 从右往左找比基准点小的数，一旦找到，就先将这个数放到第0个位置了。
	 * 这个是自己写的方法。
	 */
	public static void quicksort(int[] array, int start, int end){//left=0, right=array.length-1
		
		//为什么这里要将start和end分别赋值给left和right，因为后面做迭代的时候还需要用到最初的start和end
		int left = start;
		int right = end;
		
		int pivot = array[start];//基准点
		int temp = start;//交换left和right时需要用到的暂存数
		while(left < right){//验证array数组至少有2个元素，才要做排序
			
			/**
			 * right从右往左，找到比pivot小的数就交换；如果right的值大于pivot，就将right继续左移：
			 * 提问：
			 * 	1.为什么是  while(array[right] >= pivot && left < right)
			 * 		而不是 while(array[right] > pivot && left < right) 
			 * 	答: 数组[181,181,187,181]，分别用上面两种while去测试：
			 * 		如果是">="时，因为 181 >= 181 成立，所以right就会从右往左移；
			 * 		如果是">"时，因为 181 > 181 成立，所以right就不会左移。
			 * 	2.这里的 while(array[right] > pivot && left < right)和下面的 while(array[left] < pivot && left < right)什么关系？
			 * 	答：依旧以 数组[181,181,187,181] 为例：
			 * 		while(left < right)成立，所以程序继续执行{}中的内容；
			 * 			执行到 while(array[right] > pivot && left < right)时，因为 181 > 181 成立，所以right是不会左移的。
			 * 			执行到  while(array[left] < pivot && left < right)时，因为 181 < 181 不成立，所以left是不会右移的。
			 * 		程序走出{}，执行完第一个while。
			 * 		while(left < right)成立，所以程序继续执行{}中的内容；
			 * 		就这样，程序进入了死循环！
			 * 	结论：如果 while(array[right] > pivot && left < right)，
			 * 		那么就得搭配  while(array[left] <= pivot && left < right)。
			 * 		如果  while(array[right] >= pivot && left < right)，
			 * 		那么  while(array[left] < pivot && left < right) 即可。
			 * 		重点！！！right或left，必须有一方得是移动的！！！否则程序就会进入死循环！！！
			 */
			while(array[right] >= pivot && left < right){
				right--;
			}
			if(left < right){//这里的判断是为了right到了left位置时(此时left位置起始也是temp位置)，不用再将执行下面这两行代码了：
				array[temp] = array[right];
				temp = right;
			}
			
			/**
			 * left从左往右，找到比pivot大的数，如果left的值小于pivot，就将left右移一位：
			 */
			while(array[left] < pivot && left < right){
				left++;
			}
			if(left < right){//这里的判断是为了当left和right到了同一个位置时，不用再执行下面这两行代码了：
				array[temp] = array[left];
				temp = left;
			}
		}
		//最后，把基准点放到最后那个被空出来的temp位置上：
		array[temp] = pivot;
		
		if (left - start > 1) {
			quicksort(array, 0, left-1);//这里为什么是left-1?因为如果不是用"left-1"进行排序，就会将完成了上一次排序后的那个基准点也纳入到这次排序中
		}
		// 如果分割点右侧有2个以上的元素，递归调用继续对其进行快速排序
		if (end - right > 1) {
			quicksort(array, right + 1, end);//这里为什么是right+1？理由同"left-1"
		}
	}
	
	/**
	 * 从右往左找，找到比基准点小的数；再从左往右找，找到比基准点大的数；然后再做交换
	 */
	public static void quicksort2(int[] array, int start, int end){
		int pivot = array[start];
		int left = start, right = end;
		int temp;
		while(left < right){
			while(array[right] >= pivot && left < right){
				right--;
			}
			while(array[left] < pivot && left < right){
				left++;
			}
			if(left < right){
				temp = array[right];
				array[right] = array[left];
				array[left] = temp;
			}
		}
		//将"基准点"和最后"left和right共同所在位置的那个数"进行交换：
		array[start] = array[left];
		array[left] = pivot;
		
		if (left - start > 1) {
			quicksort(array, 0, left-1);
		}
		if (end - right > 1) {
			quicksort(array, right + 1, end);
		}
	}
}
