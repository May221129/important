package MyIOCAndMyAop.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ǰ��֪ͨ�����㷽�����쳣�ˣ�Ҳ��ִ�е�֪ͨ��
 * 
 * @author May
 * 2019��6��27��
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface After {

}