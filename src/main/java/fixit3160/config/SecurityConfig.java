package fixit3160.config;

/**
 * @author Benjamin McDonnell (c3166457)
 *
 */

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * Configuration for Spring Security
	 * TODO: Reconfigure DB/this page to use encoded passwords (hash or BCrypt)
	 */
	
  @Autowired
  DataSource dataSource; //automatically wires up to the DB information supplied in application.properties
 
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	  /*
	   * Automatically wires the datasource to access the user/pass/role from DB to use in Spring Security as logins
	   */
    auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username,CONCAT('{noop}',password),true from public.users where username=?") //{noop} tells the password encoder to use plaintext
        .authoritiesByUsernameQuery("select username,role from public.users where username=?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  /*
	   * Tells the system which pages are accessible by whom, and if they need authentication
	   * ie all can access login page, all can access dashboard IF logged in, only manager can access usermanagement, etc
	   */
	 
	  //http.authorizeRequests().anyRequest().permitAll(); //allow all requests TEST
	  
	  http.authorizeRequests()
    	.antMatchers("/login", "/logout").permitAll()				//all can access login/logout
    	.antMatchers("/", "/tickets").hasAnyAuthority("Manager","Regular","Caseworker")						//all other pages must be logged in to view
    	.antMatchers("/admin", "/users").access("hasAuthority('Manager')")	//manager only can access admin
		.and()
		.formLogin().loginPage("/login").failureUrl("/login?error")	//take us to this page for login request, and to error page for failure
		.usernameParameter("username").passwordParameter("password")//params from form
		.and()
		.logout().logoutSuccessUrl("/login?logout")					//where to go on successful logout
		.and()
		.exceptionHandling().accessDeniedPage("/403")				//where to go if user is logged in but not allowed access to this page
		.and()
		.csrf();
		
  } 
  
  /*
  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
      db.setDataSource(dataSource);
      return db;
  }
  */

  /*
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  */
}
