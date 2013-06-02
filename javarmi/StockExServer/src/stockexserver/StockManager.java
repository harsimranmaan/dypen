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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockEx.Client;
import stockEx.ConfigManager;
import stockEx.Stock;
import stockexserver.dataAccess.DataAccess;

/**
 *
 * @author harsimran.maan
 */
public class StockManager extends Thread
{

    public static void update(String stock, double price) throws Exception
    {
        try
        {
            ResultSet set = DataAccess.getResultSet("SELECT * FROM Stock WHERE stockName='" + stock + "'");

            if (set != null && set.next())
            {
                DataAccess.updateOrInsertSingle("UPDATE Stock SET price = " + price + " WHERE stockName='" + stock + "'");
            }
            else
            {
                throw new Exception("Untracked/ Invalid stock.");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new RemoteException("Update failed.");
        }
    }

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

    public static Stock fetchOrCreate(String stock) throws Exception
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
                s.setQuantity(set.getInt("remaining"));
            }
            else
            {
                URL finance = new URL(ConfigManager.getInstance().getPropertyValue("url").replace("$1", stock));
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(finance.openStream()));
                String inputLine;
                if ((inputLine = in.readLine()) != null && !inputLine.equals("0.00"))
                {
                    String quantity = ConfigManager.getInstance().getPropertyValue("intialStock");
                    DataAccess.updateOrInsertSingle("INSERT INTO Stock VALUES('" + stock + "'," + inputLine + "," + quantity + ")");
                    s = new Stock(stock);
                    s.setPrice(Double.valueOf(inputLine));
                    s.setQuantity(Integer.valueOf(quantity));
                }
                else
                {
                    in.close();
                    throw new Exception("Stock not found");
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

    public static List<Stock> listOfStocks(String clientName)
    {
        ResultSet set;
        List<Stock> list = new ArrayList<Stock>();
        try
        {
            set = DataAccess.getResultSet("SELECT * FROM ClientBuy WHERE clientName='" + clientName + "' AND quantity>0");
            if (set != null && set.next())
            {
                do
                {
                    Stock s = new Stock(set.getString("stockName"));
                    s.setPrice(set.getDouble("price"));
                    s.setQuantity(set.getInt("quantity"));
                    list.add(s);
                }
                while (set.next());
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(StockManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static synchronized Client buy(Client client, String stockName, int quantity) throws Exception
    {
        ResultSet set = DataAccess.getResultSet("SELECT * from Client, Stock WHERE stockName='" + stockName + "' AND clientName='" + client.getUsername() + "'");
        if (set != null && set.next())
        {
            int stockQuantity = set.getInt("remaining");
            double price = set.getDouble("price");
            double balance = set.getDouble("balance");
            if (stockQuantity >= quantity)
            {
                if (price * quantity <= balance)
                {
                    try
                    {
                        DataAccess.updateOrInsertMultiple("INSERT INTO ClientBuy(clientName,stockName,quantity,price) VALUES('" + client.getUsername() + "','" + stockName + "'," + quantity + "," + price + ")");
                        DataAccess.updateOrInsertMultiple("UPDATE Client SET balance=balance-" + price * quantity + " WHERE clientName='" + client.getUsername() + "'");
                        DataAccess.updateOrInsertMultiple("UPDATE Stock SET remaining=remaining-" + quantity + " WHERE stockName='" + stockName + "'");
                        DataAccess.commit();
                    }
                    catch (SQLException ex)
                    {
                        DataAccess.rollback();
                        throw new Exception("Not able to process the request at this time. Re-try after sometime");

                    }
                    set = DataAccess.getResultSet("SELECT * FROM Client where clientName = '" + client.getUsername() + "'");
                    if (set != null && set.next())
                    {
                        client.setBalance(set.getDouble("balance"));
                    }
                }
                else
                {
                    throw new Exception("Insufficent balance to buy stocks");
                }

            }
            else
            {
                throw new Exception("Only " + stockQuantity + " stocks can be traded.");
            }
        }
        else
        {
            throw new Exception("Invalid stock.");
        }
        return client;
    }

    public static synchronized Client sell(Client client, String stockName, int quantity) throws Exception
    {
        ResultSet set = DataAccess.getResultSet("SELECT * FROM Stock WHERE stockName='" + stockName + "'");

        if (set != null && set.next())
        {
            double price = set.getDouble("price");
            set = DataAccess.getResultSet("SELECT * from ClientBuy WHERE stockName='" + stockName + "' AND clientName='" + client.getUsername() + "' AND quantity>0");
            List<ClientBuy> list = new ArrayList<>();
            int totalBought = 0;
            if (set != null && set.next())
            {
                do
                {
                    int quant = set.getInt("quantity");
                    totalBought += quant;
                    ClientBuy cb = new ClientBuy();
                    cb.setId(set.getInt("id"));
                    cb.setQuantity(quant);
                    list.add(cb);
                }
                while (set.next());
                if (totalBought >= quantity)
                {
                    try
                    {
                        DataAccess.updateOrInsertMultiple("INSERT INTO ClientSell(clientName,stockName,quantity,price) VALUES('" + client.getUsername() + "','" + stockName + "'," + quantity + "," + price + ")");
                        DataAccess.updateOrInsertMultiple("UPDATE Client SET balance=balance+" + price * quantity + " WHERE clientName='" + client.getUsername() + "'");
                        DataAccess.updateOrInsertMultiple("UPDATE Stock SET remaining=remaining+" + quantity + " WHERE stockName='" + stockName + "'");
                        for (ClientBuy buys : list)
                        {
                            if (quantity > 0)
                            {
                                int qu = buys.getQuantity() <= quantity ? buys.getQuantity() : quantity;
                                quantity -= qu;
                                DataAccess.updateOrInsertMultiple("UPDATE ClientBuy SET quantity=quantity-" + qu + " WHERE id='" + buys.getId() + "'");
                            }
                            else
                            {
                                break;
                            }
                        }
                        DataAccess.commit();
                    }
                    catch (SQLException ex)
                    {
                        DataAccess.rollback();
                        throw new Exception("Not able to process the request at this time. Re-try after sometime");

                    }
                    set = DataAccess.getResultSet("SELECT * FROM Client where clientName = '" + client.getUsername() + "'");
                    if (set != null && set.next())
                    {
                        client.setBalance(set.getDouble("balance"));
                    }
                }
                else
                {
                    throw new Exception("Cannot sell more stocks than stocks in the portfolio.");
                }
            }
            else
            {
                throw new Exception("Stock not bought.");
            }
        }
        else
        {
            throw new Exception("Invalid Stock.");
        }
        return client;
    }
}
