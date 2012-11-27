package dmc.brewjournal.vaadin;

import java.util.Collections;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import dmc.brewjournal.entity.Yeast;

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
	private Yeast selectedYeast;
	private Button btnClose;
	private Button btnAdd;
	private Button btnEdit;

	private Table tblYeastList;

	public YeastView() {
		
		buildMainLayout();
		
		setCompositionRoot(mainLayout);

		addEventListeners();
		
		refreshYeastListTable();
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
		
		tblYeastList = new Table();
		tblYeastList.setImmediate(false);
		tblYeastList.setWidth("100.0%");
		tblYeastList.setHeight("-1px");
		mainLayout.addComponent(tblYeastList);
		
		btnClose = new Button("Close");
		mainLayout.addComponent(btnClose);
		
	}
	
	/**
	 * Refresh the list of yeast
	 * @return
	 */
	public void refreshYeastListTable() {
		List<Yeast> yeastList = AppData.getBrewJournalService().findAllYeast();
		
		Collections.sort(yeastList);
		
		YeastListContainer container = new YeastListContainer(Yeast.class, yeastList);
		tblYeastList.setContainerDataSource(container);
		tblYeastList.setVisibleColumns(YeastListContainer.NATURAL_COL_ORDER);
		tblYeastList.setColumnHeaders(YeastListContainer.COL_HEADERS_ENGLISH);
		
		//tblYeastList.setColumnWidth("name", 20);// TODO: needs to be a better way to specify property ID
		
	}


	public Yeast getSelectedYeast() {
		return selectedYeast;
	}

	public void setSelectedYeast(Yeast selectedYeast) {
		this.selectedYeast = selectedYeast;
	}

}
