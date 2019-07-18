package MyIOCAndMyAop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import MyIOCAndMyAop.Annotations.InOutLog;
import MyIOCAndMyAop.bean.Subject;


/**
 * 自己实现AOP：1.0版本：在某个方法上加"@InOutLog"注解，那么执行到该方法时，方法的前面、后面会输出日志信息。
 * 
 * @author May
 * 2019年6月25日
 */
public class MyAOP2 {
	public static void main(String[] args) {
		/**
		 * 使用步骤：
		 * ① 給指定类的某个方法加@InOutLog注解；
		 * ② 通过BeanFactory或的该类的实例；
		 * ③ 执行bean.method()；
		 * 效果：method()方法的前后有了log的输出。
		 */
		String completeClassName = "MyIOCAndMyAop.bean.Person";
		Object bean = MyIOC.getBean(completeClassName);
		Subject person = (Subject)bean;
		person.test();
	}
	
	static {
		init();
	}
	
	public static void init() {
		updateBeanFromBeanFactory();
	}
	
	/**
	 * 扫描BeanFactory，找出方法上有@InOutLog注解的bean，为其创建代理类对象，并替代原bean。
	 */
	public static void updateBeanFromBeanFactory() {
		for(Map.Entry<String, Object> entry : MyIOC.getBeanFactory().entrySet()) {
			for(Method method : entry.getValue().getClass().getDeclaredMethods()) {
				if(null != method.getAnnotation(InOutLog.class)) {
					MyProxy2.updateBean(entry.getKey(), entry.getValue());
				}
			}
		}
	}
}

//动态创建“代理类的对象”的类
class MyProxy2 {
	
	/**
	 * 动态的创建一个代理类的对象
	 * MyProxy动态创建的“代理类的对象”：
	 * 	class A implements Subject{
	 * 		private Handler  handler;
	 * 		public void test() {
	 * 			//获得到当前方法名
	 * 			handler.invoke();
	 * 		}
	 * 	}
	 */
	public static Object getProxyInstance(Object obj) {
		MyInvocationHandler2 handler = new MyInvocationHandler2();
		handler.setObject(obj);

		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}
	
	/**
	 * 对于有@InOutLog注解的，用代理类的bean来替代BeanFactory中的被代理类的bean。
	 * 这一步很重要，因为当执行到bean.method()，执行的就一定是bean对应的method()方法，
	 * 如果此时没有用代理类对象去替换，那么执行的就是没有InOutLog的原来的那个方法。
	 */
	public static void updateBean(String completeClassName, Object object) {
		MyIOC.updateBeanFromBeanFactory(completeClassName, getProxyInstance(object));//(全类名，代理类的bean)
	}
}

//动态代理类
class MyInvocationHandler2 implements InvocationHandler{
	private Object object;//被代理类
	private Object invoke;
	public void setObject(Object object) {
		this.object = object;
	}
	
	/**
	 *  在BeanFactory中，方法上有@InOutLog注解，则生成动态代理方法
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//这里做判断，看是否需要做下面的输出
		Boolean bool = false;
		//！！！注意，要用被代理类的对象去判断其method方法上是否有@InOutLog注解，而不是用入参method对象（该method对象是被代理类的接口的）
		//怎么处理入参的类型：见MyAOP2.这里没有做入参处理，可能会报方法找不到异常，注意！！！
		Method declaredMethod = object.getClass().getDeclaredMethod(method.getName());
		if(null != declaredMethod.getAnnotation(InOutLog.class)) {
			System.out.println("我是日志输出~~~start~~~");
			bool = true;
		}
		
		invoke = method.invoke(object, args);
		
		//这里做判断，同上
		if(bool) {
			System.out.println("我是日志输出~~~end~~~");
			System.out.println("------------------------------------------------------------------------------");
		}
		return invoke;
	}
}
