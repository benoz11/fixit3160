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

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fixit3160.entities.User;

/**
 * @author Benjamin McDonnell (c3166457)
 * Handles database access for the public.user database
 */

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	@Transactional
	public ArrayList<User> findAll();
	
	public User findByUsername(String username);
	
	public User findById(int id);
}
