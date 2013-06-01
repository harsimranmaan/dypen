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
        System.out.println("|             Welcome to Maan Banking                |");
        System.out.println("------------------------------------------------------");
        System.out.println("");
    }

    public void init() throws RemoteException
    {
        Client client;
        String message, command;
        int quantity;
        String[] commandString;
        boolean isExit = false;

        do
        {
            printPrompt();

            System.out.println("> ");
            command = getInput();
            commandString = command.split(" ");
            switch (commandString[0])
            {
                case "user":
                    if (commandString.length == 2)
                    {
                        client = authentication.init(commandString[1], false);
                    }
                    break;
                case "buy":
                    if (commandString.length == 3)
                    {
                        try
                        {
                            quantity = Integer.parseInt(commandString[2]);
                            //                               stockQuery.buy(commandString[0],commandString[1],quantity);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "sell":
                    if (commandString.length == 3)
                    {
                        try
                        {
                            quantity = Integer.parseInt(commandString[2]);
                            //                             stockQuery.sell(commandString[0],commandString[1],quantity);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "query":
                    if (commandString.length == 2)
                    {
                        stockQuery.query(commandString[0], commandString[1]);
                    }
                    break;
                case "update":
                    if (commandString.length == 3)
                    {
                    }
                    break;
                case "help":

                    printPrompt();
                    break;
                default:
                    System.out.println("Command not recognized.");
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
}
