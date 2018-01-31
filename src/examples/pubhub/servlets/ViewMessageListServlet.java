package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.model.Author;
import examples.pubhub.model.Message;
import examples.pubhub.rest.PubHubRestClient;
import examples.pubhub.rest.RESTUtilities;

@WebServlet(name = "ViewMessageList", urlPatterns = { "/ViewMessageList" })
public class ViewMessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String authorName = req.getParameter("author");
		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();
		Author author = restClient.getAuthorChannel(authorName);
		
		List<Message> message = author.getMessages();
		

			req.getSession().setAttribute("messages", message);
			req.getSession().setAttribute("author", author);
			req.getRequestDispatcher("listMessages.jsp").forward(req, resp);

		}

	}


