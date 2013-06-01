/**
 * @fileName InteractionManager.java
 * @description Bank.InteractionManager.java	
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank;

import java.util.Scanner;
import bank.exception.*;

/**
 * @author harsimran.maan Controls interactions with the user
 */
public class InteractionManager
{
	private AccountManager _accManager;
	private Scanner scanIn;

	/**
	 * 
	 * @param accManager
	 */
	InteractionManager(AccountManager accManager)
	{
		printMessage("------------------------------------------------------");
		printMessage("|             Welcome to Maan Banking                |");
		printMessage("------------------------------------------------------");
		printMessage("");
		_accManager = accManager;
	}

	/**
	 * Initializes the UI
	 */
	public void init()
	{
		String command, message;
		IBankAccount selectedAccount;
		boolean isExit = false;

		do
		{
			printPrompt();
			message = "Current account: None selected.";
			selectedAccount = _accManager.getSelectedAccount();
			if (selectedAccount != null)
			{
				message = selectedAccount.toString();
			}
			printMessage(message);
			printInlineMessage("Enter Command: ");
			command = getInput();
			switch (command)
			{
				case "s":
					selectAccount();
					break;
				case "o":
					openAccount();
					break;

				case "d":
					deposit();
					break;

				case "w":
					withdraw();
					break;

				case "c":
					close();
					break;

				case "q":
					isExit = true;
					printMessage("Thank You. Have a nice day.");
					break;
				default:
					printMessage("Command not recognized.");
					break;
			}
		} while (!isExit);
	}

	/**
	 * Handles close command
	 */
	private void close()
	{
		try
		{
			_accManager.closeAccount();
		}
		catch (AccountNotSelectedException e)
		{
			printSelectAccountMessage();
		}
		catch (AccountCloseException e)
		{
			printMessage(e.getMessage());
		}

	}

	/**
	 * Prints the select account message
	 */
	private void printSelectAccountMessage()
	{
		printMessage("Please select an account.");
	}

	/**
	 * Handles withdraw command
	 */
	private void withdraw()
	{
		IBankAccount account = _accManager.getSelectedAccount();
		if (account != null)
		{
			printInlineMessage("Enter amount to withdraw: $");
			try
			{
				double balance = getInputAmount();
				_accManager.withdraw(balance);
			}
			catch (NumberFormatException ex)
			{
				printBalanceInvalid();
			}

			catch (WithdrawException e)
			{
				printMessage(e.getMessage());
			}
			catch (AccountNotSelectedException e)
			{
				printSelectAccountMessage();
			}
		}
		else
		{
			printSelectAccountMessage();
		}

	}

	/**
	 * Handles deposit command
	 */
	private void deposit()
	{
		IBankAccount account = _accManager.getSelectedAccount();
		if (account != null)
		{
			printInlineMessage("Enter amount to deposit: $");
			try
			{
				double balance = getInputAmount();
				_accManager.deposit(balance);
			}
			catch (NumberFormatException ex)
			{
				printBalanceInvalid();
			}
			catch (DepositException e)
			{
				printMessage(e.getMessage());
			}
			catch (AccountNotSelectedException e)
			{
				printSelectAccountMessage();
			}
		}
		else
		{
			printSelectAccountMessage();
		}
	}

	/**
	 * Handles open command
	 */
	private void openAccount()
	{
		printInlineMessage("Enter new account number: ");
		try
		{
			String accountNumber = getInput();
			if (!accountNumber.matches(IBankAccount.accountPattern))
			{
				throw new AccountOpenException(
						"Account number should be of type 999-999");
			}
			if(_accManager.getAccountByNumber(accountNumber)!=null)
			{
				throw new AccountAlreadyExistsException("Account already exists.");
			}
			printInlineMessage("Enter initial balance: $");
			double balance = getInputAmount();
			_accManager.openAccount(accountNumber, balance);
		}
		catch (NumberFormatException ex)
		{
			printBalanceInvalid();

		}
		catch (AccountOpenException e)
		{
			printMessage(e.getMessage());
		}
		catch (AccountAlreadyExistsException e)
		{
			printMessage(e.getMessage());
		}

	}

	/**
	 * Gets the amount input from user
	 * 
	 * @return The input amount
	 */
	private double getInputAmount()
	{
		String bal = getInput();

		//check if a  valid decimal
		if (!bal.matches("^\\d+$|^[.]?\\d{1,2}$|^\\d+[.]?\\d{1,2}$"))
		{
			throw new NumberFormatException("Invalid entry");
		}
		return Double.valueOf(bal);
	}

	private void printBalanceInvalid()
	{
		printMessage("Amount should be numeric of type 9.99");
	}

	/**
	 * Command message
	 */
	private void printPrompt()
	{
		printMessage("------------------------------------------------------");
		printMessage("| Commands : o - Open account      c - Close account |");
		printMessage("|            d - Deposit           w - Withdraw      |");
		printMessage("|            s - Select account    q - Quit          |");
		printMessage("------------------------------------------------------");
	}

	private void printMessage(String message)
	{
		System.out.println(message);
	}

	private void printInlineMessage(String message)
	{
		System.out.print(message);
	}

	/**
	 * Handles select command
	 */
	private void selectAccount()
	{
		printInlineMessage("Enter account number: ");
		String accountNum = getInput();
		try
		{
			_accManager.selectAccount(accountNum);
		}
		catch (AccountNotFoundException e)
		{
			printMessage(e.getMessage());
		}
	}

	/**
	 * Gets input from console
	 * 
	 * @return Input string
	 */
	private String getInput()
	{
		scanIn = new Scanner(System.in);
		String input = scanIn.nextLine().trim().toLowerCase();
		return input;
	}
}
