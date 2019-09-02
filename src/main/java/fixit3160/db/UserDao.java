/*
 * Class: UserDao.java
 * Package: fixit3160.db
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 * SENG3160 University of Newcastle 2019
 *
 * Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 */
package fixit3160.db;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import fixit3160.entities.User;

/**
 * @author Benjamin McDonnell (c3166457)
 * Handles database access for the public.user database
 */

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	
	@PreAuthorize ("hasRole('Manager')")
	public ArrayList<User> findAll();
	
	public Optional<User> findByUsername(String username);
	
	@PreAuthorize ("hasRole('Manager')")
	public Optional<User> findById(int id);
	
	public ArrayList<User> findAllByRole(String role);
	
	/*
	 * Adding a new entry to DB
	 * -Create new Object
	 * -Set some values on it
	 * -call the save method
	 *
	 * Updating a DB entry
	 * -Create object by querying by id
	 * -change some values on it
	 * -call the save method
	 */
	@PreAuthorize ("hasRole('Manager')")
	public <S extends User> S save(S user);
	
	@Transactional
	@PreAuthorize ("hasRole('Manager')")
	public void deleteById(int id);
	
	
}
