package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookTagsDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.BookTags;
import examples.pubhub.utilities.DAOUtilities;

@MultipartConfig // This annotation tells the server that this servlet has
// complex data other than forms
// Notice the lack of the @WebServlet annotation? This servlet is mapped the old
// fashioned way - Check the web.xml!
public class SearchBookByTagsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Grab the list of all book associated to tags from the Database

		BookDAO dao = DAOUtilities.getBookDAO();
		List<Book> books = dao.getAllBooks();
		BookTagsDAO booktagdao = DAOUtilities.getTagDAO();
		List<BookTags> tagging = booktagdao.getBookTags();

		// Populate the list into a variable that will be stored in the session

		request.getSession().setAttribute("books", books);
		request.getSession().setAttribute("tagging", tagging);

		request.getRequestDispatcher("bookTags.jsp").forward(request, response);

	}

}
