package MyIOCAndMyAop.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����֪ͨ����������ִ���꣬��ִ�е�֪ͨ��
 * 
 * @author lightli
 * 2019��6��27��
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterReturning {

}
