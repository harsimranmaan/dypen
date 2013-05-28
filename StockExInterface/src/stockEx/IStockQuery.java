/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockEx;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author harsimran.maan
 */
public interface IStockQuery extends Remote
{

    /**
     *
     * @param ticketName <p/>
     * @return <p/>
     * @throws RemoteException
     */
    String query(String ticketName) throws RemoteException;

    /**
     *
     * @param ticketName
     * @param quantity   <p/>
     * @throws RemoteException
     */
    void buy(String ticketName, int quantity) throws RemoteException;

    /**
     *
     * @param ticketName
     * @param quantity   <p/>
     * @throws RemoteException
     */
    void sell(String ticketName, int quantity) throws RemoteException;
}
