package dmc.brewjournal.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import dmc.brewjournal.entity.Note;

public class NotesDAO extends BaseDAO {
	
	public NotesDAO() {
	}

	
	public void createUpdate(Note newInstance) {
		getLogger().fine("createUpdate: " + newInstance);
		_makePersistent(newInstance);
	}
	
	public void delete(Long noteId) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Note note = getPersistenceManager().getObjectById(Note.class, noteId);
			pm.deletePersistent(note);
		} finally {
			pm.close();
		}
	}
	
	public List<Note> findAllForBatch(Long batchId) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Query notesByBatchIdQuery = pm.newQuery(Note.class);
			notesByBatchIdQuery.setFilter("batchId==batchIdParam");
			notesByBatchIdQuery.declareParameters("String batchIdParam");

			@SuppressWarnings("unchecked")
			List<Note> result = (List<Note>) notesByBatchIdQuery
					.execute(batchId);

			return result;
		} finally {
			pm.close();
		}
	}
	
}
