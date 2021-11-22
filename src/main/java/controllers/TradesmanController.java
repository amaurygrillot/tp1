package controllers;

import Users.Tradesman;
import Users.User;
import repositories.RepositoryEntitiesManager;
import repositories.UserRepository;

public class TradesmanController implements UserController{
    private final RepositoryEntitiesManager repositoryEntitiesManager;
    private final static String className = "TradesmanRepository";
    public TradesmanController() {
        this.repositoryEntitiesManager = RepositoryEntitiesManager.getInstance();
    }

    @Override
    public Tradesman getOne(String id) {
        return (Tradesman) this.repositoryEntitiesManager.getRepository(className).getOne(id);
    }

    @Override
    public Tradesman[] getAll() {
        return (Tradesman[]) this.repositoryEntitiesManager.getRepository(className).getAll();
    }

    @Override
    public void insert(Object object) {
        this.repositoryEntitiesManager.getRepository(className).insert(object);
    }

    @Override
    public void update(Object object, String id) {
        this.repositoryEntitiesManager.getRepository(className).update(object,id);
    }

    @Override
    public void delete(String id) {
        this.repositoryEntitiesManager.getRepository(className).delete(id);
    }

    @Override
    public void verifyUser(String id, boolean verified) {
        ((UserRepository)this.repositoryEntitiesManager.getRepository(className)).verifyUser(id, verified);
    }
}
