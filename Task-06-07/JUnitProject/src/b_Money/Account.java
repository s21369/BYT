package b_Money;

import java.util.Hashtable;

public class Account {

	// renamed content to money
	private Money money;
	// fixed naming to the convention
	private final Hashtable<String, TimedPayment> timedPayments = new Hashtable<>();

	// added public keyword
	public Account(Currency currency) {
		this.money = new Money(0, currency);
	}

	/**
	 * Add a timed payment
	 * @param id Id of timed payment
	 * @param interval Number of ticks between payments
	 * @param next Number of ticks till first payment
	 * @param amount Amount of Money to transfer each payment
	 * @param toBank Bank where receiving account resides
	 * @param toAccount Id of receiving account
	 */
	// fixed namings
	public void addTimedPayment(String id, Integer interval, Integer next, Money amount, Bank toBank, String toAccount) {
		TimedPayment tp = new TimedPayment(interval, next, amount, this, toBank, toAccount);
		timedPayments.put(id, tp);
	}

	/**
	 * Remove a timed payment
	 * @param id Id of timed payment to remove
	 */
	public void removeTimedPayment(String id) {
		timedPayments.remove(id);
	}

	/**
	 * Check if a timed payment exists
	 * @param id Id of timed payment to check for
	 */
	public boolean timedPaymentExists(String id) {
		return timedPayments.containsKey(id);
	}

	/**
	 * A time unit passes in the system
	 */
	// removed unnecessary second tick
	public void tick() {
		for (TimedPayment tp : timedPayments.values()) {
			tp.tick();
		}
	}

	/**
	 * Deposit money to the account
	 * @param money Money to deposit.
	 */
	public void deposit(Money money) {
		this.money = this.money.add(money);
	}

	/**
	 * Withdraw money from the account
	 * @param money Money to withdraw.
	 */
	public void withdraw(Money money) {
		this.money = this.money.sub(money);
	}

	/**
	 * Get balance of account
	 * @return Amount of Money currently on account
	 */
	public Money getBalance() {
		return money;
	}

	/* Everything below belongs to the private inner class, TimedPayment */
	// fixed namings
	private static class TimedPayment {

		private final int interval;
		private final Account fromAccount;
		private final Money amount;
		private final Bank toBank;
		private final String toAccount;
		private int next;

		public TimedPayment(Integer interval, Integer next, Money amount, Account fromAccount, Bank toBank, String toAccount) {
			this.interval = interval;
			this.next = next;
			this.amount = amount;
			this.fromAccount = fromAccount;
			this.toBank = toBank;
			this.toAccount = toAccount;
		}

		/* Return value indicates whether a transfer was initiated */
		public Boolean tick() {
			if (next == 0) {
				next = interval;

				fromAccount.withdraw(amount);
				try {
					toBank.deposit(toAccount, amount);
				}
				catch (AccountDoesNotExistException e) {
					/* Revert transfer.
					 * In reality, this should probably cause a notification somewhere. */
					fromAccount.deposit(amount);
				}
				return true;
			}
			// removed else statement
			next--;
			return false;
		}

	}

}
