package Algorithm;

import org.junit.Test;

/**
 * 探究：位运算和取模运算的运算效率对比。
 * 	总说计算机是擅长于做位运算的，那么到底它有多擅长呢？
 * 
 * 结果：（时间单位：毫秒）
 * 	计算次数			位运算			取模运算			倍数（位运算：取模运算）
 * 	10万：			734				20489			27
 * 	100万：			742				20544			27
 * 	1000万：			735				20408			27
 * 	1亿：			712				19545			27
 * 
 * @author May
 * 2019年7月26日
 */
public class BitAndModulus {
	@Test
	public void bit() {
		int number = 10000 * 10;
		int a = 1;
		
		long start = System.currentTimeMillis();
		for(int i = number; i > 0 ; i++) {
			a &= i;
		}
		long end = System.currentTimeMillis();
		System.out.println("位运算耗时： " + (end - start));
	}
	
	@Test
	public void modulus() {
		int number = 10000 * 1000;
		int a = 1;
		
		long start = System.currentTimeMillis();
		for(int i = number; i > 0; i++) {
			a %= i;
		}
		long end = System.currentTimeMillis();
		System.out.println("取模运算耗时： " + (end - start));
	}
}
