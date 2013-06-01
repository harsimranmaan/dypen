/**
 * @fileName DepositException.java
 * @description Exception thrown when trying to deposit an invalid amount to account
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank.exception;

/**
 * @author harsimran.maan
 *
 */
public class DepositException extends Exception {

	public DepositException(String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}
