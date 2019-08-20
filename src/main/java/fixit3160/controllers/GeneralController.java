package fixit3160.controllers;

/**
 * @author Benjamin McDonnell (c3166457)
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import fixit3160.db.CommentDao;
import fixit3160.db.TicketDao;
import fixit3160.db.UserDao;

@Controller
public class GeneralController {
	/*
	 * Map urls to views, push models through with the view, 
	 * session is handled elsewhere (Spring Security)
	 * 
	 * application.properties dictates that all pages are in webapp/WEB-INF/pages and must end with .jsp
	 * SecurityConfig dictates which users/roles can access which pages
	 */

	@Autowired
	private UserDao userDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private CommentDao commentDao;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mvc = new ModelAndView("dashboard");
		return mvc;
	}
	
	@GetMapping("/admin")
	public ModelAndView admin() {
		return new ModelAndView("admin");
	}
	
	@GetMapping("/users")
	public ModelAndView users() {
		ModelAndView mvc = new ModelAndView("users");
		mvc.addObject("users", userDao.findAll());
		return mvc;
	}
	
	@GetMapping("/tickets")
	public ModelAndView tickets() {
		ModelAndView mvc = new ModelAndView("tickets");
		mvc.addObject("tickets", ticketDao.findAll());
		return mvc;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping("/403")
	public ModelAndView error() {
		return new ModelAndView("403");
	}
}
