package sort;

import java.util.Random;

import org.junit.Test;

/**
 * 1.ð������ѡ�����򡢲�������
 * 2.ð�������ѡ�������Ч�ʶԱȡ�
 * �ʼǼ���G:\mystudy\studynotes\algorithm\sort.xmind
 */
public class A03ThreeTypesOfBaseSort {
//	========================== ð�������ѡ�������Ч�ʶԱ� ============================
	public static void main(String[] args) {
		A03ThreeTypesOfBaseSort sort = new A03ThreeTypesOfBaseSort();
		
		// ���԰��򼶱���������򣬿�ð�������ѡ�������Ч�ʲ��
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
		System.out.println("����׼�����~");
		
		long start1 = System.currentTimeMillis();
		sort.bubbleSort(array);
		long end1 = System.currentTimeMillis();
		System.out.println("bubbleSort ��ʱ��" + (end1 - start1));//5��4157��50��430255��100��1644079
		
		long start2 = System.currentTimeMillis();
		sort.selectionSort(array1);
		long end2 = System.currentTimeMillis();
		System.out.println("selectSort ��ʱ��" + (end2 - start2));//5��727��50��74253��100��281276 ==��ѡ�������ð�ݿ���5.7����
		
		long start3 = System.currentTimeMillis();
		sort.insertionSort(array2);
		long end3 = System.currentTimeMillis();
		System.out.println("insertionSort ��ʱ��" + (end3 - start3));//5��827��50��84644�� ==�����������ѡ����������һ�㡣
	}
	
//	========================== BubbleSort ============================
	/**
	 * ð���������ڵ����������бȽϣ��ϴ���Ǹ����ź�ÿ�ζ�Ҫ������
	 * 1.�����Ǹ��������
	 * 2.�����������
	 * 	�� int[] array = new int[3];
	 * 	  array[0] = 1;
	 * 	  array[1] = 2;
	 * 	  array[2] = 3;
	 * 	�� int[] array = new int[]{1,2,3};
	 * 3.ð�������Ƿ���Ҫ����ֵ������Ҫ��
	 * 4.����ı�����
	 * 5.ð��������Ż�˼·��
	 * 	�� �пգ�
	 * 	�� Ԫ�صĸ���������ʱ������Ϊ0��1ʱ��
	 * 	�� �����Ѿ�������ģ�����������������Ԫ�ض���ͬ��
	 * 		--> boolean��ʼֵΪtrue������false��
	 * 		--> boolean�����Ķ������жϣ�
	 * 		--> ������Ҫ�ı�boolean��ֵ��
	 * 	�� �ȽϵĴ�����
	 * 6.��ͨforѭ������ǿ��forѭ��������
	 * 7.ʲôʱ��������Ϊnull��
	 * 	�� �����ˣ���ûָ���κζѿռ�Ķ���ʵ�塣
	 * 	�� �����ˣ�Ҳָ���˶ѿռ��ʵ�壬�������ֽ�null��ֵ�����ˣ�String str = "abc"; str = null;
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
		
		System.out.println("����ǿ��forѭ�������һ�Σ�");
		for(int i : target){
			System.out.println(i);
		}
	}
	
	public void bubbleSort(int[] target){
		if(target == null){
			return;
		}
		boolean tag = true;//�������������ģ�����Ҫ�ٽ��н�����
		for(int i = 0; i < target.length -1 && tag; i++){//���ѭ�������ƱȽϵ���������Ԫ�ظ���-1���Ρ�
			tag = false;
			for(int j = 0; j < target.length - i - 1; j++){//�ڲ�ѭ��������ÿ��ȽϵĴ�����ÿ��Ƚ϶��ӵ�һ��Ԫ�ؿ�ʼ�Ƚϣ���ÿ�αȽ�ʱ��max�Ƶ�����ȥ��ÿ��ıȽϴ���=target.length-1-i��
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
	 * ѡ������
	 * ��һ�˴�n��Ԫ�ص�����������ѡ���ؼ�����С/���Ԫ�ز�������ǰλ�ã�
	 * ��һ�˴�n-1��Ԫ����ѡ����С/���Ԫ�ز�����δ�ź���Ԫ�ص���ǰλ�á��Դ����ƣ�����n-1���������
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
	 * �������򣺽�һ�����ݲ��뵽�Ѿ��ź�������������У�һ��Ĭ�ϵ�һ��Ԫ��������ģ��Ƚϴӵڶ���Ԫ�ؿ�ʼ����
	 * �Ӷ��õ�һ���µġ�������һ���������ݣ��㷨�������������ݵ�����
	 */
	public void insertionSort(int[] target){
		if(target == null){
			return;
		}
		//���ѭ�����ƱȽϵ�������
		for(int i = 0; i < target.length - 1; i++){
			//����ѭ���������ƱȽϵ��������ڲ㸺���ҳ�����Ƚ��У������ź�������֮��ĵ�һ�������Ԫ�أ���ͨ���ȽϽ��������Ԫ�ز��뵽���������С�
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
