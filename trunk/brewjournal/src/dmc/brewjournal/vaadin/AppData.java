package dmc.brewjournal.vaadin;

import java.io.Serializable;
import java.util.List;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;

import dmc.brewjournal.entity.Batch;
import dmc.brewjournal.service.BrewJournalService;
import dmc.brewjournal.service.BrewJournalServiceImpl;

/**
 * The pattern for using AppData is analogous to Session data for a Vaadin app.
 * The basis for the datastructure, use of ThreadLocal, and initialization comes directly
 * from the Book of Vaadin.
 * 
 * @author David
 *
 */
public class AppData implements TransactionListener, Serializable {

	// plumbing
	private static final long serialVersionUID = -5572290752153990843L;
	private Application app = null;
	private static ThreadLocal<AppData> instance = new ThreadLocal<AppData>();
	
	// "session" data
	private BrewJournalService brewJournalService = null;
	private List<Batch> batchList = null;
//	private BatchDetailView batchDetailView = null;
//	private BatchListView batchListView = null;

	
	/**
	 * Wrap Vaadin Application, and bind this instance to the ThreadLocal "instance"
	 * @param app
	 */
	public AppData(Application app) {
		this.app = app;

		// It's usable from now on in the current request
		instance.set(this);
	}
	@Override
	public void transactionStart(Application application, Object transactionData) {
		// Set this data instance of this application
		// as the one active in the current thread.
		if (this.app == application)
			instance.set(this);
	}

	@Override
	public void transactionEnd(Application application, Object transactionData) {
		// Clear the reference to avoid potential problems
		if (this.app == application)
			instance.set(null);
	}
	
	public static BrewJournalService getBrewJournalService() {
		if (instance.get().brewJournalService == null) {
			instance.get().brewJournalService = new BrewJournalServiceImpl();
		}
		return instance.get().brewJournalService;
	}
	
	public static List<Batch> getBatchList() {
		return instance.get().batchList;
	}
	
//	public static BatchDetailView getBatchDetailView() {
//		if (instance.get().batchDetailView == null) {
//			instance.get().batchDetailView = new BatchDetailView();
//		}
//		return instance.get().batchDetailView;
//	}
//	
//	public static BatchListView getBatchListView() {
//		if (instance.get().batchListView == null) {
//			instance.get().batchListView = new BatchListView();
//		}
//		return instance.get().batchListView;
//	}
	
}
