package examples.pubhub.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.Author;
import examples.pubhub.model.Book;
import examples.pubhub.model.Users;
import examples.pubhub.rest.PubHubRestClient;
import examples.pubhub.rest.RESTUtilities;
import examples.pubhub.utilities.DAOUtilities;

@MultipartConfig
public class PublishBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String AUTHOR_ROLE = "Author";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("publishBook.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String isbn13 = req.getParameter("isbn13");
		String username = req.getRemoteUser();
		BookDAO database = DAOUtilities.getBookDAO();
		UserDAO userdao = DAOUtilities.getUserDAO();
		Users user = userdao.getUserByusername(username);

		Book tempBook = database.getBookByISBN(isbn13);
		if (tempBook != null) {

			req.getSession().setAttribute("message", "ISBN of " + isbn13 + " is already in use");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("publishBook.jsp").forward(req, resp);

		} else {

			Book book = new Book();
			String authorName = (user.getFirstname() + " " + user.getLastname());
			Author author = getAuthorChannel(username);
			book.setIsbn13(req.getParameter("isbn13"));
			book.setTitle(req.getParameter("title"));
			book.setAuthor(authorName);
			book.setPublishDate(LocalDate.now());
			book.setPrice(Double.parseDouble(req.getParameter("price")));

			// Uploading a file requires the data to be sent in "parts", because
			// one HTTP packet might not be big
			// enough anymore for all of the data. Here we get the part that has
			// the file dat//cgange author to get //use to
			Part content = req.getPart("content");

			InputStream is = null;
			ByteArrayOutputStream os = null;

			try {
				is = content.getInputStream();
				os = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];

				while (is.read(buffer) != -1) {
					os.write(buffer);
				}

				book.setContent(os.toByteArray());

			} catch (IOException e) {
				System.out.println("Could not upload file!");
				e.printStackTrace();
			} finally {
				if (is != null)
					is.close();
				if (os != null)
					os.close();
			}

			boolean isSuccess = database.addBook(book);

			if (isSuccess) {

				if (!(user.getRole().getRolename()).equalsIgnoreCase(AUTHOR_ROLE)) {
					userdao.updateUserRole(user, AUTHOR_ROLE);

					req.getSession().setAttribute("message", "Book successfully published");
					req.getSession().setAttribute("messageClass", "alert-success");

					req.getSession().setAttribute("author", author);
					resp.sendRedirect("editProfile.jsp");
				}
				if ((user.getRole().getRolename()).equalsIgnoreCase(AUTHOR_ROLE)) {
					req.getSession().setAttribute("message", "Book successfully published");
					req.getSession().setAttribute("messageClass", "alert-success");
					resp.sendRedirect(req.getContextPath() + "/ViewAuthorProfile");
				}

			} else {
				req.getSession().setAttribute("message", "There was a problem publishing the book");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("publishBook.jsp").forward(req, resp);

			}
		}
	}

	private Author getAuthorChannel(String username) {

		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();

		// REST call will return an author object if it exist or create new if
		// none

		return restClient.createAuthorChannel(username);

	}

}
