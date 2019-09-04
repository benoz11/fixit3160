/*
 * Class: AppConfig.java
 * Package: fixit3160.config
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 *    SENG3160 University of Newcastle 2019
 *
 *    Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 */
package fixit3160.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Compiler can't make the bean for dataSource if it doesn't exist here
 */

@EnableWebMvc
@Configuration
@ComponentScan({ "fixit3160.*" })
@Import({ SecurityConfig.class })
public class AppConfig {

	/**
	 * Defines the database information for the dataSource Bean object
	 * @return
	 */
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	    driverManagerDataSource.setUrl("jdbc:postgresql://ec2-54-243-238-226.compute-1.amazonaws.com:5432/dalob4q95vnv38");
	    driverManagerDataSource.setUsername("qbjosxuxuthptx");
	    driverManagerDataSource.setPassword("9662c9f1a2bc9fb187804b81b9f513c7fcec066d7b9a8e9f47cdb96cb69f8f13");
	    return driverManagerDataSource;
	}
	
	/**
	 * Defining prefix and suffix for all view mappings - tells it to look in this folder and for files of .jsp type
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/view/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	
}
