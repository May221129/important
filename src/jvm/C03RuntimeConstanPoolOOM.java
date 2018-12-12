package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * ����ʱ�������ڴ����
 * 1.VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 2.���н������û�������ڵı�OutOfMemoryError��PermGen Space
 * 3.δ���쳣��ԭ��
 * 	����jdk1.6֮��İ汾���Ѿ�������permSpace�����ǻ���metaSpace�ˣ����������VM args������Ч��
 * 	����jdk1.6֮��İ汾�������ַ���Ҳ������metaspace�У����Ƕ��С�
 * 4.����3���ԣ������ַ����Ѿ��ŵ��˶��У����԰�VM args��������Ϊ���ڴ����-Xms20M -Xmx20M��
 * 	���������Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class C03RuntimeConstanPoolOOM {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
