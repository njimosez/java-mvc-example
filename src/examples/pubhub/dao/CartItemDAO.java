package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.CartItem;
import examples.pubhub.model.ShoppingCart;

public interface CartItemDAO {

	public boolean addCartItem(Book books, ShoppingCart shoppingCart);

	public List<CartItem> getUserCartItemsByShoppingCardId(Long cartId);

	public boolean deleteCartItem(int id);

	public CartItem findById(int id);

	public boolean updateItemQuantity(CartItem item, int quantity);

	public CartItem getCartItemByISBN(String isbn);

	public CartItem ExistCartItem(String isbn, ShoppingCart cart);

}
