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

import com.copycenter.pojo.PrintJob;
import com.copycenter.pojo.PrintOrder;

@Repository
public class PrintJobDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveJob(PrintJob printJob) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(printJob);

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
	public ArrayList<PrintJob> getPrintJobList(PrintOrder printOrder) {
		Session session = null;
		Transaction transaction = null;
		ArrayList<PrintJob> printJob = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = (Query) session
					.createQuery("from PrintJob pj where pj.printOrder =:order");
			query.setParameter("order", printOrder);
			printJob = new ArrayList<PrintJob>();

			for (Object obj : query.list()) {
				PrintJob documentObj = (PrintJob) obj;
				printJob.add(documentObj);
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
		return printJob;
	}

	@Transactional
	public ArrayList<PrintJob> getPrintJobByID(int orderId) {
		Session session = null;
		Transaction transaction = null;
		ArrayList<PrintJob> printJob = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = (Query) session
					.createQuery("from PrintJob where printOrder_id =:orderid");
			query.setParameter("orderid", orderId);
			printJob = new ArrayList<PrintJob>();

			for (Object obj : query.list()) {
				PrintJob documentObj = (PrintJob) obj;
				printJob.add(documentObj);
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
		return printJob;
	}

	@Transactional
	public PrintJob getSinglePrintJobByID(int printJobId) {
		Session session = null;
		Transaction transaction = null;
		PrintJob printJob = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = (Query) session
					.createQuery("from PrintJob where printJobID =:printJobid");
			query.setParameter("printJobid", printJobId);
			printJob = (PrintJob) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return printJob;
	}

	@Transactional
	public void deleteJobBy(int printJobId) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = (Query) session
					.createQuery("delete PrintJob where printJobID =:printJobid");
			query.setParameter("printJobid", printJobId);
			int r = query.executeUpdate();
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
