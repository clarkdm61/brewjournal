package dmc.brewjournal.vaadin;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 * Yeast View - Window->FormLayout->Form->BeanItem
 * Not compatible with visual editor.
 * 
 * @author David
 *
 */
public class YeastView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3535923470754297989L;
	
	private VerticalLayout mainLayout;
	private Button btnClose;

	public YeastView() {
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// user code here
		addEventListeners();
	}

	private void addEventListeners() {
		
		// Close button navigates back to Batch List View
		btnClose.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 8086037022373453153L;
			@Override
			public void buttonClick(ClickEvent event) {
				//AppMain app = (AppMain) getApplication();				
				AppData.getApplication().showListView();
			}
		});
		
	}

	private void buildMainLayout() {
		
		mainLayout = new VerticalLayout();
		btnClose = new Button("Close");
		mainLayout.addComponent(btnClose);
		
	}

}
