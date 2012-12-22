package dmc.brewjournal.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;


@PersistenceCapable(detachable="true")
public class Batch implements java.io.Serializable, Comparable<Batch> {

	private static final long serialVersionUID = -3802556750841815571L;
	// Fields
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	@Persistent
	private Integer batchNumber = 0;
	@Persistent
	private String batchName = "";
	
	@Persistent
	private Date brewDate;
	@Persistent
	private Date rackingDate;
	@Persistent
	private Date finalDate;
	
	@Persistent
	private Text description = new Text("");
	@Persistent
	private String ingredients = "";
	@Persistent
	private Integer targetOG = 0;
	@Persistent
	private Integer actualOG = 0;
	@Persistent
	private Integer actualFG = 0;
	// userId is never used by the client
	@Persistent
	private String userId;
	@Persistent
	private Long yeastId = 0L;
	
	// todo
	// itemized collection of materials
	// yeast type
	// yield - probably used the bottle calc to determine ounces
	// collection of notes
//	@Transient try TODO: @NotPersistent
//	private List<Note> notes = new ArrayList<Note>();

	public Batch() {
	}

	@Override
	public int compareTo(Batch arg0) {
		return batchNumber.compareTo(batchNumber);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(Integer batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getDescription() {
		return description.getValue();
	}

	public void setDescription(String description) {
		this.description = new Text(description);
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Integer getTargetOG() {
		return targetOG;
	}

	public void setTargetOG(Integer targetOG) {
		this.targetOG = targetOG;
	}

	public Integer getActualOG() {
		return actualOG;
	}

	public void setActualOG(Integer actualOG) {
		this.actualOG = actualOG;
	}

	public Integer getActualFG() {
		return actualFG;
	}

	public void setActualFG(Integer actualFG) {
		this.actualFG = actualFG;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Date getBrewDate() {
		return brewDate;
	}

	public void setBrewDate(Date brewDate) {
		this.brewDate = brewDate;
	}

	public Date getRackingDate() {
		return rackingDate;
	}

	public void setRackingDate(Date rackingDate) {
		this.rackingDate = rackingDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date bottleDate) {
		this.finalDate = bottleDate;
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", batchNumber=" + batchNumber
				+ ", batchName=" + batchName + ", brewDate=" + brewDate
				+ ", rackingDate=" + rackingDate + ", finalDate=" + finalDate
				+ ", description=" + description + ", ingredients="
				+ ingredients + ", targetOG=" + targetOG + ", actualOG="
				+ actualOG + ", actualFG=" + actualFG + ", userId=" + userId
				+ "]";
	}

	public Long getYeastId() {
		return yeastId;
	}

	public void setYeastId(Long yeastId) {
		this.yeastId = yeastId;
	}

//	public List<Note> getNotes() {
//		return notes;
//	}
//
//	public void setNotes(List<Note> notes) {
//		this.notes = notes;
//	}
	
}
