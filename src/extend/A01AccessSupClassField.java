package extend;

/**
 * ����A��̳�B��֮����д�ķ������Զ��synchronized�ؼ����������� 
 * 	��
 * 	���ԡ����ǣ��������ı�������private���εģ���ô������ֻ��ͨ������ķ���ȥ����/�޸ĸñ�����
 * 
 * @author May 2019��8��14��
 */
public class A01AccessSupClassField {
	public static void main(String[] args) {
		SubClass sub = new SubClass();
	}
}

class SubClass extends SupClass {
	{
		setName("С����");
		System.out.println(getName());
	}

	@Override
	public synchronized void setName(String name) {
		System.out.println("����.setName()");
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
		System.out.println("����.setName()");
		this.name = name;
	}

	public String getName() {
		System.out.println("2.2 : " + this);//ͨ��������õ������getName()����ʱ�������this�����ࡣ
		return this.name;
	}	
}