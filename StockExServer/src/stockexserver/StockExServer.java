/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockEx.IStockQuery;

/**
 *
 * @author harsimran.maan
 */
public class StockExServer
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            if (System.getSecurityManager() == null)
            {
                System.setSecurityManager(new SecurityManager());
            }
            StockQuery queryObj = new StockQuery();
            IStockQuery stockQueryInterface = (IStockQuery) UnicastRemoteObject.exportObject(queryObj, 60011);
            // Bind the remote object in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("StockQuery", stockQueryInterface);
            System.out.println("Server started");
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(StockExServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
