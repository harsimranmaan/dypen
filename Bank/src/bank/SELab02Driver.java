/**
 * @fileName BankApp.java
 * @description Initializes and starts the app	
 * @author Harsimran Singh Maan
 * @date 26-Jan-2013
 * @version 1.0	
 */
package bank;

/**
 * @author harsimran.maan
 *
 */
public class SELab02Driver {

	/**
	 * @param args Gets the command line arguments
	 */
	public static void main(String[] args) {
		AccountManager acc= new AccountManager();
		InteractionManager interact= new InteractionManager(acc);
		//Start
		interact.init();
	}
	
}
