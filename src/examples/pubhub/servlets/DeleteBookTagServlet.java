package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagsDAO;
import examples.pubhub.utilities.DAOUtilities;

@MultipartConfig // This annotation tells the server that this servlet has
// complex data other than forms
// Notice the lack of the @WebServlet annotation? This servlet is mapped the old
// fashioned way - Check the web.xml!
@WebServlet("/DeleteBookTag")
public class DeleteBookTagServlet extends HttpServlet {
	BookTagsDAO database = DAOUtilities.getTagDAO();

	private static final long serialVersionUID = 1L;

	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// request.getRequestDispatcher("createTag.jsp").forward(request, response);
	// }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Get data from web user
		String tagName = req.getParameter("tagName");
		String isbn_13 = req.getParameter("isbn_13");

		boolean isSuccess = database.deleteBookTag(tagName, isbn_13);

		if (isSuccess) {
			req.getSession().setAttribute("message", "Book tag successfully deleted");
			req.getSession().setAttribute("messageClass", "alert-success");

			// We use a redirect here instead of a forward, because we don't
			// want request data to be saved. Otherwise, when
			// a user clicks "refresh", their browser would send the data
			// again!
			// This would be bad data management, and it
			// could result in duplicate rows in a database.
			resp.sendRedirect(req.getContextPath() + "/ViewBookDetails?" + "isbn13=" + isbn_13);

		} else {
			req.getSession().setAttribute("message", "There was a problem adding the book tag");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("createTag.jsp").forward(req, resp);

		}
	}
}
