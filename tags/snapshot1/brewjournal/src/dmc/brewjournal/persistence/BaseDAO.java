package dmc.brewjournal.persistence;

import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class BaseDAO {
	private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

	public BaseDAO() {
		super();
	}
	protected PersistenceManager getPersistenceManager() {
		return pmfInstance.getPersistenceManager();
	}
	protected Logger getLogger() {
		return Logger.getLogger(getClass().getName());
	}
}
