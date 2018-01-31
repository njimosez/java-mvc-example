package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.Author;
import examples.pubhub.model.Users;
import examples.pubhub.rest.PubHubRestClient;
import examples.pubhub.rest.RESTUtilities;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet(name = "ViewAllAuthors", urlPatterns = { "/ViewAllAuthors" })
public class ViewAllAuthorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getRemoteUser();
		UserDAO userdao = DAOUtilities.getUserDAO();
		Users user = userdao.getUserByusername(username);
		String authorName = (user.getFirstname() + " " + user.getLastname());
		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();
		List<Author> authors = restClient.getAllAuthors(username);

		if (authors == null) {

			req.getSession().setAttribute("message", "Author Channels not active");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("listAuthors.jsp").forward(req, resp);

		} else {

			req.getSession().setAttribute("authors", authors);
			req.getSession().setAttribute("loguser", username);
			req.getSession().setAttribute("authorName", authorName);
			req.getRequestDispatcher("listAuthors.jsp").forward(req, resp);

		}

	}

}
