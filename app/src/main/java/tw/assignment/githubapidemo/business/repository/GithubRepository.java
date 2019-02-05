package tw.assignment.githubapidemo.business.repository;

import tw.assignment.githubapidemo.business.model.RepositoryDomainModel;
import tw.assignment.githubapidemo.network.model.Repository;
import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GithubRepository {
    private SharedPreferences mSharedPreferences;
    private RepositoryDatabase mRepositoryDatabase;

    @Inject
    public GithubRepository(SharedPreferences sharedPreferences,
                            RepositoryDatabase repositoryDatabase) {
        mSharedPreferences = sharedPreferences;
        mRepositoryDatabase = repositoryDatabase;
    }

    public void cacheAllLocalRepositories(final List<Repository> repositoryList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mRepositoryDatabase.daoAccess().deleteAllRepositories();
                mRepositoryDatabase.daoAccess().addAllRepositories(repositoryList);
            }
        }).start();
    }

    public Flowable<List<RepositoryDomainModel>> getAllCachedRepositories() {
        return mRepositoryDatabase.daoAccess().getAllCachedRepositories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<Repository>, List<RepositoryDomainModel>>() {
                    @Override
                    public List<RepositoryDomainModel> apply(final List<Repository> repositories) throws Exception {
                        return RepositoryDomainModel.fromAllRepositories(repositories);
                    }
                });
    }

    public void saveCurrentTime(long currentTime) {
        mSharedPreferences.edit().putLong("CURRENT_TIME", currentTime).apply();
    }

    public long getSavedTime() {
        return mSharedPreferences.getLong("CURRENT_TIME", 0);
    }
}

