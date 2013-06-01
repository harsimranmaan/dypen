/**
 * @fileName AccountAlreadyExistsException.java
 * @description Exception thrown when trying to add a duplicate account
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank.exception;

/**
 * @author harsimran.maan
 *
 */
public class AccountAlreadyExistsException extends Exception
{

	private static final long serialVersionUID = 1L;
	public AccountAlreadyExistsException(String message)
	{
		super(message);
	}

}
