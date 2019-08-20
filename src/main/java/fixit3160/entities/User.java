package fixit3160.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.context.annotation.ComponentScan;
/**
 * @author Benjamin McDonnell (c3166457)
 *
 */

@ComponentScan({ "fixit3160.*" })
@Entity
@Table(name = "users", schema="public")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @Column(name = "created")
    @Basic(optional = false)
    private String created;

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

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

}
