/*
 * Class: CommentDao.java
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

import fixit3160.entities.Comment;
import fixit3160.entities.User;

/**
 * Handles database access for the public.comment database
 */

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {
	@Transactional
	public ArrayList<Comment> findAll();
	
	@Transactional
	public Optional<Comment> findById(int id);
	
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
	@Transactional
	public <S extends Comment> S save(S comment);
	
	@Transactional
	public void deleteById(int id);
}
