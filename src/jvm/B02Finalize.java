package jvm;

/**
 * 以下是一篇博客上说的,讲的挺好.
 * 
 关于finalize方法说明： 
垃圾回收时，如果判断对象不可达，且覆盖了finalize方法，则会将对象放入到F-Queue队列 ，
	有一个名为”Finalizer”的守护线程执行finalize方法，它的优先级为8，做最后的清理工作，
	执行finalize方法完毕后，GC会再次判断该对象是否可达，若不可达，则进行回收，否则，对象复活 
	注意：网上很多人说 ，Finalizer线程的优先级低，个人认为这是不对的，Finalizer线程在jdk1.8的优先级是8，比我们创建线程默认优先级5要高，
	之前其它版本的jdk我记得导出的线程栈信息里面优先级是5，忘记是哪个版本的jdk了，即使是5优先级也不比自建的线程默认优先级低，总之我没见过优先级低于5的Finalizer线程； 
	这个线程会不停的循环等待java.lang.ref.Finalizer.ReferenceQueue中的新增对象。一旦Finalizer线程发现队列中出现了新的对象，
	它会弹出该对象，调用它的finalize()方法，将该引用从Finalizer类中移除，因此下次GC再执行的时候，这个Finalizer实例以及它引用的那个对象就可以回垃圾回收掉了。 
大多数时候，Finalizer线程能够赶在下次GC带来更多的Finalizer对象前清空这个队列,但是当它的处理速度没法赶上新对象创建的速度，
	对象创建的速度要比Finalizer线程调用finalize()结束它们的速度要快，这导致最后堆中所有可用的空间都被耗尽了; 
当我们大量线程频繁创建重写了finalizer（）方法的对象的情况下，高并发情况下，它可能会导致你内存的溢出；虽然Finalizer线程优先级高，
	但是毕竟它只有一个线程；最典型的例子就是数据库连接池,proxool，对要释放资源的操作加了锁，并在finalized方法中调用该加锁方法，
	在高并发情况下，锁竞争严重，finalized竞争到锁的几率减少，finalized无法立即释放资源，越来越多的对象finalized()方法无法被执行，
	资源无法被回收，最终导致导致oom；所以覆盖finalized方法，执行一定要快，不能有锁竞争的操作，否则在高并发下死的很惨； 
（proxool使用了cglib，它用WrappedConnection代理实际的Conneciton。在运行WrappedConnection的方法时，包括其finalize方法，都会调用Conneciton.isClosed()方法去判断是否真的需要执行某些操作。不幸的是JDBC中的这个方法是同步的，锁是连接对象本身。于是， Finalizer线程回收刚执行过的WrappedConnection对象时就总会与还在使用Connection的各个工作线程争用锁。）
 * @author lgr
 *
 */
public class B02Finalize {

}
