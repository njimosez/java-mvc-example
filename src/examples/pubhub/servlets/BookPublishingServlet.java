package examples.pubhub.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.CartItemDAO;
import examples.pubhub.dao.PurchasedBooksDAO;
import examples.pubhub.dao.ShoppingCartDAO;
import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.CartItem;
import examples.pubhub.model.PurchasedBooks;
import examples.pubhub.model.Roles;
import examples.pubhub.model.ShoppingCart;
import examples.pubhub.model.Users;
import examples.pubhub.utilities.DAOUtilities;

/*
 * This servlet will take you to the homepage for the Book Publishing module (level 100)
 */
@WebServlet("/BookPublishing")
public class BookPublishingServlet extends HttpServlet {

	// log4j2 logger static instance for this class
	final static Logger log = LogManager.getLogger(BookPublishingServlet.class);

	private static final long serialVersionUID = 1L;
	BookDAO dao = DAOUtilities.getBookDAO();
	CartItemDAO cartObj = DAOUtilities.getCartItemDAO();
	UserDAO userdao = DAOUtilities.getUserDAO();
	ShoppingCartDAO cartdao = DAOUtilities.getShoppingCartDAO();
	PurchasedBooksDAO purchasedao = DAOUtilities.getPurchasedBooksDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getRemoteUser();
		List<CartItem> cartItem = new ArrayList<>();
		// get the user object and cart items to enable display of user specific
		// and cart items qty information upon login
		log.info("Getting  user information ");
		Users user = userdao.getUserByusername(username);
		log.debug("Username is :" + user.getUsername());

		// Grab the list of Books from the Database
		log.info("Getting published books list");
		List<Book> bookList = dao.getAllBooks();
		log.fatal("Is the book list empty?:  " + bookList.isEmpty());

		// get cart object for user
		log.info("Getting user cart object");
		ShoppingCart userCart = cartdao.getCartByUserName(username);
		if (userCart != null) {
			log.debug("CartID: " + userCart.getId());
			log.info("Getting user cart items ");

			cartItem = cartObj.getUserCartItemsByShoppingCardId(userCart.getId());

		}

		/*
		 * get a list of books purchased by user if it exist this will enable
		 * use an if-else statement to show that a "download" button appears for
		 * each book that has been purchased in a previous order
		 */
		log.info("Getting purchased goods list for the user ");
		List<PurchasedBooks> boughtByUser = purchasedao.getBooksOrderedByUser(username);
		if (boughtByUser != null) {
			log.debug("Size of purchased goods list : " + boughtByUser.size());
		}

		// Populate the list into a variable that will be stored in the
		// session
		Roles roles = user.getRole();
		req.getSession().setAttribute("ShoppingCart", userCart);
		req.getSession().setAttribute("cartItemList", cartItem);
		req.getSession().setAttribute("previousOrder", boughtByUser);
		req.getSession().setAttribute("LogUser", user);
		req.getSession().setAttribute("role", roles);
		req.getSession().setAttribute("bookList", bookList);
		req.getRequestDispatcher("bookPublishingHome.jsp").forward(req, resp);
	}

}