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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fixit3160.db.CommentDao;
import fixit3160.db.TicketDao;
import fixit3160.db.UserDao;
import fixit3160.entities.Ticket;

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
	
	//add ticket to database
	//get info from form
	//call dao method to create ticket
	//load a view for that ticket (redirect)
	
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
