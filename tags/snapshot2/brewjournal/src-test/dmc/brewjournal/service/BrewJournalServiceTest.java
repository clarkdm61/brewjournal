package dmc.brewjournal.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class BrewJournalServiceTest {

	@Test
	public void testCalculateABV() {
		
		BrewJournalService service = new BrewJournalServiceImpl(); 
		
		double og = 54;
		double fg = 12;
		
		double result = service.calculateABV(og, fg);
		System.out.println(result);
		assertEquals(5.51, result, .01);
	}

}
