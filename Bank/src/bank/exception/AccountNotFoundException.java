/**
 * @fileName AccountNotFoundException.java
 * @description Exception thrown when searched account is not found.
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank.exception;

/**
 * @author harsimran.maan
 *
 */
public class AccountNotFoundException extends Exception {

	public AccountNotFoundException(String message)
	{
		super(message);
	}
	private static final long serialVersionUID = 1L;

}
