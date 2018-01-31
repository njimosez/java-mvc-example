package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.Roles;
import examples.pubhub.model.Users;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet(name = "FanOutreach", urlPatterns = { "/FanOutreach" })
public class FanOutreachServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String AUTHOR_ROLE = "Author";
	private static final String USER_ROLE = "RegularUser";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getRemoteUser();
		UserDAO userdao = DAOUtilities.getUserDAO();
		Users user = userdao.getUserByusername(username);
		Roles roles = user.getRole();

		if ((user.getRole().getRolename()).equalsIgnoreCase(USER_ROLE)) {
			req.getSession().setAttribute("LogUser", user);
			req.getSession().setAttribute("role", roles);
			resp.sendRedirect(req.getContextPath() + "/ViewAllAuthors");
		}

			if ((user.getRole().getRolename()).equalsIgnoreCase(AUTHOR_ROLE)) {
			req.getSession().setAttribute("LogUser", user);
			req.getSession().setAttribute("role", roles);
			resp.sendRedirect(req.getContextPath() + "/ViewAuthorProfile");
		}

	}

}
