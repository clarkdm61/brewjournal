package dmc.brewjournal.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import dmc.brewjournal.entity.Batch;

public class BatchDAO extends BaseDAO {

	public BatchDAO() {
		super();
	}
	
	public void createUpdate(Batch newInstance) {
		getLogger().fine("createUpdate newInstance: " + newInstance);
		_makePersistent(newInstance);
	}
	
	public void delete(Long id) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Batch batch = getPersistenceManager().getObjectById(Batch.class, id);
			pm.deletePersistent(batch);
		} finally {
			pm.close();
		}
	}
	
	public List<Batch> findAll(String userId) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Query batchByUserIdQuery = pm.newQuery(Batch.class);
			batchByUserIdQuery.setFilter("userId==userIdParam");
			batchByUserIdQuery.declareParameters("String userIdParam");

			@SuppressWarnings("unchecked")
			List<Batch> extent = (List<Batch>) batchByUserIdQuery
					.execute(userId);
			
			ArrayList<Batch> result = new ArrayList<Batch>();
			for (Batch item : extent) {
				result.add(item);
			}

			return result;
		} finally {
			pm.close();
		}
	}

}
