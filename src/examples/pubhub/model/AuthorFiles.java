package examples.pubhub.model;
// Generated Sep 7, 2017 10:11:05 PM by Hibernate Tools 5.2.3.Final

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AuthorFiles implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Author author;
	private byte[] portrait;
	
	

	public AuthorFiles() {
	}

	public AuthorFiles(int id) {
		this.id = id;
	}

	public AuthorFiles(byte[] portrait) {
		super();
		this.portrait = portrait;
		
	}

	

	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	
	public byte[] getPortrait() {
		return this.portrait;
	}

	public void setPortrait(byte[] portrait) {
		this.portrait = portrait;
	}

	
	

}
