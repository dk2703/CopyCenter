package com.copycenter.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.copycenter.pojo.StoreManager;

@Repository
public class StoreManagerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveStoreManager(StoreManager storeManager) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(storeManager);

			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
	}

}
