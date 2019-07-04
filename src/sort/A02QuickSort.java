package sort;

/**
 * �����Ż�
 * 
 * һ��ΪʲôҪ�Կ��Ž����Ż���
 * 	A01Quicksort�еĿ����㷨����˵��������Ŀ���������Ϊ����û�п����κ��������ݡ�
 * 	���ǣ����Ǻ����׷�������㷨��ȱ�ݣ�������������������ݻ�������������ȫ�����ʱ��
 * 	���㷨�˻�Ϊð�����򣬲�����O(n�Sn)(N*logN,�����ܱ���ΪN*log2 N����Ϊ�ܶ�ʱ�򣬿��Ŷ����ܴﵽ�����ÿ��ƽ����Ϊ2��)������O(n^2)�ˡ�
 * 
 * ������ô�����Ż���
 * 	1.�Ż�ѡȡ���᣺
 * 		����ȡ�У���ȡ����Ԫ���Ƚ������򣬽��м�����Ϊ���ᣬ һ����ȡ��ˡ��Ҷ˺��м��������� Ҳ�������ѡȡ�� 
 *		���ڷǳ���Ĵ������������˵���ǲ����Ա�֤�ܹ�ѡ���һ���õ�pivot�� ��˻��и��취����ν�ľ���ȡ�У�
 *		�ȴ������з�����ȡ����ÿ��ȡ��������������Ʒ��ȡ��������Ȼ�������������������ȡ��һ��������Ϊ���� ��
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
	 * ѡ��һ���ؼ��֣��뾡�취�����ŵ�һ��λ�ã�ʹ������ߵ�ֵ������С���ұߵ�ֵ�����������ǳ�����ؼ��ֽ����ᡣ
	 */
	public static void quicksort(int[] array, int start, int end) {
		if (array == null || start < 0 || end >= array.length) {
			return;
		}
		
		int middle = getPivot(array, start, end);// ѡȡ����ֵ
		int pivot = array[middle];
		
		//��Ϊ��getPivot()�У����Ѿ���start��middle��end������ֵ���й������ˣ��������ﲻ���ظ�����
		int left = start + 1, right = end - 1;
		
		// �ӱ���������м�ɨ��
		while (left < right){
			while (left < right && array[right] >= pivot) {// �����������ֵ�����±��һ����������ѭ����
				right--;
			}
			while (left < right && array[left] < pivot) {// ���С������ֵ�����±��һ����������ѭ����
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
	 * ����������
	 */
	public static void swap(int[] array, int first, int second) {
		int temp = array[first];
		array[first] = array[second];
		array[second] = temp;
	}
	
	/**
	 * �� ��һ��Ԫ�ء��м�һ��Ԫ�ء����һ��Ԫ�أ�������Ԫ���У��ҳ��м�ֵ��Ϊ��׼�㣬���ŵ��м�λ��
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
