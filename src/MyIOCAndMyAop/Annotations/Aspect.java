package MyIOCAndMyAop.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ɨ��BeanFactory���ҳ���������@Aspectע���bean��Ϊ�䴴����������󣬲����ԭbean��
 * 	����дSpring AOP��com.llm.a02aop.a02useaopwithannotation.A03LoggingAspect��
 * 
 * @author May
 * 2019��6��27��
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

}
