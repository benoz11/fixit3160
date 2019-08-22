/*
 * Class: Fixit3160Application.java
 * Package: fixit3160.launch
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 * SENG3160 University of Newcastle 2019
 *
 * Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 */
package fixit3160.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fixit3160.db.CommentDao;
import fixit3160.db.TicketDao;
import fixit3160.db.UserDao;
import fixit3160.entities.Comment;
import fixit3160.entities.Ticket;
import fixit3160.entities.User;

/**
 * @author Benjamin McDonnell (c3166457)
 * The launch point for this Spring application
 * Scans for controllers, entities, and JPA repositories
 */

@SpringBootApplication
@ComponentScan(basePackages = { "fixit3160.*"} )
@EnableJpaRepositories(basePackageClasses= {UserDao.class, TicketDao.class, CommentDao.class})
@EntityScan(basePackageClasses= {User.class,Ticket.class,Comment.class})
//@PropertySource({"classpath:application.properties"}) //needed?
public class Fixit3160Application {
	public static void main(String[] args) {
		SpringApplication.run(Fixit3160Application.class, args);
	}

}
