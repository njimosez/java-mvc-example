package examples.pubhub.servlets;

import java.io.IOException;

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

/**
 * Servlet implementation class AuthorProfileServlet
 */
@WebServlet(name = "AuthorProfile", urlPatterns = { "/AuthorProfile" })
public class AuthorProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getRemoteUser();
		UserDAO userdao = DAOUtilities.getUserDAO();
		Users authorUser = userdao.getUserByusername(username);
		String authorName = (authorUser.getFirstname() + " " + authorUser.getLastname());

		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();

		Author author = restClient.getAuthorChannel(authorName);

		if (author == null) {

			req.getSession().setAttribute("message", "Author Channel does not exist");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("editProfile.jsp").forward(req, resp);

		}

		req.getSession().setAttribute("author", author);

		req.getRequestDispatcher("editProfile.jsp").forward(req, resp);

	}
	
	

}
