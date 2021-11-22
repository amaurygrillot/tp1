package repositories;

import Users.User;

import java.util.List;

public interface Repository <O>{

    public O getOne(String id);
    public O[] getAll();
    public void insert(O object);
    public void update(O object, String id);
    public void delete(String id);

}
