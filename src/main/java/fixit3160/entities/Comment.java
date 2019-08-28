/*
 * Class: Comment.java
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
@Table(name = "comments", schema="public")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "posterid")
    @Basic(optional = false)
    private Integer posterid;
    
    @Column(name = "ticketid")
    @Basic(optional = false)
    private Integer ticketid;

    @Column(name = "contents")
    @Basic(optional = false)
    private String contents;

    @Column(name = "created")
    @Basic(optional = false)
    private String created;
    
    @OneToOne
    @JoinColumn(name="posterid", updatable=false, insertable=false)
    private User poster;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPosterid() {
		return posterid;
	}

	public void setPosterid(Integer posterid) {
		this.posterid = posterid;
	}

	public Integer getTicketid() {
		return ticketid;
	}

	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

}
