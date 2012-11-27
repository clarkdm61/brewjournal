package dmc.brewjournal.vaadin;

import java.util.Collection;

import com.vaadin.data.util.BeanItemContainer;

import dmc.brewjournal.entity.Yeast;

public class YeastListContainer extends BeanItemContainer<Yeast> {

	
	private static final long serialVersionUID = 6291292344386885538L;

	/**
	 * Natural property order for Batch bean. Used in tables and forms.
	 */
	public static final Object[] NATURAL_COL_ORDER = new Object[] {
			"name" };

	/**
	 * "Human readable" captions for properties in same order as in
	 * NATURAL_COL_ORDER.
	 */
	public static final String[] COL_HEADERS_ENGLISH = new String[] {
			"Name" };
	
	public YeastListContainer(Class<? super Yeast> type,
			Collection<? extends Yeast> collection)
			throws IllegalArgumentException {
		super(type, collection);
		// 
	}


}
