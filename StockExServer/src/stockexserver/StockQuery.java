/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexserver;

import java.rmi.RemoteException;
import stockEx.IStockQuery;

/**
 *
 * @author harsimran.maan
 */
public class StockQuery implements IStockQuery
{

    @Override
    public String query(String ticketName) throws RemoteException
    {
        return "Test Success!";
    }

    @Override
    public void buy(String ticketName, int quantity) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sell(String ticketName, int quantity) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
