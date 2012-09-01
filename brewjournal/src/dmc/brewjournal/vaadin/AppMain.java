package dmc.brewjournal.vaadin;

import java.io.Serializable;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class AppMain extends Application implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 418883975687018098L;

	@Override
	public void init() {
		Window mainWindow = new Window("Brew Journal");
		mainWindow.setSizeFull();

		 // Create the application data instance
        //AppData sessionData = new AppData(this);
        
        // Register it as a listener in the application context
        //getContext().addTransactionListener(sessionData);

        
		Label test = new Label("This will be ui for the Brew Journal!");
		BatchListView listView = new BatchListView();
		
		mainWindow.addComponent(listView);
		setMainWindow(mainWindow);
		
		//setTheme("brewjournal");   

	}

}
