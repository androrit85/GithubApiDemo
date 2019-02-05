package tw.assignment.githubapidemo.business.usecase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import tw.assignment.githubapidemo.business.repository.GithubRepository;
import tw.assignment.githubapidemo.network.model.Repository;
import tw.assignment.githubapidemo.network.rest.GithubRetrofitAPI;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GithubUseCaseTest {

    @Mock GithubRetrofitAPI mGitHubRetrofitAPI;
    @Mock GithubRepository mGithubRepository;
    private GithubUseCase mGithubUseCase;


    @Before
    public void setup() {
        mGithubUseCase = new GithubUseCase(mGitHubRetrofitAPI,
                mGithubRepository);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(h -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(h -> Schedulers.trampoline());
    }

    @Test
    public void getAllRepositories_givenRequestIsSuccessful_returnAllRepositories() {
        final List<Repository> repositoryList = new ArrayList<>();
        when(mGitHubRetrofitAPI.getAllRepositories()).thenReturn(Single.just(repositoryList));

        mGithubUseCase.getAllRepositories()
                .test()
                .assertNoErrors()
                .assertComplete();
    }

    @Test
    public void getAllRepositories_givenRequestIsNotSuccesful_doNotReturnAllRepositories() {
        final Throwable throwable = new Throwable();

        when(mGitHubRetrofitAPI.getAllRepositories()).thenReturn(Single.<List<Repository>>error(throwable));

        mGithubUseCase.getAllRepositories()
                .test()
                .assertError(throwable)
                .assertNotComplete();
    }
}
