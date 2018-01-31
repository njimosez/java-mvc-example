package examples.pubhub.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookOrderDAO;
import examples.pubhub.dao.CartItemDAO;
import examples.pubhub.dao.PurchasedBooksDAO;
import examples.pubhub.dao.ShoppingCartDAO;
import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.BookOrder;
import examples.pubhub.model.PurchasedBooks;
import examples.pubhub.model.ShoppingCart;
import examples.pubhub.model.Users;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation for Checkout process
 */

@MultipartConfig
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CartItemDAO cartdao = DAOUtilities.getCartItemDAO();
	ShoppingCartDAO shoppingcartdao = DAOUtilities.getShoppingCartDAO();
	UserDAO userdao = DAOUtilities.getUserDAO();
	BookOrderDAO orderdao = DAOUtilities.getBookOrderDAO();
	PurchasedBooksDAO purchaseddao = DAOUtilities.getPurchasedBooksDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		processRequest(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		processRequest(req, resp);

	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Get the session user
		String username = req.getRemoteUser();

		// Get data user cart ID from the session
		long cartId = Long.parseLong(req.getParameter("ShoppingCartId"));

		Users user = userdao.getUserByusername(username);
		ShoppingCart cart = shoppingcartdao.findById(cartId);

		if (cart.getGrandTotal().equals(new BigDecimal(0))) {

			resp.sendRedirect(req.getContextPath() + "/BookPublishing");

		} else {

			// method should be move into a service object;
			Long orderId = orderNoGenerator();

			BookOrder currentOrder = orderdao.MCreateOrder(user, cart, orderId);

			if (currentOrder != null) {
				// clear the cart and remove/delete all cart items
				// since they have been successfully transferred .
				shoppingcartdao.clearShoppingCart(cart);
				// BookOrder currentOrder = orderdao.findbyId(orderId);
				List<PurchasedBooks> itemsPurchased = purchaseddao.getBooksByOrderNo(currentOrder.getId());

				// retrieve the order attributes to be posted on the
				// confirmation page
				// block the confirm page if non new order exist(Time stamp)
				// create the order history page under the user account tab
				// post data on the history page in sequential order
				req.getSession().setAttribute("itemsBought", itemsPurchased);
				req.setAttribute("ProcessedOrder", currentOrder);
				req.getSession().setAttribute("message", "Your order has been successfully processed");
				req.getSession().setAttribute("messageClass", "alert-success");

				req.getRequestDispatcher("confirmation.jsp").forward(req, resp);

				

			} else {
				req.getSession().setAttribute("message", "There was a problem processing your order");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("checkout.jsp").forward(req, resp);
			}

		}
	}

	/*------------------------------------------------------------------------------------------------*/

	private Long orderNoGenerator() {
		// Create a random generator to combine with time stamp to get a
		// unique(hopefully) value for orderId
		Random idRoller = new Random();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Use only the absolute value since the number we want should be
		// positive
		Long orderId = Math.abs(((timestamp.getTime() - idRoller.nextLong()) / timestamp.getTime()));
		// TODO check if order id is unique with a loop
		return orderId;
	}
}
