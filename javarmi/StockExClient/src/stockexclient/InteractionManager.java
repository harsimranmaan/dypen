/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexclient;

import java.rmi.RemoteException;
import java.util.Scanner;
import stockEx.Client;
import stockEx.IAuthentication;
import stockEx.IStockQuery;

/**
 *
 * @author Kuntal
 */
public class InteractionManager
{

    private String selectedUserName;
    private Scanner scanIn;
    private IStockQuery stockQuery;
    private IAuthentication authentication;
    private Client client;

    private void printloginMessage()
    {
        System.out.println("Please log in before you send the request.");
    }

    private boolean isLoggedIn()
    {
        return client != null;
    }

    private void printWarning(String commandName)
    {
        System.out.print("Invalid parameters or Invalid command " + commandName);
        System.out.println(". Use help for syntax.");
    }

    public String getSelectedUserName()
    {
        return selectedUserName;
    }

    public void setSelectedUserName(String selectedUserName)
    {
        this.selectedUserName = selectedUserName;
    }

    public InteractionManager(IStockQuery stockQuery, IAuthentication authentication)
    {
        this.stockQuery = stockQuery;
        this.authentication = authentication;
        System.out.println("------------------------------------------------------");
        System.out.println("|             Welcome to dypen Stock Exchange        |");
        System.out.println("------------------------------------------------------");
        System.out.println("");
    }

    public void init() throws RemoteException
    {

        String message, command;
        int quantity = 0;
        String[] commandString;
        boolean isExit = false;

        do
        {
            System.out.println("> ");
            command = getInput();
            commandString = command.split(" ");
            switch (commandString[0])
            {
                case "query":
                    if (commandString.length == 2)
                    {
                        if (isLoggedIn())
                        {
                            System.out.println(stockQuery.query(client, commandString[1]));

                        }
                        else
                        {
                            printloginMessage();
                        }
                    }
                    else
                    {
                        printWarning(commandString[0]);
                    }
                    break;

                case "buy":
                    if (commandString.length == 3)
                    {
                        if (isLoggedIn())
                        {
                            quantity = getInteger(commandString[2]);
                            stockQuery.buy(client, commandString[1], quantity);
                            System.out.println(Integer.toString(quantity) + " " + commandString[1] + " bought.");
                        }
                        else
                        {
                            printloginMessage();
                        }
                    }
                    else
                    {
                        printWarning(commandString[0]);
                    }
                    break;
                case "sell":
                    if (commandString.length == 3)
                    {
                        if (isLoggedIn())
                        {
                            quantity = getInteger(commandString[2]);
                            stockQuery.sell(client, commandString[1], quantity);
                            System.out.println(Integer.toString(quantity) + " " + commandString[1] + " sold.");
                        }
                        else
                        {
                            printloginMessage();
                        }
                    }
                    else
                    {
                        printWarning(commandString[0]);
                    }
                    break;

                case "update":
                    if (commandString.length == 3)
                    {
                        if (isLoggedIn())
                        {
                            stockQuery.query(client, commandString[1]);
                        }
                        else
                        {
                            printloginMessage();
                        }
                    }
                    else
                    {
                        printWarning(commandString[0]);
                    }
                    break;
                case "user":
                    if (commandString.length == 2)
                    {
                        client = authentication.init(commandString[1], false);
                    }
                    else
                    {
                        printWarning(commandString[0]);
                    }
                    break;
                case "help":
                    printPrompt();
                    break;
                default:
                    System.out.println("Command not recognized.");
                    printWarning(commandString[0]);

                    break;
            }
        }
        while (!isExit);
    }

    public void printPrompt()
    {
        System.out.println("-----------------------------------------------");
        System.out.println("|   Commands: user username                   |");
        System.out.println("|           : buy  ticker name quantity       |");
        System.out.println("|           : sell ticker name quantity       |");
        System.out.println("|           : query ticker name               |");
        //  System.out.println("|           : update  stockticker            |");
        System.out.println("-----------------------------------------------");
    }

    private String getInput()
    {
        scanIn = new Scanner(System.in);
        String input = scanIn.nextLine().trim().toLowerCase();
        return input;
    }

    private String[] inputSplit(String inputString)
    {
        String SplitString[] = inputString.split(" ");
        if (SplitString.length > 1 || SplitString.length < 4)
        {
            return SplitString;
        }
        else
        {
            SplitString = new String[]
            {
                " "
            };
            return SplitString;
        }
    }

    private double getInputAmount(String bal) throws NumberFormatException
    {

        //check if a  valid decimal
        if (!bal.matches("^\\d+$|^[.]?\\d{1,2}$|^\\d+[.]?\\d{1,2}$"))
        {
            throw new NumberFormatException("Invalid entry");
        }
        return Double.valueOf(bal);
    }

    private int getInteger(String strInt) throws NumberFormatException
    {

        //check if a  valid decimal
        if (!strInt.matches("^[0-9]+$"))
        {
            throw new NumberFormatException("Invalid entry");
        }
        return Integer.valueOf(strInt);
    }
}
