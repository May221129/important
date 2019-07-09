package Algorithm.sort;

/**
 * ����֮�������򡾸�����д�Ķ���û���Ż��İ汾���Ż�֮�����A02Quicksort��
 * 1. ʵ�֣������ͣ�https://blog.csdn.net/shujuelin/article/details/82423852��
 * 	���һ�׼�㣺��һ��Ԫ�أ�
 * 	��right������������һ��Ԫ�ؿ�ʼ����������ֱ���ҵ�С�ڻ���ڻ�׼���Ԫ�أ�ÿ�ζ�Ҫright��left���ߣ�
 * 	��left���ӻ�׼��ĺ�һ��Ԫ�ؿ�ʼ���������ң�ֱ���ҵ����ڻ���ڻ�׼���Ԫ�أ�
 * 	�ܽ���left��right����λ�õ�Ԫ�أ�
 * 	��left���������ߣ�right���������ߣ�ֱ��������������
 * 	�߽�����������λ�õ�Ԫ�غͻ�׼������λ�õ�Ԫ������������׼�㵽���м�λ�ã�
 * 	�ࡾ�ݹ顿����׼����ߵ�����Ԫ�ص���һ�����飬�ظ���~�߲��裻��׼���ұߵ�����Ԫ��Ҳ����ˣ�
 * 
 * 2. ���ŵ�Ч��Ϊʲô�ߣ��ռ临�ӶȺ�ʱ�临�Ӷȣ��ø�������ð�ݺͿ��š�
 * 	��1��һ������£��㷨�л��������ظ�ִ�еĴ����������ģn��ĳ����������T(n)��ʾ
 * 	��2���ڼ����㷨���Ӷ�ʱһ��ֻ�õ���O����
 * 	��3���ڸ��ֲ�ͬ�㷨�У����㷨�����ִ�д���Ϊһ����������ʱ�临�Ӷ�ΪO(1)
 * 	��4��һ���㷨�е����ִ�д�����Ϊ���Ƶ�Ȼ�ʱ��Ƶ�ȡ���ΪT(n)
 * 	��5���������㷨ʱ�临�Ӷ���С��������Ϊ����(1)����(log2n)����(n)����(nlog2n)����(n2)����(n3)��������(2n)����(n!)
 * 	��6����ʾ��������ִ�д�����һ��������һ����˵��ֻҪ�㷨�в�����ѭ����䣬��ʱ�临�ӶȾ��Ǧ�(1)��
 * 		���Ц�(log2n)����(n)�� ��(nlog2n)����(n2)�ͦ�(n3)��Ϊ����ʽʱ�䣬����(2n)�ͦ�(n!)��Ϊָ��ʱ�䡣
 * 		�������ѧ���ձ���Ϊǰ�ߣ�������ʽʱ�临�Ӷȵ��㷨������Ч�㷨
 * 
 * 3. ���ŵ�ʱ�临�Ӷȣ�O��n��log��n����
 * 	ʲô����£����ŵ�ʱ�临�ӶȻή�͵�"N��ƽ��"��ͨ�����Ž�һ���Ӵ�С�����飬����Ϊ��С����ʱ����Ч��Ϊ"N��ƽ��"��
 * 	�����ͣ�https://www.cnblogs.com/fengty90/p/3768827.html��
 */
public class A01QuickSort {
	public static void main(String[] args) {
		A01QuickSort quickSort = new A01QuickSort();
		// ���Կ��ŵ�Ч�ʣ�
//		int number = 1000000;
//		int[] array = new int[number];
//		for (int i = 0; i < array.length; i++) {
//			array[i] = new Random().nextInt(number);
//		}
		
		//��Ϻ����Ԫ����������Կ����Ƿ�����׼ȷ��
		int[] array = new int[] {181,181,187,181};
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
			// ���rightһֱ�����ڻ����pivot��������ߣ�ֱ���ҵ���keyС�ģ�
			while (target[j] >= pivot && i < j) {
				j--;
			}
			// ���leftһֱ��С�ڵ���pivot��������ߣ�ֱ���ҵ���key��ģ�
			while (target[i] <= pivot && i < j) {
				i++;
			}
			// ��ʱright < key, left > key����i��j��������
			if (i < j) {//�������ж���Ϊ��right����leftλ��ʱ�������ٽ�ִ�����������д����ˣ�
				temp = target[i];
				target[i] = target[j];
				target[j] = temp;
			}
		}
		// left��right�����ˣ�
		// �ٽ��������Ԫ�غ�key��������
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
