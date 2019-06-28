package MyIOCAndMyAop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import MyIOCAndMyAop.Annotations.Aspect;
import MyIOCAndMyAop.bean.SuperMan;

/**
 * 自己实现AOP（2.0版本，仿照Spring的AOP写的，有环绕通知、前置通知、后置通知、返回通知、异常通知等）。
 * 已实现：①通过动态代理+通知的注解类，实现了前置通知、后置通知等各种通知；②切点（在需要通知的方法上加注解）；③切面（同②）；
 * 未做：①通知的格式没写成可配置的； ②切点、切面没抽取成一个更方便配置的切面类；
 * （1）spring:被代理类的接口、被代理类、各种注解类、BeanFactory、切面类（@Aspect、@Pointcut、@Before等各种通知）、XML、
 * 【/spring/src/com/llm/a02aop/a02useaopwithannotation/A03LoggingAspect.java】
 * （2）MyAOP3：被代理类的接口、被代理类、代理类、生成“代理类的对象”的类、注解类、BeanFactory、各种通知、
 * 
 * @author May 2019年6月25日
 */
public class MyAOP3 {
	public static void main(String[] args) {
		/**
		 * 使用步骤： ① 給指定类的某个方法加@InOutLog注解； ② 通过BeanFactory或的该类的实例； ③ 执行bean.method()；
		 * 效果：method()方法的前后有了log的输出。
		 */
		String completeClassName1 = "MyIOCAndMyAop.bean.Student";
		Object bean = MyIOC.getBean(completeClassName1);
		SuperMan superMan = (SuperMan) bean;
		superMan.add(2, 3);
		superMan.divide(10, 5);
	}
	
	//重点：init()执行了什么！
	static {
		init();
	}

	public static void init() {
		updateBeanFromBeanFactory();
	}

	/**
	 * 扫描BeanFactory，找出方法上有@Aspect注解的bean，为其创建代理类对象，并替代原bean。
	 */
	public static void updateBeanFromBeanFactory() {
		for (Map.Entry<String, Object> entry : MyIOC.getBeanFactory().entrySet()) {
			if (null != entry.getValue().getClass().getDeclaredAnnotation(Aspect.class)) {
				MyProxy3.updateBean(entry.getKey(), entry.getValue());
			}
		}
	}

}

// 动态创建“代理类的对象”的类
class MyProxy3 {

	/**
	 * 动态的创建一个代理类的对象.
	 * 
	 * MyProxy动态创建的“代理类的对象”： 
	 * 	class A implements Subject{ 
	 * 		private	Handler handler; 
	 * 		public void test() { 
	 * 			//获得到当前方法名:
	 * 			 handler.invoke(); 
	 * 		} 
	 * 	}
	 */
	public static Object getProxyInstance(Object obj) {
		MyInvocationHandler3 handler = new MyInvocationHandler3();
		handler.setObject(obj);

		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}

	/**
	 * 对于有@InOutLog注解的，用代理类的bean来替代BeanFactory中的被代理类的bean。
	 * 这一步很重要，因为当执行到bean.method()，执行的就一定是bean对应的method()方法，
	 * 如果此时没有用代理类对象去替换，那么执行的就是没有InOutLog的原来的那个方法。
	 */
	public static void updateBean(String completeClassName, Object object) {
		MyIOC.updateBeanFromBeanFactory(completeClassName, getProxyInstance(object));// (全类名，代理类的bean)
	}
}

// 动态代理类
class MyInvocationHandler3 implements InvocationHandler {
	private Object object;// 被代理类
	private Object invoke;

	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * 动态代理：实现了环绕通知、前置通知、后置通知等通知。
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 入参的类型的处理，返回被代理对象真正要执行的那个方法：
		Method declaredMethod = handleArgs(method);

		// 环绕通知：
		Boolean bool = false;
		if (null != declaredMethod.getAnnotation(MyIOCAndMyAop.Annotations.Around.class)) {
			bool = true;
		}
		aroundInform(declaredMethod, bool, method, args);

		// 前置通知、后置通知、返回通知、异常通知等：
		try {
			if (null != declaredMethod.getAnnotation(MyIOCAndMyAop.Annotations.Before.class)) {
				System.out.println(declaredMethod.getName() + " begings with : " + declaredMethod.getParameters());
			}
			
			//通过放射，真正执行被代理对象的方法：
			invoke = method.invoke(object, args);
			
			if (null != declaredMethod.getAnnotation(MyIOCAndMyAop.Annotations.AfterReturning.class)) {
				System.out.println(declaredMethod.getName() + "  ends with : " + invoke);
			}
		} catch (Exception e) {
			if (null != declaredMethod.getAnnotation(MyIOCAndMyAop.Annotations.AfterThrowing.class)) {
				System.out.println(declaredMethod.getName() + " occurs exception : " + e);
			}
		} finally {
			if (null != declaredMethod.getAnnotation(MyIOCAndMyAop.Annotations.After.class)) {
				System.out.println(declaredMethod.getName() + " ends.");
			}
		}
		return invoke;
	}

	/**
	 * 入参的类型的处理，这个方法很重要。
	 *  
	 * 如果没做入参处理： 
	 * 	被代理对象声明的方：method(1, 2);
	 * 	代理对象的invoke()方法中declaredMethod = object.getClass().getDeclaredMethod(method.getName());//这里没做入参处理，执行代理对象的invoke()方法时，就会报找不到空参的method()。
	 * 	案例见：MyIOCAndMyAop.MyInvocationHandler2.invoke()；
	 * @return 被代理对象真正要执行的那个方法
	 * @param method 被代理对象的接口中声明的被代理方法
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Method handleArgs(Method method) throws NoSuchMethodException, SecurityException {
		Class<?>[] parameterTypes = method.getParameterTypes();
		switch (parameterTypes.length) {
		case 1:
			System.out.println("parameterTypes.length = 1 : " + parameterTypes[0]);
			return object.getClass().getDeclaredMethod(method.getName(), parameterTypes[0]);
		case 2:
			System.out.println("parameterTypes.length = 2 : " + parameterTypes[0] + " ; " + parameterTypes[1]);
			return object.getClass().getDeclaredMethod(method.getName(), parameterTypes[0], parameterTypes[1]);
		case 3:
			System.out.println("parameterTypes.length = 3 : " + parameterTypes[0] + " ; " + parameterTypes[1] + " ; "
					+ parameterTypes[2]);
			return object.getClass().getDeclaredMethod(method.getName(), parameterTypes[0], parameterTypes[1],
					parameterTypes[2]);
		default:
			System.out.println("parameterTypes.length = 0 : " + parameterTypes.length);
			return object.getClass().getDeclaredMethod(method.getName());
		}
	}

	/**
	 * 环绕通知
	 * 
	 * @param declaredMethod 被代理对象的被代理方法
	 * @param bool
	 * @param method         被代理对象的接口中声明的被代理方法
	 * @param args           被代理方法的声明的入参
	 */
	private void aroundInform(Method declaredMethod, Boolean bool, Method method, Object[] args) {
		if (bool) {
			try {
				System.out.println(declaredMethod.getName() + " begings with : " + declaredMethod.getParameters());
				invoke = method.invoke(object, args);
				System.out.println(declaredMethod.getName() + "  ends with : " + invoke);
			} catch (Exception e) {
				System.out.println(declaredMethod.getName() + " occurs exception : " + e);
			} finally {
				System.out.println(declaredMethod.getName() + " ends.");
			}
		}
	}
}
