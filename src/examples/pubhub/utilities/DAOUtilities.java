package examples.pubhub.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookDAOImpl;
import examples.pubhub.dao.BookOrderDAO;
import examples.pubhub.dao.BookOrderDAOImpl;
import examples.pubhub.dao.BookTagsDAO;
import examples.pubhub.dao.BookTagsDAOImpl;
import examples.pubhub.dao.CartItemDAO;
import examples.pubhub.dao.CartItemDAOImpl;
import examples.pubhub.dao.PurchasedBooksDAO;
import examples.pubhub.dao.ShoppingCartDAO;
import examples.pubhub.dao.ShoppingCartDAOImpl;
import examples.pubhub.dao.UserDAO;
import examples.pubhub.dao.UserDAOImpl;

/**
 * Class used to retrieve DAO Implementations. Serves as a factory. Also manages
 * a single instance of the database connection.
 */
public class DAOUtilities {

	private static final String CONNECTION_USERNAME = "demosez";
	private static final String CONNECTION_PASSWORD = "Aiyah@5108";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static Connection connection;

	public static synchronized Connection getConnection() throws SQLException {

		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}

		// If connection was closed then retrieve a new connection
		if (connection.isClosed()) {
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}

	/*------------------------------------------------------------------------------------------------*/
	public static SessionFactory getSession() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;

		try {

			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		} catch (Exception ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			ex.printStackTrace();
			// The registry would be destroyed by the SessionFactory, but we had
			// trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
		return sessionFactory;
	}

	/*------------------------------------------------------------------------------------------------*/
	public static BookDAO getBookDAO() {
		return new BookDAOImpl();
	}

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	public static BookTagsDAO getTagDAO() {
		return new BookTagsDAOImpl();
	}

	public static ShoppingCartDAO getShoppingCartDAO() {
		return new ShoppingCartDAOImpl();
	}

	public static CartItemDAO getCartItemDAO() {
		return new CartItemDAOImpl();
	}

	public static BookOrderDAO getBookOrderDAO() {
		return new BookOrderDAOImpl();
	}

	public static PurchasedBooksDAO getPurchasedBooksDAO() {
		return new BookOrderDAOImpl();
	}
	
	
}