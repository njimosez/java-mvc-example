package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.CartItemDAO;
import examples.pubhub.dao.ShoppingCartDAO;
import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.CartItem;
import examples.pubhub.model.ShoppingCart;
import examples.pubhub.model.Users;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation for Shopping Cart
 */

@MultipartConfig // This annotation tells the server that this servlet has
// complex data other than forms
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ITEM_DEFAULT_QTY = 1;
	// log4j2 logger static instance for this class
	final static Logger log = LogManager.getLogger(ShoppingCartServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	CartItemDAO itemdao = DAOUtilities.getCartItemDAO();
	UserDAO userdao = DAOUtilities.getUserDAO();
	ShoppingCartDAO cartdao = DAOUtilities.getShoppingCartDAO();
	BookDAO bookdao = DAOUtilities.getBookDAO();

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
		String isbn = req.getParameter("isbn13");

		// get the user object
		Users user = userdao.getUserByusername(username);

		// get cart object for user
		ShoppingCart userCart = cartdao.getCartByUserName(username);
		// CartItem itemExistInCart = itemdao.getCartItemByISBN(isbn);

		// Check if user cart exist and retrieve cart(if any) saved from a
		// previous session to enable user access cart without adding item to
		// cart

		if ((userCart != null) && ((isbn == null))) {
			log.info("Condition 1: User accesses his/her empty shopping card ");
			List<CartItem> cartItem = itemdao.getUserCartItemsByShoppingCardId(userCart.getId());
			req.getSession().setAttribute("ShoppingCart", userCart);
			req.getSession().setAttribute("cartItemList", cartItem);
			req.getSession().setAttribute("LogUser", user);
			req.getSession().setAttribute("message", "Welcome to your Cart");

			req.getSession().setAttribute("messageClass", "alert-success");
			req.getRequestDispatcher("cart.jsp").forward(req, resp);

			// add new item to existing user cart(not emptied) from previous
			// sessions

		}

		if ((userCart != null) && (isbn != null)) {
			log.info("Condition 2: Cart exist an item is being added to cart");
			// get cart item if it exist
			CartItem inCart = itemdao.ExistCartItem(isbn, userCart);

			if (inCart != null) {
				log.info("Condition 2.1: Test for updated qty of item if in cart");
				int qty = inCart.getQuantity() + ITEM_DEFAULT_QTY;
				boolean isSuccess = itemdao.updateItemQuantity(inCart, qty);
				log.debug("Is cart updated ?" + isSuccess);
				log.error(isSuccess == false);
				if (isSuccess) {
					// userCart = cartdao.updateShoppingCart(userCart);
					List<CartItem> cartItem = itemdao.getUserCartItemsByShoppingCardId(userCart.getId());
					req.getSession().setAttribute("message", "Item already in cart and quantity successfully updated");
					req.getSession().setAttribute("messageClass", "alert-success");
					req.getSession().setAttribute("ShoppingCart", cartdao.updateShoppingCart(userCart));
					req.getSession().setAttribute("cartItemList", cartItem);
					req.getSession().setAttribute("LogUser", user);
					resp.sendRedirect(req.getContextPath() + "/BookPublishing");
				}

			} else {
				log.info("Condition 3: Item exit in Cart");
				// add new items to the existing cart
				boolean isAddItem = itemdao.addCartItem(bookdao.getBookByISBN(isbn), userCart);
				log.debug("Is new item added to cart ? " + isAddItem);

				if (isAddItem) {

					List<CartItem> newCartItem = itemdao.getUserCartItemsByShoppingCardId(userCart.getId());

					req.getSession().setAttribute("message", "Item added to cart");
					req.getSession().setAttribute("messageClass", "alert-success");
					req.getSession().setAttribute("ShoppingCart", cartdao.updateShoppingCart(userCart));
					req.getSession().setAttribute("cartItemList", newCartItem);
					req.getSession().setAttribute("LogUser", user);
					req.getRequestDispatcher("cart.jsp").forward(req, resp);

				}

			}

		}

		if ((userCart == null)) {
			log.info("Condition 4: cart does not exist");
			// create a new shopping cart if user has no saved cart or it is
			// empty and

			boolean isCreateCartSuccess = cartdao.createCart(user);
			log.debug("Is new cart created?" + isCreateCartSuccess);

			if (isCreateCartSuccess) {
				// create a user cart item
				boolean isCreateItem = itemdao.addCartItem(bookdao.getBookByISBN(isbn), userCart);
				log.debug("Is item added to cart created?" + isCreateItem);
				if (isCreateItem) {

					ShoppingCart newUserCart = cartdao.getCartByUserName(username);
					List<CartItem> cartItem = itemdao.getUserCartItemsByShoppingCardId(newUserCart.getId());

					req.getSession().setAttribute("message", "Item added to cart");
					req.getSession().setAttribute("messageClass", "alert-success");
					req.getSession().setAttribute("ShoppingCart", cartdao.updateShoppingCart(newUserCart));
					req.getSession().setAttribute("cartItemList", cartItem);
					req.getSession().setAttribute("LogUser", user);
					req.getRequestDispatcher("cart.jsp").forward(req, resp);

				} else {
					req.getSession().setAttribute("message", "There was a problem adding items to your shopping Cart");
					req.getSession().setAttribute("messageClass", "alert-danger");
					resp.sendRedirect(req.getContextPath());

				}

			} else {
				log.info("Something is Wrong if we get to this point");
				req.getSession().setAttribute("message",
						"There was a problem creating your Shopping Cart\n" + "Please contact Customer Service");
				req.getSession().setAttribute("messageClass", "alert-danger");
				resp.sendRedirect(req.getContextPath());

			}
		}
	}
}