/*
 * Class: TicketController.java
 * Package: fixit3160.controllers
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 *    SENG3160 University of Newcastle 2019
 *
 *    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 */
package fixit3160.controllers;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fixit3160.db.CommentDao;
import fixit3160.db.TicketDao;
import fixit3160.db.UserDao;
import fixit3160.entities.Comment;
import fixit3160.entities.Ticket;
import fixit3160.entities.User;

/**
 * Map urls to views, push models through with the view, session is handled elsewhere (Spring Security)
 * 
 * application.properties dictates that all pages are in webapp/WEB-INF/pages and must end with .jsp
 * SecurityConfig dictates which users/roles can access which pages
 */

@Controller
public class TicketController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private CommentDao commentDao;
	
	/**
	 * Dynamically up date the priority of all tickets
	 * Get all tickets that the current user is allowed to see
	 * @return
	 */
	@GetMapping("/tickets")
	public ModelAndView tickets() {
		ModelAndView mvc = new ModelAndView("tickets");
		ArrayList<Ticket> allTickets = ticketDao.findAll();
		LocalDateTime localDateTime = LocalDateTime.now(Clock.systemUTC());		// get current time in UTC
		for(Ticket ticket : allTickets) {
			if (ticket.getPrioritypoints() < 1) {								// if prioritypoints is equal to 0 or negative for some reason, reset to 1
				ticket.setPriorityPoints(1);
			}
			if (ticket.getCreated().equals(localDateTime) == false) {			// check that ticket has not been just created
				if (ticket.getPrioritylevel().equals("Low")) {
					ticket.setPriorityPoints(ticket.getPrioritypoints() * 2);
				} else if (ticket.getPrioritylevel().equals("Medium")) {
					ticket.setPriorityPoints(ticket.getPrioritypoints() * 3);
				} else if (ticket.getPrioritylevel().equals("High")) {
					ticket.setPriorityPoints(ticket.getPrioritypoints() * 4);
				} else {
					ticket.setPriorityPoints(ticket.getPrioritypoints() * 6);	// if prioritylevel is critical
				}
			}
		}
		ticketDao.saveAll(allTickets);
		ArrayList<Ticket> tickets = ticketDao.findForCurrentUser();
		mvc.addObject("tickets", tickets);
		return mvc;
	}

	@GetMapping("/knowledgeBase")
	public ModelAndView knowledgebase() {
		ModelAndView mvc = new ModelAndView("knowledgebase");
		ArrayList<Ticket> knowledgeBaseTickets = ticketDao.findInKnowledgeBase();
		mvc.addObject("knowledgeBaseTickets", knowledgeBaseTickets);
		return mvc;
	}
	
	@GetMapping("/tickets/{id}")
	public ModelAndView viewTicket(@PathVariable int id) {
		ModelAndView mvc;
		Optional<Ticket> ticket = ticketDao.findById(id);
		if (ticket.isPresent() && canView(ticket.get())) {
			mvc = new ModelAndView("viewticket");
			mvc.addObject("ticket", ticket.get());
			return mvc;
		}
		return new ModelAndView("redirect:/tickets");
	}
	
	// Deletes the ticket
	@PostMapping("/tickets/{id}/delete")
	public ModelAndView deleteTicket(@PathVariable int id) {
		return new ModelAndView("redirect:/tickets");
	}
	 // Takes you to the edit a ticket page
	@PostMapping("/tickets/{id}/edit")
	public ModelAndView editTicket(@PathVariable int id) {
		return new ModelAndView("redirect:/tickets");
	}
	// Submits the edits
	@PostMapping("/tickets/{id}/edit/submit")
	public ModelAndView submitEditTicket(@PathVariable int id) {
		return new ModelAndView("redirect:/tickets");
	}
	// Takes you to the create a ticket page
	@GetMapping("/tickets/create")
	public ModelAndView createTicket() {
		return new ModelAndView("redirect:/tickets");
	}
	// submits the ticket
	@GetMapping("/tickets/create/submit")
	public ModelAndView submitCreateTicket() {
		return new ModelAndView("redirect:/tickets");
	}
	
	@PostMapping("/tickets/{id}/assign")
	public ModelAndView assignTicket(@PathVariable int id) {
		ModelAndView mvc = new ModelAndView("assigncaseworker");
		Optional<Ticket> ticket = ticketDao.findById(id);
		if (ticket.isPresent()) {
			mvc.addObject("ticket",ticket.get());
			mvc.addObject("caseworkers", userDao.findAllByRole("Caseworker"));
			return mvc;
		}
		return new ModelAndView("redirect:/tickets");
	}

	@PostMapping("/tickets/{id}/assign/submit")
	public ModelAndView submitAssignTicket(@PathVariable int id, @RequestParam(value="caseworkerid") int caseworkerid) {
		Optional<Ticket> dbTicket = ticketDao.findById(id);
		if (dbTicket.isPresent()) {
			Optional<User> dbUser = userDao.findById(caseworkerid);
			if (dbUser.isPresent()) {
				Ticket ticket = dbTicket.get();
				User user = dbUser.get();
				ticket.setCaseworker(user);
				if(ticket.getState().equals("Open")) {ticket.setState("In Progress");}
				ticketDao.save(ticket);
				return new ModelAndView("redirect:/tickets/"+id);
			}
		}
		return new ModelAndView("redirect:/tickets/");
	}
	
	@PostMapping("/tickets/{id}/close")
	public ModelAndView closeTicket(@PathVariable int id, Authentication authentication) {
		//Manager can close an open ticket
		//Manager or caseworker can close an in progress, resolved, or completed ticket
		return setStateRedirect(id, "Closed", authentication);
	}
	
	@PostMapping("/tickets/{id}/complete")
	public ModelAndView completeTicket(@PathVariable int id, Authentication authentication) {
		//poster can use this if old state was Resolved
		//manager can use this at any time
		return setStateRedirect(id, "Completed", authentication);
	}
	
	@PostMapping("/tickets/{id}/reject")
	public ModelAndView rejectTicket(@PathVariable int id, Authentication authentication) {
		//poster or manager can use this IF old state was Resolved
		return setStateRedirect(id, "In Progress", authentication);
	}
	
	@PostMapping("/tickets/{id}/kb")
	public ModelAndView kbTicket(@PathVariable int id, Authentication authentication) {
		//Manager can use this if old state was Completed
		return setStateRedirect(id, "Knowledge Base", authentication);
	}
	
	@PostMapping("/tickets/{id}/open")
	public ModelAndView openTicket(@PathVariable int id, Authentication authentication) {
		//Manager can use this if old state was Closed
		return setStateRedirect(id, "Open", authentication);
	}
	
	/*
	 * Comments related mappings
	 */
	@PostMapping("/tickets/{id}/postcomment")
	public ModelAndView postComment(@PathVariable int id, @RequestParam(value="contents") String contents, @RequestParam(value="resolution") Optional<String> resolution) {
		Optional<Ticket> dbTicket = ticketDao.findById(id); 							// find ticket by id
		if (dbTicket.isPresent() && canView(dbTicket.get())) {	 	// if ticket exists and user is allowed to view it
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // principal is the currently logged in Spring Security user object
			Optional<User> dbposter = userDao.findByUsername(((UserDetails)principal).getUsername()); // find user by display name (currently all I can get from spring security)
			if (dbposter.isPresent()) {													// if user exists
				User poster = dbposter.get();											// get the user object
				Comment newcomment = new Comment();										// create a new comment
				newcomment.setContents(contents);										// set its values...
				newcomment.setTicketid(id);
				newcomment.setPosterid(poster.getId());
				commentDao.save(newcomment);											// save comment to db
				
				if(resolution.isPresent()) {											// if we have ticked the post as resolution box
					Ticket ticket = dbTicket.get();
					ticket.setState("Resolved");
					ticketDao.save(ticket);
				}
				
				return new ModelAndView("redirect:/tickets/"+id);						// reload ticket page
			}
		} //ticket or user not found
		return new ModelAndView("redirect:/tickets");
	}
	
	@PostMapping("/tickets/{id}/deletecomment")
	public ModelAndView deleteComment(@PathVariable int id, @RequestParam(value="commentid") int commentid, Authentication authentication) {
		boolean manager = (authentication.getAuthorities().toArray()[0] + "").equals("Manager"); //true if user is manager
		Optional<Ticket> dbTicket = ticketDao.findById(id); 							// find ticket by id
		if (dbTicket.isPresent() && canView(dbTicket.get())) {	
			Optional<Comment> dbComment = commentDao.findById(commentid);
			if (dbComment.isPresent()) {
				Comment comment = dbComment.get();
				if (isOwner(comment) || manager) {
					commentDao.deleteById(commentid);
					return new ModelAndView("redirect:/tickets/"+id);
				}
			}
		}
		return new ModelAndView("redirect:/tickets");
	}
	
	@PostMapping("/tickets/{id}/editcomment")
	public ModelAndView editComment(@PathVariable int id, @RequestParam(value="commentid") int commentid,
			@RequestParam(value="commentcontents") String commentcontents, Authentication authentication) {
		boolean manager = (authentication.getAuthorities().toArray()[0] + "").equals("Manager"); //true if user is manager
		Optional<Ticket> dbTicket = ticketDao.findById(id); 							// find ticket by id
		if (dbTicket.isPresent() && canView(dbTicket.get())) {	
			Optional<Comment> dbComment = commentDao.findById(commentid);
			if (dbComment.isPresent()) {
				Comment comment = dbComment.get();
				if(isOwner(comment) || manager) {
					comment.setContents(commentcontents);
					commentDao.save(comment);
					return new ModelAndView("redirect:/tickets/"+id);
				}
			}
		}
		return new ModelAndView("redirect:/tickets");
	}
	
	/*
	 * Helper functions
	 */
	
	/**
	 * Set the state for ticket with this id, return model and view for redirect
	 * Most of the authentication logic is not exactly necessary, as GET requests are not supported
	 *  	for the relevant paths and the POST forms only appear 
	 *  		for the relevant parties in the jsp.
	 *  However due to the fact that POST requests can be falsified using certain software,
	 *  	Extra protections are helpful to stop users 
	 *  		from performing actions outside of their scope.
	 * @param id
	 * @param newstate
	 * @param authentication
	 * @return
	 */
	private ModelAndView setStateRedirect(int id, String newstate, Authentication authentication) {
		Optional<Ticket> dbTicket = ticketDao.findById(id);
		if (dbTicket.isPresent()) {
			Ticket ticket = dbTicket.get();  //get the ticket from the optional object
			String role = authentication.getAuthorities().toArray()[0] + ""; //get user's role as string
			String oldstate = ticket.getState();
			boolean owner = isOwner(ticket);
			boolean manager = role.equals("Manager");
			//if user and state conditions are correct, set the state and save, else return to tickets
			if ( (manager && oldstate.equals("Open") && newstate.equals("Closed")) ||
					(equalsAny(role,"Manager","Caseworker") && (equalsAny(oldstate,"In Progress","Resolved","Completed")) && newstate.equals("Closed")) ||
					(owner && oldstate.equals("Resolved") && newstate.equals("Completed")) ||
					(manager && oldstate.equals("Knowledge Base") && newstate.equals("Completed")) ||
					((owner || manager) && oldstate.equals("Resolved") && newstate.equals("In Progress")) ||
					(manager && oldstate.equals("Completed") & newstate.equals("Knowledge Base")) ||
					(manager && oldstate.equals("Closed") && newstate.equals("Open"))) {
				ticket.setState(newstate);       //close ticket
				ticket = ticketDao.save(ticket); //save the changes and reassign the ticket ref to the newly saved ticket
				
				return new ModelAndView("redirect:/tickets/"+id);
			}
		}
		return new ModelAndView("redirect:/tickets");
	}
	
	/**
	 * Quick way to check if the first string equals any of the other strings
	 * Can take any number of args, id equalsAny(lhs,a,b) or equalsAny(lhs,a,b,c,d,e,f), etc
	 * @param lhs
	 * @param stringarr
	 * @return
	 */
	private boolean equalsAny(String lhs, String... stringarr) {
		for(String s : stringarr) {
			if (lhs.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if current user is the owner (poster) of this ticket
	 * @param ticket
	 * @return
	 */
	private boolean isOwner(Ticket ticket) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // principal is the currently logged in Spring Security user object
		Optional<User> dbUser = userDao.findByUsername(((UserDetails)principal).getUsername()); // find user by display name (currently all I can get from spring security)
		if (dbUser.isPresent()) {													// if user exists
			User user = dbUser.get();
			if (user.getId() == ticket.getPosterid()) {return true;}
		}
		return false;
	}
	
	/**
	 * Check if current user is owner (poster) of this comment
	 * @param comment
	 * @return
	 */
	private boolean isOwner(Comment comment) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // principal is the currently logged in Spring Security user object
		Optional<User> dbUser = userDao.findByUsername(((UserDetails)principal).getUsername()); // find user by display name (currently all I can get from spring security)
		if (dbUser.isPresent()) {													// if user exists
			User user = dbUser.get();
			if (user.getId() == comment.getPosterid()) {return true;}
		}
		return false;
	}
	
	/**
	 * Boolean to check if the user is allowed to view this ticket
	 * @param ticket
	 * @return
	 */
	private boolean canView(Ticket ticket) {
		//get current user
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //Get Spring Security principal (user)
		Optional<User> dbUser = userDao.findByUsername(((UserDetails)principal).getUsername()); //Get username
		if (dbUser.isPresent()) {
			User user = dbUser.get(); 
			int userid = user.getId();
			//if user is allowed to view ticket, direct as desired
			if (userid == ticket.getPosterid() || userid == ticket.getCaseworkerid() 
					|| user.getRole().equals("Manager")) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Outdated or for education purposes
	 */
	
	//add ticket to database
	//get info from form
	//call dao method to create ticket
	//load a view for that ticket (redirect)
	
	//Just a demonstration for educational purposes
	@GetMapping("/ticketwithdescription")
	public ModelAndView ticket2(@RequestParam(value = "orderBy", required=false) String orderBy) {
		ModelAndView mvc = new ModelAndView("tickets");
		ArrayList<Ticket> tickets = new ArrayList<>();
		//get the parameter orderby
		//check if null
		//if (orderBy.equals("asc")) {tickets = ticketDao.findAllByOrderByDescriptionAsc();}
		//else {tickets = ticketDao.findAllByOrderByDescriptionDesc();}
		tickets = ticketDao.findAllByOrderByDescriptionAsc();
		mvc.addObject("tickets", tickets);
		return mvc;
	}
}