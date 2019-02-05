package tw.assignment.githubapidemo.di.modules;

import android.app.Activity;
import tw.assignment.githubapidemo.di.scopes.ActivityScope;
import tw.assignment.githubapidemo.ui.githubrepositories.GithubRepositoryAdapter;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @ActivityScope
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    GithubRepositoryAdapter getAllRepositoriesAdapter() {
        return new GithubRepositoryAdapter();
    }

    @Provides
    @ActivityScope
    SharedPreferences getAppPreferences() {
        return mActivity.getSharedPreferences("GitHubSharedPrefs", Context.MODE_PRIVATE);
    }
}
