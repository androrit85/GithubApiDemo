package tw.assignment.githubapidemo.ui.model;

import tw.assignment.githubapidemo.business.model.RepositoryDomainModel;

public class RepositoryModel {
    private String mAuthorImageUrl;
    private String mAuthorName;
    private String mRepositoryName;
    private String mRepositoryDescription;
    private String mRepositoryFullName;

    public RepositoryModel(RepositoryDomainModel repositoryDomainModel) {
        mAuthorImageUrl = repositoryDomainModel.getAuthorImageUrl();
        mAuthorName = repositoryDomainModel.getName();
        mRepositoryName = repositoryDomainModel.getRepositoryName();
        mRepositoryDescription = repositoryDomainModel.getRepositoryDescription();
        mRepositoryFullName = repositoryDomainModel.getRepositoryFullName();
    }

    public String getAuthorImageUrl() {
        return mAuthorImageUrl;
    }

    public String getAuthorName() {
        return mAuthorName;
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
