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
@Table(name = "tickets", schema="public")
public class Ticket implements Serializable{

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
    private String posterid;
    
    @Column(name = "caseworkerid")
    @Basic(optional = true)
    private String caseworkerid;

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

	public String getPosterid() {
		return posterid;
	}

	public void setPosterid(String posterid) {
		this.posterid = posterid;
	}

	public String getCaseworkerid() {
		return caseworkerid;
	}

	public void setCaseworkerid(String caseworkerid) {
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
}
