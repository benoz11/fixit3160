/*
 * Class: GeneralController.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import fixit3160.db.CommentDao;
import fixit3160.db.TicketDao;
import fixit3160.db.UserDao;

/**
 * @author Benjamin McDonnell (c3166457)
 *
 * Map urls to views, push models through with the view, session is handled elsewhere (Spring Security)
 * 
 * application.properties dictates that all pages are in webapp/WEB-INF/pages and must end with .jsp
 * SecurityConfig dictates which users/roles can access which pages
 */

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private CommentDao commentDao;
	
	@GetMapping("/users")
	public ModelAndView users() {
		ModelAndView mvc = new ModelAndView("users");
		mvc.addObject("users", userDao.findAll());
		return mvc;
	}
}
