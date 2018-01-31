package examples.pubhub.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Implementation for the BookDAO, responsible for querying the database for
 * Book objects.
 */
public class BookDAOImpl implements BookDAO {

	Session session = null;
	Transaction tx = null;

	/*------------------------------------------------------------------------------------------------*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {

		List<Book> books = new ArrayList<>();

		// Create Session

		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();
			books = session.createQuery("FROM Book").getResultList();
			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		// return the list of Book objects populated by the DB.
		return books;
	}

	/*------------------------------------------------------------------------------------------------*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooksByTitle(String title) {

		List<Book> books = new ArrayList<>();

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("FROM Book b where b.title LIKE= :title");
			query.setParameter("title", "%" + title + "%");

			books = query.getResultList();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return books;
	}

	/*------------------------------------------------------------------------------------------------*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooksByAuthor(String author) {
		List<Book> books = new ArrayList<>();

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("FROM Book b where b.author LIKE= :author");
			query.setParameter("author", "%" + author + "%");

			books = query.getResultList();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return books;
	}

	/*------------------------------------------------------------------------------------------------*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooksLessThanPrice(double price) {
		List<Book> books = new ArrayList<>();

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("FROM Book b where b.price <:price");
			query.setParameter("price", price);

			books = query.getResultList();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return books;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public Book getBookByISBN(String isbn) {
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

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean addBook(Book book) {

		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			// start transaction
			tx = session.beginTransaction();

			// save session
			session.save(book);

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
	public boolean updateBook(Book book) {
		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			// update using session.update method

			session.update(book);

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
	public boolean deleteBookByISBN(String isbn) {
		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			Book book = getBookByISBN(isbn);

			// delete using session.delete method

			session.delete(book);

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

}
