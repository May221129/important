package Algorithm;

import org.junit.Test;

public class A01_StrStr {
	
	@Test
	public void testStrStr(){
		String source = "abeabcaf";
		String target = "abc";
		int count = strStr(source, target);
		System.out.println(count);//3
	}
	/**
	 * strstr(str1,str2) 函数用于判断字符串str2是否是str1的子串。如果是，则该函数返回str2在str1中首次出现的地址；否则，返回NULL。
	 */
	public int strStr(String source, String target){
		if(source == null || target == null){
			return -1;
		}
		for(int i = 0; i < (source.length() - target.length()); i++){
			for(int j = 0; j < target.length(); j++){
				if(source.charAt(i + j) != target.charAt(j) ){
					break;
				}
				if(j == target.length() - 1){
					return i;
				}
			}
		}
		return -1;
	}
}
