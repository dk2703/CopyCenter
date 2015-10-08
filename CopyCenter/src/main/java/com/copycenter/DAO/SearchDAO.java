package com.copycenter.DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copycenter.pojo.PrintOrder;
import com.copycenter.pojo.Student;

@Repository
public class SearchDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ArrayList<Student> searchStudent(String keyword) {
		Session session = null;
		Transaction t = null;
		ArrayList<Student> result = null;

		try {
			session = sessionFactory.openSession();
			FullTextSession fullTextSession = Search
					.getFullTextSession(session);
			t = fullTextSession.beginTransaction();

			QueryBuilder qb = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(Student.class).get();

			org.apache.lucene.search.Query luceneQ = qb
					.keyword()
					.onFields("firstName", "lastName", "contactNumber", "email")
					.matching(keyword).createQuery();

			org.hibernate.Query hibernateQuery = fullTextSession
					.createFullTextQuery(luceneQ, Student.class);

			System.out.println("\n\n\n\nHibernate Query\n\n\n\n"
					+ hibernateQuery.toString() + "\n\n\n\nEND\n\n\n\n");

			if (!hibernateQuery.list().isEmpty()) {
				result = (ArrayList<Student>) hibernateQuery.list();
			}

			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.println(e.toString());
		} finally {
			session.close();
		}
		return result;
	}
}
