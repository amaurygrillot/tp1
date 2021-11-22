package repositories;

import Users.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserRepository extends Repository{

    @Override
    User getOne(String id);

    @Override
    User[] getAll();


    public void verifyUser(String id, boolean verified);

    public User resultSetToUser(ResultSet resultSet);
}
