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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fixit3160.db.CommentDao;
import fixit3160.db.TicketDao;
import fixit3160.db.UserDao;
import fixit3160.entities.User;

/**
 * Map urls to views, push models through with the view, session is handled elsewhere (Spring Security)
 * 
 * application.properties dictates that all pages are in webapp/WEB-INF/pages and must end with .jsp
 * SecurityConfig dictates which users/roles can access which pages
 */

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@GetMapping("/users")
	public ModelAndView users() {
		ModelAndView mvc = new ModelAndView("users");
		mvc.addObject("users", userDao.findAll());
		return mvc;
	}
	
	/**
	 * If user is found, show the viewuser page for this user
	 * Otherwise, redirect to the users page
	 * Shows how to use Optional objects with CrudRepository
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public ModelAndView viewUser(@PathVariable int id) {
		ModelAndView mvc;
		Optional<User> user = userDao.findById(id);
		if (user.isPresent()) {
			mvc = new ModelAndView("viewuser");
			mvc.addObject("user", user.get());
			return mvc;
		}
		return new ModelAndView("redirect:/users");
	}
	
	@GetMapping("/users/{id}/edit")
	public ModelAndView editUser(@PathVariable int id) {
		ModelAndView mvc;
		Optional<User> user = userDao.findById(id);
		if (user.isPresent()) { //if the user exists in the DB
			mvc = new ModelAndView("edituser");
			mvc.addObject("user", user.get());
			return mvc;
		} //else
		return new ModelAndView("redirect:/users");
	}
	
	@PostMapping("/users/{id}/edit/submit")
	public ModelAndView submitUserEdit(@PathVariable int id, @RequestParam(value="name") String name,
			@RequestParam(value="role") String role, @RequestParam(value="username") String username) {
		Optional<User> dbUser = userDao.findById(id);
		if (dbUser.isPresent()) { 		// if the user exists in the DB
			User user = dbUser.get();
			user.setName(name);
			user.setRole(role);
			user.setUsername(username);
			userDao.save(user);
			return new ModelAndView("redirect:/users/{id}");
		}
		return new ModelAndView("redirect:/users");
	}
	
	@GetMapping("/users/create")
	public ModelAndView createUser() {
		return new ModelAndView("createuser");
	}
	
	@PostMapping("/users/create/submit")
	public ModelAndView submitUserCreate(@RequestParam(value="name") String name,
			@RequestParam(value = "role") String role, @RequestParam(value="username") String username,
			@RequestParam(value = "password") String password) {
		User user = new User();
		user.setName(name);
		user.setRole(role);
		user.setUsername(username);
		user.setPassword(password);
		user = userDao.save(user);
		return new ModelAndView("redirect:/users/" + user.getId());
	}
	
	@PostMapping("/users/{id}/delete")
	public ModelAndView submitUserDelete(@PathVariable int id) {
		userDao.deleteById(id);
		return new ModelAndView("redirect:/users/");
	}
	
	@GetMapping("/myprofile")
	public ModelAndView myProfile() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //principal is the currently logged in Spring Security user object
		Optional<User> dbUser = userDao.findByUsername(((UserDetails)principal).getUsername()); //find user by display name (currently all I can get from spring security)
		if (dbUser.isPresent()) {        // if user exists
			
			ModelAndView mvc = new ModelAndView("myprofile");
			mvc.addObject("user", dbUser.get());
			return mvc;
		}
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/myprofile/edit")
	public ModelAndView editMyProfile() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //principal is the currently logged in Spring Security user object
		Optional<User> dbUser = userDao.findByUsername(((UserDetails)principal).getUsername()); //find user by display name (currently all I can get from spring security)
		if (dbUser.isPresent()) {        // if user exists
			ModelAndView mvc = new ModelAndView("editmyprofile");
			mvc.addObject("user", dbUser.get());
			return mvc;
		}
		return new ModelAndView("redirect:/");
	}
	
	@PostMapping("/myprofile/edit/submit")
	public ModelAndView submitEditMyProfile(@RequestParam(value="name") String name,
			 @RequestParam(value="username") String username) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //principal is the currently logged in Spring Security user object
		Optional<User> dbUser = userDao.findByUsername(((UserDetails)principal).getUsername()); //find user by display name (currently all I can get from spring security)
		if (dbUser.isPresent()) {        // if user exists
			User user = dbUser.get();
			user.setName(name);
			user.setUsername(username);
			userDao.save(user);
			return new ModelAndView("redirect:/myprofile");
		}
		return new ModelAndView("/");
	}
	
}
