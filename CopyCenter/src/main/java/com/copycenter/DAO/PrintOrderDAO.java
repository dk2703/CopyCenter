package com.copycenter.DAO;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.copycenter.pojo.PrintOrder;

@Repository
public class PrintOrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void savePrintOrder(PrintOrder printOrder) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(printOrder);

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
	public ArrayList<PrintOrder> getAllOrders() {
		Session session = null;
		Transaction transaction = null;
		ArrayList<PrintOrder> orderList = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session.createQuery("from PrintOrder");

			orderList = (ArrayList<PrintOrder>) q.list();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return orderList;
	}

	@Transactional
	public void setStatus(int id, String status) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = session
					.createQuery("update PrintOrder set status=:status where orderID=:id");
			q.setString("status", status);
			q.setInteger("id", id);
			int result = q.executeUpdate();
			System.out.println("\n\n\n" + result + "\n\n\n");
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
	public ArrayList<PrintOrder> getOrdersByUser(int id) {
		Session session = null;
		Transaction transaction = null;
		ArrayList<PrintOrder> orderList = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("from PrintOrder where student_id=:id");
			q.setInteger("id", id);
			orderList = (ArrayList<PrintOrder>) q.list();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return orderList;
	}

	@Transactional
	public PrintOrder getOrdersByOrderID(int id) {
		Session session = null;
		Transaction transaction = null;
		PrintOrder order = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("from PrintOrder where orderID=:id");
			q.setInteger("id", id);
			order = (PrintOrder) q.uniqueResult();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return order;
	}

	@Transactional
	public void deleteOrderAtOrderID(int id) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("delete PrintOrder where orderID=:id");
			q.setInteger("id", id);
			int result = q.executeUpdate();

			System.out.println("\n\n" + result + "\n\n");

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
	public void statusUpdatedBy(int id) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query q = (Query) session
					.createQuery("delete PrintOrder where storeMAnager_id=:id");
			q.setInteger("id", id);
			int result = q.executeUpdate();

			System.out.println("\n\n" + result + "\n\n");

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
