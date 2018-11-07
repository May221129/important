package jvm;

public class B01GC {
	private static final int TEN_MILLION     = 		10000000;//1千万
	
	private static final int HUNDRED_MILLION = 		100000000;//1亿
	
	private static final int TOW_HUNDRED_MILLION = 	200000000;//2亿
	
	private static final int THOUSAND_MILLION = 	1000000000;//10亿
	
	private Object[] objArr;
	
	public static void main(String[] args) {
		B01GC gc = new B01GC();
		gc.createByObjectArr(HUNDRED_MILLION >> 2);
		
		gc.objArr = null;//400M大小的回收：就几毫秒的事情,  反而每次的扩容, 每次的从新生代复制对象到年老代遮掩的操作比较耗时
		
		System.out.println("aaa");
	}
	
	public void createByObjectArr(int amount){
		objArr = new Object[amount];
		for(int i = 0; i < amount; i++){
			objArr[i] = i;
			if((i+1) % (amount/10) == 0){
				System.out.println(i + "次");
			}
		}
		System.out.println("结束了");
	}
}
