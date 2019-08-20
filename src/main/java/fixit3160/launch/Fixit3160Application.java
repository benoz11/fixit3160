package fixit3160.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fixit3160.db.CommentDao;
import fixit3160.db.TicketDao;
import fixit3160.db.UserDao;
import fixit3160.entities.Comment;
import fixit3160.entities.Ticket;
import fixit3160.entities.User;

@SpringBootApplication
@ComponentScan(basePackages = { "fixit3160.*"} )
@EnableJpaRepositories(basePackageClasses= {UserDao.class, TicketDao.class, CommentDao.class})
@EntityScan(basePackageClasses= {User.class,Ticket.class,Comment.class})
//@PropertySource({"classpath:application.properties"})
public class Fixit3160Application {

	public static void main(String[] args) {
		SpringApplication.run(Fixit3160Application.class, args);
	}

}
