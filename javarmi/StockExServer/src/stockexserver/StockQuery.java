/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import stockEx.Client;
import stockEx.IStockQuery;
import stockEx.Stock;
import java.util.List;

/**
 *
 * @author harsimran.maan
 */
public class StockQuery extends UnicastRemoteObject implements IStockQuery, Serializable
{

    private static final long serialVersionUID = 222L;

    /**
     * StockQuery constructor
     * <p/>
     * @throws RemoteException
     */
    public StockQuery() throws RemoteException
    {
        super();
    }

    /**
     *
     * @param client
     * @param ticketName <p/>
     * @return Stock Information
     * <p/>
     * @throws RemoteException
     * @throws Exception
     */
    @Override
    public Stock query(Client client, String ticketName) throws RemoteException, Exception
    {
        return StockManager.fetchOrCreate(ticketName);
    }

    /**
     *
     * @param client
     * @param stock
     * @param quantity <p/>
     * @return client with updated account information after buying
     * <p/>
     * @throws RemoteException
     * @throws Exception
     */
    @Override
    public Client buy(Client client, String stock, int quantity) throws RemoteException, Exception
    {
        return StockManager.buy(client, stock, quantity);
    }

    /**
     *
     * @param client
     * @param stock
     * @param quantity <p/>
     * @return client with updated account information after selling
     * <p/>
     * @throws RemoteException
     * @throws Exception
     */
    @Override
    public Client sell(Client client, String stock, int quantity) throws RemoteException, Exception
    {
        return StockManager.sell(client, stock, quantity);
    }

    /**
     * Updates the stock price
     * <p/>
     * @param client
     * @param stock
     * @param price <p/>
     * @throws RemoteException
     * @throws Exception
     */
    @Override
    public void update(Client client, String stock, double price) throws RemoteException, Exception
    {
        StockManager.update(stock, price);
    }

    /**
     *
     * @param client <p/>
     * @return list of stocks client has bought
     * <p/>
     * @throws RemoteException
     */
    @Override
    public List<Stock> list(Client client) throws RemoteException
    {
        return StockManager.listOfStocks(client.getUsername());
    }
}
