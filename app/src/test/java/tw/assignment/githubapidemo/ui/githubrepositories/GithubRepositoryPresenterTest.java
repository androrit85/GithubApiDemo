package tw.assignment.githubapidemo.ui.githubrepositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import tw.assignment.githubapidemo.business.model.RepositoryDomainModel;
import tw.assignment.githubapidemo.business.repository.GithubRepository;
import tw.assignment.githubapidemo.business.usecase.GithubUseCase;
import tw.assignment.githubapidemo.network.model.Owner;
import tw.assignment.githubapidemo.network.model.Repository;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GithubRepositoryPresenterTest {

    @Mock GithubUseCase mGithubUseCase;
    @Mock GithubRepository mGithubRepository;
    @Mock GithubRepositoryContract.RepositoryView mView;
    private GithubRepositoryPresenter mGithubRepositoryPresenter;

    @Before
    public void setup() {
        mGithubRepositoryPresenter = new GithubRepositoryPresenter(mGithubUseCase,
                mGithubRepository);
        mGithubRepositoryPresenter.attachView(mView);
    }

    @Test
    public void getAllRepositories_givenRequestSuccess_returnAllRepositoriesList() {
        final Repository repository = new Repository();
        final Owner owner = new Owner();
        owner.setAvatarUrl("https://api.github.com/avatarUrl");
        owner.setLogin("riteshm927");
        owner.setFollowersUrl("https://api.github.com/followersUrl");
        repository.setDescription("Any Description");
        repository.setFullName("Ritesh Manapure");
        repository.setName("riteshmanapure");
        repository.setId(1);
        repository.setOwner(owner);
        final RepositoryDomainModel repositoryDetailsDomainModel = new RepositoryDomainModel(repository);
        final List<RepositoryDomainModel> repositoryDomainModelList = new ArrayList<>();
        repositoryDomainModelList.add(repositoryDetailsDomainModel);

        when(mGithubUseCase.shouldGetAllRepositories()).thenReturn(true);
        when(mGithubUseCase.getAllRepositories()).thenReturn(Single.just(repositoryDomainModelList));

        mGithubRepositoryPresenter.getAllRepositories();

        verify(mView).showAllRepositories(anyList());
    }

    @Test
    public void getAllRepositories_givenRequestFailure_doNotReturnAllRepositoriesList() {
        when(mGithubUseCase.shouldGetAllRepositories()).thenReturn(true);
        when(mGithubUseCase.getAllRepositories()).thenReturn(Single.<List<RepositoryDomainModel>>error(new Throwable()));

        mGithubRepositoryPresenter.getAllRepositories();

        verify(mView, never()).showAllRepositories(anyList());
    }

    @Test
    public void openRepositoryDetailsActivity_showRepositoryDetailsActivity() {
        mGithubRepositoryPresenter.openRepositoryDetailsActivity(anyString());

        verify(mView).showRepositoryDetailsActivity(anyString());
    }
}
