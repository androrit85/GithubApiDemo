package tw.assignment.githubapidemo.ui;


import tw.assignment.githubapidemo.App;
import tw.assignment.githubapidemo.di.components.ActivityComponent;
import tw.assignment.githubapidemo.di.modules.ActivityModule;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected ActivityComponent getControllerComponent() {
        return ((App) getApplication()).getApplicationComponent()
                .newActivityComponent(new ActivityModule(this));
    }
}
