package examples.pubhub.dao;

import java.util.List;
import examples.pubhub.model.*;

/**
 * Interface for to handle book tagging database queries.
 */

public interface BookTagsDAO {

	public List<BookTags> getBookTags();

	public BookTags getBookByTagName(String tagName);

	public boolean addBookTag(BookTags tag);

	public boolean updateTag(String OldTag, String Newtag);

	public boolean deleteBookTag(String tagName, String isbn);

	public boolean createBookTag(String isbn, String tagName);
}
