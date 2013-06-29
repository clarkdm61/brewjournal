package dmc.brewjournal.service;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import dmc.brewjournal.entity.Batch;
import dmc.brewjournal.entity.Note;
import dmc.brewjournal.entity.Yeast;
import dmc.brewjournal.persistence.BatchDAO;
import dmc.brewjournal.persistence.NotesDAO;
import dmc.brewjournal.persistence.YeastDAO;
import dmc.brewjournal.vaadin.AppData;

/**
 * This is a Stateful service class. So consider the consequences.
 * 
 * @author David
 *
 */
public class BrewJournalServiceImpl implements BrewJournalService, Serializable {

	/**
	 * TODO: Vaadin want's everything serializabe - does it make sense for services?
	 */
	private static final long serialVersionUID = -3680125108130758731L;
	private transient BatchDAO batchDAO = null;
	private transient NotesDAO notesDAO = null;
	private transient YeastDAO yeastDAO = null;
	//private List<Yeast> yeastListCache = null; // can't use with Vaadin since List is not serializable
	
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
	
	protected YeastDAO getYeastDAO() {
		if (yeastDAO == null) {
			yeastDAO = new YeastDAO();
		}
		return yeastDAO;
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
	public List<Batch> findAllBatches() {
		UserService userService = UserServiceFactory.getUserService();
		return getBatchDAO().findAll(userService.getCurrentUser().getUserId());
	}
	
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#delete(java.lang.Long)
	 */
	@Override
	public void deleteBatch(Long id) {
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
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#findAllYeast()
	 */
	@Override
	public List<Yeast> findAllYeast() {
		UserService userService = UserServiceFactory.getUserService();
		return getYeastDAO().findAll(userService.getCurrentUser().getUserId());
	}
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#createUpdate(dmc.brewjournal.entity.Yeast)
	 */
	@Override
	public void createUpdate(Yeast instance) {
		UserService userService = UserServiceFactory.getUserService();
		instance.setUserId(userService.getCurrentUser().getUserId());
		getYeastDAO().createUpdate(instance);
		refreshYeastListCache();
	}
	
	/* (non-Javadoc)
	 * @see dmc.brewjournal.service.BrewJournalService#calculateABV(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Double calculateABV(Double og, Double fg) {
//		Double dog = new Double(og);
//		Double dfg = new Double(fg);
		Double result = (og-fg) * .13125;
		
		return result;
	}
	
	/**
	 * See refreshYeastListCache()
	 * @return
	 */
	@Override
	public List<Yeast> getYeastListCache() {
//		if (yeastListCache == null) {
//			refreshYeastListCache();
//		}
		return findAllYeast();
	}
	
	/**
	 * Search references to find out when cache is refreshed.
	 */
	public void refreshYeastListCache() {
		//yeastListCache = findAllYeast();
	}
	
	@Override
	public Yeast getYeastForBatch(Batch batch) {
		List<Yeast> yeastList = getYeastListCache();
		if (batch.getYeastId() != 0) {
			for (Yeast yeast : yeastList) {
				if (yeast.getId().equals(batch.getYeastId())) {
					return yeast;
				}
			}
			throw new RuntimeException("Yeast not found for batch.id = " + batch.getId());
		}
		return null;
	}

}
