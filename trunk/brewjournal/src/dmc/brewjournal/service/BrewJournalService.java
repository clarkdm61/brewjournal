package dmc.brewjournal.service;

import java.util.List;

import dmc.brewjournal.entity.Batch;

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

}