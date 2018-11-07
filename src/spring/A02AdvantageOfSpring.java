package spring;
/**
 * http://blog.csdn.net/xingxiupaioxue/article/details/68943036
 * һ��Spring��ʲô��
 * 	Spring��һ����������DI��AOP������ܡ�
 * 		��������Spring�Ƿ�����ʽ��(��ν������ʽ����Զ�̵��� ����ֲ�룬spring������new�ķ�ʽ���������󣬶���ʹ������ע��ķ�ʽ)��
 * 			����spring������Ӧ��һ�㲻������spring���ࡣ
 * 		DI����������ע��(Dependency Injection),�Ϳ��Ʒ�תһ���������Ľ�����һ����ɫ��Ҫ����һ����ɫЭ����ʱ��
 * 			�ڴ�ͳ�ĳ�������У�ͨ���е������������������ߵ�ʵ����������spring�д����������߽������е�������ɣ���˽п��Ʒ�ת��
 * 			���������ö�����Spring����ɣ�������ʵ���������ʱ�������Ľ��������ߣ�����˵������������ע������ö�������ֽ�����ע�롣
 * 		AOP��Spring�������������ṩ��ǿ������֧�֣�ͨ���������ǽ�ҵ���߼���Ӧ�÷�������������з��������
 * 			ʵ���˸��ھۿ���(��ν���ھ۾��ǳ�˵�ĵ�һ����ԭ��)��Ӧ�ö���ֻ��עҵ���߼������ٸ�������ϵͳ���⣨����־������ȣ���Spring֧���û��Զ������档
 * 
 * ����ʹ��Spring��ܵĺô���
 * 
 * 1. �������������̣���Ӱ������������֧������һЩͨ��������������־�Ƚ��м���ʽ�����Ӷ��ṩ�˸��õĸ���
 * 
 * 2. ʹ��Spring��IOC������������֮���������ϵ����Spring���������֮�������ԣ������Ǹ�רע��Ӧ���߼�
 * 	==����û��ʹ��Springʱ��
 * 	������StudentService����Ҫ����TeacherDao�ķ���������ΪTeacherDao���ṩ�ķ�������ֱ���������ݿ������ɾ�Ĳ飬Ȩ��̫��
 * 		����StudentService����ֱ������TeacherDao��Ϊ��Ա��������������TeacherService��
 * 		����TeacherService����StudentService��Ϊ��Ա������ԭ��Ҳһ����
 * 		����������ʾ��
 * 			class StudentService{
 * 				//��Ա������
 * 				private TeacherService teacherService = new TeacherService();
 * 			}
 * 			class TeacherService{
 * 				//��Ա������
 * 				private StudentService studentService = new StudentService();
 * 			}
 * 		����Ĵ���������ѭ�����⣬���յ����ڴ������ԭ�򣺸�����ļ��غ�newһ������Ĺ��̡�
 * 			����ʼ��Object-��ʼ��StudentService��-��ʼ��ʵ������teacherService-��ʼ��TeacherService��
 * 			-��ʼ��ʵ������studentService-�ص�StudentService��ȥ��ʼ������ʵ������teacherService
 * 			-�ֻص�TeacherService��ȥ��ʼ������ʵ������studentService-...��
 * 		�ڲ�ʹ��Spring������£����������
 * 			class StudentService{
 * 				//ͨ���������
 * 				public TeacherService getTeacherService(){
 * 					TeacherService teacherService = new TeacherService();
 * 				}
 * 			}
 * 			class TeacherService{
 * 				public StudentService getStudentService(){
 * 					StudentService studentService = new StudentService();
 * 				}
 * 			}
 * 		����Ĵ�����ڵ����⣺һ�������������Ҫ��StudentService���õ�TeacherService�ķ���ʱ������Ҫ��֮new��һ��TeacherService�������ڴ����������Բ����ʡ�
 * 	==��ʹ��Springʱ��ֱ��ͨ��IOC��������һ��Bean����Beanע�뵽��Ҫ�ĵط����ɡ��������ѭ�������⣬Ҳ������ظ�new��������⡣
 * 
 * ���䣺�������Servlet�ǵ����ģ�Service�Ƕ����ģ�Dao�ǵ����ģ�ͨ��Spring��Beanע����ܺܺõĽ��������⡣
 * 
 * # Spring����������๤���г����Ķ�Singleton�Ĺ���ʹ�á�
 * # Spring����Ч����֯����м�����
 * # Spring���������ָ����Զ����ʽ�������ļ�����Ҫ��ʹ������ϢһԪ����
 * # Spring�ܹ�������������������ʵ����Խӿڱ�̡�
 * # ʹ��Spring������Ӧ�ó������ڵ�Ԫ���ԡ�
 * # JNDI����㣬���ڸı�ʵ��ϸ�ڣ����Է������Զ�̷���ͱ��ط�����л���
 * # �򻯷������ݿ�ʱ�����⴦��
 * # �ṩ��JavaMail�������ʼ�ϵͳ��֧�֡�
 * # �����ṩ�ڶ�����������WS�ȡ�
 * # �������Ŀ���ṩ�˺ܺõļ���֧�֣���Hibernate,Struts2,JPA��
 * # Spring DI���ƽ�����ҵ������滻�ĸ����ԡ�
 * # Spring���ڵ����룬������Ⱦ���͡�
 * # Spring�ĸ߶ȿɿ����ԣ�����ǿ��������Spring�������߿�������ѡ��Spring���ֻ�ȫ��
 * 
 * ioc��˼������ĵĵط����ڣ���Դ����ʹ����Դ��˫���������ɲ�ʹ����Դ�ĵ�������������Դ����ܶ�ô���
 * 	��һ����Դ���й���ʵ����Դ�Ŀ����ú��׹����ڶ���������ʹ����Դ˫���������̶ȣ�Ҳ��������˵����϶ȡ�
 */
public class A02AdvantageOfSpring {
	
}
