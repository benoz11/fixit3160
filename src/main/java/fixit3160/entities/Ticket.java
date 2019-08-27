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
    
    @Column(name = "posterid")
    @Basic(optional = false)
    private Integer posterid;
    
    @Column(name = "caseworkerid")
    @Basic(optional = true)
    private Integer caseworkerid;

    @Column(name = "name")
    @Basic(optional = false)
    private String name;

    @Column(name = "created")
    @Basic(optional = false)
    private String created;
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

	public Integer getPosterid() {
		return posterid;
	}

	public void setPosterid(Integer posterid) {
		this.posterid = posterid;
	}

	public Integer getCaseworkerid() {
		return caseworkerid;
	}

	public void setCaseworkerid(Integer caseworkerid) {
		this.caseworkerid = caseworkerid;
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
}
