package jvm;

/**
 * 利用反射获取某个类的Class
 */
public class A05ClassLoader{
	public static void main(String[] args) throws Exception{
		/**
		 * 手动加载某个类
		 * 当然，就算没有自己手动去加载类C,jvm也会自动帮我们去加载。
		 * 【感觉需要用到classLoader的时候应该是动态生成一个类的时候，然后需要被加载，用到的地方就是动态代理-生成代理类】
		 */
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();//类加载器.得到系统类加载器()
		//加载类(明确就只做加载，不会进行初始化)
		Class<?> clazz = classLoader.loadClass("jvm.C");//全类名
		clazz = Class.forName("jvm.C");//forName()方法会进行初始化，具体的要看代码。
	}
}

class C{
	static{
		System.out.println("初始化了C");
	}
}