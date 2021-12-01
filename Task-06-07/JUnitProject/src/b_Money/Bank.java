package b_Money;

import java.util.Hashtable;

public class Bank {

	// added final keyword, fixed namings
	private final Hashtable<String, Account> accountsList = new Hashtable<>();
	private final String name;
	private final Currency currency;

	/**
	 * New Bank
	 * @param name Name of this bank
	 * @param currency Base currency of this bank (If this is a Swedish bank, this might be a currency class representing SEK)
	 */
	// added public keyword
	public Bank(String name, Currency currency) {
		this.name = name;
		this.currency = currency;
	}

	/**
	 * Get the name of this bank
	 * @return Name of this bank
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the Currency of this bank
	 * @return The Currency of this bank
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Open an account at this bank.
	 * @param accountId The ID of the account
	 * @throws AccountExistsException If the account already exists
	 */
	// fixed namings
	public void openAccount(String accountId) throws AccountExistsException {
		if (accountsList.containsKey(accountId)) {
			throw new AccountExistsException();
		}
		// removed else statement, changed from get to put in the list
		accountsList.put(accountId, new Account(currency));
	}

	/**
	 * Deposit money to an account
	 * @param accountId Account to deposit to
	 * @param money Money to deposit.
	 * @throws AccountDoesNotExistException If the account does not exist
	 */
	// fixed namings, added negation to check whether account is in list
	public void deposit(String accountId, Money money) throws AccountDoesNotExistException {
		if (!accountsList.containsKey(accountId)) {
			throw new AccountDoesNotExistException();
		}
		accountsList.get(accountId).deposit(money);
	}

	/**
	 * Withdraw money from an account
	 * @param accountId Account to withdraw from
	 * @param money Money to withdraw
	 * @throws AccountDoesNotExistException If the account does not exist
	 */
	// fixed namings, changed from deposit to withdraw money
	public void withdraw(String accountId, Money money) throws AccountDoesNotExistException {
		if (!accountsList.containsKey(accountId)) {
			throw new AccountDoesNotExistException();
		}
		accountsList.get(accountId).withdraw(money);
	}

	/**
	 * Get the balance of an account
	 * @param accountId Account to get balance from
	 * @return Balance of the account
	 * @throws AccountDoesNotExistException If the account does not exist
	 */
	// fixed namings, removed else statement
	public Integer getBalance(String accountId) throws AccountDoesNotExistException {
		if (!accountsList.containsKey(accountId)) {
			throw new AccountDoesNotExistException();
		}
		return accountsList.get(accountId).getBalance().getAmount();
	}

	/**
	 * Transfer money between two accounts
	 * @param fromAccount Id of account to deduct from in this Bank
	 * @param toBank Bank where receiving account resides
	 * @param toAccount Id of receiving account
	 * @param amount Amount of Money to transfer
	 * @throws AccountDoesNotExistException If one of the accounts do not exist
	 */
	public void transfer(String fromAccount, Bank toBank, String toAccount, Money amount) throws AccountDoesNotExistException {
		if (!accountsList.containsKey(fromAccount) || !toBank.accountsList.containsKey(toAccount)) {
			throw new AccountDoesNotExistException();
		}
		// removed else statement
		accountsList.get(fromAccount).withdraw(amount);
		toBank.accountsList.get(toAccount).deposit(amount);
	}

	/**
	 * Transfer money between two accounts on the same bank
	 * @param fromAccount Id of account to deduct from
	 * @param toAccount Id of receiving account
	 * @param amount Amount of Money to transfer
	 * @throws AccountDoesNotExistException If one of the accounts do not exist
	 */
	public void transfer(String fromAccount, String toAccount, Money amount) throws AccountDoesNotExistException {
		// fixed transfer(fromAccount, this, fromAccount, amount) to transfer(fromAccount, this, toAccount, amount)
		transfer(fromAccount, this, toAccount, amount);
	}

	/**
	 * Add a timed payment
	 * @param accountId Id of account to deduct from in this Bank
	 * @param payId Id of timed payment
	 * @param interval Number of ticks between payments
	 * @param next Number of ticks till first payment
	 * @param amount Amount of Money to transfer each payment
	 * @param toBank Bank where receiving account resides
	 * @param toAccount Id of receiving account
	 */
	public void addTimedPayment(String accountId, String payId, Integer interval, Integer next, Money amount, Bank toBank, String toAccount) {
		Account account = accountsList.get(accountId);
		account.addTimedPayment(payId, interval, next, amount, toBank, toAccount);
	}

	// removed removeTimedPayment() method because it can be removed from class Account

	/**
	 * A time unit passes in the system
	 */
	public void tick() throws AccountDoesNotExistException {
		for (Account account : accountsList.values()) {
			account.tick();
		}
	}

}
