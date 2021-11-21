package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate());
		assertEquals(Double.valueOf(0.20), DKK.getRate());
		assertEquals(Double.valueOf(1.5), EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		EUR.setRate(1.3);
		assertEquals(Double.valueOf(1.3), EUR.getRate());
		DKK.setRate(0.25);
		assertEquals(Double.valueOf(0.25), DKK.getRate());
		SEK.setRate(0.17);
		assertEquals(Double.valueOf(0.17), SEK.getRate());
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(225), SEK.universalValue(1500));
		assertEquals(Integer.valueOf(400), DKK.universalValue(2000));
		assertEquals(Integer.valueOf(1500), EUR.universalValue(1000));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(Integer.valueOf(15000), SEK.valueInThisCurrency(1500, EUR));
		assertEquals(Integer.valueOf(525), DKK.valueInThisCurrency(700, SEK));
		assertEquals(Integer.valueOf(200), EUR.valueInThisCurrency(1500, DKK));
	}

}
