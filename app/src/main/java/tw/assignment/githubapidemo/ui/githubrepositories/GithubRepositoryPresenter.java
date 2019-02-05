package tw.assignment.githubapidemo.ui.githubrepositories;

import tw.assignment.githubapidemo.business.model.RepositoryDomainModel;
import tw.assignment.githubapidemo.business.repository.GithubRepository;
import tw.assignment.githubapidemo.business.usecase.GithubUseCase;
import tw.assignment.githubapidemo.ui.model.RepositoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GithubRepositoryPresenter
        implements GithubRepositoryContract.RepositoryActionListener<GithubRepositoryContract.RepositoryView> {

    private GithubRepositoryContract.RepositoryView mView;
    private final GithubUseCase mGithubUseCase;
    private final GithubRepository mGithubRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    public GithubRepositoryPresenter(GithubUseCase mGithubUseCase,
                                     GithubRepository mGithubRepository) {
        this.mGithubUseCase = mGithubUseCase;
        this.mGithubRepository = mGithubRepository;
    }

    @Override
    public void getAllRepositories() {
        final boolean shouldgetAllRepositories = mGithubUseCase.shouldGetAllRepositories();
        final Disposable disposable;
        if (shouldgetAllRepositories) {
            disposable = getAllGitHubRepositories();
        } else {
            disposable = getAllCachedRepositories();
        }
        mCompositeDisposable.add(disposable);
    }

    private Disposable getAllGitHubRepositories() {
        return mGithubUseCase.getAllRepositories()
                .subscribe(new Consumer<List<RepositoryDomainModel>>() {
                    @Override
                    public void accept(List<RepositoryDomainModel> repositoryDomainModelList)
                            throws Exception {
                        mGithubRepository.saveCurrentTime(System.currentTimeMillis());
                        mView.showAllRepositories(fromRepositoryDomainModel(repositoryDomainModelList));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //Should create a custom Logger to be able to test it
                    }
                });
    }

    private Disposable getAllCachedRepositories() {
        return mGithubRepository.getAllCachedRepositories()
                .subscribe(new Consumer<List<RepositoryDomainModel>>() {
                    @Override
                    public void accept(List<RepositoryDomainModel> repositoryDomainModelList)
                            throws Exception {
                        mView.showAllRepositories(fromRepositoryDomainModel(repositoryDomainModelList));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void openRepositoryDetailsActivity(String repositoryName) {
        mView.showRepositoryDetailsActivity(repositoryName);
    }

    @Override
    public void attachView(GithubRepositoryContract.RepositoryView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mCompositeDisposable.clear();
    }

    private List<RepositoryModel> fromRepositoryDomainModel(List<RepositoryDomainModel> repositoryDomainModelList) {
        final List<RepositoryModel> repositoryModelList = new ArrayList<>();
        for (RepositoryDomainModel repositoryDomainModel : repositoryDomainModelList) {
            repositoryModelList.add(new RepositoryModel(repositoryDomainModel));
        }
        return repositoryModelList;
    }
}
