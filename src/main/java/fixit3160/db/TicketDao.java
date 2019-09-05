/*
 * Class: TicketDao.java
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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import fixit3160.entities.Ticket;

/**
 * Handles database access for the public.ticket database
 * Note:
 * 		We do NOT need to provide implementation for these!
 * 		Hibernate will automatically wire these up to our db
 * 		Just specify the return/param type and use the given naming conventions, eg
 * 		 	findByColumname
 * 			findAll
 * 			findByColumnameContaining
 *    		findByColumn1AndColumn2
 *    		findDistinctByColumname
 *    		deleteByID
 *    		public ArrayList<Comments> findByTicketId(int id);
 *
 *    	See: https://www.concretepage.com/spring-boot/spring-boot-crudrepository-example
 *    		 https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 *    		 https://www.logicbig.com/tutorials/spring-framework/spring-data/query-advance-like-expression.html
 *
 */

@ComponentScan
@Repository
public interface TicketDao extends CrudRepository<Ticket, Long> {

	public ArrayList<Ticket> findByNameContaining(String name); //consider some kind of fuzzysearch in future

	@PreAuthorize("hasRole('Manager'")
	public ArrayList<Ticket> findAll();

	/**
	 * If user is manager get all tickets, else get tickets where user.id = posterid OR caseworkerid
	 * @return
	 */
	@Query("SELECT t FROM Ticket t WHERE "
			+ "(Select u.role FROM User u WHERE u.username = ?#{ principal?.username }) = 'Manager'"
			+ " OR t.poster = (SELECT u FROM User u WHERE u.username = ?#{ principal?.username })"
			+ " OR t.caseworker = (SELECT u FROM User u WHERE u.username = ?#{ principal?.username })")
	public ArrayList<Ticket> findForCurrentUser();

	/**
	 * Returns all tickets that have a 'knowledge base' state. This is intended for the knowledge base page.
	 * This should return the same result for any user.
	 *
	 * @return ArrayList of Ticket objects
	 */
	@Query("SELECT t FROM Ticket t WHERE t.state = 'Knowledge Base'")
	public ArrayList<Ticket> findInKnowledgeBase();

	/**
	 * TODO: We want this to only be accessible if the user is allowed to see this ticket
	 * @param id
	 * @return
	 */
	public Optional<Ticket> findById(int id);

	/* Sorting alphabetically, both ascending and descending */
	@Query ("SELECT t FROM Ticket t "
			+ "ORDER BY t.description ASC")
	public ArrayList<Ticket> orderAlphabeticallyAscending();

	@Query ("SELECT t FROM Ticket t "
			+ "ORDER BY t.description DESC")
	public ArrayList<Ticket> orderAlphabeticallyDescending();

	/* modified query that searches description */
	@Query ("SELECT t FROM Ticket t WHERE "
			+ "t.description LIKE '%keyboard%'")
	public ArrayList<Ticket> findKeyboard();

	public ArrayList<Ticket> findByDescriptionContaining(String containing);

	/* modified query that searches both name and description*/
	@Query ("SELECT t FROM Ticket t WHERE "
			+ "t.name LIKE '%testing%' OR "
			+ "t.name LIKE '%testing%' OR "
			+ "t.description LIKE '%testing%' OR "
			+ "t.description LIKE '%testing%'")
	public ArrayList<Ticket> findSearchTerm(String searchTerm);

	public ArrayList<Ticket> findAllByOrderByDescriptionAsc();
	public ArrayList<Ticket> findAllByOrderByDescriptionDesc();


}
