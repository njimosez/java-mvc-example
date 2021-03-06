package examples.pubhub.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Book generated by hbm2java
 */
@XmlRootElement
@Entity
@Table(name = "BOOKS")
public class Book {

	/**
	 * 
	 */
	@Id
	@Column(name = "ISBN_13", unique = true, nullable = false)
	private String isbn13;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "AUTHOR")
	private String author;
	@Column(name = "PUBLISH_DATE")
	private LocalDate publishDate;
	@Column(name = "PRICE")
	private double price;
	@Column(name = "CONTENT")
	private byte[] content;

	@OneToMany(mappedBy = "books")
	private Set<PurchasedBooks> purchasedBooks = new HashSet<>();
	@OneToMany(mappedBy = "books")
	private Set<CartItem> cartItems = new HashSet<>();
	@OneToMany(mappedBy = "books", fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<BookTags> bookTags = new HashSet<>();

	public Book() {
	}

	// Constructor used when no date is specified
	public Book(String isbn, String title, String author, byte[] content) {
		this.isbn13 = isbn;
		this.title = title;
		this.author = author;
		this.publishDate = LocalDate.now();
		this.content = content;
	}

	// Constructor used when a date is specified
	public Book(String isbn, String title, String author, byte[] content, LocalDate publishDate) {
		this.isbn13 = isbn;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.content = content;
	}

	public Book(String isbn13, Set<BookTags> bookTags) {

		this.isbn13 = isbn13;
		this.bookTags = bookTags;
	}

	public Book(String isbn13, String title, String author, LocalDate publishDate, double price, byte[] content,
			Set<PurchasedBooks> purchasedBooks, Set<CartItem> cartItems, Set<BookTags> bookTags) {
		this.isbn13 = isbn13;
		this.title = title;
		this.author = author;
		this.publishDate = LocalDate.now();
		this.price = price;
		this.content = content;
		this.purchasedBooks = purchasedBooks;
		this.cartItems = cartItems;
		this.bookTags = bookTags;
	}

	public String getIsbn13() {
		return this.isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Set<PurchasedBooks> getPurchasedBooks() {
		return this.purchasedBooks;
	}

	public void setPurchasedBooks(Set<PurchasedBooks> purchasedBooks) {
		this.purchasedBooks = purchasedBooks;
	}

	public Set<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Set<BookTags> getBookTags() {
		return this.bookTags;
	}

	public void setBookTags(Set<BookTags> bookTags) {
		this.bookTags = bookTags;
	}

	@Override
	public String toString() {
		return "Book [isbn13=" + isbn13 + ", title=" + title + ", author=" + author + ", publishDate=" + publishDate
				+ ", price=" + price + "]";
	}

}
