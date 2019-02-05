package tw.assignment.githubapidemo.di.components;

import tw.assignment.githubapidemo.App;
import tw.assignment.githubapidemo.di.modules.ActivityModule;
import tw.assignment.githubapidemo.di.modules.AppModule;
import tw.assignment.githubapidemo.di.scopes.ApplicationScope;

import dagger.Component;


@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App mainApplication);

    ActivityComponent newActivityComponent(ActivityModule activityModule);
}
