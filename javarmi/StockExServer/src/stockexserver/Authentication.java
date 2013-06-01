/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import stockEx.Client;
import stockEx.IAuthentication;

/**
 *
 * @author harsimran.maan
 */
public class Authentication extends UnicastRemoteObject implements IAuthentication, Serializable
{

    private static final long serialVersionUID = 2222L;

    public Authentication() throws RemoteException
    {
        super();
    }

    @Override
    public Client init(String username, boolean isAdmin)
    {
        return new Client(username);
    }
}
