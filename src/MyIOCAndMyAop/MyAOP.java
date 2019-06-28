package MyIOCAndMyAop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP的底层：动态代理。
 * 
 * @author May
 * 2019年6月25日
 */
public class MyAOP {

	public static void main(String[] args) {
		ByProxy byProxy = new ByProxy();//创建一个被代理类的对象
		Object obj = MyProxy.getProxyInstance(byProxy);//返回一个代理类的对象
		Subject sb = (Subject)obj;
		sb.doSomething();//通过代理类的对象调用重写的抽象方法
	}
}

class MyProxy {
	// 动态的创建一个代理类的对象
	public static Object getProxyInstance(Object obj) {
		MyInvocationHandler handler = new MyInvocationHandler();
		handler.setObject(obj);

		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}
}

//动态代理类
class MyInvocationHandler implements InvocationHandler{
	private Object object;//被代理类
	private Object invoke;
	public void setObject(Object object) {
		this.object = object;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我是日志输出~~~start~~~");
		
		invoke = method.invoke(object, args);
		
		System.out.println("我是日志输出~~~end~~~");
		
		return invoke;
	}
	
}

//被代理类
class ByProxy implements Subject{

	@Override
	public void doSomething() {
		System.out.println(this + ".doSomething()");
	}
}

//接口
interface Subject{
	public void doSomething();
}
