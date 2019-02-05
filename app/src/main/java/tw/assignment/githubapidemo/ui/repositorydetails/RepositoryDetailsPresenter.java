package tw.assignment.githubapidemo.ui.repositorydetails;

import tw.assignment.githubapidemo.business.model.RepositoryDetailsDomainModel;
import tw.assignment.githubapidemo.business.usecase.GithubUseCase;
import tw.assignment.githubapidemo.ui.model.RepositoryDetailsModel;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class RepositoryDetailsPresenter
        implements RepositoryDetailsContract.RepositoryDetailsActionListener<RepositoryDetailsContract.RepositoryDetailsView> {
    private static String TAG = "RepositoryDetailsPresenter";
    private RepositoryDetailsContract.RepositoryDetailsView mView;
    private final GithubUseCase mGitHubUseCase;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    public RepositoryDetailsPresenter(GithubUseCase gitHubUseCase) {
        mGitHubUseCase = gitHubUseCase;
    }

    @Override
    public void attachView(RepositoryDetailsContract.RepositoryDetailsView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mCompositeDisposable.clear();
    }

    @Override
    public void getRepositoryDetails(String repositoryFullName) {
        Disposable disposable = mGitHubUseCase.getRepositoryDetails(repositoryFullName)
                .subscribe(new Consumer<RepositoryDetailsDomainModel>() {
                    @Override
                    public void accept(RepositoryDetailsDomainModel repositoryDetailsDomainModel)
                            throws Exception {
                        mView.showRepositoryDetails(new RepositoryDetailsModel(repositoryDetailsDomainModel));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
