package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class DatabaseManager {
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3308/projet_database?serverTimezone=UTC";
    private ResultSet queryResult;
    private PreparedStatement statement;//permet d'éviter les injections SQL
    //il échappe les char spéciaux automatiquement
    private Connection con;
    private static DatabaseManager databaseManager;
    public boolean connectionIsOpened;
    public DatabaseManager()
    {
        try
        {
            con = DriverManager.getConnection(url, user, password);
            connectionIsOpened = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            connectionIsOpened = false;
        }

    }

    public static DatabaseManager getInstance()
    {
        if(databaseManager == null)
        {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public ResultSet executeQuery()
    {
        return this.executeQuery("");
    }

    /**
     * only for select queries
     * @param query
     * @return
     */
    public ResultSet executeQuery(String query)
    {
        try
        {
            if(query.length() > 0) {
                statement = con.prepareStatement(query);
            }
            return statement.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * for update, delete and insert queries
     * @param query
     */
    public void executeUpdate(String query)
    {
        try
        {
            if(query.length() > 0) {
                statement = con.prepareStatement(query);
            }
            statement.executeUpdate();
            statement.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void prepareStatement(String query) {
        try {
            statement = con.prepareStatement(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void closeStatement()
    {
        try
        {
            statement.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public void setInt(int index, int value)
    {
        try {
            statement.setInt(index,value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void setString(int index, String value)
    {
        try {
            statement.setString(index,value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void setDate(int index, LocalDate value)
    {
        try {
            statement.setString(index,value.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void closeConnection()
    {
        try
        {
            con.close();
            connectionIsOpened = false;
            System.out.println("Connection fermée");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
