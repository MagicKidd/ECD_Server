package com.dehoo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dehoo.beans.Countries;
import com.dehoo.beans.HibernateSessionFactory;

public class CountriesDao {

	/**
	 * Function: insert 添加
	 * @author dehoo­HuangDong 2013-6-18上午11:08:57
	 * @param country
	 */
	public void insert(Countries country) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			transaction.begin();
			session.save(country);
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
	 * @author dehoo­HuangDong 2013-6-21上午10:20:09
	 * @param id
	 * @return List<Countries>
	 */
	public List<Countries> queryById(int id) {
		List<Countries> list = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			String hql = " from Countries where movie_id = " + id;
			Query query = session.createQuery(hql);
			list = query.list();
		} finally {
			session.close();
		}
		return list;
	}

}
