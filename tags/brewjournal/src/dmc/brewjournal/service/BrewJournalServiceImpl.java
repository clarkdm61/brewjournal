package dmc.brewjournal.service;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import dmc.brewjournal.entity.Batch;
import dmc.brewjournal.entity.Note;
import dmc.brewjournal.persistence.BatchDAO;
import dmc.brewjournal.persistence.NotesDAO;

public class BrewJournalServiceImpl implements BrewJournalService, Serializable {

	/**
	 * TODO: Vaadin want's everything serializabe - does it make sense for services?
	 */
	private static final long serialVersionUID = -3680125108130758731L;
	private transient BatchDAO batchDAO = null;
	private transient NotesDAO notesDAO = null;
	
	public BrewJournalServiceImpl() {
		super();
	}
	protected Logger getLogger() {
		return Logger.getLogger(getClass().getName());
	}
	
	protected BatchDAO getBatchDAO() {
		if (batchDAO == null) {
			batchDAO = new BatchDAO();
		}
		return batchDAO;
	}
	
	protected NotesDAO getNotesDAO() {
		if (notesDAO == null) {
			notesDAO = new NotesDAO();
		}
		return notesDAO;
	}
	
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#createUpdate(dmc.brewjournal.entity.Batch)
	 */
	@Override
	public void createUpdate(Batch instance) {
		UserService userService = UserServiceFactory.getUserService();
		instance.setUserId(userService.getCurrentUser().getUserId());
		getBatchDAO().createUpdate(instance);
	}
	
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#findAll()
	 */
	@Override
	public List<Batch> findAll() {
		UserService userService = UserServiceFactory.getUserService();
		return getBatchDAO().findAll(userService.getCurrentUser().getUserId());
	}
	
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		getBatchDAO().delete(id);
	}
	
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#findNotesForBatch(java.lang.Long)
	 */
	@Override
	public List<Note> findNotesForBatch(Long batchId) {
		return getNotesDAO().findAllForBatch(batchId);
	}
	
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#createUpdateNotes(java.util.List)
	 */
	@Override
	public void createUpdateNotes(List<Note> notes) {
		
		for (Note newInstance : notes) {
			getNotesDAO().createUpdate(newInstance);
		}
		
	}

}
