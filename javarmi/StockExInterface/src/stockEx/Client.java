/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockEx;

import java.io.Serializable;

/**
 *
 * @author harsimran.maan
 */
public class Client implements Serializable
{

    private static final long serialVersionUID = 1111L;
    private String username;
    private double balance;
    private boolean isAdmin;

    public Client(String username)
    {
        this.username = username;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @return the balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    /**
     * @return the isAdmin
     */
    public boolean isAdmin()
    {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }
}
