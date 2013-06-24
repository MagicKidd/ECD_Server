package com.dehoo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dehoo.beans.Casts;
import com.dehoo.beans.HibernateSessionFactory;

public class CastsDao {

	/**
	 * Function: insert 添加
	 * @author dehoo­HuangDong 2013-6-18上午11:08:04
	 * @param cast
	 */
	public void insert(Casts cast) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			transaction.begin();
			session.save(cast);
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
	 * Function: queryById 根据id查询
	 * @author dehoo­HuangDong 2013-6-21上午10:17:39
	 * @param id
	 * @return List<Casts>
	 */
	public List<Casts> queryById(int id) {
		List<Casts> list = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			String hql = " from Casts where movie_id = " + id;
			Query query = session.createQuery(hql);
			list = query.list();
		} finally {
			session.close();
		}
		return list;
	}
}
