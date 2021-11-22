package repositories;

import repositories.ContractorRepository;
import repositories.Repository;
import repositories.TradesmanRepository;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryEntitiesManager {
    private File repositoryFolder;
    private static RepositoryEntitiesManager repositoryEntitiesManager;
    private TradesmanRepository tradesmanRepository;
    private ContractorRepository contractorRepository;
    private List<Repository> allRepositories;

    public RepositoryEntitiesManager() {
        this.repositoryFolder = new File("src/main/java/repositories");
        allRepositories = Arrays.asList(
                new TradesmanRepository(),
                new ContractorRepository());
    }

    public static RepositoryEntitiesManager getInstance()
    {
        if(repositoryEntitiesManager == null)
        {
            repositoryEntitiesManager = new RepositoryEntitiesManager();
        }
        return repositoryEntitiesManager;
    }

    public Repository getRepository(String className)
    {
        List<Repository> collectedRepository = allRepositories
                                    .stream()
                                    .filter(repository -> repository.getClass().getName().equalsIgnoreCase(className))
                                    .collect(Collectors.toList());
        if(collectedRepository.size() > 0)
        {
            return collectedRepository.get(0);
        }
        else
        {
            return null;
        }
    }
}
