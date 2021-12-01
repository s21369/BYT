package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

	// removed unnecessary vars
	Currency SEK;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account(SEK);
		testAccount.deposit(new Money(10_000_000, SEK));
		SweBank.deposit("Alice", new Money(1_000_000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		String id = "tp1";
		// added timed payment
		testAccount.addTimedPayment(id, 0, 0, new Money(100_000, SEK), SweBank, "Alice");
		assertTrue("Timed payment should exist", testAccount.timedPaymentExists(id));
		// removed timed payment
		testAccount.removeTimedPayment(id);
		assertFalse("Timed payment should not exist", testAccount.timedPaymentExists(id));
	}

	// tick() method was called twice
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		int nPayments = 5;
		int amountToPay = 100_000;
		assertEquals("Balance should be 10,000,000", Integer.valueOf(10_000_000), testAccount.getBalance().getAmount());
		// add timed payment
		testAccount.addTimedPayment("tp1", 0, 0, new Money(amountToPay, SEK), SweBank, "Alice");
		// perform payments
		for (int i = 0; i < nPayments; i++) {
			testAccount.tick();
		}
		assertEquals(String.format("Balance should be %d", (10_000_000 - amountToPay * nPayments)), Integer.valueOf(10_000_000 - amountToPay * nPayments), testAccount.getBalance().getAmount());
	}

	@Test
	public void testAddWithdraw() {
		int amount = 100_000;
		// deposit 100,000 to account
		testAccount.deposit(new Money(amount, SEK));
		assertEquals(String.format("Balance should be %d", (10_000_000 + amount)), Integer.valueOf(10_000_000 + amount), testAccount.getBalance().getAmount());
		// withdraw 100,000 from account
		testAccount.withdraw(new Money(amount, SEK));
		assertEquals("Balance should be 10,000,000", Integer.valueOf(10_000_000), testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals("Balance should be 10,000,000", Integer.valueOf(10_000_000), testAccount.getBalance().getAmount());
	}

}
