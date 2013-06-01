/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockEx;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

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
    Stock query(Client client, String ticketName) throws RemoteException, Exception;

    /**
     *
     * @param stock
     * @param quantity <p/>
     * @throws RemoteException
     */
    Client buy(Client client, String stock, int quantity) throws RemoteException, Exception;

    /**
     *
     * @param stock
     * @param quantity <p/>
     * @throws RemoteException
     */
    Client sell(Client client, String stock, int quantity) throws RemoteException, Exception;

    /**
     *
     * @param stock <p/>
     * @throws RemoteException
     */
    void update(Client client, String stock, double price) throws RemoteException, Exception;

    /**
     *
     * @param client <p/>
     * @return List of stock
     * <p/>
     * @throws RemoteException
     */
    List<Stock> list(Client client) throws RemoteException;
}
