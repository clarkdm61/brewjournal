package dmc.brewjournal.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import dmc.brewjournal.entity.Yeast;

public class YeastDAO extends BaseDAO {
	public void createUpdate(Yeast newInstance) {
		getLogger().fine("createUpdate newInstance: " + newInstance);
		_makePersistent(newInstance);
	}
	
	public void delete(Long id) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Yeast o = getPersistenceManager().getObjectById(Yeast.class, id);
			pm.deletePersistent(o);
		} finally {
			pm.close();
		}
	}
	
	public List<Yeast> findAll(String userId) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Query byUserIdQuery = pm.newQuery(Yeast.class);
			byUserIdQuery.setFilter("userId==userIdParam");
			byUserIdQuery.declareParameters("String userIdParam");

			@SuppressWarnings("unchecked")
			List<Yeast> result = (List<Yeast>) byUserIdQuery
					.execute(userId);
			return result;
		} finally {
			pm.close();
		}
	}
}
