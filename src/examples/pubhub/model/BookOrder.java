package examples.pubhub.model;
// default package

// Generated Jul 8, 2017 3:58:01 PM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Book Order Class
 */
@Entity
@Table(name = "BOOK_ORDER")
public class BookOrder implements java.io.Serializable {

	private static final long serialVersionUID = -7245532326074255325L;

	@Id
	// @GeneratedValue(strategy=GenerationType.SEQUENCE,
	// generator="BOOK_ORDER_SEQ")
	// @SequenceGenerator(name="BOOK_ORDER_SEQ", sequenceName="BOOK_ORDER_SEQ",
	// allocationSize=1)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "GRAND_TOTAL")
	private BigDecimal grandTotal;

	@Column(name = "TIMESTAMP")
	private LocalDate orderDate;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne
	@JoinColumn(name = "USERID")
	private Users users;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookorder", fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<PurchasedBooks> purchasedBooks = new HashSet<>();

	public BookOrder() {
	}

	public BookOrder(Long id, Users users) {
		this.id = id;
		this.users = users;
	}

	public BookOrder(Long id, BigDecimal grandTotal, LocalDate orderDate, String status, Users users) {

		this.id = id;
		this.grandTotal = grandTotal;
		this.orderDate = LocalDate.now();
		this.status = status;
		this.users = users;

	}

	public BookOrder(Users users, BigDecimal grandTotal, LocalDate orderDate, String status) {

		this.grandTotal = grandTotal;
		this.orderDate = orderDate;
		this.status = status;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Set<PurchasedBooks> getPurchasedBooks() {
		return purchasedBooks;
	}

	public void setPurchasedBooks(Set<PurchasedBooks> purchasedBooks) {
		this.purchasedBooks = purchasedBooks;
	}

}
