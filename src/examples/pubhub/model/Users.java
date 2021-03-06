package examples.pubhub.model;
// default package

// Generated Jul 8, 2017 3:58:01 PM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "USERS")
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = -2382525813781301724L;

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "FIRSTNAME")
	private String firstname;
	@Column(name = "LASTNAME")
	private String lastname;
	@Column(name = "PHONE")
	private String phone;
	@OneToMany(mappedBy = "users")
	private Set<BookOrder> bookorder = new HashSet<>();
	@OneToMany(mappedBy = "users")
	private Set<ShoppingCart> shoppingCarts = new HashSet<>();
	@OneToOne(mappedBy = "users")
	private Roles roles;
//	@OneToOne(mappedBy = "users")
//	private Author author;

	public Users() {
	}

	public Users(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Users(String username, String firstname, String lastname, String email, String phone, String password) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.password = password;

	}

	public Users(String username, String email, String password, String firstname, String lastname, String phone,
			Set<BookOrder> bookorder, Set<ShoppingCart> shoppingCarts, Roles roles) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.bookorder = bookorder;
		this.shoppingCarts = shoppingCarts;
		this.roles = roles;
	}
	
	

	

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<BookOrder> getBookorder() {
		return bookorder;
	}

	public void setBookorder(Set<BookOrder> bookorder) {
		this.bookorder = bookorder;
	}

	public Set<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	public Roles getRole() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	

	@Override
	public String toString() {
		return "Users [username=" + username + ", email=" + email + ", firstname=" + firstname + ", lastname="
				+ lastname + ", phone=" + phone + ", ]";
	}

}
