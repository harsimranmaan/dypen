package stockexclient;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockEx.IAuthentication;
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
    public static void main(String[] args) throws NotBoundException
    {
        try
        {


            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IStockQuery stockQuery = (IStockQuery) registry.lookup(IStockQuery.class.getSimpleName());
            IAuthentication auth = (IAuthentication) registry.lookup(IAuthentication.class.getSimpleName());

            InteractionManager interact = new InteractionManager(stockQuery, auth);
            //Start
            interact.init();

            for (int i = 1; i <= 100; i++)
            {
                System.out.println(auth.init("maan", true));
                System.out.println(stockQuery.query("" + i, "GOOG"));
                Thread.sleep(100);
            }
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(StockExClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(StockExClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
