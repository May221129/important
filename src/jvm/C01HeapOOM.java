package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * �ѵ��ڴ����
 * ������-Xms20M -Xmx20M
 */
public class C01HeapOOM {
	public static void main(String[] args) {
		C01HeapOOM c1 = new C01HeapOOM();
		c1.testStackOverflowError();
	}
	
	public void testStackOverflowError(){
		List<AA> list = new ArrayList<>();
		while(true){
			list.add(new AA());
		}
	}
	
	private class AA{
		
	}
}

