package dmc.brewjournal.vaadin;

import java.util.Arrays;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import dmc.brewjournal.entity.Yeast;

public class YeastDialog extends Window {

	private static final long serialVersionUID = -1819780982364160925L;
	private YeastView yeastView = null;
	private VerticalLayout mainLayout;
	private FormLayout formLayout;
	private Form form;
	private Yeast yeast;

	public YeastDialog(YeastView yeastView) {
		this.yeastView = yeastView;
		buildMainLayout();
		setContent(mainLayout);
	}

	private void buildMainLayout() {
		// the main layout and components will be created here
		// mainLayout->form->formLayout->item
		mainLayout = new VerticalLayout();
		formLayout = new FormLayout();
		
		form = new Form(formLayout);
		
		//form.setImmediate(true); // validate as data is entered.
		
		mainLayout.addComponent(form);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		
		// create Save button and event handler
		buttonLayout.addComponent(new Button("Save", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
//				if (yeast.getId() != null) {
//					// making transient copy for JDO to work with.
//					// TODO: is there a way around this?
//					yeast = yeast.deepCopy();
//				}
				try {
					// synch UI data with model data
					form.commit();
				} catch (RuntimeException e) {
					return;
				}
				AppData.getBrewJournalService().createUpdate(yeast);
				yeastView.refreshYeastListTable();
				close();
			}
		}));
		
		// create Cancel button and event handler
		buttonLayout.addComponent(new Button("Cancel", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//yeastView.refreshYeastListTable();
				close();
			}
		}));
		
		mainLayout.addComponent(buttonLayout);

		
	}
	
public void openForEdit(Yeast yeast) {
		
		this.setModal(true);
		
		this.yeast = yeast;
		this.setCaption("Edit Yeast");
		BeanItem<Yeast> model = new BeanItem<Yeast>(yeast);
		
		form.setItemDataSource(model);
		
		form.setVisibleItemProperties(Arrays.asList(new String[]{
				"name",
				"notes",
				"minAttenuation",
				"maxAttenuation",
				}));
		
		Field f = form.getField("name");
		f.setRequired(true);
		
		f = form.getField("minAttenuation");
		f.setRequired(true);
		
		f = form.getField("maxAttenuation");
		f.setRequired(true);
		
		formLayout.setMargin(true);
		form.setSizeUndefined();
		mainLayout.setSizeUndefined();
		formLayout.setSizeUndefined();

		this.setPositionX(50);
		this.setPositionY(50);
	}

	/**
	 * Handle close window request.
	 */
	protected void close() {
		getParent().removeWindow(this);
	}
	

}
