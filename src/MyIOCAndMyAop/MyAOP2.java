package MyIOCAndMyAop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import MyIOCAndMyAop.Annotations.InOutLog;
import MyIOCAndMyAop.bean.Subject;


/**
 * �Լ�ʵ��AOP��1.0�汾����ĳ�������ϼ�"@InOutLog"ע�⣬��ôִ�е��÷���ʱ��������ǰ�桢����������־��Ϣ��
 * 
 * @author May
 * 2019��6��25��
 */
public class MyAOP2 {
	public static void main(String[] args) {
		/**
		 * ʹ�ò��裺
		 * �� �oָ�����ĳ��������@InOutLogע�⣻
		 * �� ͨ��BeanFactory��ĸ����ʵ����
		 * �� ִ��bean.method()��
		 * Ч����method()������ǰ������log�������
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
	 * ɨ��BeanFactory���ҳ���������@InOutLogע���bean��Ϊ�䴴����������󣬲����ԭbean��
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

//��̬������������Ķ��󡱵���
class MyProxy2 {
	
	/**
	 * ��̬�Ĵ���һ��������Ķ���
	 * MyProxy��̬�����ġ�������Ķ��󡱣�
	 * 	class A implements Subject{
	 * 		private Handler  handler;
	 * 		public void test() {
	 * 			//��õ���ǰ������
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
	 * ������@InOutLogע��ģ��ô������bean�����BeanFactory�еı��������bean��
	 * ��һ������Ҫ����Ϊ��ִ�е�bean.method()��ִ�еľ�һ����bean��Ӧ��method()������
	 * �����ʱû���ô��������ȥ�滻����ôִ�еľ���û��InOutLog��ԭ�����Ǹ�������
	 */
	public static void updateBean(String completeClassName, Object object) {
		MyIOC.updateBeanFromBeanFactory(completeClassName, getProxyInstance(object));//(ȫ�������������bean)
	}
}

//��̬������
class MyInvocationHandler2 implements InvocationHandler{
	private Object object;//��������
	private Object invoke;
	public void setObject(Object object) {
		this.object = object;
	}
	
	/**
	 *  ��BeanFactory�У���������@InOutLogע�⣬�����ɶ�̬������
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//�������жϣ����Ƿ���Ҫ����������
		Boolean bool = false;
		//������ע�⣬Ҫ�ñ�������Ķ���ȥ�ж���method�������Ƿ���@InOutLogע�⣬�����������method���󣨸�method�����Ǳ�������Ľӿڵģ�
		//��ô������ε����ͣ���MyAOP2.����û������δ������ܻᱨ�����Ҳ����쳣��ע�⣡����
		Method declaredMethod = object.getClass().getDeclaredMethod(method.getName());
		if(null != declaredMethod.getAnnotation(InOutLog.class)) {
			System.out.println("������־���~~~start~~~");
			bool = true;
		}
		
		invoke = method.invoke(object, args);
		
		//�������жϣ�ͬ��
		if(bool) {
			System.out.println("������־���~~~end~~~");
			System.out.println("------------------------------------------------------------------------------");
		}
		return invoke;
	}
}
