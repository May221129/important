package MyIOCAndMyAop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP�ĵײ㣺��̬����
 * 
 * @author May
 * 2019��6��25��
 */
public class MyAOP {

	public static void main(String[] args) {
		ByProxy byProxy = new ByProxy();//����һ����������Ķ���
		Object obj = MyProxy.getProxyInstance(byProxy);//����һ��������Ķ���
		Subject sb = (Subject)obj;
		sb.doSomething();//ͨ��������Ķ��������д�ĳ��󷽷�
	}
}

class MyProxy {
	// ��̬�Ĵ���һ��������Ķ���
	public static Object getProxyInstance(Object obj) {
		MyInvocationHandler handler = new MyInvocationHandler();
		handler.setObject(obj);

		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}
}

//��̬������
class MyInvocationHandler implements InvocationHandler{
	private Object object;//��������
	private Object invoke;
	public void setObject(Object object) {
		this.object = object;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("������־���~~~start~~~");
		
		invoke = method.invoke(object, args);
		
		System.out.println("������־���~~~end~~~");
		
		return invoke;
	}
	
}

//��������
class ByProxy implements Subject{

	@Override
	public void doSomething() {
		System.out.println(this + ".doSomething()");
	}
}

//�ӿ�
interface Subject{
	public void doSomething();
}
