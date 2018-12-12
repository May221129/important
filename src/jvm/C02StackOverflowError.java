package jvm;

/**
 * 参数：-Xss128k
 * 
 * 栈溢出异常：StackOverflowError
 * 1.栈，通常指java虚拟机栈；
 * 2.栈的生命周期随线程；
 * 3.栈帧：随着方法的执行而被创建；
 * 4.随着方法被调用直至执行结束的过程，对应着一个栈帧在Java虚拟机栈中从入栈到出栈的过程；
 * 5.我们通常关注栈都是关注栈帧中的局部变量表；
 * 
 * 栈一般会报两种异常：
 * 	①StackOverflowError：线程请求的栈深度大于虚拟机允许的最大深度。
 * 	②OutofMemoryError：虚拟机在扩展栈时无法申请到足够的内存空间。
 */
public class C02StackOverflowError {
	private int i = 0;
	
	/**
	 * 方法掉方法，形成了一个没有出口的递归，每创建一个方法，就会在虚拟机中创建一个栈帧并入栈到虚拟机栈中。
	 */
	public void show(){
		i++;
		show();//栈深度
	}
	
	public static void main(String[] args) throws Throwable {
		C02StackOverflowError c2 = new C02StackOverflowError();
		try {
			c2.show();
		} catch (Throwable e) {
			System.out.println("==================> i = " + c2.i);
			throw e;
		}
	}
}
