package com.dehoo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dehoo.beans.HibernateSessionFactory;
import com.dehoo.beans.Movies;

public class MoviesDao {

	/**
	 * Function: insert 添加
	 * @author dehoo­HuangDong 2013-6-20上午9:59:57
	 * @param movie
	 * @return 添加操作是否成功
	 */
	public boolean insert(Movies movie) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			transaction.begin();
			session.save(movie);
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return false;
		} finally {
			transaction = null;
			session.close();
		}
		return true;
	}
	
	// 删
	
	
	// 改
	
	
	/**
	 * Function: queryAll 查询所有
	 * @author dehoo­HuangDong 2013-6-18上午11:04:47
	 * @return List<Movies>
	 */
	public List<Movies> queryAll() {
		Session session = null;
		List<Movies> movies = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			String hql = " from Movies";
			Query query = session.createQuery(hql);
			movies = query.list();
		} finally {
			session.close();
		}
		return movies;
	}
	
	
	/**
	 * Function: queryByDoubanId 根据影片的豆瓣id值查询
	 * @author dehoo­HuangDong 2013-6-18下午7:15:01
	 * @param id 影片的豆瓣id
	 * @return Movies
	 */
	public Movies queryByDoubanId(int id){
		Session session = null;
		Movies movie = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			String hql = " from Movies where douban_id = " + id;
			Query query = session.createQuery(hql);
			movie = (Movies) query.list().get(0);
		} finally {
			session.close();
		}
		return movie;
	}
}
