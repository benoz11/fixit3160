/*
 * Class: TicketController.java
 * Package: fixit3160.controllers
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 * SENG3160 University of Newcastle 2019
 *
 * Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
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
 * @author Benjamin McDonnell (c3166457)
 *
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
	
	@GetMapping("/tickets")
	public ModelAndView tickets() {
		ModelAndView mvc = new ModelAndView("tickets");
		
		ArrayList<Ticket> allTickets = ticketDao.findAll();
		LocalDateTime localDateTime = LocalDateTime.now(Clock.systemUTC());
		for(Ticket ticket : allTickets) {
			if (ticket.getCreated().equals(localDateTime) == false) {
				if (ticket.getPrioritylevel().equals("Low")) {
					ticket.setPriorityPoints(ticket.getPrioritypoints() * 2); //getDateDifference(Date.from(localDateTime.atZone),ticket.getCreated(),TimeUnit.DAYS));
				} else if (ticket.getPrioritylevel().equals("Medium")) {
					ticket.setPriorityPoints(ticket.getPrioritypoints() * 3);
				} else if (ticket.getPrioritylevel().equals("Medium")) {
					ticket.setPriorityPoints(ticket.getPrioritypoints() * 5);
				} else {
					ticket.setPriorityPoints(ticket.getPrioritypoints() * 8);
				}
			}
		}
		
		ArrayList<Ticket> tickets = ticketDao.findForCurrentUser(); //moved to after priority edits so they reflect the updates
		mvc.addObject("tickets", tickets);

		ticketDao.saveAll(allTickets);
		return mvc;
	}

	public long getDateDifference(Date date1, Date date2, TimeUnit tUnit) {
		long dateDiff = date2.getTime() - date1.getTime();
		return tUnit.convert(dateDiff, TimeUnit.DAYS);
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
		if (ticket.isPresent()) {
			mvc = new ModelAndView("viewticket");
			mvc.addObject("ticket", ticket.get());
			return mvc;
		}
		return new ModelAndView("redirect:/tickets");
	}
	
	/*
	 * Related to ticket manipulation or state changing
	 */
	//TODO: @Kundayi
	@PostMapping("/tickets/{id}/delete")
	public ModelAndView deleteTicket(@PathVariable int id) {
		
		return new ModelAndView("redirect:/tickets");
	}
	//TODO: @Kundayi
	@PostMapping("/tickets/{id}/edit")
	public ModelAndView editTicket(@PathVariable int id) {
		
		return new ModelAndView("redirect:/tickets");
	}
	
	@PostMapping("/tickets/{id}/close")
	public ModelAndView closeTicket(@PathVariable int id) {
		return setState(id, "Closed");
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
	//TODO: @Benjamin McDonnell
	@PostMapping("/tickets/{id}/assign/submit")
	public ModelAndView submitAssignTicket(@PathVariable int id, @RequestParam(value="caseworkerid") int caseworkerid) {
		Optional<Ticket> dbTicket = ticketDao.findById(id);
		if (dbTicket.isPresent()) {
			Optional<User> dbUser = userDao.findById(caseworkerid);
			if (dbUser.isPresent()) {
				Ticket ticket = dbTicket.get();
				User user = dbUser.get();
				ticket.setCaseworker(user); //Is it sufficient to just change the Caseworker col and the id will auto change???
				if(ticket.getState().equals("Open")) {ticket.setState("In Progress");}
				ticketDao.save(ticket);
				return new ModelAndView("redirect:/tickets/"+id);
			}
		}
		return new ModelAndView("redirect:/tickets/");
	}
	
	@PostMapping("/tickets/{id}/complete")
	public ModelAndView completeTicket(@PathVariable int id) {
		return setState(id, "Completed");
	}
	
	@PostMapping("/tickets/{id}/reject")
	public ModelAndView rejectTicket(@PathVariable int id) {
		return setState(id, "In Progress");
	}
	
	@PostMapping("/tickets/{id}/kb")
	public ModelAndView kbTicket(@PathVariable int id) {
		return setState(id, "Knowledge Base");
	}
	
	@PostMapping("/tickets/{id}/open")
	public ModelAndView openTicket(@PathVariable int id) {
		return setState(id, "Open");
	}
	
	/*
	 * Comments related mappings
	 */
	@PostMapping("/tickets/{id}/postcomment")
	public ModelAndView postComment(@PathVariable int id, @RequestParam(value="contents") String contents) {
		Optional<Ticket> dbTicket = ticketDao.findById(id); 							//find ticket by id
		if (dbTicket.isPresent()) {														//if ticket exists
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //principal is the currently logged in Spring Security user object
			Optional<User> dbposter = userDao.findByUsername(((UserDetails)principal).getUsername()); //find user by display name (currently all I can get from spring security)
			if (dbposter.isPresent()) {													//if user exists
				User poster = dbposter.get();											//get the user object
				Comment newcomment = new Comment();										//create a new comment
				newcomment.setContents(contents);										//set its values...
				newcomment.setTicketid(id);
				newcomment.setPosterid(poster.getId());
				commentDao.save(newcomment);											//save comment to db
				return new ModelAndView("redirect:/tickets/"+id);						//reload ticket page
			}
		} //ticket or user not found
		return new ModelAndView("redirect:/tickets");
	}
	
	@PostMapping("/tickets/{id}/deletecomment")
	public ModelAndView deleteComment(@PathVariable int id, @RequestParam(value="commentid") int commentid) {
		Optional<Ticket> dbTicket = ticketDao.findById(id); 							//find ticket by id
		if (dbTicket.isPresent()) {	
			commentDao.deleteById(commentid);
			return new ModelAndView("redirect:/tickets/"+id);
		}
		return new ModelAndView("redirect:/tickets");
	}
	
	@PostMapping("/tickets/{id}/editcomment")
	public ModelAndView editComment(@PathVariable int id, @RequestParam(value="commentid") int commentid,
			@RequestParam(value="commentcontents") String commentcontents) {
		Optional<Ticket> dbTicket = ticketDao.findById(id); 							//find ticket by id
		if (dbTicket.isPresent()) {	
			Optional<Comment> dbComment = commentDao.findById(commentid);
			if (dbComment.isPresent()) {
				Comment comment = dbComment.get();
				comment.setContents(commentcontents);
				commentDao.save(comment);
				return new ModelAndView("redirect:/tickets/"+id);
			}
		}
		return new ModelAndView("redirect:/tickets");
	}
	
	/*
	 * Helper functions
	 */
	
	/**
	 * Set the state for ticket with this id, return model and view for redirect
	 * @param id
	 * @param newstate
	 * @return
	 */
	private ModelAndView setState(int id, String newstate) {
		Optional<Ticket> dbTicket = ticketDao.findById(id);
		if (dbTicket.isPresent()) {
			Ticket ticket = dbTicket.get();  //get the ticket from the optional object
			ticket.setState(newstate);       //close ticket
			ticket = ticketDao.save(ticket); //save the changes and reassign the ticket ref to the newly saved ticket
			
			return new ModelAndView("redirect:/tickets/"+id);
		}
		return new ModelAndView("redirect:/tickets");
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
