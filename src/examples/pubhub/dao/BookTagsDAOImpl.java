package examples.pubhub.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import examples.pubhub.model.Book;
import examples.pubhub.model.BookTags;
import examples.pubhub.utilities.DAOUtilities;

public class BookTagsDAOImpl implements BookTagsDAO {
	Session session = null;
	Transaction tx = null;
	List<BookTags> bookTag = new ArrayList<>();

	/*------------------------------------------------------------------------------------------------*/

	@SuppressWarnings("unchecked")
	@Override
	public List<BookTags> getBookTags() {

		// call a session
		session = DAOUtilities.getSession().openSession();
		try {
			tx = session.beginTransaction();
			bookTag = session.createQuery("FROM BookTags").getResultList();
			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return bookTag;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public BookTags getBookByTagName(String tagName) {

		BookTags booktag = null;

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("FROM BookTags t where t.tagName= :tagName");
			query.setParameter("tagName", tagName);
			// get Unique result using result type that matches the query
			booktag = (BookTags) query.uniqueResult();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return booktag;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public boolean addBookTag(BookTags tag) {
		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			// start transaction
			tx = session.beginTransaction();

			// save session
			session.save(tag);

			// commit transaction
			tx.commit();

			return true;

			// session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

			return false;
		} finally {

			session.close();

		}

	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public boolean updateTag(String OldTag, String Newtag) {
		// TODO Auto-generated method stub
		return false;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public boolean deleteBookTag(String tagName, String isbn) {
		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("DELETE FROM BookTags t where t.tagName= :tagName");
			query.setParameter("tagName", tagName);

			query.executeUpdate();
			// commit transaction
			session.getTransaction().commit();

			return true;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			return false;
			// e.printStackTrace();

		} finally {
			session.close();

		}
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean createBookTag(String isbn, String tagName) {

		Book book = getBookByISBN(isbn);
		BookTags tag = new BookTags();
		tag.setTagName(tagName);
		tag.setBook(book);
		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			// start transaction
			tx = session.beginTransaction();

			// save session
			session.save(tag);

			// session.getTransaction().commit();

			// commit transaction
			tx.commit();

			return true;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

			return false;
		} finally {

			session.close();

		}
	}

	/*------------------------------------------------------------------------------------------------*/
	private Book getBookByISBN(String isbn) {

		Book book = null;

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();
			book = session.get(Book.class, isbn);
			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return book;
	}

}
