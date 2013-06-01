/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexserver;

import stockEx.IAuthentication;

/**
 *
 * @author harsimran.maan
 */
public class Authentication implements IAuthentication
{

    @Override
    public boolean authenticate(String username)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String init(String username)
    {
        return username;
    }
}
