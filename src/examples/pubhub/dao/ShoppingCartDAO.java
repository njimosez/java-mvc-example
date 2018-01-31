package examples.pubhub.dao;

import examples.pubhub.model.ShoppingCart;
import examples.pubhub.model.Users;

public interface ShoppingCartDAO {

	public boolean createCart(Users user);

	public ShoppingCart getCartByUserName(String username);

	public ShoppingCart updateShoppingCart(ShoppingCart cart);

	public ShoppingCart findById(Long id);

	public void clearShoppingCart(ShoppingCart cart);

}
