/**
 * @fileName IBankAccount.java
 * @description Interace for all types of bank accounts	
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank;

import bank.exception.DepositException;
import bank.exception.WithdrawException;

/**
 * @author harsimran.maan
 * 
 */
public interface IBankAccount
{

	String accountPattern = "^[0-9]{3}-[0-9]{3}$";
	double maxBalance = 9999999999999.00;

	/**
	 * Deposit money into an account
	 * 
	 * @param amount
	 * @throws DepositException
	 */
	void deposit(double amount) throws DepositException;

	/**
	 * Withdraw money from an account
	 * 
	 * @param amount
	 * @throws WithdrawException
	 */
	void withdraw(double amount) throws WithdrawException;

	/**
	 * Get the balance
	 * 
	 * @return
	 */
	double getBalance();

	/**
	 * Get the account number
	 * 
	 * @return
	 */
	String getAccountNumber();

	/**
	 * Compares two account
	 * 
	 * @param account
	 * @return
	 */
	boolean equals(IBankAccount account);
}
