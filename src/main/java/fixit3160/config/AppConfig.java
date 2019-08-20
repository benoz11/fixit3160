package fixit3160.config;



/**
 * @author Benjamin McDonnell (c3166457)
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "fixit3160.*" })
@Import({ SecurityConfig.class })
public class AppConfig {
	/*
	 * 	compiler can't make the bean for dataSource if it doesn't exist here
	 */

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		/*
		 * DB info for the datasource
		 */
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	    driverManagerDataSource.setUrl("jdbc:postgresql://ec2-54-243-238-226.compute-1.amazonaws.com:5432/dalob4q95vnv38");
	    driverManagerDataSource.setUsername("qbjosxuxuthptx");
	    driverManagerDataSource.setPassword("9662c9f1a2bc9fb187804b81b9f513c7fcec066d7b9a8e9f47cdb96cb69f8f13");
	    return driverManagerDataSource;
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		/*
		 * Defining prefix and suffix for all view mappings - tells it to look in this folder and for files of .jsp type
		 */
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/view/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	
}
