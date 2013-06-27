package dmc.brewjournal.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Yeast implements java.io.Serializable, Comparable<Yeast> {

	private static final long serialVersionUID = 1854447130471913003L;
	// Fields
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	@Persistent
	private String name = "";
	@Persistent
	private Integer minAttenuation = 0;
	@Persistent
	private Integer maxAttenuation = 0;
	@Persistent
	private String notes = ""; 
	@Persistent
	private String userId;


	public Yeast() {
	}
	
	/**
	 * Sort by name
	 */
	@Override
	public int compareTo(Yeast o) {
		return this.getName().compareTo(o.getName());
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getMinAttenuation() {
		return minAttenuation;
	}


	public void setMinAttenuation(Integer minAttenuation) {
		this.minAttenuation = minAttenuation;
	}


	public Integer getMaxAttenuation() {
		return maxAttenuation;
	}


	public void setMaxAttenuation(Integer maxAttenuation) {
		this.maxAttenuation = maxAttenuation;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
