package jvm;

/**
 * 问题：在继承+多态的情况下，对象到底访问的是谁的属性、调用的是谁的方法？
 * 前提：父类的属性和方法都是非private的，即子类可以继承的。
 * 思路：从内存结构和语法规则这两个方面去分析。
 * 解答：
 * 	1.什么是继承：
 * 		在子类没有重写父类的name、getName()、setName()时，子类有权限去访问父类的属性、调用父类的方法，而并非将父类中的属性和方法复制一份到子类中去；
 * 		在子类有重写父类的属性或方法时，子类的内存中就真实拥有了这个属性或方法。
 * 	2.情况分析：
 * 		2.1 子类既没有重写父类的name属性，也没有重写操作该属性的方法：
 * 			多态和非多态都一样：实际访问的是父类的属性，调用的是父类的方法；
 * 		2.2 子类即重写了父类的name属性，也重写了操作该属性的方法：
 * 			实际new出的对象是谁的，访问的就是谁的属性，调用的就是谁的方法。
 * 		2.3 子类重写了父类的name属性，但没重写操作属性的方法时：
 * 			多态和非多态都一样:对象.getName()或对象.setName()，实际调用的是父类的方法（子类有权限调用父类的方法），且访问的也是父类的属性；
 * 			【调用的是谁的方法，访问的就是谁的属性。】
 * 			【语法上来说，此时的对象确实是子类，但子类并没有get/set方法，所以内存结构上来说，子类是去调用父类的方法了。】
 * 		2.4  在子类没有重写父类的name属性，但重写了操作属性的方法时：
 * 			多态的情况下，对象.getName()或对象.setName()，实际调用的是子类的方法，但访问的是父类的属性。
 * 			【调用的是谁的方法，访问的就是谁的属性，但此时子类没有name属性，所以它通过权限访问父类的name属性。】
 */			
public class A00ExtendsNode {
	public static void main(String[] args) {
		Sup bp = new Sub();
		System.out.println("多态时-bp：" + bp.getName());
		System.out.println();
		
		Sub sub = new Sub();
		System.out.println("没有多态时-sub：" + sub.getName());
		System.out.println();
		
		Sup sup = new Sup();
		System.out.println("没有多态时-sup：" + sup.getName());
	}
}

class Sup{
	String name = "父类";
	public void setName(String name){
		this.name =  name;
	}
	public String getName(){
		System.out.println(this.getClass().getName());
		return name;
	}
}

class Sub extends Sup{
	String name = "子类";
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		System.out.println(this.getClass().getName());
		return name;
	}
}