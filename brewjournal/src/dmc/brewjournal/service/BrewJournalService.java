package dmc.brewjournal.service;

import java.util.List;

import dmc.brewjournal.entity.Batch;
import dmc.brewjournal.entity.Note;
import dmc.brewjournal.entity.Yeast;

public interface BrewJournalService {

	/**
	 * Create a new instance if ID is null, otherwise update.
	 * @param instance
	 */
	public abstract void createUpdate(Batch instance);

	/**
	 * Returns all Batches for the current user
	 * @return
	 */
	public abstract List<Batch> findAllBatches();

	/**
	 * Deletes the specified batch by ID
	 * @param id
	 */
	public abstract void deleteBatch(Long id);
	
	/**
	 * Returns all notes for specified batch
	 * @param id
	 */
	public abstract List<Note> findNotesForBatch(Long batchId);
	
	/**
	 * Create/update the list of notes
	 * @param notes
	 */
	public abstract void createUpdateNotes(List<Note> notes);
	
	/**
	 * Returns all Yeast in system
	 * @return
	 */
	public abstract List<Yeast> findAllYeast();
	
	/**
	 * Create/update yeast data
	 * @param instance
	 */
	public abstract void createUpdate(Yeast instance);
	
	/**
	 * Calculate / estimate ABV
	 * @param og
	 * @param fg
	 * @return
	 */
	public abstract Double calculateABV(Double og, Double fg);

}