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
    Stock query(String client, String ticketName) throws RemoteException;

    /**
     *
     * @param stock
     * @param quantity <p/>
     * @throws RemoteException
     */
    void buy(String client, Stock stock, int quantity) throws RemoteException;

    /**
     *
     * @param stock
     * @param quantity <p/>
     * @throws RemoteException
     */
    void sell(String client, Stock stock, int quantity) throws RemoteException;

    /**
     *
     * @param stock <p/>
     * @throws RemoteException
     */
    void update(String client, Stock stock) throws RemoteException;
}
