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
 * 自己实现简单的IOC容器，来管理bean。
 * BeanFactory<String, Object>:全类名，通过类加载器加载进来的Class对象反射创建的bean。
 */
public class MyIOC {

	// 用于存放交给IOC容器管理的bean ==> ！！！类变量？还是局部变量？ ===》肯定要是类变量，不能被回收
	private static HashMap<String, Object> beanFactory = new HashMap<>();
	
	/**
	 * 随着MyIOC类被加载到内存进行实例化，就会执行其静态代码块，所以在没有main方法时就把init方法放到静态代码块中。
	 * @param args
	 */
	static {
		init();
	}
	
//	public static void main(String[] args) {
//		init();
//	}
	
	/**
	 * 获取BeanFactory
	 * @return
	 */
	public static HashMap<String, Object> getBeanFactory(){
		return beanFactory;
	}
	
	/**
	 * 根据全类名更新BeanFactory中的bean
	 * @param typeName
	 * @param proxyInstance
	 */
	public static void updateBeanFromBeanFactory(String typeName, Object proxyInstance) {
		beanFactory.put(typeName, proxyInstance);
	}
	
	/**
	 * 通过全类名获得对应的实例
	 * @param completeClassName
	 * @return
	 */
	public static Object getBean(String completeClassName) {
		return beanFactory.get(completeClassName);
	}
	
	public static void init() {
		HashMap<String, Class> loadedClazzList;//<全类名, Class对象>
		try {
			//1.加载指定的类
			File file = new File("C:\\workplace\\test\\bin");//！！！这里写死了路径不合适，需要改进
			loadedClazzList = loadAllClazz(file);
			
			//2.实例化并放入IOC容器中：对于那些有注解的类，做实例化
			newInstance(loadedClazzList);
			
			// 3.完成依赖注入
			autoWired();
			
			// 4.测试：找到beanFactory中的某个bean，并执行其某个方法 ===> 这里有个问题，只能执行指定的方法，所以beanFactory中的所有bean都得有这个方法，这里先这么做了，但这明显不合理。
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
	 * 对BeanFactory中管理的所有bean完成依赖注入。
	 * 交给IOC容器管理的类，需要注入成员变量，如果该成员变量是自定义的类，该类也是需要交给IOC容器管理的。
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws MalformedURLException 
	 * @throws ClassNotFoundException 
	 */
	public static void autoWired() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException, MalformedURLException {
		for(Map.Entry<String, Object> entry :  beanFactory.entrySet()) {
			Field[] fields = entry.getValue().getClass().getDeclaredFields();//！！！getFields():只能获取到运行时类中及其父类中声明为public的属性；getDeclaredFields():获取运行时类本身声明的所有的属性
			for(Field field : fields) {
				Annotation[] annotations = field.getAnnotations();
				for(int i = 0; i < annotations.length; i++) {
					if(annotations[i].annotationType() == MyAutowired.class) {
						//从beanFactory中找到相应的bean，赋值给该成员变量，以完成依赖注入。
						Object object = beanFactory.get(field.getType().getTypeName());
//						System.out.println(field.getType().getTypeName());//MyIOCAndMyAop.bean.Student
						//通过Field（反射）为成员变量赋值：
						field.setAccessible(true);
						field.set(entry.getValue(), object);
					}
				}
			}
		}
	}
	
	/**
	 * 实例化： 放到loadedClazzlist集合中的Class对象都是需要做实例化的(加了@MyComponent注解的类)
	 */
	public static void newInstance(HashMap<String, Class> loadedClazzList) throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException {
		for(Map.Entry<String, Class> entry : loadedClazzList.entrySet()) {
			beanFactory.put(entry.getKey(), entry.getValue().newInstance());
		}
	}
	
	/**
	 * 加载指定路径下的类。
	 * 类加载：javase/src/classLoader/a01helloworld/A03GetExtClassLoader
	 * @return 类加载器加载进来的指定路径下的所有Class对象
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static HashMap<String, Class> loadAllClazz(File file) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
		//用于存放类加载器加载进来的Class对象<全类名， Class对象>
		HashMap<String, Class> loadedClazzList = new HashMap<>();
		
		//问：用什么加载器会更合适？
		URL[] urls = new URL[]{file.toURI().toURL()};
		URLClassLoader classLoader = new URLClassLoader(urls);
		
		ArrayList<String> allCompleteClassName = getAllCompleteClassName(file);
		
		for(String element : allCompleteClassName) {
			Class<?> clazz = classLoader.loadClass(element);
			Annotation[] annotations = clazz.getAnnotations();// ！！！拿到Class对象的时候，就进行筛选出有注解的Class再放到容器中，而不是把指定路径下的所有类都加载进来。
			for(int i = 0; i < annotations.length; i++) {
				if(annotations[i].annotationType() == MyComponent.class) {
					loadedClazzList.put(element, clazz);//!!!得到各个类对象了
				}
//				if(annotations[i].equals("MyComponent")) {
//					loadedClazzList.put(element, clazz);//!!!得到各个类对象了
//				}
			}
		}
		return loadedClazzList;
	}
	
	/**
	 * 得到allNeedLoadClassFiles中所有要加载的class文件的全类名
	 * ① 每个步骤都要有结果，检验局部结果是否正确。如，及时输出。
	 * ② 这里的全类名错了，要的是全类名，而不是完全路径名。
	 */
	private static ArrayList<String> getAllCompleteClassName(File file) {
		// 所有要加载的class的全类名，如：classLoader.a02myclassloader.bean.Bean
		ArrayList<String> completeClassNames = new ArrayList<>();
		// 用于存放指定路径下所有要加载的class文件
		ArrayList<File> allNeedLoadClassFiles = new ArrayList<File>();
		
		getAllNeedLoadClassFile(file, allNeedLoadClassFiles);
//		System.out.println("allNeedLoadClassFiles.size() : " + allNeedLoadClassFiles.size());
		
		for (File element : allNeedLoadClassFiles) {
			String filePath = element.getPath().replace("\\", ".");
//			System.out.println("filePath : " + filePath);//C:.workplace.test.bin.MyIOCAndMyAop.Person.class
//			System.out.println(element.getName());//Person.class
			
			if(filePath.endsWith(".class")) {
				//filePath.indexOf("bin.")+4:"bin."之后。filePath.lastIndexOf(".class")：".class"之前，该方法是从后往前找，性能更高。
				String completeClassName = filePath.substring(filePath.indexOf("bin.")+4, filePath.lastIndexOf(".class"));
//				System.out.println("completeClassName : " + completeClassName);//全类名：MyIOCAndMyAop.Person
				completeClassNames.add(completeClassName);
			}
		}
		return completeClassNames;
	}
	
	/**
	 * 通过递归获取指定路径下所有要加载的class文件
	 * 递归：javase/src/recursion/A_PrintFolder
	 * @param file
	 */
	private static ArrayList<File> getAllNeedLoadClassFile(File file, ArrayList<File> allNeedLoadClassFiles) {
		// 用于存放指定路径下所有要加载的class文件
//		ArrayList<File> allNeedLoadClassFiles = new ArrayList<File>();//!!!不能在这里创建局部变量，因为随着方法的执行结束，该局部变量也会随之被回收！！！
		
		if(!file.exists()) {//！！！这里要多一层判断
			return allNeedLoadClassFiles;
		}
		
		if (file.isDirectory()) {//是文件夹
			File[] listFiles = file.listFiles();
			for (File element : listFiles) {
				getAllNeedLoadClassFile(element, allNeedLoadClassFiles);
			}
		} else {//是文件
			allNeedLoadClassFiles.add(file);
		}
		return allNeedLoadClassFiles;
	}
}
