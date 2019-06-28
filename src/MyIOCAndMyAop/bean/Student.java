package MyIOCAndMyAop.bean;

import MyIOCAndMyAop.Annotations.After;
import MyIOCAndMyAop.Annotations.AfterReturning;
import MyIOCAndMyAop.Annotations.AfterThrowing;
import MyIOCAndMyAop.Annotations.Around;
import MyIOCAndMyAop.Annotations.Aspect;
import MyIOCAndMyAop.Annotations.Before;
import MyIOCAndMyAop.Annotations.MyComponent;

@Aspect//切面注解类，加了该注解就表示被注解的类的实例需要做动态代理。
@MyComponent
public class Student implements SuperMan {
	
	@After
	@AfterReturning
	@Before
	@AfterThrowing
	@Override
	public int add(int a, int b) {
		System.out.println("--> a + b = " + (a + b));
		return a + b;
	}

	@Around
	@Override
	public int divide(int a, int b) {
		return a/b;
	}
}
