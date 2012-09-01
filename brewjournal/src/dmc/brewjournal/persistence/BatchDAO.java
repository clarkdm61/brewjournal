package dmc.brewjournal.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Query;

import dmc.brewjournal.entity.Batch;

public class BatchDAO extends BaseDAO {
	
	private static Query BatchByUserIdQuery = null;

	public BatchDAO() {
		super();
	}

	public Query getBatchByUserIdQuery() {
		if (BatchByUserIdQuery == null) {
			BatchByUserIdQuery =  getPersistenceManager().newQuery(Batch.class);
			BatchByUserIdQuery.setFilter("userId==userIdParam");
			BatchByUserIdQuery.declareParameters("String userIdParam");
		}
		return BatchByUserIdQuery;
	}
	
	public void createUpdate(Batch newInstance) {
		getLogger().fine("createUpdate newInstance");
		getPersistenceManager().makePersistent(newInstance);
	}
	
	public void delete(Long id) {
		getLogger().fine("delete:" + id);
		Batch batch = findById(id);
		getPersistenceManager().deletePersistent(batch);
	}
	
	public Batch findById(Long id) {
		Batch batch = getPersistenceManager().getObjectById(Batch.class, id);
		return batch;
	}
	
	public List<Batch> findAll(String userId) {
		@SuppressWarnings("unchecked")
		List<Batch> extent = (List<Batch>) getBatchByUserIdQuery().execute(userId);
		
		// why? just do it.
		ArrayList<Batch> result = new ArrayList<Batch>();
		for (Batch item : extent) {
			result.add(item);
		}
		
		return result;
	}

}
