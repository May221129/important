package extend;

/**
 * 测试A类继承B类之后重写的方法可以多加synchronized关键字来修饰吗？ 
 * 	答：
 * 	可以。但是，如果父类的变量是用private修饰的，那么子类是只能通过父类的方法去访问/修改该变量。
 * 
 * @author May 2019年8月14日
 */
public class A01AccessSupClassField {
	public static void main(String[] args) {
		SubClass sub = new SubClass();
	}
}

class SubClass extends SupClass {
	{
		setName("小李子");
		System.out.println(getName());
	}

	@Override
	public synchronized void setName(String name) {
		System.out.println("子类.setName()");
		super.setName(name);
	}

	@Override
	public synchronized String getName() {
		System.out.println("2.1 :" + this);
		return super.getName();
	}
}

class SupClass{
	private String name;

	public void setName(String name) {
		System.out.println("父类.setName()");
		this.name = name;
	}

	public String getName() {
		System.out.println("2.2 : " + this);//通过子类调用到父类的getName()方法时，这里的this是子类。
		return this.name;
	}	
}