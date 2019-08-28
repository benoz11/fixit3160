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

import java.util.ArrayList;
import java.util.Optional;

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
		ArrayList<Ticket> tickets = ticketDao.findForCurrentUser();
		mvc.addObject("tickets", tickets);
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
