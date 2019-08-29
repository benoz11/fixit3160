/*
 * Class: SecurityConfig.java
 * Package: fixit3160.config
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 * SENG3160 University of Newcastle 2019
 *
 * Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 */
package fixit3160.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

/**
 * @author Benjamin McDonnell (c3166457)
 *
 * Configuration for Spring Security
 * TODO: Reconfigure DB/this page to use encoded passwords (hash or BCrypt)
 */

@Configuration
@EnableJpaRepositories
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
  @Autowired
  DataSource dataSource; //automatically wires up to the DB information supplied in application.properties
 
  /**
   * Automatically wires the datasource to access the user/pass/role from DB to use in Spring Security as logins
   * {noop} tells the password encoder to use plaintext
   * @param auth
   * @throws Exception
   */
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	  auth.jdbcAuthentication().dataSource(dataSource)
        	.usersByUsernameQuery("select username,CONCAT('{noop}',password),true from public.users where username=?")
        	.authoritiesByUsernameQuery("select username,role from public.users where username=?");
  }

  /**
   * Tells the system which pages are accessible by whom, and if they need authentication
   * ie all can access login page, all can access dashboard IF logged in, only manager can access usermanagement, etc
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  //http.authorizeRequests().anyRequest().permitAll(); //allow all requests TEST
	  
	  http.authorizeRequests()
    	.antMatchers("/login","/css/**","/img/**").permitAll()				//all can access login/logout
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
  
  @Bean
  public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
      return new SecurityEvaluationContextExtension();
  }
  
  /**
   * Spring Security automatically adds ROLE_ to the start of all User roles
   * This redefines it to NOT do that - Just use the role name as it appears in DB in the jsp
   * eg: <sec:authorize access="hasRole('Caseworker')">This will only be visible to Caseworkers</sec:authorize>
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
      web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
          @Override
          protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
              WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, fi);
              root.setDefaultRolePrefix(""); //remove the prefix ROLE_
              return root;
          }
      });
  }
}
