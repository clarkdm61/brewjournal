package dmc.brewjournal.vaadin;

import java.io.Serializable;
import java.util.Date;

import dmc.brewjournal.entity.Batch;

public class BatchListContainerModel implements Serializable {
	public BatchListContainerModel(Batch batch) {
		super();
		this.batch = batch;
	}

	private Batch batch;

	public Integer getBatchNumber() {
		return batch.getBatchNumber();
	}

	public String getBatchName() {
		return batch.getBatchName();
	}

	public String getBrewDate() {
		return (batch.getBrewDate().getMonth() + 1) + "/" + batch.getBrewDate().getDate() + "/" + (batch.getBrewDate().getYear() + 1900);
	}

	public String getActualABVString() {
		return batch.getActualABVString();
	}

	public Integer getAgeFromBrewDate() {
		return batch.getAgeFromBrewDate();
	}
	
	public String getYeast() {
		try {
			return AppData.getBrewJournalService().getYeastForBatch(batch).getName();
		} catch (Exception e) {
			return "npe";
		}
	}

	public Batch getBatch() {
		return batch;
	}

}
