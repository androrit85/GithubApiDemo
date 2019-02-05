package tw.assignment.githubapidemo.ui.repositorydetails;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import tw.assignment.githubapidemo.business.usecase.GithubUseCase;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryDetailsPresenterTest {
    @Mock RepositoryDetailsContract.RepositoryDetailsView mView;
    @Mock GithubUseCase mGithubUseCase;
    private RepositoryDetailsPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new RepositoryDetailsPresenter(mGithubUseCase);
        mPresenter.attachView(mView);
    }
}
