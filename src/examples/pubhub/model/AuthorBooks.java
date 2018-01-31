package examples.pubhub.model;

import java.math.BigDecimal;

public class AuthorBooks {

	private String isbn13;
	private Author author;
	private String title;
	private String authorName;

	private BigDecimal price;

	public AuthorBooks() {

	}

	public AuthorBooks(String isbn13, Author author, String title, String authorName, BigDecimal price) {
		super();
		this.isbn13 = isbn13;
		this.author = author;
		this.title = title;
		this.authorName = authorName;
		this.price = price;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AuthorBooks [isbn13=" + isbn13 + ", author=" + author + ", title=" + title + ", authorName="
				+ authorName + ", price=" + price + "]";
	}

}
