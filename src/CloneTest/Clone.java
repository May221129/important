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
 * ��¡
 * ǰ�᣺
 * 	�����ࣺ
 * 		class A implements Cloneable{
 * 			private int age;
 * 			private String name;
 * 			private B b;
 * 		}
 * 		class B implements Cloneable{
 * 			private int number;
 * 			private String address;
 * 		}
 * 	����
 * 		A a = new A();
 * 		B b = new B();
 * 		A cloneA = a.clone();		
 * 
 * 1.����
 * 	1.1 ǳ��¡��
 * 		����a.clone()��Ч����
 * 		a����������У�����ǰ��ֻ����������͵����ԣ��ͻᱻ��¡һ���µ����ݵ�cloneA�����У�
 * 		������������͵����ԣ��ͻᴴ��һ���µ����ã���ָ��ͬһ���ڴ�ռ䣺
 * 			a.name��������> name(�ڴ�ռ�����ʵ������)  <��������cloneA.name
 * 	1.2 ���¡��
 * 		��¡һ��a����ΪcloneA��a��������Բ����ǰ��ֻ����������͵ģ������������͵ģ������¡һ���µ����ݸ���cloneA����
 * 
 * 2.��ν�һ��������п�¡��
 * 	2.1 ����ǳ��¡ģ�����¡��
 * 		ʵ�֣���A������дclone()����:
 * 			@Override
			protected Object clone() throws CloneNotSupportedException {
				A cloneA = super.clone();
				cloneA.name = new String(this.name);
				cloneA.b = b.clone();
			}
 * 		�׶ˣ�B�������͵�����b�У�Ҳ���������͵�address������B��Ҳ��Ҫ��дclone()������
 * 			�����addressҲ���Զ�����࣬��Ҳ���������͵������أ�
 * 
 * 	2.2 ��Ҫ���п�¡�Ķ�����࣬ȥʵ��Cloneable�ӿں�Serializable�ӿڡ�
 * 		Ч����������Ķ�����п�¡ʱ�����Ƚ��ö�����ڴ��У�ͨ��ObjectOutputStream���л����ڴ��У�
			��ͨ��ObjectInputStream�����л�����һ���µĶ���
			���ǵĶ�������ɢ����jvm�ĸ����ط��ģ��������Ὣ�����ռ���һ��������
 * 
 * 3.�������й� Java�����¡��Clone����Cloneable�ӿڡ�Serializable�ӿڵ�����̽��
 * 		http://kentkwan.iteye.com/blog/739514
 */
public class Clone {
	/**
	 * ǳ��¡
	 * 	�����������ǰ��Ҫ�Ȱ�User��̳е�Serializable��ɾ��
	 * 		class User implements Cloneable,Serializable {}
	 * 		��Ϊ��
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
	 * ���¡
	 * 	1.Ҫ��¡�Ķ�����࣬һ��Ҫʵ�������ӿڣ�Cloneable(��������Ķ����ǿ��Խ��п�¡��)��Serializable(�����л��ӿ�);
	 * 	2.Ҫ��¡�Ķ����У�����г�Ա�������������͵�(�������User)������������Ͷ������Ҳ��Ҫʵ��Cloneable��Serializable�������ӿڡ�
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
	// ʡ��һ���get/set����
}

/**
 * ������
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