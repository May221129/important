package MyIOCAndMyAop;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import MyIOCAndMyAop.Annotations.MyAutowired;
import MyIOCAndMyAop.Annotations.MyComponent;

/**
 * �Լ�ʵ�ּ򵥵�IOC������������bean��
 * BeanFactory<String, Object>:ȫ������ͨ������������ؽ�����Class�����䴴����bean��
 */
public class MyIOC {

	// ���ڴ�Ž���IOC���������bean ==> ����������������Ǿֲ������� ===���϶�Ҫ������������ܱ�����
	private static HashMap<String, Object> beanFactory = new HashMap<>();
	
	/**
	 * ����MyIOC�౻���ص��ڴ����ʵ�������ͻ�ִ���侲̬����飬������û��main����ʱ�Ͱ�init�����ŵ���̬������С�
	 * @param args
	 */
	static {
		init();
	}
	
//	public static void main(String[] args) {
//		init();
//	}
	
	/**
	 * ��ȡBeanFactory
	 * @return
	 */
	public static HashMap<String, Object> getBeanFactory(){
		return beanFactory;
	}
	
	/**
	 * ����ȫ��������BeanFactory�е�bean
	 * @param typeName
	 * @param proxyInstance
	 */
	public static void updateBeanFromBeanFactory(String typeName, Object proxyInstance) {
		beanFactory.put(typeName, proxyInstance);
	}
	
	/**
	 * ͨ��ȫ������ö�Ӧ��ʵ��
	 * @param completeClassName
	 * @return
	 */
	public static Object getBean(String completeClassName) {
		return beanFactory.get(completeClassName);
	}
	
	public static void init() {
		HashMap<String, Class> loadedClazzList;//<ȫ����, Class����>
		try {
			//1.����ָ������
			File file = new File("C:\\workplace\\test\\bin");//����������д����·�������ʣ���Ҫ�Ľ�
			loadedClazzList = loadAllClazz(file);
			
			//2.ʵ����������IOC�����У�������Щ��ע����࣬��ʵ����
			newInstance(loadedClazzList);
			
			// 3.�������ע��
			autoWired();
			
			// 4.���ԣ��ҵ�beanFactory�е�ĳ��bean����ִ����ĳ������ ===> �����и����⣬ֻ��ִ��ָ���ķ���������beanFactory�е�����bean�����������������������ô���ˣ��������Բ�����
//			test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(Map.Entry<String, Object> entry : beanFactory.entrySet()) {
//			System.out.println(entry.getKey() + " ---> ");
			Method method = entry.getValue().getClass().getMethod("test");
			method.invoke(entry.getValue());
		}
	}
	
	/**
	 * ��BeanFactory�й��������bean�������ע�롣
	 * ����IOC����������࣬��Ҫע���Ա����������ó�Ա�������Զ�����࣬����Ҳ����Ҫ����IOC��������ġ�
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws MalformedURLException 
	 * @throws ClassNotFoundException 
	 */
	public static void autoWired() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, MalformedURLException {
		for(Map.Entry<String, Object> entry :  beanFactory.entrySet()) {
			Field[] fields = entry.getValue().getClass().getDeclaredFields();//������getFields():ֻ�ܻ�ȡ������ʱ���м��丸��������Ϊpublic�����ԣ�getDeclaredFields():��ȡ����ʱ�౾�����������е�����
			for(Field field : fields) {
				Annotation[] annotations = field.getAnnotations();
				for(int i = 0; i < annotations.length; i++) {
					if(annotations[i].annotationType() == MyAutowired.class) {
						//��beanFactory���ҵ���Ӧ��bean����ֵ���ó�Ա���������������ע�롣
						Object object = beanFactory.get(field.getType().getTypeName());
//						System.out.println(field.getType().getTypeName());//MyIOCAndMyAop.bean.Student
						//ͨ��Field�����䣩Ϊ��Ա������ֵ��
						field.setAccessible(true);
						field.set(entry.getValue(), object);
					}
				}
			}
		}
	}
	
	/**
	 * ʵ������ �ŵ�loadedClazzlist�����е�Class��������Ҫ��ʵ������(����@MyComponentע�����)
	 */
	public static void newInstance(HashMap<String, Class> loadedClazzList) throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException {
		for(Map.Entry<String, Class> entry : loadedClazzList.entrySet()) {
			beanFactory.put(entry.getKey(), entry.getValue().newInstance());
		}
	}
	
	/**
	 * ����ָ��·���µ��ࡣ
	 * ����أ�javase/src/classLoader/a01helloworld/A03GetExtClassLoader
	 * @return ����������ؽ�����ָ��·���µ�����Class����
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static HashMap<String, Class> loadAllClazz(File file) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
		//���ڴ������������ؽ�����Class����<ȫ������ Class����>
		HashMap<String, Class> loadedClazzList = new HashMap<>();
		
		//�ʣ���ʲô������������ʣ�
		URL[] urls = new URL[]{file.toURI().toURL()};
		URLClassLoader classLoader = new URLClassLoader(urls);
		
		ArrayList<String> allCompleteClassName = getAllCompleteClassName(file);
		
		for(String element : allCompleteClassName) {
			Class<?> clazz = classLoader.loadClass(element);
			Annotation[] annotations = clazz.getAnnotations();// �������õ�Class�����ʱ�򣬾ͽ���ɸѡ����ע���Class�ٷŵ������У������ǰ�ָ��·���µ������඼���ؽ�����
			for(int i = 0; i < annotations.length; i++) {
				if(annotations[i].annotationType() == MyComponent.class) {
					loadedClazzList.put(element, clazz);//!!!�õ������������
				}
//				if(annotations[i].equals("MyComponent")) {
//					loadedClazzList.put(element, clazz);//!!!�õ������������
//				}
			}
		}
		return loadedClazzList;
	}
	
	/**
	 * �õ�allNeedLoadClassFiles������Ҫ���ص�class�ļ���ȫ����
	 * �� ÿ�����趼Ҫ�н��������ֲ�����Ƿ���ȷ���磬��ʱ�����
	 * �� �����ȫ�������ˣ�Ҫ����ȫ��������������ȫ·������
	 */
	private static ArrayList<String> getAllCompleteClassName(File file) {
		// ����Ҫ���ص�class��ȫ�������磺classLoader.a02myclassloader.bean.Bean
		ArrayList<String> completeClassNames = new ArrayList<>();
		// ���ڴ��ָ��·��������Ҫ���ص�class�ļ�
		ArrayList<File> allNeedLoadClassFiles = new ArrayList<File>();
		
		getAllNeedLoadClassFile(file, allNeedLoadClassFiles);
//		System.out.println("allNeedLoadClassFiles.size() : " + allNeedLoadClassFiles.size());
		
		for (File element : allNeedLoadClassFiles) {
			String filePath = element.getPath().replace("\\", ".");
//			System.out.println("filePath : " + filePath);//C:.workplace.test.bin.MyIOCAndMyAop.Person.class
//			System.out.println(element.getName());//Person.class
			
			if(filePath.endsWith(".class")) {
				//filePath.indexOf("bin.")+4:"bin."֮��filePath.lastIndexOf(".class")��".class"֮ǰ���÷����ǴӺ���ǰ�ң����ܸ��ߡ�
				String completeClassName = filePath.substring(filePath.indexOf("bin.")+4, filePath.lastIndexOf(".class"));
//				System.out.println("completeClassName : " + completeClassName);//ȫ������MyIOCAndMyAop.Person
				completeClassNames.add(completeClassName);
			}
		}
		return completeClassNames;
	}
	
	/**
	 * ͨ���ݹ��ȡָ��·��������Ҫ���ص�class�ļ�
	 * �ݹ飺javase/src/recursion/A_PrintFolder
	 * @param file
	 */
	private static ArrayList<File> getAllNeedLoadClassFile(File file, ArrayList<File> allNeedLoadClassFiles) {
		// ���ڴ��ָ��·��������Ҫ���ص�class�ļ�
//		ArrayList<File> allNeedLoadClassFiles = new ArrayList<File>();//!!!���������ﴴ���ֲ���������Ϊ���ŷ�����ִ�н������þֲ�����Ҳ����֮�����գ�����
		
		if(!file.exists()) {//����������Ҫ��һ���ж�
			return allNeedLoadClassFiles;
		}
		
		if (file.isDirectory()) {//���ļ���
			File[] listFiles = file.listFiles();
			for (File element : listFiles) {
				getAllNeedLoadClassFile(element, allNeedLoadClassFiles);
			}
		} else {//���ļ�
			allNeedLoadClassFiles.add(file);
		}
		return allNeedLoadClassFiles;
	}
}
