package jvm;

/**
 * ���⣺�ڼ̳�+��̬������£����󵽵׷��ʵ���˭�����ԡ����õ���˭�ķ�����
 * ǰ�᣺��������Ժͷ������Ƿ�private�ģ���������Լ̳еġ�
 * ˼·�����ڴ�ṹ���﷨��������������ȥ������
 * ���
 * 	1.ʲô�Ǽ̳У�
 * 		������û����д�����name��getName()��setName()ʱ��������Ȩ��ȥ���ʸ�������ԡ����ø���ķ����������ǽ������е����Ժͷ�������һ�ݵ�������ȥ��
 * 		����������д��������Ի򷽷�ʱ��������ڴ��о���ʵӵ����������Ի򷽷���
 * 	2.���������
 * 		2.1 �����û����д�����name���ԣ�Ҳû����д���������Եķ�����
 * 			��̬�ͷǶ�̬��һ����ʵ�ʷ��ʵ��Ǹ�������ԣ����õ��Ǹ���ķ�����
 * 		2.2 ���༴��д�˸����name���ԣ�Ҳ��д�˲��������Եķ�����
 * 			ʵ��new���Ķ�����˭�ģ����ʵľ���˭�����ԣ����õľ���˭�ķ�����
 * 		2.3 ������д�˸����name���ԣ���û��д�������Եķ���ʱ��
 * 			��̬�ͷǶ�̬��һ��:����.getName()�����.setName()��ʵ�ʵ��õ��Ǹ���ķ�����������Ȩ�޵��ø���ķ��������ҷ��ʵ�Ҳ�Ǹ�������ԣ�
 * 			�����õ���˭�ķ��������ʵľ���˭�����ԡ���
 * 			���﷨����˵����ʱ�Ķ���ȷʵ�����࣬�����ಢû��get/set�����������ڴ�ṹ����˵��������ȥ���ø���ķ����ˡ���
 * 		2.4  ������û����д�����name���ԣ�����д�˲������Եķ���ʱ��
 * 			��̬������£�����.getName()�����.setName()��ʵ�ʵ��õ�������ķ����������ʵ��Ǹ�������ԡ�
 * 			�����õ���˭�ķ��������ʵľ���˭�����ԣ�����ʱ����û��name���ԣ�������ͨ��Ȩ�޷��ʸ����name���ԡ���
 */			
public class A00ExtendsNode {
	public static void main(String[] args) {
		Sup bp = new Sub();
		System.out.println("��̬ʱ-bp��" + bp.getName());
		System.out.println();
		
		Sub sub = new Sub();
		System.out.println("û�ж�̬ʱ-sub��" + sub.getName());
		System.out.println();
		
		Sup sup = new Sup();
		System.out.println("û�ж�̬ʱ-sup��" + sup.getName());
	}
}

class Sup{
	String name = "����";
	public void setName(String name){
		this.name =  name;
	}
	public String getName(){
		System.out.println(this.getClass().getName());
		return name;
	}
}

class Sub extends Sup{
	String name = "����";
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		System.out.println(this.getClass().getName());
		return name;
	}
}