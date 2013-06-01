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
    Stock query(Client client, String ticketName) throws RemoteException;

    /**
     *
     * @param stock
     * @param quantity <p/>
     * @throws RemoteException
     */
    void buy(Client client, String stock, int quantity) throws RemoteException;

    /**
     *
     * @param stock
     * @param quantity <p/>
     * @throws RemoteException
     */
    void sell(Client client, String stock, int quantity) throws RemoteException;

    /**
     *
     * @param stock <p/>
     * @throws RemoteException
     */
    void update(Client client, String stock, double price) throws RemoteException;
}
