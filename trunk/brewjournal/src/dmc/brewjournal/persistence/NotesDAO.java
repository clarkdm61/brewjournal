package dmc.brewjournal.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Query;

import dmc.brewjournal.entity.Note;

public class NotesDAO extends BaseDAO {

	private static Query NotesByBatchIdQuery = null;
	
	public NotesDAO() {
	}

	public Query getNotesByBatchIdQuery() {
		if (NotesByBatchIdQuery == null) {
			NotesByBatchIdQuery =  getPersistenceManager().newQuery(Note.class);
			NotesByBatchIdQuery.setFilter("batchId==batchIdParam");
			NotesByBatchIdQuery.declareParameters("String batchIdParam");
		}
		return NotesByBatchIdQuery;
	}
	
	public void createUpdate(Note newInstance) {
		getLogger().fine("createUpdate newInstance");
		getPersistenceManager().makePersistent(newInstance);
	}
	
	public void delete(Long noteId) {
		getLogger().fine("delete:" + noteId);
		Note note = findById(noteId);
		getPersistenceManager().deletePersistent(note);
	}
	
	public Note findById(Long id) {
		Note note = getPersistenceManager().getObjectById(Note.class, id);
		return note;
	}
	
	public List<Note> findAllForBatch(Long batchId) {
		@SuppressWarnings("unchecked")
		List<Note> extent = (List<Note>) getNotesByBatchIdQuery().execute(batchId);
		
		// why? just do it.
		// TODO: really figure out why - probably a proxy class thing
		ArrayList<Note> result = new ArrayList<Note>();
		for (Note item : extent) {
			result.add(item);
		}
		
		return result;
	}
	
}
