package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.rest.PubHubRestClient;
import examples.pubhub.rest.RESTUtilities;

/**
 * Servlet implementation class AddSubscriptionServlet
 */
@WebServlet(name = "AddSubscription", urlPatterns = { "/AddSubscription" })
public class AddSubscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getRemoteUser();
		String authorName = req.getParameter("author");
		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();
		String response = restClient.createSubscriptionToAuthorChannel(authorName, username);

		if (response != null) {
			req.getSession().setAttribute("message", response);
	        req.getSession().setAttribute("messageClass", "alert-success");
			resp.sendRedirect(req.getContextPath() + "/ViewAuthorDetails?" + "author=" + authorName);

		} else {

			req.getSession().setAttribute("message", "There was a problem subscribing to this Author Channel");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("authorDetails.jsp").forward(req, resp);

		}

	}

}
