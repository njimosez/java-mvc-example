package examples.pubhub.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.model.AuthorFiles;
import examples.pubhub.rest.PubHubRestClient;
import examples.pubhub.rest.RESTUtilities;


@WebServlet("/ViewAuthorImage")
public class ViewAuthorImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	
		String authorId = req.getParameter("authorId");
		int authorid = Integer.parseInt(authorId);
		PubHubRestClient restClient = RESTUtilities.getPubhubRestClient();
		AuthorFiles profile = restClient.getAuthorProfile(authorid);
		

		// In order to render the image to the client
		// First we set the content types  so the browser knows how to
		// interpret the data it's receiving
		resp.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	

		// Create the input stream (IN to the app FROM the profile )
		InputStream is = new ByteArrayInputStream(profile.getPortrait());

		// Create the output stream (OUT of the app TO the client)
		OutputStream os = resp.getOutputStream();

		// We're going to read and write 1KB at a time
		byte[] buffer = new byte[1024];

		// Reading returns -1 when there's no more data left to read.
		while (is.read(buffer) != -1) {
			os.write(buffer);
		}

		// Always close your streams!
		os.flush();
		os.close();
		is.close();
	}
	}

