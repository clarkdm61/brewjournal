package dmc.brewjournal.vaadin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dmc.brewjournal.entity.Batch;
import dmc.brewjournal.entity.Note;
import dmc.brewjournal.entity.Yeast;

public class BatchDetailView extends CustomComponent {
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout hLayoutButtons;
	@AutoGenerated
	private Button btnCancel;
	@AutoGenerated
	private Button btnSave;
	@AutoGenerated
	private VerticalLayout vLayoutNotes;
	@AutoGenerated
	private Label lblNotes;
	@AutoGenerated
	private GridLayout gridAttenuation;
	@AutoGenerated
	private TextField txtFinalABV;
	@AutoGenerated
	private TextField txtTargetABV;
	@AutoGenerated
	private TextField txtActualFG;
	@AutoGenerated
	private TextField txtTheoreticalFG;
	@AutoGenerated
	private TextField txtActualOG;
	@AutoGenerated
	private TextField txtTargetOG;
	@AutoGenerated
	private ComboBox cboYeast;
	@AutoGenerated
	private TextArea txtIngredients;
	@AutoGenerated
	private TextArea txtDescription;
	@AutoGenerated
	private GridLayout gridLayoutDates;
	@AutoGenerated
	private TextField txtFermentationTimeTotal;
	@AutoGenerated
	private TextField txtFermentationTimePrimary;
	@AutoGenerated
	private TextField txtAgeFromBrewDate;
	@AutoGenerated
	private PopupDateField txtFinalDate;
	@AutoGenerated
	private PopupDateField txtRackDate;
	@AutoGenerated
	private PopupDateField txtBrewDate;
	@AutoGenerated
	private TextField txtName;
	@AutoGenerated
	private TextField txtNumber;
	@AutoGenerated
	private Label lblBatchDetail;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1514013961056449667L;
	private Batch batchModel = null;
	private TextArea txtaNote; // Text area for a new note
	private List<Note> notesModel = new ArrayList<Note>(); // the text model
	private Yeast selectedYeast = null;
	private boolean initializing = false;

	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public BatchDetailView() {
		buildMainLayout();
		
		setCompositionRoot(mainLayout);

		// user code here
		addEventListeners();
		cboYeast.setMultiSelect(false);
		cboYeast.setImmediate(true);
		cboYeast.setNewItemsAllowed(false);
		cboYeast.setScrollToSelectedItem(true);
		cboYeast.setItemCaptionPropertyId("name");

	}

	/**
	 * Bind the Batch object to BatchDetailView and map the fields.
	 * @param batch
	 */
	public void setBatchModel(Batch batch) {
		
		initializing = true;
		
		this.batchModel = batch;
		
		BeanItem<Batch> item = new BeanItem<Batch>(batch);
		txtNumber.setPropertyDataSource(item.getItemProperty("batchNumber"));
		txtName.setPropertyDataSource(item.getItemProperty("batchName"));
		txtDescription.setPropertyDataSource(item.getItemProperty("description"));
		txtBrewDate.setPropertyDataSource(item.getItemProperty("brewDate"));
		txtRackDate.setPropertyDataSource(item.getItemProperty("rackingDate"));
		txtFinalDate.setPropertyDataSource(item.getItemProperty("finalDate"));
		txtActualOG.setPropertyDataSource(item.getItemProperty("actualOG"));
		txtActualFG.setPropertyDataSource(item.getItemProperty("actualFG"));
		txtTargetOG.setPropertyDataSource(item.getItemProperty("targetOG"));
		// txtFinalABV.setPropertyDataSource(item.getItemProperty("actualABV")); // needs to be handled manually
		txtIngredients.setPropertyDataSource(item.getItemProperty("ingredients"));
		
		txtFinalABV.setReadOnly(false);
		txtFinalABV.setValue(batch.getActualABVString());
		txtFinalABV.setReadOnly(true);
		
		// YEAST		
		List<Yeast> yeastList = AppData.getBrewJournalService().getYeastListCache();
		if (batch.getYeastId() != null) {
			for (Yeast yeast : yeastList) {
				if (yeast.getId().equals(batch.getYeastId())) {
					setSelectedYeast(yeast);
				}
			}
		}
		BeanItemContainer<Yeast> yeastDataSource = new YeastListContainer(Yeast.class, yeastList);
		cboYeast.setContainerDataSource(yeastDataSource);
		cboYeast.setValue(getSelectedYeast());
		
		// NOTES
		// find notes for batch, add to UI
		List<Note> notes = null;
		if (batch.getId() != null && !batch.getId().equals(0)) {
			// DB might have a list of notes for zero
			notes = AppData.getBrewJournalService().findNotesForBatch(batch.getId());
		} else {
			notes = new ArrayList<Note>();
		}
		//batch.setNotes(notes); // FIXME: why can't we have the notes part of the batch they belong to?
		notesModel = notes;

		// add entries
		vLayoutNotes.removeAllComponents(); // clear out old data
		for (Note note : notes) {
			addNoteToUI(note);
		}
		// add empty are for new next
		txtaNote = new TextArea();
		txtaNote.setImmediate(false);
		txtaNote.setSizeFull();
		vLayoutNotes.addComponent(txtaNote);
		
		txtAgeFromBrewDate.setReadOnly(false);
		txtAgeFromBrewDate.setValue(batch.getAgeFromBrewDate());
		txtAgeFromBrewDate.setReadOnly(true);
		
		txtFermentationTimeTotal.setReadOnly(false);
		txtFermentationTimeTotal.setValue(batch.getFermentationTimeTotal());
		txtFermentationTimeTotal.setReadOnly(true);
		
		txtFermentationTimePrimary.setReadOnly(false);
		txtFermentationTimePrimary.setValue(batch.getFermentationTimePrimary());
		txtFermentationTimePrimary.setReadOnly(true);
		
		updateActualValues(); // the value should already be correct on the entity
		
		// [lbl]ageFermented (should show up under RackingDate)
		// txtRackDate(null?) - txtBrewDate
		
		
		// [lbl]ageFromFinal (shows up under ageInContainer)
		// today - txtFinalDate
		
		
		initializing = false;
	}
	
	/**
	 * 
	 * @param note
	 */
	private void addNoteToUI(Note note) {
		Label uiLabelDate = new Label(note.getDate().toString());
		Label uiLabelContent = new Label(note.getText());
		
		vLayoutNotes.addComponent(uiLabelDate);
		vLayoutNotes.addComponent(uiLabelContent);
	}

	/**
	 * Add button handlers
	 */
	protected void addEventListeners() {
		
		// Save Button
		btnSave.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 6114188010429360751L;
			@Override
			public void buttonClick(ClickEvent event) {
				Long yeastId = getSelectedYeast() == null ? 0L : getSelectedYeast().getId(); 
				batchModel.setYeastId(yeastId);
				
				// If note has a text area with text, then add to batch 
				String noteText = txtaNote.getValue().toString();
				if (noteText != null && noteText.length() != 0) {
					Note note = new Note();
					note.setBatchId(batchModel.getId());
					Date date = new Date();
					note.setDate(date);
					note.setText(noteText);
					notesModel.add(note);
				}
				
				// set date resolution to day only.
				floorDate(batchModel.getBrewDate());
				floorDate(batchModel.getFinalDate());
				floorDate(batchModel.getRackingDate());
				
				AppData.getBrewJournalService().createUpdate(batchModel);
				AppData.getBrewJournalService().createUpdateNotes(notesModel);
				 
				AppMain app = AppData.getApplication(); //(AppMain) getApplication();
				// app.getBatchListView().refreshBatchListTable(); already invoked by app.showListView();
				app.showListView();
			}
		});
		
		// Cancel Button
		btnCancel.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//AppMain app = (AppMain) getApplication();				
				AppData.getApplication().showListView();
			}
		});
		
		// Yeast selection
		cboYeast.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (!initializing) {
					Property p = event.getProperty();
					Yeast y = (Yeast)p.getValue();
					if (y != null) {
						setSelectedYeast(y); // handles updateTheoreticalValues();
					} 
				}
			}
		});
		
		// OG specification
		txtTargetOG.setImmediate(true); // so we can process events immediately
		txtTargetOG.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				updateTheoreticalValues();
			}
		});

		txtActualOG.setImmediate(true); // so we can process events immediately
		txtActualOG.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				updateActualValues();
			}
		});
		
		txtActualFG.setImmediate(true); // so we can process events immediately
		txtActualFG.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				updateActualValues();
			}
		});
		
	}
	
	/**
	 * Updates actual ABV based on actuals
	 */
	private void updateActualValues() {
		Double og = new Double( toIntegerSafe(txtActualOG.getValue()) );
		Double fg = new Double( toIntegerSafe(txtActualFG.getValue()) );
		Double abv = AppData.getBrewJournalService().calculateABV(og, fg);
		
		if (!initializing) {		
			getBatchModel().setActualABV(abv);
		}
		
		String displayValue = getBatchModel().getActualABVString();
		
		//apparent attenuation = ( ( OG in points - FG in points ) / OG in points ) x 100
		Double attenuation = null;
		try {
			attenuation = ((og - fg) / og ) * 100;
		} catch (Exception e) {
			attenuation = 0.0;
		}
		displayValue += "% (" + AppMain.DECIMAL_FORMAT.format(attenuation) + "%)";
		
		txtFinalABV.setReadOnly(false);
		txtFinalABV.setValue(displayValue);
		txtFinalABV.setReadOnly(true);
	}
	
	/**
	 * Update estimated FG and ABV
	 * Try to update whenever OG and Yeast get updated
	 */
	private void updateTheoreticalValues() {
		Double og = new Double( toIntegerSafe(txtTargetOG.getValue()) ); //  might need Double
		
		Double abv = 0d;
		//DecimalFormat df = new DecimalFormat("#.##");
		
		if (og > 0 && getSelectedYeast() != null) {
			Double min = new Double(getSelectedYeast().getMinAttenuation());
			Double max = new Double(getSelectedYeast().getMaxAttenuation());
			Double lowFG = og - og * (max/100.0d);
			Double highFG = og - og * (min/100);
			///getLogger().info("lowFG: "+ lowFG);
			//getLogger().info("highFG: "+ highFG);
			
			abv = AppData.getBrewJournalService().calculateABV(og, highFG);
			//getLogger().info("low ABV: "+ abv);
			
			String displayABV = AppMain.DECIMAL_FORMAT.format(abv);
			
			abv = AppData.getBrewJournalService().calculateABV(og, lowFG);
			//getLogger().info("high ABV: "+ abv);
			
			displayABV += " up to " + AppMain.DECIMAL_FORMAT.format(abv);
			
			txtTargetABV.setReadOnly(false);
			txtTargetABV.setValue(displayABV);
			txtTargetABV.setReadOnly(true);
			
			String displayFG = AppMain.DECIMAL_FORMAT.format(highFG) + " down to " + AppMain.DECIMAL_FORMAT.format(lowFG);
			txtTheoreticalFG.setReadOnly(false);
			txtTheoreticalFG.setValue(displayFG );
			txtTheoreticalFG.setReadOnly(true);
		}
	}
	
	/**
	 * Determine if propertyValue is set
	 * @param propertyValue
	 * @return
	 */
	private boolean hasValue(Object propertyValue) {
		return (propertyValue != null && propertyValue.toString().trim().length() > 0);
	}
	
	private Integer toIntegerSafe(Object propertyValue) {
		Integer i = new Integer(0);
		if (hasValue(propertyValue)) {
			try {
				i = new Integer(propertyValue.toString());
			} catch (Exception e) {
				getLogger().log(Level.FINE, "Invalid Integer");
			}
		}
		return i;
	}
	
	protected Logger getLogger() {
		return Logger.getLogger(getClass().getName());
	}
	
	public Batch getBatchModel() {
		return batchModel;
	}
	
	/**
	 * Zero the hours and minutes.
	 * @param aDate
	 */
	private void floorDate(java.util.Date aDate) {
		if (aDate != null) {
			aDate.setHours(0);
			aDate.setMinutes(0);
		}
	}

	public Yeast getSelectedYeast() {
		return selectedYeast;
	}

	public void setSelectedYeast(Yeast selectedYeast) {
		this.selectedYeast = selectedYeast;
		updateTheoreticalValues();
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// lblBatchDetail
		lblBatchDetail = new Label();
		lblBatchDetail.setImmediate(false);
		lblBatchDetail.setWidth("-1px");
		lblBatchDetail.setHeight("-1px");
		lblBatchDetail.setValue("Batch Details");
		lblBatchDetail.setContentMode(5);
		mainLayout.addComponent(lblBatchDetail);
		
		// txtNumber
		txtNumber = new TextField();
		txtNumber.setCaption("Batch Number");
		txtNumber.setImmediate(false);
		txtNumber.setWidth("-1px");
		txtNumber.setHeight("-1px");
		txtNumber.setSecret(false);
		mainLayout.addComponent(txtNumber);
		
		// txtName
		txtName = new TextField();
		txtName.setCaption("Name");
		txtName.setImmediate(false);
		txtName.setWidth("100.0%");
		txtName.setHeight("-1px");
		txtName.setRequired(true);
		txtName.setSecret(false);
		mainLayout.addComponent(txtName);
		
		// gridLayoutDates
		gridLayoutDates = buildGridLayoutDates();
		mainLayout.addComponent(gridLayoutDates);
		
		// txtDescription
		txtDescription = new TextArea();
		txtDescription.setCaption("Description");
		txtDescription.setImmediate(false);
		txtDescription.setWidth("100.0%");
		txtDescription.setHeight("-1px");
		mainLayout.addComponent(txtDescription);
		
		// txtIngredients
		txtIngredients = new TextArea();
		txtIngredients.setCaption("Ingredients");
		txtIngredients.setImmediate(false);
		txtIngredients.setWidth("100.0%");
		txtIngredients.setHeight("-1px");
		mainLayout.addComponent(txtIngredients);
		mainLayout.setComponentAlignment(txtIngredients, new Alignment(33));
		
		// cboYeast
		cboYeast = new ComboBox();
		cboYeast.setCaption("Yeast");
		cboYeast.setImmediate(false);
		cboYeast.setWidth("-1px");
		cboYeast.setHeight("-1px");
		mainLayout.addComponent(cboYeast);
		
		// gridAttenuation
		gridAttenuation = buildGridAttenuation();
		mainLayout.addComponent(gridAttenuation);
		
		// lblNotes
		lblNotes = new Label();
		lblNotes.setImmediate(false);
		lblNotes.setWidth("-1px");
		lblNotes.setHeight("-1px");
		lblNotes.setValue("Notes");
		mainLayout.addComponent(lblNotes);
		
		// vLayoutNotes
		vLayoutNotes = new VerticalLayout();
		vLayoutNotes.setImmediate(false);
		vLayoutNotes.setWidth("100.0%");
		vLayoutNotes.setHeight("-1px");
		vLayoutNotes.setMargin(false);
		mainLayout.addComponent(vLayoutNotes);
		
		// hLayoutButtons
		hLayoutButtons = buildHLayoutButtons();
		mainLayout.addComponent(hLayoutButtons);
		
		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildGridLayoutDates() {
		// common part: create layout
		gridLayoutDates = new GridLayout();
		gridLayoutDates.setImmediate(false);
		gridLayoutDates.setWidth("100.0%");
		gridLayoutDates.setHeight("-1px");
		gridLayoutDates.setMargin(false);
		gridLayoutDates.setSpacing(true);
		gridLayoutDates.setColumns(3);
		gridLayoutDates.setRows(2);
		
		// txtBrewDate
		txtBrewDate = new PopupDateField();
		txtBrewDate.setCaption("Brew Date");
		txtBrewDate.setImmediate(false);
		txtBrewDate.setWidth("-1px");
		txtBrewDate.setHeight("-1px");
		txtBrewDate.setInvalidAllowed(false);
		txtBrewDate.setResolution(4);
		gridLayoutDates.addComponent(txtBrewDate, 0, 0);
		
		// txtRackDate
		txtRackDate = new PopupDateField();
		txtRackDate.setCaption("Racking Date");
		txtRackDate.setImmediate(false);
		txtRackDate.setWidth("-1px");
		txtRackDate.setHeight("-1px");
		txtRackDate.setInvalidAllowed(false);
		txtRackDate.setResolution(4);
		gridLayoutDates.addComponent(txtRackDate, 1, 0);
		
		// txtFinalDate
		txtFinalDate = new PopupDateField();
		txtFinalDate.setCaption("Final Date");
		txtFinalDate.setImmediate(false);
		txtFinalDate.setWidth("-1px");
		txtFinalDate.setHeight("-1px");
		txtFinalDate.setInvalidAllowed(false);
		txtFinalDate.setResolution(4);
		gridLayoutDates.addComponent(txtFinalDate, 2, 0);
		
		// txtAgeFromBrewDate
		txtAgeFromBrewDate = new TextField();
		txtAgeFromBrewDate.setImmediate(false);
		txtAgeFromBrewDate.setWidth("-1px");
		txtAgeFromBrewDate.setHeight("-1px");
		txtAgeFromBrewDate.setCaption("Age  from inception: ");
		gridLayoutDates.addComponent(txtAgeFromBrewDate, 0, 1);
		
		// lblAgeFermented
		txtFermentationTimePrimary = new TextField();
		txtFermentationTimePrimary.setImmediate(false);
		txtFermentationTimePrimary.setWidth("-1px");
		txtFermentationTimePrimary.setHeight("-1px");
		txtFermentationTimePrimary.setCaption("Primary Fermentation Time: ");
		gridLayoutDates.addComponent(txtFermentationTimePrimary, 1, 1);
		
		// lblAgeFromFinal
		txtFermentationTimeTotal = new TextField();
		txtFermentationTimeTotal.setImmediate(false);
		txtFermentationTimeTotal.setWidth("-1px");
		txtFermentationTimeTotal.setHeight("-1px");
		txtFermentationTimeTotal.setCaption("Total Fermentation Time:");
		gridLayoutDates.addComponent(txtFermentationTimeTotal, 2, 1);
		
		return gridLayoutDates;
	}

	@AutoGenerated
	private GridLayout buildGridAttenuation() {
		// common part: create layout
		gridAttenuation = new GridLayout();
		gridAttenuation.setImmediate(false);
		gridAttenuation.setWidth("100.0%");
		gridAttenuation.setHeight("-1px");
		gridAttenuation.setMargin(false);
		gridAttenuation.setColumns(2);
		gridAttenuation.setRows(3);
		
		// txtTargetOG
		txtTargetOG = new TextField();
		txtTargetOG.setCaption("Target OG (FYI)");
		txtTargetOG.setImmediate(false);
		txtTargetOG.setWidth("-1px");
		txtTargetOG.setHeight("24px");
		txtTargetOG.setSecret(false);
		gridAttenuation.addComponent(txtTargetOG, 0, 0);
		
		// txtActualOG
		txtActualOG = new TextField();
		txtActualOG.setCaption("Actual OG");
		txtActualOG.setImmediate(false);
		txtActualOG.setWidth("-1px");
		txtActualOG.setHeight("-1px");
		txtActualOG.setSecret(false);
		gridAttenuation.addComponent(txtActualOG, 1, 0);
		
		// txtTheoreticalFG
		txtTheoreticalFG = new TextField();
		txtTheoreticalFG.setCaption("Theoretical FG");
		txtTheoreticalFG.setImmediate(false);
		txtTheoreticalFG.setReadOnly(true);
		txtTheoreticalFG.setWidth("-1px");
		txtTheoreticalFG.setHeight("-1px");
		txtTheoreticalFG.setSecret(false);
		gridAttenuation.addComponent(txtTheoreticalFG, 0, 1);
		
		// txtActualFG
		txtActualFG = new TextField();
		txtActualFG.setCaption("Actual FG");
		txtActualFG.setImmediate(false);
		txtActualFG.setWidth("-1px");
		txtActualFG.setHeight("-1px");
		txtActualFG.setSecret(false);
		gridAttenuation.addComponent(txtActualFG, 1, 1);
		
		// txtTargetABV
		txtTargetABV = new TextField();
		txtTargetABV.setCaption("Theoretical target ABVs");
		txtTargetABV.setImmediate(false);
		txtTargetABV.setReadOnly(true);
		txtTargetABV.setWidth("-1px");
		txtTargetABV.setHeight("-1px");
		txtTargetABV.setSecret(false);
		gridAttenuation.addComponent(txtTargetABV, 0, 2);
		
		// txtFinalABV
		txtFinalABV = new TextField();
		txtFinalABV.setCaption("Actual ABV (attenuation)");
		txtFinalABV.setImmediate(false);
		txtFinalABV.setReadOnly(true);
		txtFinalABV.setWidth("-1px");
		txtFinalABV.setHeight("-1px");
		txtFinalABV.setSecret(false);
		gridAttenuation.addComponent(txtFinalABV, 1, 2);
		
		return gridAttenuation;
	}

	@AutoGenerated
	private HorizontalLayout buildHLayoutButtons() {
		// common part: create layout
		hLayoutButtons = new HorizontalLayout();
		hLayoutButtons.setImmediate(false);
		hLayoutButtons.setWidth("-1px");
		hLayoutButtons.setHeight("-1px");
		hLayoutButtons.setMargin(true);
		hLayoutButtons.setSpacing(true);
		
		// btnSave
		btnSave = new Button();
		btnSave.setCaption("Save and Close");
		btnSave.setImmediate(true);
		btnSave.setWidth("-1px");
		btnSave.setHeight("-1px");
		hLayoutButtons.addComponent(btnSave);
		
		// btnCancel
		btnCancel = new Button();
		btnCancel.setCaption("Cancel");
		btnCancel.setImmediate(true);
		btnCancel.setWidth("-1px");
		btnCancel.setHeight("-1px");
		hLayoutButtons.addComponent(btnCancel);
		
		return hLayoutButtons;
	}

}
