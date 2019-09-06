/*
 * Class: GeneralController.java
 * Package: fixit3160.controllers
 * Project: fixit3160
 *	  An IT help ticketing support system developed using Spring
 *
 *    SENG3160 University of Newcastle 2019
 *
 *    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 */
package fixit3160.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import fixit3160.db.CommentDao;
import fixit3160.db.TicketDao;
import fixit3160.db.UserDao;

/**
 * Map urls to views, push models through with the view, session is handled elsewhere (Spring Security)
 * 
 * application.properties dictates that all pages are in webapp/WEB-INF/pages and must end with .jsp
 * SecurityConfig dictates which users/roles can access which pages
 */

@Controller
public class GeneralController {
	/*
	 * Map urls to views, push models through with the view, 
	 * session is handled elsewhere by Spring Security
	 * 
	 * application.properties dictates that all pages are in webapp/WEB-INF/pages and must end with the .jsp suffix
	 * SecurityConfig dictates which users/roles can access which pages
	 */
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mvc = new ModelAndView("dashboard");
		return mvc;
	}
	
	@GetMapping("/admin")
	public ModelAndView admin() {
		return new ModelAndView("admin");
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping("/403")
	public ModelAndView error403() {
		return new ModelAndView("403");
	}
	
	@GetMapping("/error")
	public ModelAndView error() {
		return new ModelAndView("error");
	}
}
