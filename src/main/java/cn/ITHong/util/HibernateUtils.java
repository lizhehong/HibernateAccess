package cn.ITHong.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;

public class HibernateUtils {
	/**
	 * 1.数据库的的链接信息，映射文件的信息，持久类的信息 2.是由单例模式产生的 3.一般一个hibernate应该有一个数据库链接 4.本身线程安全
	 * 5.重量级类
	 * */
	public static SessionFactory sessionFactory;
	public static String url;

	@Before
	public  void init() {
		/**
		 * 配置文件准备
		 * */
		Configuration configuration = new Configuration();
		/**
		 * 加载配置文件 映射文件
		 * */
		if (url == null || url.equals("")) {
			System.out.println("启动默认的cfg.xml路径");
			configuration.configure();
		} else {
			configuration.configure(url);
		}
		/**
		 * 
		 * */
		sessionFactory = configuration.buildSessionFactory();
	}

	/**
	 * 创建级联或者非级联的对象 表
	 * 
	 * @param <T>
	 * */
	public static <T> void Create(List<T> ts) {
		Session session = HibernateUtils.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		for (T t : ts)
			session.save(t);

		transaction.commit();
		session.close();
	}
}
