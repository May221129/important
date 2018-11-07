package spring;
/**
 * Servlet、Service、Dao是单例的吗？为什么是单例或为什么不是单例？
 * 
 * 一. 一般情况下，Servlet都是单实例的。
 * 	1. 在Web项目启动时（或者是在接收到第一个请求时），Tomcat会自动扫描web.xml；
 * 	2. 在web.xml中，会通过下面的配置，创建一个Servlet的实例：
 * 		<servlet>
 *			<description></description>
 *			<display-name>UserServlet</display-name>
 *			<servlet-name>UserServlet</servlet-name>
 *			<servlet-class>com.llm.bookstore.servlet.UserServlet</servlet-class>
 * 		</servlet>
 * 	3. 这里的UserServlet类并非单例模式的，我们只是在程序上控制了它是单实例的，因为只在xml配置文件中创建了一个实例，并未在其他地方声明Servlet的实例。
 * 		如果你想创建多个Servlet的实例，也是可以的。
 * 
 * 二. Service和Dao也是单实例的：
 * 	1. Service是Servlet的成员变量，Dao是Service的成员变量。因为Servlet是单实例的，在初始化Servlet实例的时候，就已经将Service和Dao一起创建好了。
 * 		（这里涉及到对象的初始化：先创建父类的实例-再new出自己的实例-初始化实例变量-执行动态代码块）
 * 	2. 用户拿到的Servlet都是同一个Servlet。
 * 
 * 三. 为什么Servlet、Service、Dao一般都是单例的：通过反证法来说明
 * 	Servlet、Service、Dao基本上都是无状态的。一般状态都是放在page、request、session、application。
 * 	如果一个请求(查询用户信息的请求)过来，就需要创建一个Servlet对象，并随之创建一个Service和Dao的实例，这样是不合理的。
 */
public class A01Servlet_Service_Dao_IsSingleton {
	
}
