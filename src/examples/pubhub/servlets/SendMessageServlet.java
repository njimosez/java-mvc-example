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

@WebServlet(name = "SendMessage", urlPatterns = { "/SendMessage" })
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("createMessage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getRemoteUser();
		String authorName = req.getParameter("author");
		String content = req.getParameter("content");
		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();

		Message message = new Message();
		message.setContent(content);
		message.setMessageAuthor(username);

		Message createdMessage = restClient.sendMessage(authorName, message);

		if (createdMessage != null) {

			req.getSession().setAttribute("message", "Message successfully created");
			req.getSession().setAttribute("messageClass", "alert-success");
			resp.sendRedirect(
					req.getContextPath() + "/ViewMessageDetails?" + "messageid=" + createdMessage.getMessageid());

		} else {
			req.getSession().setAttribute("message", "There was a problem sending the message");
			req.getSession().setAttribute("messageClass", "alert-danger");
			resp.sendRedirect(req.getContextPath() + "/ViewAuthorDetails");
		}
	}
}
