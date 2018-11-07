package sort;


/**
 * ����֮�������򡾸�����д�Ķ���û���Ż��İ汾���Ż�֮�����A02Quicksort��
 * ���ֲ�ͬ��ʵ�ַ�ʽ��
 * 1.�� ���������ұȻ�׼��С������һ���ҵ������Ƚ�������ŵ���0��λ���ˡ�https://www.cnblogs.com/coderising/p/5708801.html
 * 2.�� ���������ұȻ�׼��С����A���ҵ�֮���Ȳ������������ ���������ұȻ�׼������B���ҵ���֮���ٽ�A��B�����������н����������left��right����һ�������ٺͻ�׼�㻻��
 * 	http://developer.51cto.com/art/201403/430986.htm
 * 
 * ���ŵ�Ч��Ϊʲô�ߣ��ռ临�ӶȺ�ʱ�临�Ӷȣ��ø�������ð�ݺͿ��š�
 * 	1. һ������£��㷨�л��������ظ�ִ�еĴ����������ģn��ĳ����������T(n)��ʾ
 * 	2.�ڼ����㷨���Ӷ�ʱһ��ֻ�õ���O����
 * 	3.�ڸ��ֲ�ͬ�㷨�У����㷨�����ִ�д���Ϊһ����������ʱ�临�Ӷ�ΪO(1)
 * 	4.һ���㷨�е����ִ�д�����Ϊ���Ƶ�Ȼ�ʱ��Ƶ�ȡ���ΪT(n)
 * 	5. �������㷨ʱ�临�Ӷ���С��������Ϊ����(1)����(log2n)����(n)����(nlog2n)����(n2)����(n3)��������(2n)����(n!)
 * 	6.��(1)��ʾ��������ִ�д�����һ��������һ����˵��ֻҪ�㷨�в�����ѭ����䣬��ʱ�临�ӶȾ��Ǧ�(1)��
 * 		���Ц�(log2n)����(n)�� ��(nlog2n)����(n2)�ͦ�(n3)��Ϊ����ʽʱ�䣬����(2n)�ͦ�(n!)��Ϊָ��ʱ�䡣
 * 		�������ѧ���ձ���Ϊǰ�ߣ�������ʽʱ�临�Ӷȵ��㷨������Ч�㷨
 * 
 * ���ŵ�ʱ�临�Ӷȣ�O��n��log��n����
 * ʲô����£����ŵ�ʱ�临�ӶȻή�͵�"N��ƽ��"��ͨ�����Ž�һ���Ӵ�С�����飬����Ϊ��С����ʱ����Ч��Ϊ"N��ƽ��"��
 * https://www.cnblogs.com/fengty90/p/3768827.html
 */
public class A01Quicksort {
	
	// ������
	public static void main(String[] args) {
		
//		int[] heights = { 181, 187, 181};
		int[] heights = { 181, 181, 187, 181};
//		int[] heights = { 189,188,187,186};
		
		// =====�����ҵķ�����ʹ�ÿ������򷨶Ա�ʾ8���˶�Ա��ߵ�����heights���дӵ͵��ߵ�����=====
//		quickSort(heights, 0, heights.length - 1);
//		for (int height : heights) {
//			System.out.println(height);
//		}
		
		System.out.println("-------------------------------------------------");
		
		// =====�Լ�д�ķ���=====
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
	 * �� ���������ұȻ�׼��С������һ���ҵ������Ƚ�������ŵ���0��λ����.
	 * ����������ҵ��ģ������Լ�д�ġ�
	 */
	public static final void quickSort(int[] array, int start, int end) {
		// i�൱������1��λ�ã�j�൱������2��λ��
		int i = start, j = end;
		int pivot = array[i]; // ȡ��1��Ԫ��Ϊ��׼Ԫ��
		int temp =  i; // ���ڽ���i��jλ�õ�Ԫ��
		// �����Ҫ�����Ԫ�ظ�����ֹ1�����ͽ����������(ֻҪi��j��ͬ���ͱ���������2������Ԫ����Ҫ����)
		while (i < j) {
			// ����2��ʼ��������һ�����ز���С�ڻ�׼Ԫ�ص�Ԫ��
			while (i < j && pivot <= array[j]){
				j--;
			}
			if (i < j) {//û������жϣ����������ѭ��
				/**
				 * �������2����������1֮ǰ���ҵ��˶�Ӧ��Ԫ�أ��ͽ���Ԫ�ظ�����1��"��λ"��j���˿�λ
				 * ��jλ���ϵ�ֵ������tempλ���ϵ�ֵ�����±�j��Ϊtemp������ʱjλ�ñ����ó����ˡ�
				 * �����ǡ����������ұȻ�׼��С��������һ���ҵ����������ȥ����tempλ���ϵ����������ǵȡ����������ұȻ�׼�������������������������н�����
				 */
				array[temp] = array[j];
				temp = j;
			}

			// ����1��ʼ��������һ�����ز��Ҵ��ڻ�׼Ԫ�ص�Ԫ��
			while (i < j && array[i] <= pivot)
				i++;
			if (i < j) {
				// �������1����������2֮ǰ���ҵ��˶�Ӧ��Ԫ�أ��ͽ���Ԫ�ظ�����2��"��λ"��i���˿�λ
//				array[temp] = array[temp = i];//������д�������׳����壬������ôд������
				array[temp] = array[i];
				temp = i;
			}
		}
		// ����1������2�������ֹͣѭ���������ȡ���Ļ�׼ֵ�����Ŀ�λ
		array[temp] = pivot;

		// =====���ֿ����������=====

		// ����ָ��i�����2�����ϵ�Ԫ�أ��ݹ���ü���������п�������
		if (i - start > 1) {
			quickSort(array, 0, i - 1);
		}
		// ����ָ��j�Ҳ���2�����ϵ�Ԫ�أ��ݹ���ü���������п�������
		if (end - j > 1) {
			quickSort(array, j + 1, end);
		}
	}
	
	/**
	 * �� ���������ұȻ�׼��С������һ���ҵ������Ƚ�������ŵ���0��λ���ˡ�
	 * ������Լ�д�ķ�����
	 */
	public static void quicksort(int[] array, int start, int end){//left=0, right=array.length-1
		
		//Ϊʲô����Ҫ��start��end�ֱ�ֵ��left��right����Ϊ������������ʱ����Ҫ�õ������start��end
		int left = start;
		int right = end;
		
		int pivot = array[start];//��׼��
		int temp = start;//����left��rightʱ��Ҫ�õ����ݴ���
		while(left < right){//��֤array����������2��Ԫ�أ���Ҫ������
			
			/**
			 * right���������ҵ���pivotС�����ͽ��������right��ֵ����pivot���ͽ�right�������ƣ�
			 * ���ʣ�
			 * 	1.Ϊʲô��  while(array[right] >= pivot && left < right)
			 * 		������ while(array[right] > pivot && left < right) 
			 * 	��: ����[181,181,187,181]���ֱ�����������whileȥ���ԣ�
			 * 		�����">="ʱ����Ϊ 181 >= 181 ����������right�ͻ���������ƣ�
			 * 		�����">"ʱ����Ϊ 181 > 181 ����������right�Ͳ������ơ�
			 * 	2.����� while(array[right] > pivot && left < right)������� while(array[left] < pivot && left < right)ʲô��ϵ��
			 * 	�������� ����[181,181,187,181] Ϊ����
			 * 		while(left < right)���������Գ������ִ��{}�е����ݣ�
			 * 			ִ�е� while(array[right] > pivot && left < right)ʱ����Ϊ 181 > 181 ����������right�ǲ������Ƶġ�
			 * 			ִ�е�  while(array[left] < pivot && left < right)ʱ����Ϊ 181 < 181 ������������left�ǲ������Ƶġ�
			 * 		�����߳�{}��ִ�����һ��while��
			 * 		while(left < right)���������Գ������ִ��{}�е����ݣ�
			 * 		�������������������ѭ����
			 * 	���ۣ���� while(array[right] > pivot && left < right)��
			 * 		��ô�͵ô���  while(array[left] <= pivot && left < right)��
			 * 		���  while(array[right] >= pivot && left < right)��
			 * 		��ô  while(array[left] < pivot && left < right) ���ɡ�
			 * 		�ص㣡����right��left��������һ�������ƶ��ģ������������ͻ������ѭ��������
			 */
			while(array[right] >= pivot && left < right){
				right--;
			}
			if(left < right){//������ж���Ϊ��right����leftλ��ʱ(��ʱleftλ����ʼҲ��tempλ��)�������ٽ�ִ�����������д����ˣ�
				array[temp] = array[right];
				temp = right;
			}
			
			/**
			 * left�������ң��ҵ���pivot����������left��ֵС��pivot���ͽ�left����һλ��
			 */
			while(array[left] < pivot && left < right){
				left++;
			}
			if(left < right){//������ж���Ϊ�˵�left��right����ͬһ��λ��ʱ��������ִ�����������д����ˣ�
				array[temp] = array[left];
				temp = left;
			}
		}
		//��󣬰ѻ�׼��ŵ�����Ǹ����ճ�����tempλ���ϣ�
		array[temp] = pivot;
		
		if (left - start > 1) {
			quicksort(array, 0, left-1);//����Ϊʲô��left-1?��Ϊ���������"left-1"�������򣬾ͻὫ�������һ���������Ǹ���׼��Ҳ���뵽���������
		}
		// ����ָ���Ҳ���2�����ϵ�Ԫ�أ��ݹ���ü���������п�������
		if (end - right > 1) {
			quicksort(array, right + 1, end);//����Ϊʲô��right+1������ͬ"left-1"
		}
	}
	
	/**
	 * ���������ң��ҵ��Ȼ�׼��С�������ٴ��������ң��ҵ��Ȼ�׼��������Ȼ����������
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
		//��"��׼��"�����"left��right��ͬ����λ�õ��Ǹ���"���н�����
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
