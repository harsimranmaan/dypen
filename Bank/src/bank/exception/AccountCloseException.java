/**
 * @fileName AccountCloseException.java
 * @description Exception thrown when trying to close an account
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank.exception;

/**
 * @author harsimran.maan
 *
 */
public class AccountCloseException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountCloseException(String message)
	{
		super(message);
	}

}
