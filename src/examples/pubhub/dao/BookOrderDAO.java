package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.BookOrder;
import examples.pubhub.model.ShoppingCart;
import examples.pubhub.model.Users;

public interface BookOrderDAO {

	public boolean CreateOrder(Users user, ShoppingCart cart, Long OrderId);

	public BookOrder getOrderByUsername(String username);

	public BookOrder findbyId(Long id);

	public List<BookOrder> getAllOrderForUser(String username);

	public BookOrder MCreateOrder(Users user, ShoppingCart cart, Long OrderId);
}
