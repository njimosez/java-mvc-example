package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.PurchasedBooks;

public interface PurchasedBooksDAO {

	public List<PurchasedBooks> getBooksByOrderNo(Long OrderId);

	public List<PurchasedBooks> getBooksOrderedByUser(String username);

}
