package Algorithm.sort;

/**
 * �����ʱ�临�Ӷȷ���
 * 
 * 1. ���ŵ�ʱ�临�Ӷȣ�
 * 		һ����ԣ�O(n * log(n))
 * 		����ʱ��O(n * log2(n))
 * 		���ʱ��O(n^2)
 * 
 * 2. ���ŵ�ʱ�临�Ӷ�����ô��������ģ�
 * 		
 * 3. ��ʲô����¿��ŵ�ʱ�临�Ӷ��ܴﵽ���ţ�ʲô����¿��������ʱ�临�ӶȻή����
 * 		���ţ�����
 * 		��ͨ�����Ž�һ���Ӵ�С�����飬����Ϊ��С����ʱ����ʱ�临�Ӷ�ΪO(n^2)��
 * 
 * 4. ���ŵ�Ч��Ϊʲô�ߣ��ռ临�ӶȺ�ʱ�临�Ӷȣ��ø�������ð�ݺͿ��š�
 * 	��1��һ������£��㷨�л��������ظ�ִ�еĴ����������ģn��ĳ����������T(n)��ʾ
 * 	��2���ڼ����㷨���Ӷ�ʱһ��ֻ�õ���O����
 * 	��3���ڸ��ֲ�ͬ�㷨�У����㷨�����ִ�д���Ϊһ����������ʱ�临�Ӷ�ΪO(1)
 * 	��4��һ���㷨�е����ִ�д�����Ϊ���Ƶ�Ȼ�ʱ��Ƶ�ȡ���ΪT(n)
 * 	��5���������㷨ʱ�临�Ӷ���С��������Ϊ����(1)����(log2n)����(n)����(nlog2n)����(n2)����(n3)��������(2n)����(n!)
 * 	��6����ʾ��������ִ�д�����һ��������һ����˵��ֻҪ�㷨�в�����ѭ����䣬��ʱ�临�ӶȾ��Ǧ�(1)��
 * 		���Ц�(log2n)����(n)�� ��(nlog2n)����(n2)�ͦ�(n3)��Ϊ����ʽʱ�䣬����(2n)�ͦ�(n!)��Ϊָ��ʱ�䡣
 * 		�������ѧ���ձ���Ϊǰ�ߣ�������ʽʱ�临�Ӷȵ��㷨������Ч�㷨
 * 
 * 	�����ͣ�https://www.cnblogs.com/fengty90/p/3768827.html��
 */
public class A01QuickSort3_TimeComplexity {
	public static void main(String[] args) {
		A01QuickSort3_TimeComplexity quickSort = new A01QuickSort3_TimeComplexity();
		// ���Կ��ŵ�Ч�ʣ�
//		int number = 1000000;
//		int[] array = new int[number];
//		for (int i = 0; i < array.length; i++) {
//			array[i] = new Random().nextInt(number);
//		}
		
		//��Ϻ����Ԫ����������Կ����Ƿ�����׼ȷ��
		int[] array = new int[] {9,8,7,6,5,4,3,2,1};
//		int[] array = new int[] {18,10};
		System.out.println("����׼�����~");

		long start = System.currentTimeMillis();
		quickSort.quickSort(array, 0, array.length - 1);
		long end = System.currentTimeMillis();
		System.out.println("quickSort ��ʱ��" + (end - start));// 5��11���롣50��66���롣100��136����
		
		//�����������Ԫ�أ�
		quickSort.traverseArray(array);
	}
	
	/**
	 * ���ŵ�ʵ��
	 * @param target
	 * @param left
	 * @param right
	 */
	public void quickSort(int[] target, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = target[left];// ��׼��
		int temp;
		int i = left;
		int j = right;//ΪʲôҪ����i��j����Ϊ������������ʱ����Ҫ�õ������left��right
		while (i < j) {//��֤array����������2��Ԫ�أ���Ҫ������
			/**
			 * ���ʣ�
			 * 	Ϊʲô��  while����жϣ�Ϊʲô�� ��target[j] >= pivot���������ǡ�target[j] > pivot��������
			 * 	��: ����[181,181,187,181]���ֱ�����������whileȥ���ԣ�
			 *		�����">="ʱ����Ϊ 181 >= 181 ����������right�ͻ���������ƣ�
			 *		�����">"ʱ����Ϊ 181 > 181 ����������right�Ͳ������ơ�
			 * 	�ص㣡����right��left��������һ�������ƶ��ģ������������ͻ������ѭ��������
			 */
			// ���rightһֱ�����ڻ����pivot��������ߣ�ֱ���ҵ���pivotС�ģ�
			while (target[j] >= pivot && i < j) {
				j--;
			}
			// ���leftһֱ��С�ڵ���pivot��������ߣ�ֱ���ҵ���pivot��ģ�
			while (target[i] <= pivot && i < j) {
				i++;
			}
			// ��ʱright < pivot, left > pivot����i��j��������
			if (i < j) {//�������ж���Ϊ��right����leftλ��ʱ�������ٽ�ִ�����������д����ˣ�
				temp = target[i];
				target[i] = target[j];
				target[j] = temp;
			}
		}
		// left��right�����ˣ�
		// �ٽ��������Ԫ�غ�pivot��������
		target[left] = target[j];
		target[j] = pivot;
		// �ڻ�׼�����ߵ�Ԫ�صķֱ���������
		quickSort(target, left, j - 1);
		quickSort(target, j + 1, right);
	}
	
	//��������
	public void traverseArray(int[] array) {
		for(int element : array) {
			System.out.println(element);
		}
	}
}
