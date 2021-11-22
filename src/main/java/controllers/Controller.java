package controllers;

import repositories.Repository;

public interface Controller <O>{
    public O getOne(String id);
    public O[] getAll();
    public void insert(Object object);
    public void update(Object object, String id);
    public void delete(String id);
}
