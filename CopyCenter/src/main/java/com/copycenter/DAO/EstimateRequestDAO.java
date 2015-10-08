package com.copycenter.DAO;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.copycenter.pojo.EstimateRequest;

@Repository
public class EstimateRequestDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveMessage(EstimateRequest estimateRequest) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(estimateRequest);

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

	@Transactional
	public void getMessage(EstimateRequest estimateRequest) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(estimateRequest);

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

	@Transactional
	public void deleteMessage(ArrayList<Integer> idList) {
		Session session = null;
		Transaction transaction = null;
		Query q = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			for (Integer i : idList) {
				q = session
						.createQuery("delete EstimateRequest r where r.id =:temp");
				q.setParameter("temp", i);
				q.executeUpdate();
			}
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

	@Transactional
	public ArrayList<EstimateRequest> getByFromUser(String fromUser, String user) {
		Session session = null;
		Transaction transaction = null;
		ArrayList<EstimateRequest> msgList = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("from EstimateRequest e where lower(e.fromUser)=:username and lower(e.user)=:user");
			q.setString("username", fromUser);
			q.setString("user", user);

			msgList = (ArrayList<EstimateRequest>) q.list();
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return msgList;
	}

	@Transactional
	public ArrayList<EstimateRequest> getByUser(String user) {
		Session session = null;
		Transaction transaction = null;
		ArrayList<EstimateRequest> msgList = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("from EstimateRequest e where lower(e.user)=:username");
			q.setString("username", user);

			msgList = (ArrayList<EstimateRequest>) q.list();
			transaction.commit();

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return msgList;
	}

}
