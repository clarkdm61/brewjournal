package dmc.brewjournal.vaadin;

import java.util.Collection;

import com.vaadin.data.util.BeanItemContainer;

import dmc.brewjournal.entity.Batch;

public class BatchListContainer extends BeanItemContainer<Batch> {
	
	private static final long serialVersionUID = 1612979952425087113L;

	/**
	 * Natural property order for Batch bean. Used in tables and forms.
	 */
	public static final Object[] NATURAL_COL_ORDER = new Object[] {
			"batchNumber", "batchName", "brewDate", "actualABVString" };

	/**
	 * "Human readable" captions for properties in same order as in
	 * NATURAL_COL_ORDER.
	 */
	public static final String[] COL_HEADERS_ENGLISH = new String[] {
			"Batch No.", "Name", "Date", "ABV" };


	public BatchListContainer(Class<? super Batch> type)
			throws IllegalArgumentException {
		super(type);
		// 
	}

	public BatchListContainer(Class<? super Batch> type,
			Collection<? extends Batch> collection)
			throws IllegalArgumentException {
		super(type, collection);
		// 
	}

}
