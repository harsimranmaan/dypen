/**
 * @fileName Utility.java
 * @description Utility Functions
 * @author Harsimran Singh Maan
 * @date 27-Jan-2013
 * @version 1.0	
 */
package bank.utilities;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author harsimran.maan
 * 
 */
public class Utility
{
	/**
	 * Formats a decimal into local currency string
	 * @param amount
	 * @return
	 */
	public static String FormatCurrency(double amount)
	{
		NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
		return frmt.format(amount);
	}
}
