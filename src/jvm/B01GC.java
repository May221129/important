package jvm;

public class B01GC {
	private static final int TEN_MILLION     = 		10000000;//1ǧ��
	
	private static final int HUNDRED_MILLION = 		100000000;//1��
	
	private static final int TOW_HUNDRED_MILLION = 	200000000;//2��
	
	private static final int THOUSAND_MILLION = 	1000000000;//10��
	
	private Object[] objArr;
	
	public static void main(String[] args) {
		B01GC gc = new B01GC();
		gc.createByObjectArr(HUNDRED_MILLION >> 2);
		
		gc.objArr = null;//400M��С�Ļ��գ��ͼ����������,  ����ÿ�ε�����, ÿ�εĴ����������ƶ������ϴ����ڵĲ����ȽϺ�ʱ
		
		System.out.println("aaa");
	}
	
	public void createByObjectArr(int amount){
		objArr = new Object[amount];
		for(int i = 0; i < amount; i++){
			objArr[i] = i;
			if((i+1) % (amount/10) == 0){
				System.out.println(i + "��");
			}
		}
		System.out.println("������");
	}
}
