package b_Money;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {

	// removed unnecessary currency
	Currency SEK, DKK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals("Amount should be 10,000", Integer.valueOf(10000), SEK100.getAmount());
		assertEquals("Amount should be 1,000", Integer.valueOf(1000), EUR10.getAmount());
		assertEquals("Amount should be 20,000", Integer.valueOf(20000), SEK200.getAmount());
		assertEquals("Amount should be 2,000", Integer.valueOf(2000), EUR20.getAmount());
		assertEquals("Amount should be 0", Integer.valueOf(0), SEK0.getAmount());
		assertEquals("Amount should be 0", Integer.valueOf(0), EUR0.getAmount());
		assertEquals("Amount should be -10,000", Integer.valueOf(-10000), SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertSame("Currency should be SEK", SEK, SEK100.getCurrency());
		assertSame("Currency should be EUR", EUR, EUR10.getCurrency());
		assertSame("Currency should be SEK", SEK, SEK200.getCurrency());
		assertSame("Currency should be EUR", EUR, EUR20.getCurrency());
		assertSame("Currency should be SEK", SEK, SEK0.getCurrency());
		assertSame("Currency should be EUR", EUR, EUR0.getCurrency());
		assertSame("Currency should be SEK", SEK, SEKn100.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("Should be 100.00 SEK", "100.00 SEK", SEK100.toString());
		assertEquals("Should be 10.00 EUR", "10.00 EUR", EUR10.toString());
		assertEquals("Should be 200.00 SEK", "200.00 SEK", SEK200.toString());
		assertEquals("Should be 20.00 EUR", "20.00 EUR", EUR20.toString());
		assertEquals("Should be 0.00 SEK", "0.00 SEK", SEK0.toString());
		assertEquals("Should be 0.00 EUR", "0.00 EUR", EUR0.toString());
		assertEquals("Should be -100.00 SEK", "-100.00 SEK", SEKn100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals("Value should be 1,500", Integer.valueOf(1500), SEK100.universalValue());
		assertEquals("Value should be 1,500", Integer.valueOf(1500), EUR10.universalValue());
		assertEquals("Value should be 3,000", Integer.valueOf(3000), SEK200.universalValue());
		assertEquals("Value should be 3,000", Integer.valueOf(3000), EUR20.universalValue());
		assertEquals("Value should be 0", Integer.valueOf(0), SEK0.universalValue());
		assertEquals("Value should be 0", Integer.valueOf(0), EUR0.universalValue());
		assertEquals("Value should be -1,500", Integer.valueOf(-1500), SEKn100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue("SEK100 should be equal to EUR10", SEK100.equals(EUR10));
		assertTrue("SEK200 should be equal to EUR20", SEK200.equals(EUR20));
		assertTrue("SEK0 should be equal to EUR0", SEK0.equals(EUR0));
		assertFalse("SEK100 should not be equal to SEKn100", SEK100.equals(SEKn100));
		assertFalse("EUR20 should not be equal to EUR10", EUR20.equals(EUR10));
		assertFalse("SEK100 should not be equal to SEK200", SEK100.equals(SEK200));
	}

	@Test
	public void testAdd() {
		assertEquals(-1, new Money(3000, SEK).compareTo(SEK100.add(EUR10)));
		assertEquals(-1, new Money(6000, SEK).compareTo(SEK200.add(EUR20)));
		assertEquals(0, new Money(0, SEK).compareTo(SEK0.add(EUR0)));
		assertEquals(-1, new Money(3000, SEK).compareTo(SEK100.add(EUR20)));
		assertEquals(-1, new Money(6000, SEK).compareTo(SEK200.add(EUR10)));
		assertEquals(1, new Money(0, SEK).compareTo(SEKn100.add(EUR0)));
	}

	@Test
	public void testSub() {
		assertEquals(1, new Money(-1500, SEK).compareTo(SEK100.sub(EUR20)));
		assertEquals(-1, new Money(1500, SEK).compareTo(SEK200.sub(EUR10)));
		assertEquals(0, new Money(0, SEK).compareTo(SEK0.sub(EUR0)));
		assertEquals(1, new Money(3000, SEK).compareTo(SEK100.sub(EUR20)));
		assertEquals(-1, new Money(6000, SEK).compareTo(SEK200.sub(EUR10)));
		assertEquals(1, new Money(15000, SEK).compareTo(SEKn100.sub(EUR0)));
	}

	@Test
	public void testIsZero() {
		assertFalse("SEK100 should not be 0", SEK100.isZero());
		assertFalse("EUR10 should not be 0", EUR10.isZero());
		assertTrue("SEK0 should be 0", SEK0.isZero());
		assertTrue("EUR0 should be 0", EUR0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(0, new Money(-10000, SEK).compareTo(SEK100.negate()));
		assertEquals(0, new Money(-20000, SEK).compareTo(SEK200.negate()));
		assertEquals(0, new Money(0, SEK).compareTo(SEK0.negate()));
		assertEquals(-1, new Money(-1500, EUR).compareTo(EUR10.negate()));
		assertEquals(-1, new Money(-3000, EUR).compareTo(EUR20.negate()));
		assertEquals(1, new Money(50000, SEK).compareTo(SEKn100.negate()));
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, new Money(10000, SEK).compareTo(SEK100));
		assertEquals(0, new Money(20000, SEK).compareTo(SEK200));
		assertEquals(0, new Money(0, SEK).compareTo(SEK0));
		assertEquals(1, new Money(3500, EUR).compareTo(EUR10));
		assertEquals(1, new Money(7000, EUR).compareTo(EUR20));
		assertEquals(1, new Money(-4000, SEK).compareTo(SEKn100));
	}

}
