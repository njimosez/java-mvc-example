package examples.pubhub.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import examples.pubhub.model.BookOrder;
import examples.pubhub.model.CartItem;
import examples.pubhub.model.PurchasedBooks;
import examples.pubhub.model.ShoppingCart;
import examples.pubhub.model.Users;
import examples.pubhub.utilities.DAOUtilities;

public class BookOrderDAOImpl implements BookOrderDAO, PurchasedBooksDAO {

	Session session = null;
	Transaction tx = null;
	private static final String ORDER_STATUS = "Digital";

	@Override
	public boolean CreateOrder(Users user, ShoppingCart cart, Long orderId) {

		BookOrder order = new BookOrder(orderId, cart.getGrandTotal(), LocalDate.now(), ORDER_STATUS, user);

		// Get the set of items listed in the customer cart after checkout
		List<CartItem> itemsBought = DAOUtilities.getCartItemDAO().getUserCartItemsByShoppingCardId(cart.getId());

		List<PurchasedBooks> purchasedBooks = new ArrayList<PurchasedBooks>();
		for (CartItem items : itemsBought) {
			PurchasedBooks purchasedItems = new PurchasedBooks(items.getBooks().getPrice(), items.getQuantity(),
					items.getSubTotal(), items.getBooks(), order);
			purchasedBooks.add(purchasedItems);
		}

		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			// start transaction
			tx = session.beginTransaction();

			// save session the new order and purchased books
			session.save(order);
			for (PurchasedBooks tembook : purchasedBooks) {
				session.save(tembook);

			}

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

	@Override
	public List<PurchasedBooks> getBooksByOrderNo(Long BookId) {
		List<PurchasedBooks> boughItems = new ArrayList<>();

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			Query<PurchasedBooks> query = session.createQuery("FROM PurchasedBooks p where bookorder.id= :BookId");
			query.setParameter("BookId", BookId);
			boughItems = query.getResultList();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return boughItems;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public BookOrder getOrderByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public BookOrder findbyId(Long id) {
		BookOrder order = null;

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();
			order = session.get(BookOrder.class, id);
			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return order;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public List<BookOrder> getAllOrderForUser(String username) {
		List<BookOrder> userOrders = new ArrayList<>();

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			Query<BookOrder> query = session.createQuery("FROM BookOrder b where users.username = :username");
			query.setParameter("username", username);
			userOrders = query.getResultList();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return userOrders;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public BookOrder MCreateOrder(Users user, ShoppingCart cart, Long Id) {
		BookOrder order = new BookOrder(Id, cart.getGrandTotal(), LocalDate.now(), ORDER_STATUS, user);

		// Get the set of items listed in the customer cart after checkout
		List<CartItem> itemsBought = DAOUtilities.getCartItemDAO().getUserCartItemsByShoppingCardId(cart.getId());

		List<PurchasedBooks> purchasedBooks = new ArrayList<PurchasedBooks>();
		for (CartItem items : itemsBought) {
			PurchasedBooks purchasedItems = new PurchasedBooks(items.getBooks().getPrice(), items.getQuantity(),
					items.getSubTotal(), items.getBooks(), order);
			purchasedBooks.add(purchasedItems);
		}

		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			// start transaction
			tx = session.beginTransaction();

			// save session the new order and purchased books
			session.save(order);
			for (PurchasedBooks tembook : purchasedBooks) {
				session.save(tembook);

			}
			order = session.get(BookOrder.class, Id);
			// commit transaction
			tx.commit();

			return order;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

			return null;
		} finally {

			session.close();

		}
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public List<PurchasedBooks> getBooksOrderedByUser(String username) {
		List<PurchasedBooks> boughtByUser = new ArrayList<>();

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			Query<PurchasedBooks> query = session
					.createQuery("FROM PurchasedBooks p where bookorder.users.username= :username");
			query.setParameter("username", username);
			boughtByUser = query.getResultList();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return boughtByUser;
	}

}
