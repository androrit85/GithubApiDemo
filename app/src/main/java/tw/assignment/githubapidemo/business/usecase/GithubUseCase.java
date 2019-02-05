package tw.assignment.githubapidemo.business.usecase;

import tw.assignment.githubapidemo.business.model.RepositoryDetailsDomainModel;
import tw.assignment.githubapidemo.business.model.RepositoryDomainModel;
import tw.assignment.githubapidemo.business.repository.GithubRepository;
import tw.assignment.githubapidemo.network.model.Repository;
import tw.assignment.githubapidemo.network.model.RepositoryDetails;
import tw.assignment.githubapidemo.network.rest.GithubRetrofitAPI;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GithubUseCase {
    private final GithubRetrofitAPI mGitHubRetrofitAPI;
    private final GithubRepository mGithubRepository;

    @Inject
    public GithubUseCase(GithubRetrofitAPI mGitHubRetrofitAPI, GithubRepository mGithubRepository) {
        this.mGitHubRetrofitAPI = mGitHubRetrofitAPI;
        this.mGithubRepository = mGithubRepository;
    }

    public Single<List<RepositoryDomainModel>> getAllRepositories() {
        return mGitHubRetrofitAPI.getAllRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess(new Consumer<List<Repository>>() {
                    @Override
                    public void accept(List<Repository> repositoryList) throws Exception {
                        mGithubRepository.cacheAllLocalRepositories(repositoryList);
                    }
                })
                .map(new Function<List<Repository>, List<RepositoryDomainModel>>() {
                    @Override
                    public List<RepositoryDomainModel> apply(final List<Repository> repositories) throws Exception {
                        return RepositoryDomainModel.fromAllRepositories(repositories);
                    }
                });
    }

    public Single<RepositoryDetailsDomainModel> getRepositoryDetails(String repositoryFullName) {
        return mGitHubRetrofitAPI.getRepositoryDetails(repositoryFullName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<RepositoryDetails, RepositoryDetailsDomainModel>() {
                    @Override
                    public RepositoryDetailsDomainModel apply(RepositoryDetails repositoryDetails)
                            throws Exception {
                        return RepositoryDetailsDomainModel.fromRepositoryDetails(repositoryDetails);
                    }
                });
    }

    public boolean shouldGetAllRepositories() {
        return Math.abs(mGithubRepository.getSavedTime() - System.currentTimeMillis()) > 1800000;
    }
}
