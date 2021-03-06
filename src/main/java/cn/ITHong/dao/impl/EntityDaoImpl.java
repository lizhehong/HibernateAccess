package cn.ITHong.dao.impl;

import java.io.Serializable;
import java.util.List;













import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.ITHong.dao.EntityDao;
import cn.ITHong.domain.User;
import cn.ITHong.util.HibernateUtils;

public class EntityDaoImpl<T> extends HibernateUtils implements EntityDao<T>{
	static{
		url="cn/ITHong/domain/hibernate.cfg.xml";
		new HibernateUtils().init();
	}

	public List getAllEntity(Class classt) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<T> ts = session.createQuery("from "+classt.getSimpleName()).list();
		
		transaction.commit();
		
		return ts;
		
	}

	public <T> T findByID(Class<T> t, Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		T t1 = (T) session.get(t, id);
		transaction.commit();
		return t1;
	}

	public void saveEntity(T t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		T t1 = (T) session.save(t);
		transaction.commit();
		
	}

	public <T> void updateEntry(T t) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(t);
		transaction.commit();
		
	}

	public <T> void deleteEntity(Class<T> t,Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		T tt = (T) session.get(t, id);
		
		session.delete(tt);
		transaction.commit();
		
	}
	public <T> T findEntity(Class<T> T, String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from User where username=:username and password=:password");
		query.setString("username", username);
		query.setString("password", password);
		List<T> tList = query.list();
		transaction.commit();
		if(tList.size()>0&&tList!=null)
			return tList.get(0);
			else{
				return null;
			}
	}


}
