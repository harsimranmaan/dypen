package stockexclient;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockEx.IStockQuery;

/**
 *
 * @author harsimran.maan
 */
public class StockExClient
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }
        try
        {
            String name = "StockQuery";
            Registry registry = LocateRegistry.getRegistry(60011);
            IStockQuery lookup = (IStockQuery) registry.lookup(name);
            System.out.println(lookup.query("GOOG"));

        }
        catch (RemoteException | NotBoundException ex)
        {
            Logger.getLogger(StockExClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
