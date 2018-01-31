package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.CartItemDAO;
import examples.pubhub.dao.ShoppingCartDAO;
import examples.pubhub.model.CartItem;
import examples.pubhub.model.ShoppingCart;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/UpdateCartItem")
public class UpdateCartItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CartItemDAO cartdao = DAOUtilities.getCartItemDAO();
	ShoppingCartDAO shoppingcartdao = DAOUtilities.getShoppingCartDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Get data from web user

		// String cartId = req.getParameter("cartId)

		int ItemId = Integer.parseInt(req.getParameter("cartId"));
		int qty = Integer.parseInt(req.getParameter("newQuantity"));
		long CartId = Long.parseLong(req.getParameter("ShoppingCartId"));

		ShoppingCart cart = shoppingcartdao.findById(CartId);

		CartItem item = cartdao.findById(ItemId);

		// shoppingdao.updateShoppingCart(cart);

		boolean isSuccess = cartdao.updateItemQuantity(item, qty);

		if (isSuccess) {
			cart = shoppingcartdao.updateShoppingCart(cart);
			req.getSession().setAttribute("message", "Cart successfully added");
			req.getSession().setAttribute("messageClass", "alert-success");

			// We use a redirect here instead of a forward, because we don't
			// want request data to be saved. Otherwise, when
			// a user clicks "refresh", their browser would send the data
			// again!
			// This would be bad data management, and it
			// could result in duplicate rows in a database.
			resp.sendRedirect(req.getContextPath() + "/ShoppingCart");

		} else {
			req.getSession().setAttribute("message", "There was a problem updating your cart");
			req.getSession().setAttribute("messageClass", "alert-danger");
			resp.sendRedirect(req.getContextPath() + "/ShoppingCart");

		}
	}
}
