package spring;
/**
 * http://blog.csdn.net/xingxiupaioxue/article/details/68943036
 * 一、Spring是什么？
 * 	Spring是一个轻量级的DI和AOP容器框架。
 * 		轻量级：Spring是非侵入式的(所谓非侵入式就是远程调试 而不植入，spring不再用new的方式来创建对象，而是使用依赖注入的方式)，
 * 			基于spring开发的应用一般不依赖于spring的类。
 * 		DI：称作依赖注入(Dependency Injection),和控制反转一个概念。具体的讲，当一个角色需要另外一个角色协助的时候，
 * 			在传统的程序设计中，通常有调用者来创建被调用者的实例。但是在spring中创建被调用者将不再有调用者完成，因此叫控制反转。
 * 			创建被调用对象有Spring来完成，在容器实例化对象的时候主动的将被调用者（或者说它的依赖对象）注入给调用对象，因此又叫依赖注入。
 * 		AOP：Spring对面向切面编程提供了强有力的支持，通过它让我们将业务逻辑从应用服务（如事务管理）中分离出来，
 * 			实现了高内聚开发(所谓高内聚就是常说的单一责任原则)。应用对象只关注业务逻辑，不再负责其它系统问题（如日志、事务等）。Spring支持用户自定义切面。
 * 
 * 二、使用Spring框架的好处：
 * 
 * 1. 方便面向切面编程，不影响主程序流。支持允许将一些通用任务如事务、日志等进行集中式管理，从而提供了更好的复用
 * 
 * 2. 使用Spring的IOC容器，将对象之间的依赖关系交给Spring，降低组件之间的耦合性，让我们更专注于应用逻辑
 * 	==》在没有使用Spring时：
 * 	场景：StudentService中需要到了TeacherDao的方法，但因为TeacherDao层提供的方法都是直接连接数据库进行增删改查，权限太大，
 * 		所以StudentService不是直接引用TeacherDao作为成员变量，而是引用TeacherService。
 * 		对于TeacherService引用StudentService作为成员变量的原因也一样。
 * 		代码如下所示：
 * 			class StudentService{
 * 				//成员变量：
 * 				private TeacherService teacherService = new TeacherService();
 * 			}
 * 			class TeacherService{
 * 				//成员变量：
 * 				private StudentService studentService = new StudentService();
 * 			}
 * 		上面的代码会出现死循环问题，最终导致内存溢出。原因：根据类的加载和new一个对象的过程。
 * 			【初始化Object-初始化StudentService类-初始化实例变量teacherService-初始化TeacherService类
 * 			-初始化实例变量studentService-回到StudentService类去初始化它的实例变量teacherService
 * 			-又回到TeacherService类去初始化它的实例变量studentService-...】
 * 		在不使用Spring的情况下，解决方法：
 * 			class StudentService{
 * 				//通过方法获得
 * 				public TeacherService getTeacherService(){
 * 					TeacherService teacherService = new TeacherService();
 * 				}
 * 			}
 * 			class TeacherService{
 * 				public StudentService getStudentService(){
 * 					StudentService studentService = new StudentService();
 * 				}
 * 			}
 * 		上面的代码存在的问题：一个请求过来，需要在StudentService中用到TeacherService的方法时，就需要随之new出一个TeacherService，增加内存垃圾，明显不合适。
 * 	==》使用Spring时：直接通过IOC容器创建一个Bean，将Bean注入到需要的地方即可。解决了死循环的问题，也解决了重复new对象的问题。
 * 
 * 补充：解耦：假设Servlet是单例的，Service是多例的，Dao是单例的，通过Spring的Bean注入就能很好的解决这个问题。
 * 
 * # Spring能消除在许多工程中常见的对Singleton的过多使用。
 * # Spring能有效地组织你的中间层对象。
 * # Spring能消除各种各样自定义格式的属性文件的需要，使配置信息一元化。
 * # Spring能够帮助我们真正意义上实现针对接口编程。
 * # 使用Spring构建的应用程序易于单元测试。
 * # JNDI抽象层，便于改变实现细节，可以方便地在远程服务和本地服务间切换。
 * # 简化访问数据库时的例外处理。
 * # 提供了JavaMail或其他邮件系统的支持。
 * # 可以提供众多服务，事务管理，WS等。
 * # 对主流的框架提供了很好的集成支持，如Hibernate,Struts2,JPA等
 * # Spring DI机制降低了业务对象替换的复杂性。
 * # Spring属于低侵入，代码污染极低。
 * # Spring的高度可开放性，并不强制依赖于Spring，开发者可以自由选择Spring部分或全部
 * 
 * ioc的思想最核心的地方在于，资源不由使用资源的双方管理，而由不使用资源的第三方管理，这可以带来很多好处。
 * 	第一，资源集中管理，实现资源的可配置和易管理。第二，降低了使用资源双方的依赖程度，也就是我们说的耦合度。
 */
public class A02AdvantageOfSpring {
	
}
