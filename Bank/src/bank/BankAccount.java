/**
 * @fileName BankAccount.java
 * @description The bank account class	
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank;

import bank.exception.AccountOpenException;
import bank.exception.DepositException;
import bank.exception.WithdrawException;
import bank.utilities.*;

/**
 * @author harsimran.maan
 * 
 */
public class BankAccount implements IBankAccount
{
	private double _balance;
	private final String _accountNumber;

	/**
	 * Create a new Bank account
	 * 
	 * @param accountNumber
	 * @param balance
	 * @throws AccountOpenException
	 */
	public BankAccount(String accountNumber, double balance)
			throws AccountOpenException
	{
		if (!accountNumber.matches(accountPattern))
		{
			throw new AccountOpenException(
					"Account number should be of type 999-999");
		}
		if (balance < 0 || balance > maxBalance)
		{
			throw new AccountOpenException(
					"Opening balance should be between 0 and "
							+ Utility.FormatCurrency(maxBalance));
		}

		_balance = balance;
		_accountNumber = accountNumber;

	}

	@Override
	public void deposit(double amount) throws DepositException
	{
		if (amount <= 0)
		{
			throw new DepositException(
					"Deposit amount should be greater than 0.");
		}
		if (_balance + amount > maxBalance)
		{
			throw new DepositException("Cannot exceed the max balance "
					+ Utility.FormatCurrency(maxBalance));
		}
		_balance += amount;
	}

	@Override
	public void withdraw(double amount) throws WithdrawException
	{
		if (amount <= 0)
		{
			throw new WithdrawException(
					"Withdraw amount should be greater than 0.");
		}
		if (_balance - amount < 0)
		{
			throw new WithdrawException("Insufficient balance.");
		}
		_balance -= amount;
	}

	@Override
	public double getBalance()
	{
		return _balance;
	}

	@Override
	public String getAccountNumber()
	{
		return _accountNumber;

	}

	@Override
	public String toString()
	{
		return "Current Account : " + _accountNumber + " Balance : "
				+ Utility.FormatCurrency(_balance);

	}

	@Override
	public boolean equals(IBankAccount account)
	{
		return _accountNumber.equalsIgnoreCase(account.getAccountNumber());
	}
}
