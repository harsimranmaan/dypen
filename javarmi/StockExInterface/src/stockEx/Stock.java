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
public class Stock implements Serializable
{

    private static final long serialVersionUID = 111L;
    private String name;
    private double price;

    /**
     *
     * @param name
     */
    public Stock(String name)
    {

        this.name = name;
    }

    /**
     *
     * @param name
     * @param price
     */
    public Stock(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Stock [name:" + name + ", price:" + price + "]";
    }
}
