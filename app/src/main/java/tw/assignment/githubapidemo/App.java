package tw.assignment.githubapidemo;

import android.app.Application;
import tw.assignment.githubapidemo.di.components.AppComponent;
import tw.assignment.githubapidemo.di.components.DaggerAppComponent;
import tw.assignment.githubapidemo.di.modules.AppModule;


public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        getApplicationComponent().inject(this);
        super.onCreate();
    }

    public AppComponent getApplicationComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return mAppComponent;
    }
}
