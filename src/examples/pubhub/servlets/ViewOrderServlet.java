package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookOrderDAO;
import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.BookOrder;
import examples.pubhub.model.Users;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class ViewBookDetailsServlet
 */

// This is a "View" servlet, and has been named accordingly. All it does is send
// the user to a new JSP page
// But it also takes the opportunity to populate the session or request with
// additional data as needed.
@WebServlet("/ViewOrder")

public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userdao = DAOUtilities.getUserDAO();
	BookOrderDAO orderdao = DAOUtilities.getBookOrderDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Get the session user
		String username = req.getRemoteUser();

		UserDAO userdao = DAOUtilities.getUserDAO();
		BookOrderDAO orderdao = DAOUtilities.getBookOrderDAO();

		// get the user object
		Users user = userdao.getUserByusername(username);

		// get user orders and purchased good objects

		List<BookOrder> userorders = orderdao.getAllOrderForUser(username);

		// Populate the list into a variable that will be stored in the session
		req.setAttribute("customer", user);
		req.setAttribute("orderList", userorders);

		// We can use a forward here, because if a user wants to refresh their
		// browser on this page,
		// it will just show them the most recent details for their book.
		// There's no risk of data
		// miss-handling here.
		req.getRequestDispatcher("viewOrderHistory.jsp").forward(req, resp);

	}

}
