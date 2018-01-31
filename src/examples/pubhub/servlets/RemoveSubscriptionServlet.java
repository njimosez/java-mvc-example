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
 * Servlet implementation class RemoveSubscriptionServlet
 */
@WebServlet(name = "RemoveSubscription", urlPatterns = { "/RemoveSubscription" })
public class RemoveSubscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String authorName = req.getParameter("author");
		int subscriberID = Integer.parseInt(req.getParameter("subscriptionId"));
		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();
		String response = restClient.unSubscribe(authorName, subscriberID);

		req.getSession().setAttribute("message", response);
        req.getSession().setAttribute("messageClass", "alert-success");
		resp.sendRedirect(req.getContextPath() + "/ViewAuthorDetails?" + "author=" + authorName);

	}

}
