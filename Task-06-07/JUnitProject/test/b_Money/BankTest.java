package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertSame(SEK, SweBank.getCurrency());
		assertSame(SEK, Nordea.getCurrency());
		assertSame(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		String acc1 = "Bohdan";
		DanskeBank.openAccount(acc1);
		assertNotNull(DanskeBank.getBalance(acc1));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		String acc = "Ulrika";
		int amount = 100_000;
		assertEquals(Integer.valueOf(0), SweBank.getBalance(acc));
		SweBank.deposit(acc, new Money(amount, SEK));
		assertEquals(Integer.valueOf(amount), SweBank.getBalance(acc));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		String acc = "Ulrika";
		int amount = 100_000;
		assertEquals(Integer.valueOf(0), SweBank.getBalance(acc));
		SweBank.deposit(acc, new Money(amount, SEK));
		assertEquals(Integer.valueOf(amount), SweBank.getBalance(acc));
		SweBank.withdraw(acc, new Money(amount, SEK));
		assertEquals(Integer.valueOf(0), SweBank.getBalance(acc));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(0), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(0), Nordea.getBalance("Bob"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		String accFrom = "Bob";
		String accTo = "Ulrika";
		int amountBefore = 1_000_000;
		int amountAfter = 500_000;
		SweBank.deposit(accFrom, new Money(amountBefore, SEK));
		SweBank.transfer(accFrom, accTo, new Money(amountAfter, SEK));
		assertEquals(Integer.valueOf(amountAfter), SweBank.getBalance(accFrom));
		assertEquals(Integer.valueOf(amountAfter), SweBank.getBalance(accTo));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		String accFrom = "Bob";
		String accTo = "Ulrika";
		int amountDeposit = 1_000_000;
		int amount = 100_000;
		SweBank.deposit(accFrom, new Money(amountDeposit, SEK));
		SweBank.addTimedPayment(accFrom, "tp1", 1, 1, new Money(amount, SEK), SweBank, accTo);
		for (int i = 0; i < 5; i++) {
			SweBank.tick();
		}
		assertEquals(Integer.valueOf(500_000), SweBank.getBalance(accFrom));
		assertEquals(Integer.valueOf(500_000), SweBank.getBalance(accTo));
	}
}
