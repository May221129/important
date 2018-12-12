package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池内存溢出
 * 1.VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 2.运行结果：并没有如逾期的报OutOfMemoryError：PermGen Space
 * 3.未报异常的原因：
 * 	①在jdk1.6之后的版本，已经不存在permSpace，而是换成metaSpace了，所以上面的VM args配置无效。
 * 	②在jdk1.6之后的版本，拘留字符串也不放在metaspace中，而是堆中。
 * 4.依据3所言，拘留字符串已经放到了堆中，所以把VM args参数设置为堆内存参数-Xms20M -Xmx20M。
 * 	结果：抛了Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class C03RuntimeConstanPoolOOM {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
