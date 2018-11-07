package jvm;

/**
 * ���н��������java������ܽᣩ
 * ��̬��Ա����rh11, the order 1
 * --����Linux--��̬��ʼ�������--
 * ��̬��Ա����rh22, the order 2
 * �Ǿ�̬��Ա����rh1, the order 3
 * --����Linux--��̬��ʼ�������--
 * 5
 * �Ǿ�̬��Ա����rh2, the order 4
 * --����Linux--���췽��--
 */
public class NewTest1{
	 public static void main(String[] args) {  
	     new Linux();  
	 }  
}
class Linux {
    
    RedHat rh1 = new RedHat("�Ǿ�̬��Ա����rh1");  
    static RedHat rh11 = new RedHat("��̬��Ա����rh11");  
      
    public Linux() {  
        System.out.println("--Linux--���췽��--");  
    }  
      
    int a = 5;
    {  
        System.out.println("--Linux--��̬��ʼ�������--");  
        System.out.println(a);//�ڶ�̬�����֮ǰ���Է��ʣ���ֵ
        b = 8;//�ڶ�̬����鲻�ܷ��ʣ�ֻ�ܸ�ֵ��
    }  
    int b = 7;
    static {  
        System.out.println("--Linux--��̬��ʼ�������--");  
    }  
      
    RedHat rh2 = new RedHat("�Ǿ�̬��Ա����rh2");  
    static RedHat rh22 = new RedHat("��̬��Ա����rh22");  
}  
class RedHat {  
    static int times = 1;  
    public RedHat(String info) {  
        System.out.println(info + ", the order " + (times++));  
    }  
}  