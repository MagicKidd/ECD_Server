package com.dehoo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dehoo.beans.Akas;
import com.dehoo.beans.HibernateSessionFactory;

public class AkasDao {
	
	/**
	 * Function: insert 添加
	 * @author dehoo­HuangDong 2013-6-18上午11:06:42
	 * @param aka
	 */
	public void insert(Akas aka) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			transaction.begin();
			session.save(aka);
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			transaction = null;
			session.close();
		}
	}
	
	// 删
	
	
	// 改
	
	
	// 查询所有
	
	
	/**
	 * Function: queryById 根据id进行查询
	 * @author dehoo­HuangDong 2013-6-21上午10:15:05
	 * @param id
	 * @return List<Akas>
	 */
	public List<Akas> queryById(int id) {
		Session session = null;
		List<Akas> list = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			String hql = " from Akas where movie_id = " + id;
			Query query = session.createQuery(hql); 
			list = query.list();
		} finally {
			session.close();
		}
		return list;
	}

}
