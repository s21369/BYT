package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10_000_000, SEK));
		SweBank.deposit("Alice", new Money(1_000_000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		String id = "tp1";
		testAccount.addTimedPayment(id, 1, 1, new Money(100_000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists(id));
		testAccount.removeTimedPayment(id);
		assertFalse(testAccount.timedPaymentExists(id));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		int nPayments = 5;
		int amountToPay = 100_000;
		assertEquals(Integer.valueOf(10_000_000), testAccount.getBalance().getAmount());
		testAccount.addTimedPayment("tp1", 1, 1, new Money(amountToPay, SEK), SweBank, "Alice");
		for (int i = 0; i < nPayments; i++) {
			testAccount.tick();
		}
		assertEquals(Integer.valueOf(10_000_000 - amountToPay * nPayments), testAccount.getBalance().getAmount());
	}

	@Test
	public void testAddWithdraw() {
		int amount = 100_000;
		testAccount.deposit(new Money(amount, SEK));
		assertEquals(Integer.valueOf(10_000_000 + amount), testAccount.getBalance().getAmount());
		testAccount.withdraw(new Money(amount, SEK));
		assertEquals(Integer.valueOf(10_000_000), testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(Integer.valueOf(10_000_000), testAccount.getBalance().getAmount());
	}
}
