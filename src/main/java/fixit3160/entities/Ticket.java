/*
 * Class: Ticket.java
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.context.annotation.ComponentScan;
/**
 * @author Benjamin McDonnell (c3166457)
 * Defines a JPA entity object that represents a table in the database
 */

@ComponentScan({ "fixit3160.*" })
@Entity
@Table(name = "tickets", schema = "public")
@SecondaryTable(name = "users", schema = "public")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
    @Column(name = "description")
    @Basic(optional = false)
    private String description;
    
    @Column(name = "state")
    @Basic(optional = false)
    private String state;
    
    @JoinColumn(name = "posterid")
    @ManyToOne
    @Basic(optional = false)
    private User poster;
    
    @Column(name="posterid", updatable=false, insertable=false)
    private Integer posterid;
    
    @JoinColumn(name = "caseworkerid")
    @ManyToOne
    @Basic(optional = true)
    private User caseworker;
    
    @Column(name="caseworkerid", updatable=false, insertable=false)
    private Integer caseworkerid;

    @Column(name = "name")
    @Basic(optional = false)
    private String name;

    @Column(name = "created")
    @Basic(optional = false)
    private String created;
    
    /*
     * Defines a OneToMany relationship with the comments table
     * One ticket can have many comments associated with it
     */
    @OneToMany(mappedBy="ticketid")
    private List<Comment> comments = new ArrayList<Comment>();
/*
	@Column(name = "priorityLevel")
	@Basic
	private String prioritylevel;
*/
	/*
     * From other tables
     */


	/*
     * Methods
     */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	public User getCaseworker() {
		return caseworker;
	}

	public void setCaseworker(User caseworker) {
		this.caseworker = caseworker;
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
/*
	public String getPrioritylevel() {
		return prioritylevel;
	}

	public void setPrioritylevel(String prioritylevel) {
		this.prioritylevel = prioritylevel;
	}*/

	public Integer getCaseworkerid() {
		return caseworkerid;
	}

	public void setCaseworkerid(Integer caseworkerid) {
		this.caseworkerid = caseworkerid;
	}

	public Integer getPosterid() {
		return posterid;
	}
	
	public void setPosterid(Integer posterid) {
		this.posterid = posterid;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
