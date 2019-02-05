package tw.assignment.githubapidemo.di.components;

import tw.assignment.githubapidemo.di.modules.ActivityModule;
import tw.assignment.githubapidemo.di.scopes.ActivityScope;
import tw.assignment.githubapidemo.ui.githubrepositories.GithubRepositoryActivity;
import tw.assignment.githubapidemo.ui.repositorydetails.RepositoryDetailsActivity;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(GithubRepositoryActivity githubRepositoryActivity);

    void inject(RepositoryDetailsActivity repositoryDetailsActivity);
}
