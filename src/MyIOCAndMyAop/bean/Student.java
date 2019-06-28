package MyIOCAndMyAop.bean;

import MyIOCAndMyAop.Annotations.After;
import MyIOCAndMyAop.Annotations.AfterReturning;
import MyIOCAndMyAop.Annotations.AfterThrowing;
import MyIOCAndMyAop.Annotations.Around;
import MyIOCAndMyAop.Annotations.Aspect;
import MyIOCAndMyAop.Annotations.Before;
import MyIOCAndMyAop.Annotations.MyComponent;

@Aspect//����ע���࣬���˸�ע��ͱ�ʾ��ע������ʵ����Ҫ����̬����
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
