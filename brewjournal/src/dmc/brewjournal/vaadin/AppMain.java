package dmc.brewjournal.vaadin;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Iterator;

import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

public class AppMain extends Application implements Serializable {

	private static final long serialVersionUID = 418883975687018098L;
	
	// all of the views for this application:
	private BatchDetailView batchDetailView = null;
	private BatchListView batchListView = null;
	private YeastView yeastView = null;
	
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

	@Override
	public void init() {
		Window mainWindow = new Window("Brew Journal");
		mainWindow.setSizeFull();

		 // Create the application data instance
        AppData sessionData = new AppData(this);
        
        // Register it as a listener in the application context
        getContext().addTransactionListener(sessionData);

        
		//Label test = new Label("This will be ui for the Brew Journal!");
				
		batchDetailView = new BatchDetailView();
		batchListView = new BatchListView();
		yeastView = new YeastView();

		mainWindow.addComponent(batchListView);
		setMainWindow(mainWindow);

		//setTheme("brewjournal");   

	}
	
	/**
	 * Replace MainWindow's component with the batch detail view
	 */
	public void showDetailView() {
		//getMainWindow().removeComponent(batchListView);
		removeCurrentComponent();
		getMainWindow().addComponent(batchDetailView);
	}
	
	/**
	 * Replace MainWindow's component with the batch list view
	 */
	public void showListView() {
		removeCurrentComponent();
		getBatchListView().refreshBatchListTable();
		getMainWindow().addComponent(batchListView);
	}
	
	/**
	 * Replace MainWindow's component with the yeast view
	 */
	public void showYeastView() {
		removeCurrentComponent();
		yeastView.refreshYeastListTable();
		getMainWindow().addComponent(yeastView);
	}
	
	private void removeCurrentComponent() {
		Iterator<Component> itr = getMainWindow().getComponentIterator();
		while (itr.hasNext()) {
			Component c = itr.next();
			//System.out.println("Removing component: " + c);
			getMainWindow().removeComponent(c);
		}
	}

	public BatchDetailView getBatchDetailView() {
		return batchDetailView;
	}

	public BatchListView getBatchListView() {
		return batchListView;
	}
}
