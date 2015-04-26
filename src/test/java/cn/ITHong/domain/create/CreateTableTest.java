package cn.ITHong.domain.create;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.ITHong.util.HibernateUtils;
import junit.framework.TestCase;

public class CreateTableTest extends HibernateUtils{
	static{
		url="cn/ITHong/domain/hibernate.cfg.xml";
	}
	@Test
	public void testCreateTabele(){
		Session session =  sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		
		
		
		
		transaction.commit();
	}
}
