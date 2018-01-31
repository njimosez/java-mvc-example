package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.model.Author;
import examples.pubhub.model.AuthorBooks;
import examples.pubhub.model.AuthorFiles;
import examples.pubhub.model.Subscriber;
import examples.pubhub.rest.PubHubRestClient;
import examples.pubhub.rest.RESTUtilities;

/**
 * Servlet implementation class ViewAuthorDetailsServlet
 */
@WebServlet(name = "ViewAuthorDetails", urlPatterns = { "/ViewAuthorDetails" })
public class ViewAuthorDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getRemoteUser();
		String authorName = req.getParameter("author");
		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();
		Author author = restClient.getAuthorChannel(authorName,username);
		
			
		
		if (author == null) {

			req.getSession().setAttribute("message", "Author Channel does not exist");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("authorDetails.jsp").forward(req, resp);

		} else {
			
			// get the embedded author objects published through webServices 
			Subscriber subscription = author.getSubscription();
			List<AuthorBooks> books = author.getBook();
			AuthorFiles profile = author.getProfile();
			req.getSession().setAttribute("author", author);
			req.getSession().setAttribute("authorBooks", books);
			req.getSession().setAttribute("subscription", subscription);
			req.getSession().setAttribute("profile", profile);
			req.getRequestDispatcher("authorDetails.jsp").forward(req, resp);
			
		}

	}

}
