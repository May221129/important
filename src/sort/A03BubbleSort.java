package sort;

import org.junit.Test;

/**
 * ð������ѡ�����򡢲�������
 */
public class A03BubbleSort {
	
//	========================== BubbleSort ============================
	/**
	 * 1.�����Ǹ��������
	 * 2.�����������
	 * 	�� int[] array = new int[3];
	 * 	  array[0] = 1;
	 * 	  array[1] = 2;
	 * 	  array[2] = 3;
	 * 	�� int[] array = new int[]{1,2,3};
	 * 3.ð�������Ƿ���Ҫ����ֵ������Ҫ����Ϊ
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
		boolean tag = true;//�������������ģ�����Ҫ�ٽ��н�����
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
	 * ѡ������
	 * ��һ�˴�n��Ԫ�ص�����������ѡ���ؼ�����С/���Ԫ�ز�������ǰ/��λ�ã�
	 * ��һ�˴�n-1��Ԫ����ѡ����С/���Ԫ�ز�������ǰ/��λ�á��Դ����ƣ�����n-1���������
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
	 * ��������
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
