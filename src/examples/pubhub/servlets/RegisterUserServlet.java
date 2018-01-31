package examples.pubhub.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.HistoryRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;

import examples.pubhub.dao.UserDAO;
import examples.pubhub.model.Users;
import examples.pubhub.utilities.DAOUtilities;

@MultipartConfig
public class RegisterUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ROLE_NAME = "RegularUser";
	UserDAO database = DAOUtilities.getUserDAO();
	List<Users> AllUsers = database.getAllUsers();
	Rule userNameRule = new HistoryRule();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Get data from web user
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String phone = req.getParameter("phone");

		boolean isNewAccount = true;

		if (!validateUserNameLength(username)) {
			req.getSession().setAttribute("message", "Username should contain at least 8 characters");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			isNewAccount = false;
		}

		if (!uniqueUserName(username, AllUsers)) {
			req.getSession().setAttribute("message", "Username must be unique");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			isNewAccount = false;
		}

		if (validatePassword(password)==false) {
			req.getSession().setAttribute("message",
					"Passwords must be at least 8 characters long, and include 2 numbers");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			isNewAccount = false;
		}

		if ((database.getUserByEmail(email) != null)) {
			req.getSession().setAttribute("message", email + " is already registered ");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			isNewAccount = false;
		}

		if (isNewAccount) {

			Users user = new Users(username, firstName, lastName, email, phone, HashedPaswword(password));

			boolean isSuccess = database.createUser(user, ROLE_NAME);

			if (isSuccess) {
				req.getSession().setAttribute("message", "Account successfully created");
				req.getSession().setAttribute("messageClass", "alert-success");
				req.getSession().setAttribute("name", username);
				req.getRequestDispatcher("registrationSuccess.jsp").forward(req, resp);
			} else {

				req.getSession().setAttribute("message", "There is a problem creating your user account");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("register.jsp").forward(req, resp);

			}
		}
	}

	/*------------------------------------------------------------------------------------------------*/
	private String HashedPaswword(String password) {
		String hashPassword;

		ConfigurablePasswordEncryptor encryptor3 = new ConfigurablePasswordEncryptor();
		encryptor3.setAlgorithm("SHA-256");
		encryptor3.setPlainDigest(true);
		hashPassword = encryptor3.encryptPassword(password);

		return hashPassword;
	}

	private boolean uniqueUserName(String userName, List<Users> users) {
		Rule userNameRule = new HistoryRule();
		PasswordValidator validator = new PasswordValidator(userNameRule);
		PasswordData data = new PasswordData(userName);
		data.setPasswordReferences(getUsernameHistory(users));
		RuleResult result = validator.validate(data);
		if (result.isValid()) {

			return true;
		}
		System.out.println("Username must be unique");
		return false;
	}

	private boolean validateUserNameLength(String userName) {
		PasswordValidator validator = new PasswordValidator(
				// length between 8 and 16 characters
				new LengthRule(8, 16));
		RuleResult result = validator.validate(new PasswordData(new String(userName)));
		if (result.isValid()) {

			return true;
		}

		return false;
	}

	private boolean validatePassword(String Password) {
		PasswordValidator validator = new PasswordValidator(
				// length between 8 and 16 characters
				new LengthRule(8, 16),
				// at least two digit character
				new CharacterRule(EnglishCharacterData.Digit, 2));
		RuleResult result = validator.validate(new PasswordData(new String(Password)));
		if (result.isValid()) {

			return true;
		}

		return false;
	}

	private List<PasswordData.Reference> getUsernameHistory(List<Users> users) {

		List<PasswordData.Reference> history = new ArrayList<>();
		for (Users temp : users) {
			history.add(new PasswordData.HistoricalReference(temp.getUsername()));
		}

		return history;

	}
}
