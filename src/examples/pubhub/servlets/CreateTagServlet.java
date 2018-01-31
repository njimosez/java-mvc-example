package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagsDAO;
import examples.pubhub.model.BookTags;
import examples.pubhub.utilities.DAOUtilities;

@MultipartConfig // This annotation tells the server that this servlet has
					// complex data other than forms
// Notice the lack of the @WebServlet annotation? This servlet is mapped the old
// fashioned way - Check the web.xml!
public class CreateTagServlet extends HttpServlet {
	// private static Logger log =
	// Logger.getLogger(CreateTagServlet.class.getName());

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("createTag.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Get data from web user
		String isbn = req.getParameter("tagID");
		String tagName = req.getParameter("tagName");

		BookTagsDAO database = DAOUtilities.getTagDAO();
		BookTags bookTag = database.getBookByTagName(tagName);

		if (bookTag != null) {
			// Check if tag is already in use
			req.getSession().setAttribute("message", tagName + " is already in use for book " + isbn);
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("createTag.jsp").forward(req, resp);

		} else {

			boolean isSuccess = database.createBookTag(isbn, tagName);

			if (isSuccess) {
				req.getSession().setAttribute("message", "Book tag successfully added");
				req.getSession().setAttribute("messageClass", "alert-success");

				// We use a redirect here instead of a forward, because we don't
				// want request data to be saved. Otherwise, when
				// a user clicks "refresh", their browser would send the data
				// again!
				// This would be bad data management, and it
				// could result in duplicate rows in a database.
				resp.sendRedirect(req.getContextPath() + "/ViewBookDetails?" + "isbn13=" + isbn);

			} else {
				req.getSession().setAttribute("message", "There was a problem adding the book tag");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("createTag.jsp").forward(req, resp);

			}
		}
	}

}
