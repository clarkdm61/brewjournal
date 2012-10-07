package dmc.brewjournal.service;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import dmc.brewjournal.entity.Batch;
import dmc.brewjournal.persistence.BatchDAO;

public class BrewJournalServiceImpl implements BrewJournalService, Serializable {

	/**
	 * TODO: Vaadin want's everything serializabe - does it make sense for services?
	 */
	private static final long serialVersionUID = -3680125108130758731L;
	private transient BatchDAO batchDAO = null;
	
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

}
