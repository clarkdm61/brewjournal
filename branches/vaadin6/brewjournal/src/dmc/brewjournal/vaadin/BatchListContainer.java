package dmc.brewjournal.vaadin;

import java.util.Collection;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class BatchListContainer extends BeanItemContainer<BatchListContainerModel> {
	
	private static final long serialVersionUID = 1612979952425087113L;

	/**
	 * Natural property order for Batch bean. Used in tables and forms.
	 */
	public static final Object[] NATURAL_COL_ORDER = new Object[] {
			"batchNumber", "batchName", "brewDate", "ageFromBrewDate", "actualABVString", "yeast" };

	/**
	 * "Human readable" captions for properties in same order as in
	 * NATURAL_COL_ORDER.
	 */
	public static final String[] COL_HEADERS_ENGLISH = new String[] {
			"Batch No.", "Name", "Date", "age", "ABV", "Yeast" };


	public BatchListContainer(Class<? super BatchListContainerModel> type)
			throws IllegalArgumentException {
		super(type);
		// 
	}

	public BatchListContainer(Class<? super BatchListContainerModel> type,
			Collection<? extends BatchListContainerModel> collection)
			throws IllegalArgumentException {
		super(type, collection);
		// 
	}
	
//	public BeanItem<Batch> getItem(Object itemId) {
//		if (itemId.toString().equals("foo")) {
//			return "hello";
//		} else {
//			return super.getItem(itemId);
//		}
//	}

}
