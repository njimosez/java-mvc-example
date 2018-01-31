package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.model.Message;
import examples.pubhub.rest.PubHubRestClient;
import examples.pubhub.rest.RESTUtilities;

@WebServlet(name = "ViewMessageDetails", urlPatterns = { "/ViewMessageDetails" })
public class ViewMessageDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	String username = req.getRemoteUser();
		
	
		
		int messageId = Integer.parseInt(req.getParameter("messageid"));
	    PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();
	   Message message = restClient.getMessageDetails(messageId);
		
		
		
		
		
		if (message == null ) {

			req.getSession().setAttribute("message", "No messages in Author channel");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("messageDetails.jsp").forward(req, resp);

		} else {
			
		
			req.getSession().setAttribute("message", message);
			req.getRequestDispatcher("messageDetails.jsp").forward(req, resp);
			
		}

	}

}
