package examples.pubhub.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import examples.pubhub.model.Book;
import examples.pubhub.model.CartItem;
import examples.pubhub.model.ShoppingCart;
import examples.pubhub.utilities.DAOUtilities;

public class CartItemDAOImpl implements CartItemDAO {
	Session session = null;
	Transaction tx = null;
	// private static Logger log = Logger.getLogger(CartItem.class);

	@Override
	public boolean addCartItem(Book books, ShoppingCart shoppingCart) {
		// if exist update instead
		int quantity = 1;
		// BigDecimal grandTotal = new BigDecimal(books.getPrice()).multiply(new
		// BigDecimal(0));
		// BigDecimal cartTotal = getCartTotal(shoppingCart); //= new
		// BigDecimal(books.getPrice());
		CartItem item = new CartItem();
		item.setBooks(books);
		item.setShoppingCart(shoppingCart);
		item.setQuantity(quantity);
		item.setSubTotal(new BigDecimal(books.getPrice()).multiply(new BigDecimal(quantity)));
		// item.setShoppingCart(shoppingCart.setGrandTotal(grandTotal));
		// shoppingCart.setGrandTotal(grandTotal);

		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			// start transaction
			tx = session.beginTransaction();

			// save session
			session.save(item);
			// session.update(shoppingCart);

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

	@SuppressWarnings("unchecked")
	@Override
	public List<CartItem> getUserCartItemsByShoppingCardId(Long cartID) {
		List<CartItem> userCartItem = new ArrayList<>();

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			Query<CartItem> query = session.createQuery("FROM CartItem c where shoppingCart.id= :cartID");
			query.setParameter("cartID", cartID);
			userCartItem = query.getResultList();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return userCartItem;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean deleteCartItem(int id) {
		// CartItem item = findById(id);

		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			// start transaction
			tx = session.beginTransaction();
			// get the cart item to delete
			CartItem cartItem = session.get(CartItem.class, id);
			// System.out.println(cartItem);
			// save session
			session.delete(cartItem);
			// session.update(shoppingCart);

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

	@Override
	public boolean updateItemQuantity(CartItem item, int quantity) {

		item.setQuantity(quantity);

		item.setSubTotal(new BigDecimal(item.getBooks().getPrice()).multiply(new BigDecimal(quantity)));

		// ShoppingCart cart =
		// DAOUtilities.getShoppingCartDAO().findById(item.getShoppingCart().getId());

		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			// update using session.update method

			session.update(item);

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
	public CartItem findById(int id) {

		CartItem item = null;
		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();
			item = session.get(CartItem.class, id);
			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return item;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public CartItem getCartItemByISBN(String isbn) {

		CartItem item = null;
		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("FROM CartItem c where books.isbn13 = :isbn");
			query.setParameter("isbn", isbn);
			// get Unique result using result type that matches the query
			item = (CartItem) query.uniqueResult();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return item;
	}

	/*------------------------------------------------------------------------------------------------*/

	@SuppressWarnings("rawtypes")
	@Override
	public CartItem ExistCartItem(String isbn, ShoppingCart cart) {

		Long cartId = cart.getId();

		CartItem inCart = null;

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("FROM CartItem c where  books.isbn13 = :isbn and CART_ID = :cartId");
			query.setParameter("isbn", isbn);
			query.setParameter("cartId", cartId);
			inCart = (CartItem) query.uniqueResult();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}

		return inCart;
	}

}
