package examples.pubhub.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import examples.pubhub.model.CartItem;
import examples.pubhub.model.ShoppingCart;
import examples.pubhub.model.Users;
import examples.pubhub.utilities.DAOUtilities;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean createCart(Users user) {
		BigDecimal cartTotal = new BigDecimal(0);
		ShoppingCart cart = new ShoppingCart();
		cart.setUsers(user);
		cart.setGrandTotal(cartTotal);

		// call session
		session = DAOUtilities.getSession().openSession();

		try {
			// start transaction
			tx = session.beginTransaction();

			// save session
			session.save(cart);

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
	public ShoppingCart getCartByUserName(String username) {
		// check if cart exist then no
		ShoppingCart userCart = null;
		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("FROM ShoppingCart c where users.username= :username");
			query.setParameter("username", username);
			// get Unique result using result type that matches the query
			userCart = (ShoppingCart) query.uniqueResult();

			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return userCart;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart cart) {
		BigDecimal grandTotal = new BigDecimal(0);
		List<CartItem> itemsInCart = DAOUtilities.getCartItemDAO().getUserCartItemsByShoppingCardId(cart.getId());

		for (CartItem items : itemsInCart) {
			grandTotal = grandTotal.add(items.getSubTotal());
		}

		cart.setGrandTotal(grandTotal);

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();

			// save session
			session.update(cart);

			// session.getTransaction().commit();

			// commit transaction
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return cart;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public ShoppingCart findById(Long id) {
		ShoppingCart cart = null;
		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();
			cart = session.get(ShoppingCart.class, id);
			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

		return cart;
	}

	/*------------------------------------------------------------------------------------------------*/

	@Override
	public void clearShoppingCart(ShoppingCart cart) {
		BigDecimal grandTotal = new BigDecimal(0);
		List<CartItem> itemsInCart = DAOUtilities.getCartItemDAO().getUserCartItemsByShoppingCardId(cart.getId());
		cart.setGrandTotal(grandTotal);

		// call a session
		session = DAOUtilities.getSession().openSession();

		try {
			tx = session.beginTransaction();
			for (CartItem items : itemsInCart) {
				session.remove(items);
			}

			session.update(cart);
			// commit transaction
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}

	}

}
