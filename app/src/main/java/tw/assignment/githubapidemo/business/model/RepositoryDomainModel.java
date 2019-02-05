package tw.assignment.githubapidemo.business.model;

import tw.assignment.githubapidemo.network.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryDomainModel {
    private String mName;
    private String mAuthorImageUrl;
    private String mRepositoryName;
    private String mRepositoryDescription;
    private String mRepositoryFullName;

    public RepositoryDomainModel(Repository repository) {
        mName = repository.getOwner().getLogin();
        mAuthorImageUrl = repository.getOwner().getAvatarUrl();
        mRepositoryName = repository.getName();
        mRepositoryDescription = repository.getDescription();
        mRepositoryFullName = repository.getFullName();
    }

    public static List<RepositoryDomainModel> fromAllRepositories(List<Repository> repositoriesList) {
        final List<RepositoryDomainModel> repositoryDomainModelList = new ArrayList<>();
        for (Repository repository : repositoriesList) {
            repositoryDomainModelList.add(new RepositoryDomainModel(repository));
        }
        return repositoryDomainModelList;
    }

    public String getName() {
        return mName;
    }

    public String getAuthorImageUrl() {
        return mAuthorImageUrl;
    }

    public String getRepositoryName() {
        return mRepositoryName;
    }

    public String getRepositoryDescription() {
        return mRepositoryDescription;
    }

    public String getRepositoryFullName() {
        return mRepositoryFullName;
    }
}
