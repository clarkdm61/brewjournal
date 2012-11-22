package dmc.brewjournal.vaadin;

import java.util.Date;
import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
	private Label lblFinalABV;
	@AutoGenerated
	private Label lblTargetABV;
	@AutoGenerated
	private TextField txtActualFG;
	@AutoGenerated
	private Label lblTheoreticalFG;
	@AutoGenerated
	private TextField txtActualOG;
	@AutoGenerated
	private TextField txtTargetOG;
	@AutoGenerated
	private TextArea txtIngredients;
	@AutoGenerated
	private TextArea txtDescription;
	@AutoGenerated
	private GridLayout gridLayoutDates;
	@AutoGenerated
	private Label lblAgeFromFinal;
	@AutoGenerated
	private Label lblAgeFermented;
	@AutoGenerated
	private Label lblAgeFromBrewDate;
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

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1514013961056449667L;
	private Batch batchModel = null;
	private TextArea txtaNote;

	
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
	}

	/**
	 * Bind the Batch object to BatchDetailView and map the fields.
	 * @param batch
	 */
	public void setBatchModel(Batch batch) {
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
		txtIngredients.setPropertyDataSource(item.getItemProperty("ingredients"));
		
		// TODO: verify add notes + new note entry
		// find notes for batch, add to UI
		List<Note> notes = AppData.getBrewJournalService().findNotesForBatch(batch.getId()); 
		batch.setNotes(notes);

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
			
			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println(getBatchModel()); // TODO remove
				// TODO: If note has a text area with text, then add to batch 
				
				String noteText = txtaNote.getValue().toString();
				if (noteText != null && noteText.length() != 0) {
					Note note = new Note();
					note.setBatchId(batchModel.getId());
					Date date = new Date();
					note.setDate(date);
					note.setText(noteText);
				}
				
				AppData.getBrewJournalService().createUpdate(batchModel);
				AppData.getBrewJournalService().createUpdateNotes(batchModel.getNotes());
				 
				AppMain app = AppData.getApplication(); //(AppMain) getApplication();
				app.getBatchListView().refreshBatchListTable();
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
		
		
	}
	
	public Batch getBatchModel() {
		return batchModel;
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
		
		// lblAgeFromBrewDate
		lblAgeFromBrewDate = new Label();
		lblAgeFromBrewDate.setImmediate(false);
		lblAgeFromBrewDate.setWidth("-1px");
		lblAgeFromBrewDate.setHeight("-1px");
		lblAgeFromBrewDate.setValue("Age  from inception: ");
		gridLayoutDates.addComponent(lblAgeFromBrewDate, 0, 1);
		
		// lblAgeFermented
		lblAgeFermented = new Label();
		lblAgeFermented.setImmediate(false);
		lblAgeFermented.setWidth("-1px");
		lblAgeFermented.setHeight("-1px");
		lblAgeFermented.setValue("Fermentation Time: ");
		gridLayoutDates.addComponent(lblAgeFermented, 1, 1);
		
		// lblAgeFromFinal
		lblAgeFromFinal = new Label();
		lblAgeFromFinal.setImmediate(false);
		lblAgeFromFinal.setWidth("-1px");
		lblAgeFromFinal.setHeight("-1px");
		lblAgeFromFinal.setValue("Age in containter:");
		gridLayoutDates.addComponent(lblAgeFromFinal, 2, 1);
		
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
		txtTargetOG.setCaption("Target OG");
		txtTargetOG.setImmediate(false);
		txtTargetOG.setWidth("-1px");
		txtTargetOG.setHeight("-1px");
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
		
		// lblTheoreticalFG
		lblTheoreticalFG = new Label();
		lblTheoreticalFG.setImmediate(false);
		lblTheoreticalFG.setWidth("-1px");
		lblTheoreticalFG.setHeight("-1px");
		lblTheoreticalFG.setValue("Theoretical FG:");
		gridAttenuation.addComponent(lblTheoreticalFG, 0, 1);
		
		// txtActualFG
		txtActualFG = new TextField();
		txtActualFG.setCaption("Actual FG");
		txtActualFG.setImmediate(false);
		txtActualFG.setWidth("-1px");
		txtActualFG.setHeight("-1px");
		txtActualFG.setSecret(false);
		gridAttenuation.addComponent(txtActualFG, 1, 1);
		
		// lblTargetABV
		lblTargetABV = new Label();
		lblTargetABV.setImmediate(false);
		lblTargetABV.setWidth("-1px");
		lblTargetABV.setHeight("-1px");
		lblTargetABV.setValue("Target ABV (theoretical):");
		gridAttenuation.addComponent(lblTargetABV, 0, 2);
		
		// lblFinalABV
		lblFinalABV = new Label();
		lblFinalABV.setImmediate(false);
		lblFinalABV.setWidth("-1px");
		lblFinalABV.setHeight("-1px");
		lblFinalABV.setValue("Final ABV (actual):");
		gridAttenuation.addComponent(lblFinalABV, 1, 2);
		
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
