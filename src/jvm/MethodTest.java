package jvm;

public class MethodTest{
	public static void main(String[] args){
		Demo d = new Demo();
	}
}

class Demo{
	{
		a();//执行方法a就行
		//System.out.println(i);访问a就不行。
	}
	int i = 5;
	public void a(){
		System.out.println("执行了方法a");
	}
}