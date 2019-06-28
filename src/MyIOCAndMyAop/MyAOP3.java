package MyIOCAndMyAop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import MyIOCAndMyAop.Annotations.Aspect;
import MyIOCAndMyAop.bean.SuperMan;

/**
 * �Լ�ʵ��AOP��2.0�汾������Spring��AOPд�ģ��л���֪ͨ��ǰ��֪ͨ������֪ͨ������֪ͨ���쳣֪ͨ�ȣ���
 * ��ʵ�֣���ͨ����̬����+֪ͨ��ע���࣬ʵ����ǰ��֪ͨ������֪ͨ�ȸ���֪ͨ�����е㣨����Ҫ֪ͨ�ķ����ϼ�ע�⣩�������棨ͬ�ڣ���
 * δ������֪ͨ�ĸ�ʽûд�ɿ����õģ� ���е㡢����û��ȡ��һ�����������õ������ࣻ
 * ��1��spring:��������Ľӿڡ��������ࡢ����ע���ࡢBeanFactory�������ࣨ@Aspect��@Pointcut��@Before�ȸ���֪ͨ����XML��
 * ��/spring/src/com/llm/a02aop/a02useaopwithannotation/A03LoggingAspect.java��
 * ��2��MyAOP3����������Ľӿڡ��������ࡢ�����ࡢ���ɡ�������Ķ��󡱵��ࡢע���ࡢBeanFactory������֪ͨ��
 * 
 * @author May 2019��6��25��
 */
public class MyAOP3 {
	public static void main(String[] args) {
		/**
		 * ʹ�ò��裺 �� �oָ�����ĳ��������@InOutLogע�⣻ �� ͨ��BeanFactory��ĸ����ʵ���� �� ִ��bean.method()��
		 * Ч����method()������ǰ������log�������
		 */
		String completeClassName1 = "MyIOCAndMyAop.bean.Student";
		Object bean = MyIOC.getBean(completeClassName1);
		SuperMan superMan = (SuperMan) bean;
		superMan.add(2, 3);
		superMan.divide(10, 5);
	}
	
	//�ص㣺init()ִ����ʲô��
	static {
		init();
	}

	public static void init() {
		updateBeanFromBeanFactory();
	}

	/**
	 * ɨ��BeanFactory���ҳ���������@Aspectע���bean��Ϊ�䴴����������󣬲����ԭbean��
	 */
	public static void updateBeanFromBeanFactory() {
		for (Map.Entry<String, Object> entry : MyIOC.getBeanFactory().entrySet()) {
			if (null != entry.getValue().getClass().getDeclaredAnnotation(Aspect.class)) {
				MyProxy3.updateBean(entry.getKey(), entry.getValue());
			}
		}
	}

}

// ��̬������������Ķ��󡱵���
class MyProxy3 {

	/**
	 * ��̬�Ĵ���һ��������Ķ���.
	 * 
	 * MyProxy��̬�����ġ�������Ķ��󡱣� 
	 * 	class A implements Subject{ 
	 * 		private	Handler handler; 
	 * 		public void test() { 
	 * 			//��õ���ǰ������:
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
	 * ������@InOutLogע��ģ��ô������bean�����BeanFactory�еı��������bean��
	 * ��һ������Ҫ����Ϊ��ִ�е�bean.method()��ִ�еľ�һ����bean��Ӧ��method()������
	 * �����ʱû���ô��������ȥ�滻����ôִ�еľ���û��InOutLog��ԭ�����Ǹ�������
	 */
	public static void updateBean(String completeClassName, Object object) {
		MyIOC.updateBeanFromBeanFactory(completeClassName, getProxyInstance(object));// (ȫ�������������bean)
	}
}

// ��̬������
class MyInvocationHandler3 implements InvocationHandler {
	private Object object;// ��������
	private Object invoke;

	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * ��̬����ʵ���˻���֪ͨ��ǰ��֪ͨ������֪ͨ��֪ͨ��
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// ��ε����͵Ĵ������ر������������Ҫִ�е��Ǹ�������
		Method declaredMethod = handleArgs(method);

		// ����֪ͨ��
		Boolean bool = false;
		if (null != declaredMethod.getAnnotation(MyIOCAndMyAop.Annotations.Around.class)) {
			bool = true;
		}
		aroundInform(declaredMethod, bool, method, args);

		// ǰ��֪ͨ������֪ͨ������֪ͨ���쳣֪ͨ�ȣ�
		try {
			if (null != declaredMethod.getAnnotation(MyIOCAndMyAop.Annotations.Before.class)) {
				System.out.println(declaredMethod.getName() + " begings with : " + declaredMethod.getParameters());
			}
			
			//ͨ�����䣬����ִ�б��������ķ�����
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
	 * ��ε����͵Ĵ��������������Ҫ��
	 *  
	 * ���û����δ��� 
	 * 	��������������ķ���method(1, 2);
	 * 	��������invoke()������declaredMethod = object.getClass().getDeclaredMethod(method.getName());//����û����δ���ִ�д�������invoke()����ʱ���ͻᱨ�Ҳ����ղε�method()��
	 * 	��������MyIOCAndMyAop.MyInvocationHandler2.invoke()��
	 * @return �������������Ҫִ�е��Ǹ�����
	 * @param method ���������Ľӿ��������ı�������
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
	 * ����֪ͨ
	 * 
	 * @param declaredMethod ���������ı�������
	 * @param bool
	 * @param method         ���������Ľӿ��������ı�������
	 * @param args           �������������������
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
