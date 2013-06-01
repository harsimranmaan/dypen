/**
 * @fileName AccountNotSelectedException.java
 * @description Exception thrown when trying to operate without selecting an account
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank.exception;

/**
 * @author harsimran.maan
 *
 */
public class AccountNotSelectedException extends Exception
{

	public AccountNotSelectedException(String message)
	{
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
