/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexserver.dataAccess;

/**
 *
 * @author harsimran.maan
 */
import com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import stockEx.ConfigManager;
import stockexserver.encryption.Encryptor;

/**
 * DatabaseConnector class. JDBC driver to connect to mysql server.
 */
public class DataAccess
{

    private static Connection conn;

    /**
     * Creates a connection to the database
     * <p/>
     * <
     * p/>
     */
    public static void Connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            ConfigManager context = ConfigManager.getInstance();
            conn = DriverManager.getConnection(context.getPropertyValue("dbConnection"), context.getPropertyValue("dbUserId"), Encryptor.decryptAES(context.getPropertyValue("dbUserToken")));
            conn.setAutoCommit(false);
            //      System.out.println("success conn =" + (conn).toString());
        }
        catch (Exception ex)
        {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Close the database connection
     */
    public static void close()
    {
        try
        {
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void commit() throws SQLException
    {
        conn.commit();
    }

    public static void rollback() throws SQLException
    {
        conn.rollback();
    }

    /**
     * Returns the database connection
     * <p/>
     * @return database connection
     */
    public static Connection getConnection()
    {
        try
        {
            if (!conn.isValid(100))
            {
                Connect();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return conn;
        }
    }

    /**
     * Runs a database query that does not return any records
     * <p/>
     * @param sql query to be run
     * <p/>
     * @throws SQLException
     */
    public static void updateOrInsertSingle(String sql) throws SQLException
    {
        //System.out.println(sql);
        Statement stmt = getConnection().createStatement();
        try
        {
            stmt.execute(sql);
            conn.commit();
        }
        catch (MySQLNonTransientConnectionException se)
        {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, se);

        }
        catch (SQLException se)
        {
            conn.rollback();
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, se);
            throw se;
        }
        stmt.close();
    }

    /**
     * Runs a database query that does not return any records without committing
     * data
     * <p/>
     * @param sql query to be run
     * <p/>
     * @throws SQLException
     */
    public static void updateOrInsertMultiple(String sql) throws SQLException
    {
        // System.out.println(sql);
        Statement stmt = getConnection().createStatement();
        try
        {
            stmt.execute(sql);
        }
        catch (MySQLNonTransientConnectionException se)
        {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, se);
        }
        catch (SQLException se)
        {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, se);
            throw se;
        }
        stmt.close();
    }

    /**
     * Runs a database query and returns results in a ResultSet
     * <p/>
     * @param sql query to be run
     * <p/>
     * @return ResultSet returned by the query
     * <p/>
     * @throws SQLException
     */
    public static ResultSet getResultSet(String sql) throws SQLException
    {
        // System.out.println(sql);
        Statement stmt;
        ResultSet rs = null;
        try
        {
            stmt = getConnection().createStatement();
            stmt.execute(sql);
            rs = stmt.getResultSet();
        }
        catch (Exception ex)
        {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
