package com.copycenter.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.copycenter.pojo.UserAccount;

@Repository
public class UserAccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveUserAccount(UserAccount userAccount) {

		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(userAccount);

			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.print(e.toString());
			}
		} finally {
			session.close();
		}
	}

	@Transactional
	public UserAccount getUserAccount(UserAccount userAccount) {

		UserAccount ua = null;
		Transaction transaction = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("from UserAccount where userName =:username");
			q.setParameter("username", userAccount.getUserName());

			ua = (UserAccount) q.uniqueResult();
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.print(e.toString());
			}
		} finally {
			session.close();
		}
		return ua;
	}

	@Transactional
	public UserAccount getUserAccountByUserName(String userName) {

		Transaction transaction = null;
		Session session = null;
		UserAccount ua = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("from UserAccount where userName =:username");
			q.setString("username", userName);

			ua = (UserAccount) q.uniqueResult();

			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return ua;
	}

	@Transactional
	public boolean checkUserName(String userName) {

		Transaction transaction = null;
		Session session = null;
		UserAccount ua = null;
		boolean flag = false;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("from UserAccount where userName =:username");
			q.setString("username", userName);

			ua = (UserAccount) q.uniqueResult();
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		if (ua == null) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

}
