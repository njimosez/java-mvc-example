package examples.pubhub.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.Author;
import examples.pubhub.model.Link;
import examples.pubhub.model.Users;
import examples.pubhub.rest.PubHubRestClient;
import examples.pubhub.rest.RESTUtilities;
import examples.pubhub.utilities.DAOUtilities;

@MultipartConfig
public class AddAuthorProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.getRequestDispatcher("editProfile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getRemoteUser();
		UserDAO userdao = DAOUtilities.getUserDAO();
		Users authorUser = userdao.getUserByusername(username);
		String authorName = (authorUser.getFirstname() + " " + authorUser.getLastname());
		
		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();
		
	
		Author author = restClient.getAuthorChannel(authorName);
		if (author == null) {
		

			req.getSession().setAttribute("message", "Author does not exist");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("editProfile.jsp").forward(req, resp);

		} else {

		InputStream is = null;// input stream of the upload file
			
			 // obtains the image to upload in this multipart request
			Part content = req.getPart("content");
             if (content != null){
            	// prints out some information for debugging
            	 System.out.println(content.getName());
            	 System.out.println(content.getSize());
            	
            	 // obtains input stream of the upload file
            	 is = content.getInputStream();
             }
			


			boolean isSuccess = restClient.createAuthorProfile(authorName, is);

			if (isSuccess) {
				// change user role to author if the user 
				// has not published before
				
				
				List<Link> authorHATEOSLinks = author.getLinks();
				req.getSession().setAttribute("message", "Profile successfully updated");
				req.getSession().setAttribute("messageClass", "alert-success");
				req.getSession().setAttribute("authorLinks", authorHATEOSLinks);
				req.getSession().setAttribute("author", author);
				// We use a redirect here instead of a forward, because we don't
				// want request data to be saved. Otherwise, when
				// a user clicks "refresh", their browser would send the data
				// again!
				// This would be bad data management, and it
				// could result in duplicate rows in a database.
				resp.sendRedirect("authorWelcome.jsp" ); 
				} 
			else {
				req.getSession().setAttribute("message", "There was a problem creating your profile");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("editProfile.jsp").forward(req, resp);

			}
		}
	}


}
