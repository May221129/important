package MyIOCAndMyAop.bean;

import MyIOCAndMyAop.Annotations.InOutLog;
import MyIOCAndMyAop.Annotations.MyAutowired;
import MyIOCAndMyAop.Annotations.MyComponent;

/**
 * ��������
 */
@MyComponent
public class Person implements Subject{
	
	@MyAutowired
	private Student student;
	
	@InOutLog
	public void test(){
		System.out.println(this + ".test() : " + student);
	}
}
