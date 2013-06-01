/**
 * @fileName WithdrawException.java
 * @description Exception thrown when trying to withdraw an invalid amount to account	
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank.exception;

/**
 * @author harsimran.maan
 *
 */
public class WithdrawException extends Exception {

	public WithdrawException(String message) {
		super(message);
	}
	private static final long serialVersionUID = 1L;

}
