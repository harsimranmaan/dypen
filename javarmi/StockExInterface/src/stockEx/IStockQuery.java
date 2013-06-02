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
     * Get the stock data for the given ticket name
     * <p/>
     * @param ticketName <p/>
     * @return <p/>
     * @throws RemoteException
     */
    Stock query(Client client, String ticketName) throws RemoteException, Exception;

    /**
     * Buy stocks for the given ticket name according to the given number to buy
     * <p/>
     * @param stock
     * @param quantity <p/>
     * @throws RemoteException
     */
    Client buy(Client client, String stock, int quantity) throws RemoteException, Exception;

    /**
     * Sell stocks of the given ticket name according to the given number to
     * sell
     * <p/>
     * @param stock
     * @param quantity <p/>
     * @throws RemoteException
     */
    Client sell(Client client, String stock, int quantity) throws RemoteException, Exception;

    /**
     * Update the price of stock of the given ticket name
     * <p/>
     * @param stock <p/>
     * @throws RemoteException
     */
    void update(Client client, String stock, double price) throws RemoteException, Exception;

    /**
     * List all stock information of a client
     * <p/>
     * @param client <p/>
     * @return List of stock
     * <p/>
     * @throws RemoteException
     */
    List<Stock> list(Client client) throws RemoteException;
}
