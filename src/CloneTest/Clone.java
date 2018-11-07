package CloneTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import org.junit.Test;

/**
 * 克隆
 * 前提：
 * 	两个类：
 * 		class A implements Cloneable{
 * 			private int age;
 * 			private String name;
 * 			private B b;
 * 		}
 * 		class B implements Cloneable{
 * 			private int number;
 * 			private String address;
 * 		}
 * 	对象：
 * 		A a = new A();
 * 		B b = new B();
 * 		A cloneA = a.clone();		
 * 
 * 1.概念
 * 	1.1 浅克隆：
 * 		调用a.clone()，效果：
 * 		a对象的属性中，如果是八种基本数据类型的属性，就会被克隆一份新的数据到cloneA对象中；
 * 		如果是引用类型的属性，就会创建一个新的引用，并指向同一份内存空间：
 * 			a.name————> name(内存空间中真实的数据)  <————cloneA.name
 * 	1.2 深克隆：
 * 		克隆一个a对象为cloneA，a对象的属性不管是八种基本数据类型的，还是引用类型的，都会克隆一份新的数据给到cloneA对象。
 * 
 * 2.如何将一个对象进行克隆：
 * 	2.1 利用浅克隆模拟深克隆。
 * 		实现：在A类中重写clone()方法:
 * 			@Override
			protected Object clone() throws CloneNotSupportedException {
				A cloneA = super.clone();
				cloneA.name = new String(this.name);
				cloneA.b = b.clone();
			}
 * 		弊端：B引用类型的属性b中，也有引用类型的address，所以B类也需要重写clone()方法。
 * 			那如果address也是自定义的类，它也有引用类型的属性呢？
 * 
 * 	2.2 将要进行克隆的对象的类，去实现Cloneable接口和Serializable接口。
 * 		效果：当对类的对象进行克隆时，会先将该对象从内存中，通过ObjectOutputStream序列化到内存中，
			再通过ObjectInputStream反序列化返回一个新的对象。
			我们的对象本来是散落在jvm的各个地方的，对象流会将他们收集到一起做处理。
 * 
 * 3.该类是有关 Java对象克隆（Clone）及Cloneable接口、Serializable接口的深入探讨
 * 		http://kentkwan.iteye.com/blog/739514
 */
public class Clone {
	/**
	 * 浅克隆
	 * 	进行这个测试前，要先把User类继承的Serializable先删掉
	 * 		class User implements Cloneable,Serializable {}
	 * 		改为：
	 * 		class User implements Cloneable {}
	 */
	@Test
	public void testClone() throws CloneNotSupportedException{
		User u1 = new User("Kent", "123456", new Date());
		User u2 = u1;
		User u3 = (User) u1.clone();
		
		System.out.println(u1 == u2);		// true
		System.out.println(u1.equals(u2));	// true
		
		System.out.println(u1 == u3);		// false
		System.out.println(u1.equals(u3));	// true
	}
	
	/**
	 * 深克隆
	 * 	1.要克隆的对象的类，一定要实现两个接口：Cloneable(标记这个类的对象是可以进行克隆的)和Serializable(可序列化接口);
	 * 	2.要克隆的对象中，如果有成员变量是引用类型的(如下面的User)，则该引用类型对象的类也需要实现Cloneable、Serializable这两个接口。
	 */
	@Test
	public void testCloneTo() {
		Administrator src = new Administrator(new User("Kent", "123456", new Date()), true);
		Administrator dist = BeanUtil.cloneTo(src);
		
		System.out.println(src == dist);			// false
		System.out.println(src.equals(dist));		// true
		
		System.out.println(src.getUser() == dist.getUser());		//false ! Well done!
		System.out.println(src.getUser().equals(dist.getUser()));	//true
	}
}

class User implements Cloneable,Serializable {
	private String username;
	private String password;
	private Date birthdate;
	public User(String username, String password, Date birthdate) {
		this.username = username;
		this.password = password;
		this.birthdate = birthdate;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	// 省略一大堆get/set方法
}

/**
 * 管理人
 */
class Administrator implements Cloneable,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Boolean editable;
	public Administrator(User user, Boolean editable) {
		this.user = user;
		this.editable = editable;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((editable == null) ? 0 : editable.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		if (editable == null) {
			if (other.editable != null)
				return false;
		} else if (!editable.equals(other.editable))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}

abstract class BeanUtil {
	@SuppressWarnings("unchecked")
	public static <T> T cloneTo(T src) throws RuntimeException {
		ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		T dist = null;
		try {
			out = new ObjectOutputStream(memoryBuffer);
			out.writeObject(src);
			out.flush();
			in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
			dist = (T) in.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null)
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			if (in != null)
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
		return dist;
	}
}