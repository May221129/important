package spring;
/**
 * Servlet��Service��Dao�ǵ�������Ϊʲô�ǵ�����Ϊʲô���ǵ�����
 * 
 * һ. һ������£�Servlet���ǵ�ʵ���ġ�
 * 	1. ��Web��Ŀ����ʱ���������ڽ��յ���һ������ʱ����Tomcat���Զ�ɨ��web.xml��
 * 	2. ��web.xml�У���ͨ����������ã�����һ��Servlet��ʵ����
 * 		<servlet>
 *			<description></description>
 *			<display-name>UserServlet</display-name>
 *			<servlet-name>UserServlet</servlet-name>
 *			<servlet-class>com.llm.bookstore.servlet.UserServlet</servlet-class>
 * 		</servlet>
 * 	3. �����UserServlet�ಢ�ǵ���ģʽ�ģ�����ֻ���ڳ����Ͽ��������ǵ�ʵ���ģ���Ϊֻ��xml�����ļ��д�����һ��ʵ������δ�������ط�����Servlet��ʵ����
 * 		������봴�����Servlet��ʵ����Ҳ�ǿ��Եġ�
 * 
 * ��. Service��DaoҲ�ǵ�ʵ���ģ�
 * 	1. Service��Servlet�ĳ�Ա������Dao��Service�ĳ�Ա��������ΪServlet�ǵ�ʵ���ģ��ڳ�ʼ��Servletʵ����ʱ�򣬾��Ѿ���Service��Daoһ�𴴽����ˡ�
 * 		�������漰������ĳ�ʼ�����ȴ��������ʵ��-��new���Լ���ʵ��-��ʼ��ʵ������-ִ�ж�̬����飩
 * 	2. �û��õ���Servlet����ͬһ��Servlet��
 * 
 * ��. ΪʲôServlet��Service��Daoһ�㶼�ǵ����ģ�ͨ����֤����˵��
 * 	Servlet��Service��Dao�����϶�����״̬�ġ�һ��״̬���Ƿ���page��request��session��application��
 * 	���һ������(��ѯ�û���Ϣ������)����������Ҫ����һ��Servlet���󣬲���֮����һ��Service��Dao��ʵ���������ǲ�����ġ�
 */
public class A01Servlet_Service_Dao_IsSingleton {
	
}
