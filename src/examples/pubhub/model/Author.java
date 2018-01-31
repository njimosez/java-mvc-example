package examples.pubhub.model;
// Generated Aug 22, 2017 9:17:06 AM by Hibernate Tools 5.2.3.Final

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Author {

	private int authorid;
	private Users users;
	private String authorname;
	private boolean isASubscriber;

	private List<Link> links = new ArrayList<>();

	private List<AuthorBooks> book = new ArrayList<>();
	private List<Message> messages = new ArrayList<>();

	private AuthorFiles profile;

	private Subscriber subscriber;

	public Author() {
	}

	public Author(int authorid, Users users, String authorname, boolean isASubscriber, List<Link> links,
			List<AuthorBooks> book, List<Message> messages, AuthorFiles profile, Subscriber subscriber) {
		super();
		this.authorid = authorid;
		this.users = users;
		this.authorname = authorname;
		this.isASubscriber = isASubscriber;
		this.links = links;
		this.book = book;
		this.messages = messages;
		this.profile = profile;
		this.subscriber = subscriber;
	}

	public Author(int authorid, String authorname, boolean isASubscriber, List<Link> links, List<AuthorBooks> book,
			AuthorFiles profile, Subscriber subscriber) {
		super();
		this.authorid = authorid;
		this.authorname = authorname;
		this.isASubscriber = isASubscriber;
		this.links = links;
		this.book = book;
		this.profile = profile;
		this.subscriber = subscriber;
	}

	public int getAuthorid() {
		return this.authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	@XmlTransient
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getAuthorname() {
		return this.authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public boolean isASubscriber() {
		return isASubscriber;
	}

	public void setASubscriber(boolean isASubscriber) {
		this.isASubscriber = isASubscriber;
	}

	@Transient
	public List<AuthorBooks> getBook() {
		return book;
	}

	public void setBook(List<AuthorBooks> book) {
		this.book = book;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Transient
	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}

	@Transient
	public List<Link> getLinks() {
		return links;
	}

	@Transient
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Transient
	public AuthorFiles getProfile() {
		return profile;
	}

	public void setProfile(AuthorFiles profile) {
		this.profile = profile;
	}

	@Transient
	public Subscriber getSubscription() {
		return subscriber;
	}

	public void setSubscription(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return "Author [authorid=" + authorid + ", authorname=" + authorname + "]";
	}

}
