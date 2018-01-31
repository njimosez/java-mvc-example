package examples.pubhub.rest;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import examples.pubhub.model.Author;
import examples.pubhub.model.AuthorFiles;
import examples.pubhub.model.Book;
import examples.pubhub.model.Message;
import examples.pubhub.model.Subscriber;

public class PubHubRestClientImpl implements PubHubRestClient {

	private static final String ENDPOINT = "/FanOutreach/pubhub/service/";

	private static final String GET_AUTHORS = "/FanOutreach/pubhub/service/authors";

	private static final String MESSAGE_DETAILS = "/FanOutreach/pubhub/service/message/";
	
	private static final String PROFILE = "/FanOutreach/pubhub/service/profile/";


	Client client = ClientBuilder.newClient();

	WebTarget hostTarget = client.target("http://localhost:8080");

	Response output = null;
	// public PubHubRestClientImpl(String host) {

	// ClientConfig clientConfig = new ClientConfig()
	// .property(ClientProperties.READ_TIMEOUT, 30000)
	// .property(ClientProperties.CONNECT_TIMEOUT, 5000);
	// ClientConfig clientConfig = new ClientConfig()
	// .property(ClientProperties.READ_TIMEOUT, 30000)
	// .property(ClientProperties.CONNECT_TIMEOUT, 5000);
	//
	// webTarget = ClientBuilder
	// .newClient(clientConfig)
	// .target(host);
	// }
	//

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public Author createAuthorChannel(String userName) {

		// retrieve author resource if it exist or let the REST server create a
		// new author channel
		output = hostTarget.path(ENDPOINT).request().post(Entity.json(userName));

		Author author = output.readEntity(Author.class);

		return author;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public Author getAuthorChannel(String authorName) {

		output = hostTarget.path(ENDPOINT + authorName).request(MediaType.APPLICATION_JSON).get();

		if (output.getStatus() == 200) {
			Author author = output.readEntity(Author.class);
			return author;

		}

		return null;
	}
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Book> getBooksByAuthor(String authorName) {
		output = hostTarget.path(ENDPOINT + authorName + "/books").request(MediaType.APPLICATION_JSON).get();

		if (output.getStatus() == 200) {
			List<Book> books = output.readEntity(new GenericType<List<Book>>() {
			});

			return books;

		}

		return null;

	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public String createSubscriptionToAuthorChannel(String authorName, String userName) {

		output = hostTarget.path(ENDPOINT + authorName + "/subscribe").request(MediaType.APPLICATION_JSON)
				.put(Entity.json(userName));

		if (output.getStatus() == 201) {
			String subscribe = output.readEntity(String.class);
			return subscribe;

		}

		return null;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public String unSubscribe(String authorName, int subscriberID) {
		output = hostTarget.path(ENDPOINT + authorName + "/unsubscribe/" + subscriberID)
				.request(MediaType.APPLICATION_JSON).delete();

		String unSubscribe = output.readEntity(String.class);
		return unSubscribe;

	}
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public Subscriber getAuthorChannelSubscriber(String authorName, String userName) {
		output = hostTarget.path(ENDPOINT + authorName + "/subscribe/" + userName).request(MediaType.APPLICATION_JSON)
				.get();

		if (output.getStatus() == 200) {
			Subscriber subscribe = output.readEntity(Subscriber.class);
			return subscribe;

		}

		return null;

	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public Message sendMessage(String authorName, Message message) {
		output = hostTarget.path(ENDPOINT + authorName + "/messages/").request(MediaType.APPLICATION_JSON)
				.post(Entity.json(message));

		if (output.getStatus() == 201) {
			Message ResponseMessage = output.readEntity(Message.class);
			return ResponseMessage;

		}

		return null;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public boolean createAuthorProfile(String authorName, InputStream is) {

		output = hostTarget.path(ENDPOINT + authorName + "/profile/" ).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(is, MediaType.APPLICATION_OCTET_STREAM));

		if (output.getStatus() == 201) {
			String ResponseMessage = output.readEntity(String.class);
			System.out.println(ResponseMessage);
			return true;

		}

		return false;

	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public Message getMessageDetails(int messageID) {
		output = hostTarget.path(MESSAGE_DETAILS + messageID).request(MediaType.APPLICATION_JSON).get();

		if (output.getStatus() == 200) {
			Message message = output.readEntity(Message.class);
			return message;

		}

		return null;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public List<Message> getAllMessagesInAuthorChannel(String authorName) {
		output = hostTarget.path(ENDPOINT + authorName + "/messages").request(MediaType.APPLICATION_JSON).get();

		if (output.getStatus() == 200) {
			List<Message> message = output.readEntity(new GenericType<List<Message>>() {
			});

			return message;

		}

		return null;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	public List<Author> getAllAuthors(String userName) {
		output = hostTarget.path(GET_AUTHORS + "/" + userName).request(MediaType.APPLICATION_JSON).get();

		if (output.getStatus() == 200) {
			List<Author> author = output.readEntity(new GenericType<List<Author>>() {
			});

			return author;

		}

		return null;
	}

	/*------------------------------------------------------------------------------------------------*/
	@Override
	// overload method to get author with subscriber object specific to the user
	// this method will enable to check if user is a subscriber to the author
	// channel
	// without the need of an additional REST of subscriber type
	public Author getAuthorChannel(String authorName, String userName) {
		output = hostTarget.path(ENDPOINT + authorName + "/" + userName).request(MediaType.APPLICATION_JSON).get();

		if (output.getStatus() == 200) {
			Author author = output.readEntity(Author.class);
			return author;

		}

		return null;
	}

	@Override
	public AuthorFiles getAuthorProfile(int authorId) {
		
		output = hostTarget.path(PROFILE + authorId).request(MediaType.APPLICATION_JSON).get();

		if (output.getStatus() == 200) {
			AuthorFiles profile = output.readEntity(AuthorFiles.class);
			return profile;

		}

		return null;
	}

	/*------------------------------------------------------------------------------------------------*/
}