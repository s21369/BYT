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
		assertEquals("Name should be SweBank", "SweBank", SweBank.getName());
		assertEquals("Name should be Nordea", "Nordea", Nordea.getName());
		assertEquals("Name should be DanskeBank", "DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertSame("Currency should be SEK", SEK, SweBank.getCurrency());
		assertSame("Currency should be SEK", SEK, Nordea.getCurrency());
		assertSame("Currency should be DKK", DKK, DanskeBank.getCurrency());
	}

	// changed from get to put
	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		String acc1 = "Bohdan";
		// open new account
		DanskeBank.openAccount(acc1);
		assertNotNull("Account should exist", DanskeBank.getBalance(acc1));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		String acc = "Ulrika";
		int amount = 100_000;
		assertEquals("Balance should be 0", Integer.valueOf(0), SweBank.getBalance(acc));
		SweBank.deposit(acc, new Money(amount, SEK));
		assertEquals(String.format("Balance should be %d", amount), Integer.valueOf(amount), SweBank.getBalance(acc));
	}

	// changed from deposit to withdraw
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		String acc = "Ulrika";
		int amount = 100_000;
		assertEquals("Balance should be 0", Integer.valueOf(0), SweBank.getBalance(acc));
		SweBank.deposit(acc, new Money(amount, SEK));
		assertEquals(String.format("Balance should be %d", amount), Integer.valueOf(amount), SweBank.getBalance(acc));
		SweBank.withdraw(acc, new Money(amount, SEK));
		assertEquals("Balance should be 0", Integer.valueOf(0), SweBank.getBalance(acc));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals("Balance should be 0", Integer.valueOf(0), SweBank.getBalance("Ulrika"));
		assertEquals("Balance should be 0", Integer.valueOf(0), Nordea.getBalance("Bob"));
	}

	// changed transfer from "fromAccount" to "toAccount"
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		String accFrom = "Bob";
		String accTo = "Ulrika";
		int amountDeposit = 1_000_000;
		int amount = 500_000;
		SweBank.deposit(accFrom, new Money(amountDeposit, SEK));
		SweBank.transfer(accFrom, accTo, new Money(amount, SEK));
		assertEquals("Balance should be 500,000", Integer.valueOf(500_000), SweBank.getBalance(accFrom));
		assertEquals("Balance should be 500,000", Integer.valueOf(500_000), SweBank.getBalance(accTo));
	}

	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		String accFrom = "Bob";
		String accTo = "Ulrika";
		int amountDeposit = 1_000_000;
		int amount = 100_000;
		SweBank.deposit(accFrom, new Money(amountDeposit, SEK));
		SweBank.addTimedPayment(accFrom, "tp1", 0, 0, new Money(amount, SEK), SweBank, accTo);
		for (int i = 0; i < 5; i++) {
			SweBank.tick();
		}
		assertEquals("Balance should be 500,000", Integer.valueOf(500_000), SweBank.getBalance(accFrom));
		assertEquals("Balance should be 500,000", Integer.valueOf(500_000), SweBank.getBalance(accTo));
	}
}
