package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Users;

/**
 * Interface for our User Access Object to handle database queries related to
 * Users and their roles.
 */
public interface UserDAO {

	public List<Users> getAllUsers();

	public Users getUserByEmail(String email);

	public Users getUserByusername(String username);

	public boolean createUser(Users user, String rolename);

	public boolean updateUserEmail(String username, String email);

	public boolean deleteUser(Users user);
	
	public void updateUserRole(Users user,String roleName);

}
