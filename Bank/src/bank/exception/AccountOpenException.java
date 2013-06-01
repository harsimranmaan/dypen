/**
 * @fileName AccountOpenException.java
 * @description Exception thrown when trying to open a new account with invalid data	
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank.exception;

/**
 * @author harsimran.maan
 *
 */
public class AccountOpenException extends Exception
{
	private static final long serialVersionUID = 1L;

	public AccountOpenException(String message)
	{
		super(message);
	}

}
