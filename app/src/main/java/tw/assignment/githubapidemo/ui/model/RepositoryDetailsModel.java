package tw.assignment.githubapidemo.ui.model;

import tw.assignment.githubapidemo.business.model.RepositoryDetailsDomainModel;

public class RepositoryDetailsModel {
    private String mAuthorName;
    private String mAuthorImageUrl;
    private String mRepositoryName;
    private String mRepositoryDescription;
    private int mRepositoryStargazers;
    private int mRepositoryWatchers;
    private int mRepositoryForks;
    private int mRepositoryOpenIssues;

    public RepositoryDetailsModel(RepositoryDetailsDomainModel repositoryDetailsDomainModel) {
        mAuthorName = repositoryDetailsDomainModel.getAuthorName();
        mAuthorImageUrl = repositoryDetailsDomainModel.getAuthorImageUrl();
        mRepositoryName = repositoryDetailsDomainModel.getRepositoryName();
        mRepositoryDescription = repositoryDetailsDomainModel.getRepositoryDescription();
        mRepositoryStargazers = repositoryDetailsDomainModel.getRepositoryStargazers();
        mRepositoryWatchers = repositoryDetailsDomainModel.getRepositoryWatchers();
        mRepositoryForks = repositoryDetailsDomainModel.getRepositoryForks();
        mRepositoryOpenIssues = repositoryDetailsDomainModel.getRepositoryOpenIssues();
    }

    public String getAuthorName() {
        return mAuthorName;
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

    public int getRepositoryStargazers() {
        return mRepositoryStargazers;
    }

    public int getRepositoryWatchers() {
        return mRepositoryWatchers;
    }

    public int getRepositoryForks() {
        return mRepositoryForks;
    }

    public int getRepositoryOpenIssues() {
        return mRepositoryOpenIssues;
    }
}
