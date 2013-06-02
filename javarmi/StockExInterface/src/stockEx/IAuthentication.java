/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockEx;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Function to authenticate for the user to be Updater or Buyer/Seller
 * <p/>
 * @author harsimran.maan
 */
public interface IAuthentication extends Remote
{

    Client init(String username, boolean isAdmin) throws RemoteException;
}
