package dmc.brewjournal.vaadin;

import java.util.Collections;
import java.util.List;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
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
	private Button btnNew;
	private Button btnEdit;

	private Table tblYeastList;

	public YeastView() {
		
		buildMainLayout();
		
		setCompositionRoot(mainLayout);

		addEventListeners();
		
		refreshYeastListTable();
	}

	private void addEventListeners() {
		
		// allow selectable rows
		tblYeastList.setSelectable(true);
		tblYeastList.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				Yeast item = (Yeast) ((Table) event.getProperty()).getValue();
				setSelectedYeast(item);
			}
		});
		
		// Edit button
		btnEdit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (selectedYeast != null) {
					YeastDialog yeastDialog = createYeastDialog();
					yeastDialog.openForEdit(selectedYeast);
					getWindow().addWindow(yeastDialog);
				}
			}
		});		
		
		// New button
		btnNew.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				YeastDialog yeastDialog = createYeastDialog();
				Yeast newYeast = new Yeast();
				yeastDialog.openForEdit(newYeast);
				getWindow().addWindow(yeastDialog);
			}
		});
				
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
		
		btnEdit = new Button("Edit");
		btnEdit.setImmediate(true);
		btnEdit.setWidth("-1px");
		btnEdit.setHeight("-1px");
		mainLayout.addComponent(btnEdit);
		
		btnNew = new Button("New");
		btnNew.setImmediate(true);
		btnNew.setWidth("-1px");
		btnNew.setHeight("-1px");
		mainLayout.addComponent(btnNew);
		
		btnClose = new Button("Close");
		btnClose.setImmediate(true);
		btnClose.setWidth("-1px");
		btnClose.setHeight("-1px");
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

	private YeastDialog createYeastDialog() {
		YeastDialog dialog = new YeastDialog(this);
		return dialog;
	}
	public Yeast getSelectedYeast() {
		return selectedYeast;
	}

	public void setSelectedYeast(Yeast selectedYeast) {
		this.selectedYeast = selectedYeast;
	}

}
