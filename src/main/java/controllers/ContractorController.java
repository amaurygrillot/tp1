package controllers;

import Users.User;
import repositories.RepositoryEntitiesManager;
import repositories.UserRepository;

public class ContractorController implements UserController{

    private RepositoryEntitiesManager repositoryEntitiesManager;
    private final static String className = "TradesmanRepository";

    public ContractorController() {
        this.repositoryEntitiesManager = RepositoryEntitiesManager.getInstance();
    }

    @Override
    public User getOne(String id) {
        return ((UserRepository) this.repositoryEntitiesManager.getRepository(className)).getOne(id);
    }

    @Override
    public User[] getAll() {
        return ((UserRepository) this.repositoryEntitiesManager.getRepository(className)).getAll();
    }

    @Override
    public void insert(Object object) {
        this.repositoryEntitiesManager.getRepository(className).insert((User)object);
    }

    @Override
    public void update(Object object, String id) {
        this.repositoryEntitiesManager.getRepository(className).update((User) object, id);
    }

    @Override
    public void delete(String id) {
        this.repositoryEntitiesManager.getRepository(className).delete(id);
    }

    @Override
    public void verifyUser(String id, boolean verified) {
        ((UserRepository) this.repositoryEntitiesManager.getRepository(className)).verifyUser(id, verified);
    }
}
