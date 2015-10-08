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

import com.copycenter.pojo.Student;
import com.copycenter.pojo.UserAccount;

@Repository
public class StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveStudent(Student student) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(student);

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
	public Student getStudent(UserAccount userAccount) {
		Session session = null;
		Transaction transaction = null;
		Student s = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = (Query) session
					.createQuery("from Student where userAccount =:useraccount");
			query.setParameter("useraccount", userAccount);

			s = (Student) query.uniqueResult();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return s;
	}

	@Transactional
	public Student getStudentByUserName(String userName) {
		Session session = null;
		Transaction transaction = null;
		Student s = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Query query = (Query) session
					.createQuery("from Student s where lower(s.username) like :username");
			query.setParameter("username", userName);

			s = (Student) query.uniqueResult();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
				System.out.println(e.toString());
			}
		} finally {
			session.close();
		}
		return s;
	}

	@Transactional
	public void updateStudent(Student student) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.update(student);

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
