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

    public StockQuery() throws RemoteException
    {
        super();
    }

    @Override
    public Stock query(Client client, String ticketName) throws RemoteException
    {
        return StockManager.fetchOrCreate(ticketName);
    }

    @Override
    public Client buy(Client client, String stock, int quantity) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client sell(Client client, String stock, int quantity) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Client client, String stock, double price) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Stock> list(Client client)
    {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
