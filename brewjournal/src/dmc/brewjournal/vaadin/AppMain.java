package dmc.brewjournal.vaadin;

import java.io.Serializable;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class AppMain extends Application implements Serializable {

	@Override
	public void init() {
		Window mainWindow = new Window("Brew Journal");
		mainWindow.setSizeFull();

		 // Create the application data instance
        //AppData sessionData = new AppData(this);
        
        // Register it as a listener in the application context
        //getContext().addTransactionListener(sessionData);

        
		Label test = new Label("This will be ui for the Brew Journal!");
		
		mainWindow.addComponent(test);
		setMainWindow(mainWindow);
		
		//setTheme("brewjournal");   

	}

}
