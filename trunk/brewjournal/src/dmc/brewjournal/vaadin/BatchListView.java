package dmc.brewjournal.vaadin;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class BatchListView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Table tblBatchList;
	@AutoGenerated
	private Button button_1;
	@AutoGenerated
	private Label lblMyBrewJournal;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public BatchListView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// lblMyBrewJournal
		lblMyBrewJournal = new Label();
		lblMyBrewJournal.setImmediate(false);
		lblMyBrewJournal.setWidth("-1px");
		lblMyBrewJournal.setHeight("-1px");
		lblMyBrewJournal.setValue("My Brew Journal");
		mainLayout.addComponent(lblMyBrewJournal);
		mainLayout.setComponentAlignment(lblMyBrewJournal, new Alignment(20));
		
		// button_1
		button_1 = new Button();
		button_1.setCaption("New Batch");
		button_1.setImmediate(false);
		button_1.setWidth("-1px");
		button_1.setHeight("-1px");
		mainLayout.addComponent(button_1);
		
		// tblBatchList
		tblBatchList = new Table();
		tblBatchList.setImmediate(false);
		tblBatchList.setWidth("100.0%");
		tblBatchList.setHeight("-1px");
		mainLayout.addComponent(tblBatchList);
		
		return mainLayout;
	}

}
