/**
 * @fileName AccountManager.java
 * @description Manages all the accounts in the bank	
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank;

import bank.exception.*;

/**
 * @author harsimran.maan
 * 
 */
public class AccountManager
{
	private IBankAccount[] _bankAccounts;
	private IBankAccount _selectedAccount;
	private int _totalAccounts;

	/**
	 * Initializes the account manager
	 */
	AccountManager()
	{
		_bankAccounts = new IBankAccount[1];
		_totalAccounts = 0;
	}

	/**
	 * Opens a new account.
	 * 
	 * @param accountNumber
	 * @param balance
	 * @return
	 * @throws AccountOpenException
	 * @throws AccountAlreadyExistsException
	 */
	public void openAccount(String accountNumber, double balance)
			throws AccountOpenException, AccountAlreadyExistsException
	{
		BankAccount newAccount = new BankAccount(accountNumber, balance);
		if (getAccountByNumber(accountNumber) == null)
		{
			if (_totalAccounts == _bankAccounts.length)
			{
				IBankAccount[] _doubleSized = new IBankAccount[_totalAccounts * 2];
				for (int counter = 0; counter < _totalAccounts; counter++)
				{
					_doubleSized[counter] = _bankAccounts[counter];
				}
				_bankAccounts = _doubleSized;
			}
			_bankAccounts[_totalAccounts] = newAccount;
			_totalAccounts++;
			_selectedAccount = newAccount;
		}
		else
		{
			throw new AccountAlreadyExistsException("Account already exists.");
		}
	}

	/**
	 * Gets an account by the account number
	 * 
	 * @param accountNumber
	 * @return The bank account if found else null
	 */
	public IBankAccount getAccountByNumber(String accountNumber)
	{
		int counter = 0;
		IBankAccount accountReq = null;
		while (counter < _totalAccounts)
		{
			IBankAccount account = _bankAccounts[counter++];
			if (account.getAccountNumber().equalsIgnoreCase(accountNumber))
			{
				accountReq = account;
			}
		}
		return accountReq;
	}

	/**
	 * Closes the selected account
	 * 
	 * @return Success Status
	 * @throws AccountNotSelectedException
	 */
	public void closeAccount() throws AccountNotSelectedException,
			AccountCloseException
	{
		if (_selectedAccount != null)
		{
			if (_selectedAccount.getBalance() > 0)
			{
				throw new AccountCloseException(
						"Cannot close an account with balance greater than 0");
			}
			IBankAccount[] _tempHolder = new IBankAccount[_bankAccounts.length];
			int offset = 0;
			for (int counter = 0; counter < _totalAccounts; counter++)
			{
				IBankAccount account = _bankAccounts[counter];
				if (account.equals(_selectedAccount))
				{
					// skip
					offset = 1;
					continue;
				}
				_tempHolder[counter - offset] = account;
			}
			_bankAccounts = _tempHolder;
			_totalAccounts--;
			_selectedAccount = null;
		}
		else{
		throw new AccountNotSelectedException("No account selected to close!");
		}
	}

	/**
	 * Selects an account for various operations
	 * 
	 * @param accountNumber
	 * @return The selected account number
	 * @throws AccountNotFoundException
	 */
	public IBankAccount selectAccount(String accountNumber)
			throws AccountNotFoundException
	{
		_selectedAccount = getAccountByNumber(accountNumber);
		if (_selectedAccount == null)
		{
			throw new AccountNotFoundException("Account " + accountNumber
					+ " not found");
		}
		return _selectedAccount;
	}

	/**
	 * Gets the selected account
	 * 
	 * @return
	 */
	public IBankAccount getSelectedAccount()
	{
		return _selectedAccount;

	}

	/**
	 * Withdraws a given amount from the selected account
	 * 
	 * @param balance
	 * @throws WithdrawException
	 * @throws AccountNotSelectedException
	 */
	public void withdraw(double balance) throws WithdrawException,
			AccountNotSelectedException
	{
		if (_selectedAccount != null)
		{
			_selectedAccount.withdraw(balance);
		}
		else
		{
			throw new AccountNotSelectedException(
					"No account selected to withdraw from!");
		}
	}

	/**
	 * Deposits a given amount to the selected account
	 * 
	 * @param balance
	 * @throws DepositException
	 * @throws AccountNotSelectedException
	 */
	public void deposit(double balance) throws DepositException,
			AccountNotSelectedException
	{
		if (_selectedAccount != null)
		{
			_selectedAccount.deposit(balance);
		}
		else
		{
			throw new AccountNotSelectedException(
					"No account selected to deposit into!");
		}
	}
}
