/*
 * Class: User.java
 * Package: fixit3160.entities
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 * SENG3160 University of Newcastle 2019
 *
 * Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 */
package fixit3160.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import javax.persistence.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
/**
 * Defines a JPA entity object that represents a table in the database
 */

@ComponentScan({ "fixit3160.*" })
@Entity
@Table(name = "users", schema="public")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
	
    @Column(name = "username")
    @Basic(optional = false)
    private String username;
    
    @Column(name = "role")
    @Basic(optional = false)
    private String role;
    
    @Column(name = "password")
    @Basic(optional = false)
    private String password;

    @Column(name = "name")
    @Basic(optional = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", insertable=false)
    private Date created;

    // getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
