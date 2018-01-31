package examples.pubhub.rest;

import java.io.InputStream;
import java.util.List;

import examples.pubhub.model.Author;
import examples.pubhub.model.AuthorFiles;
import examples.pubhub.model.Book;
import examples.pubhub.model.Message;
import examples.pubhub.model.Subscriber;

public interface PubHubRestClient {

	public Author createAuthorChannel(String userName);
	
	public Author getAuthorChannel(String authorName);
	
	public Author getAuthorChannel(String authorName, String userName);
	
	public List<Book> getBooksByAuthor(String authorName);
	
	public String createSubscriptionToAuthorChannel(String authorName, String userName);
	
	public Subscriber getAuthorChannelSubscriber(String authorName, String userName);
	
	public String unSubscribe(String authorName , int subscriberID);
	
	public Message sendMessage(String authorName, Message message);
	
	public boolean createAuthorProfile(String authorName, InputStream is);
	
	public List<Message> getAllMessagesInAuthorChannel(String authorName);
	
	public Message getMessageDetails(int messageID);
	
	public List<Author> getAllAuthors(String userName);
	
	public AuthorFiles getAuthorProfile(int authorId);
	
	
	
	
	
}
