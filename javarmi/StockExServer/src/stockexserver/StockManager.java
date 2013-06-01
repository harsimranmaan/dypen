/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockEx.ConfigManager;
import stockEx.Stock;
import stockexserver.dataAccess.DataAccess;

/**
 *
 * @author harsimran.maan
 */
public class StockManager extends Thread
{

    private void updateStocks(String stocks)
    {
        try
        {
            String[] stcks = stocks.split(",");
            URL finance = new URL(ConfigManager.getInstance().getPropertyValue("url").replace("$1", stocks));
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(finance.openStream()));
            String inputLine;
            int counter = 0;
            while ((inputLine = in.readLine()) != null && counter < stcks.length)
            {
                try
                {
                    DataAccess.updateOrInsertSingle("UPDATE Stock SET price = " + inputLine + " WHERE stockName='" + stcks[counter++] + "'");
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            in.close();

        }
        catch (IOException ex)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Stock fetchOrCreate(String stock)
    {

        Stock s = null;
        stock = stock.split(",")[0];
        ResultSet set;
        try
        {
            set = DataAccess.getResultSet("SELECT * FROM Stock WHERE stockName='" + stock + "'");

            if (set != null && set.next())
            {
                s = new Stock(stock);
                s.setPrice(set.getDouble("price"));
            }
            else
            {
                URL finance = new URL(ConfigManager.getInstance().getPropertyValue("url").replace("$1", stock));
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(finance.openStream()));
                String inputLine;
                if ((inputLine = in.readLine()) != null && !inputLine.equals("0.00"))
                {
                    DataAccess.updateOrInsertSingle("INSERT INTO Stock VALUES('" + stock + "'," + inputLine + "," + ConfigManager.getInstance().getPropertyValue("intialStock") + ")");
                    s = new Stock(stock);
                    s.setPrice(Double.valueOf(inputLine));
                }
                else
                {
                    in.close();
                    throw new RemoteException("Stock not found");
                }
                in.close();
            }

        }
        catch (SQLException ex)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void run()
    {
        while (true)
        {

            String stocks = "goog";
            ResultSet set;
            try
            {
                set = DataAccess.getResultSet("SELECT  IFNULL(GROUP_CONCAT(stockName),'') FROM Stock");
                if (set != null && set.next())
                {
                    stocks = set.getString(1);
                }
            }
            catch (SQLException ex)
            {
                Logger.getLogger(StockManager.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            if (!stocks.equals(""))
            {
                updateStocks(stocks);
            }
            try
            {
                Thread.sleep(Integer.valueOf(ConfigManager.getInstance().getPropertyValue("interval")));
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(StockManager.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}