package dmc.brewjournal.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(detachable="true")
public class Batch implements java.io.Serializable, Comparable<Batch> {

	// Fields
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	@Persistent
	private Integer batchNumber;
	@Persistent
	private String batchName;
	@Persistent
	private String description;
	@Persistent
	private String ingredients;
	@Persistent
	private Integer targetOG;
	@Persistent
	private Integer actualOG;
	@Persistent
	private Integer actualFG;
	// userId is never used by the client
	@Persistent
	private String userId;
	
	// todo
	// itemized collection of materials
	// yeast type
	// yield - probably used the bottle calc to determine ounces
	// collection of notes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
}
