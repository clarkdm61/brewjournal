package dmc.brewjournal.persistence;

import java.util.Collection;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import dmc.brewjournal.entity.Batch;

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
	
	protected Object _findById(Class cls, Long id) {
		PersistenceManager pm = getPersistenceManager();
		Object result = null;
		try {
			result = pm.getObjectById(cls, id);
			return result;
		} finally {
			pm.close();
		}
	}
	
	protected void _makePersistent(Object record) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.makePersistent(record);
		} finally {
			pm.close();
		}
	}
	
	protected void _makePersistentAll(Collection<Object> records) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.makePersistentAll(records);
		} finally {
			pm.close();
		}
	}

}
