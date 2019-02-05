package tw.assignment.githubapidemo.network.rest;

import tw.assignment.githubapidemo.network.model.Repository;
import tw.assignment.githubapidemo.network.model.RepositoryDetails;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubRetrofitAPI {
    @GET("repositories")
    Single<List<Repository>> getAllRepositories();

    @GET("repos/{repositoryFullName}")
    Single<RepositoryDetails> getRepositoryDetails(@Path(value = "repositoryFullName", encoded = true)
                                                           String repositoryFullName);
}
