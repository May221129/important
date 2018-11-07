package jvm;

/**
 * 运行结果：（看java虚拟机总结）
 * 静态成员变量rh11, the order 1
 * --父类Linux--静态初始化代码块--
 * 静态成员变量rh22, the order 2
 * 非静态成员变量rh1, the order 3
 * --父类Linux--动态初始化代码块--
 * 5
 * 非静态成员变量rh2, the order 4
 * --父类Linux--构造方法--
 */
public class NewTest1{
	 public static void main(String[] args) {  
	     new Linux();  
	 }  
}
class Linux {
    
    RedHat rh1 = new RedHat("非静态成员变量rh1");  
    static RedHat rh11 = new RedHat("静态成员变量rh11");  
      
    public Linux() {  
        System.out.println("--Linux--构造方法--");  
    }  
      
    int a = 5;
    {  
        System.out.println("--Linux--动态初始化代码块--");  
        System.out.println(a);//在动态代码块之前可以访问，赋值
        b = 8;//在动态代码块不能访问，只能赋值。
    }  
    int b = 7;
    static {  
        System.out.println("--Linux--静态初始化代码块--");  
    }  
      
    RedHat rh2 = new RedHat("非静态成员变量rh2");  
    static RedHat rh22 = new RedHat("静态成员变量rh22");  
}  
class RedHat {  
    static int times = 1;  
    public RedHat(String info) {  
        System.out.println(info + ", the order " + (times++));  
    }  
}  