package repositories;

import Database.DatabaseManager;
import Users.Contractor;
import Users.Tradesman;
import Users.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TradesmanRepository implements UserRepository{

    private final String tableName = "tradesman";
    @Override
    public Tradesman getOne(String id) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        if(databaseManager.connectionIsOpened)
        {
            databaseManager.prepareStatement("SELECT * FROM " + this.tableName + " WHERE ID = ?");
            databaseManager.setString(1,id);
            ResultSet resultSet = databaseManager.executeQuery();
            return this.resultSetToUser(resultSet);
        }
        return null;
    }

    @Override
    public Tradesman[] getAll() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        if(databaseManager.connectionIsOpened)
        {
            databaseManager.prepareStatement("SELECT * FROM " + this.tableName);
            ResultSet resultSet = databaseManager.executeQuery();
            List<Tradesman> tradesmanArrayList = new ArrayList<>();
            try {
                while (!resultSet.isLast()) {
                    tradesmanArrayList.add(this.resultSetToUser(resultSet));
                }
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
                return null;
            }
            Tradesman[] tradesmen = {};
            return tradesmanArrayList.toArray(tradesmen);
        }
        return null;
    }

    @Override
    public void insert(Object tradesman) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        if(databaseManager.connectionIsOpened && checkUserType(tradesman))
        {
            databaseManager.prepareStatement("INSERT INTO " + this.tableName + " VALUES (?,?)");
            this.prepareUpdateStatement((Contractor) tradesman, databaseManager);
            databaseManager.executeQuery();
        }
    }

    @Override
    public void update(Object tradesman, String id) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        if(databaseManager.connectionIsOpened && checkUserType(tradesman))
        {
            databaseManager.prepareStatement("UPDATE " + this.tableName + " SET VALUES (?,?) WHERE id = ?");
            this.prepareUpdateStatement((Contractor) tradesman, databaseManager);
            databaseManager.setString(3, id);
            databaseManager.executeQuery();
        }
    }

    @Override
    public void delete(String id) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        if(databaseManager.connectionIsOpened)
        {
            databaseManager.prepareStatement("DELETE * FROM " + this.tableName + " WHERE id = ?");
            databaseManager.setString(1, id);
            databaseManager.executeQuery();
        }
    }

    @Override
    public void verifyUser(String id, boolean verified) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        if(databaseManager.connectionIsOpened)
        {
            databaseManager.prepareStatement("UPDATE " + this.tableName + " SET verified = ? WHERE id = ?");
            if(verified)
            {
                databaseManager.setString(1, "true");
            }
            else
            {
                databaseManager.setString(1, "false");
            }
            databaseManager.setString(2, id);
            databaseManager.executeQuery();
        }
    }

    @Override
    public Tradesman resultSetToUser(ResultSet resultSet) {
        try {
            if(resultSet.next()) {
                return new Tradesman(resultSet.getString(1), resultSet.getString(2));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
        return null;
    }

    private boolean checkUserType(Object object)
    {
        return object.getClass().getName().equalsIgnoreCase("Tradesman");
    }

    private void prepareUpdateStatement(Contractor contractor, DatabaseManager databaseManager)
    {
        databaseManager.setString(1, contractor.getFirstName());
        databaseManager.setString(2, contractor.getLastName());
    }
}
