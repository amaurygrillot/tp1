package controllers;

import Users.User;
import repositories.UserRepository;

public interface UserController extends Controller{

    @Override
    public User getOne(String id);
    @Override
    public User[] getAll();
    public void verifyUser(String id, boolean verified);

}
