package tw.assignment.githubapidemo.business.model;

import tw.assignment.githubapidemo.network.model.RepositoryDetails;

public class RepositoryDetailsDomainModel {
    private String mAuthorName;
    private String mAuthorImageUrl;
    private String mRepositoryName;
    private String mRepositoryDescription;
    private int mRepositoryStargazers;
    private int mRepositoryWatchers;
    private int mRepositoryForks;
    private int mRepositoryOpenIssues;

    public RepositoryDetailsDomainModel(RepositoryDetails repositoryDetails) {
        mAuthorName = repositoryDetails.getOwner().getLogin();
        mAuthorImageUrl = repositoryDetails.getOwner().getAvatarUrl();
        mRepositoryName = repositoryDetails.getName();
        mRepositoryDescription = repositoryDetails.getDescription();
        mRepositoryStargazers = repositoryDetails.getStargazersCount();
        mRepositoryWatchers = repositoryDetails.getWatchersCount();
        mRepositoryForks = repositoryDetails.getForksCount();
        mRepositoryOpenIssues = repositoryDetails.getOpenIssuesCount();
    }

    public static RepositoryDetailsDomainModel fromRepositoryDetails(RepositoryDetails repositoryDetails) {
        return new RepositoryDetailsDomainModel(repositoryDetails);
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
