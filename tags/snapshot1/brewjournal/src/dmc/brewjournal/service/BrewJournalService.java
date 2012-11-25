package dmc.brewjournal.service;

import java.util.List;

import dmc.brewjournal.entity.Batch;
import dmc.brewjournal.entity.Note;

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
	public abstract List<Batch> findAll();

	/**
	 * Deletes the specified batch by ID
	 * @param id
	 */
	public abstract void delete(Long id);
	
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

}