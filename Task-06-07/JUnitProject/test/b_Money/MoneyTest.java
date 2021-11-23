package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
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
		assertEquals(Integer.valueOf(10000), SEK100.getAmount());
		assertEquals(Integer.valueOf(1000), EUR10.getAmount());
		assertEquals(Integer.valueOf(20000), SEK200.getAmount());
		assertEquals(Integer.valueOf(2000), EUR20.getAmount());
		assertEquals(Integer.valueOf(0), SEK0.getAmount());
		assertEquals(Integer.valueOf(0), EUR0.getAmount());
		assertEquals(Integer.valueOf(-10000), SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertSame(SEK, SEK100.getCurrency());
		assertSame(EUR, EUR10.getCurrency());
		assertSame(SEK, SEK200.getCurrency());
		assertSame(EUR, EUR20.getCurrency());
		assertSame(SEK, SEK0.getCurrency());
		assertSame(EUR, EUR0.getCurrency());
		assertSame(SEK, SEKn100.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.00 SEK", SEK100.toString());
		assertEquals("10.00 EUR", EUR10.toString());
		assertEquals("200.00 SEK", SEK200.toString());
		assertEquals("20.00 EUR", EUR20.toString());
		assertEquals("0.00 SEK", SEK0.toString());
		assertEquals("0.00 EUR", EUR0.toString());
		assertEquals("-100.00 SEK", SEKn100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(1500), SEK100.universalValue());
		assertEquals(Integer.valueOf(1500), EUR10.universalValue());
		assertEquals(Integer.valueOf(3000), SEK200.universalValue());
		assertEquals(Integer.valueOf(3000), EUR20.universalValue());
		assertEquals(Integer.valueOf(0), SEK0.universalValue());
		assertEquals(Integer.valueOf(0), EUR0.universalValue());
		assertEquals(Integer.valueOf(-1500), SEKn100.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK100.equals(EUR10));
		assertTrue(SEK200.equals(EUR20));
		assertTrue(SEK0.equals(EUR0));
		assertFalse(SEK100.equals(SEKn100));
		assertFalse(EUR20.equals(EUR10));
		assertFalse(SEK100.equals(SEK200));
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
		assertFalse(SEK100.isZero());
		assertFalse(EUR10.isZero());
		assertTrue(SEK0.isZero());
		assertTrue(EUR0.isZero());
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
