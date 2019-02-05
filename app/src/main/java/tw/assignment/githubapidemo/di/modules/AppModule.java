package tw.assignment.githubapidemo.di.modules;

import android.arch.persistence.room.Room;
import tw.assignment.githubapidemo.App;
import tw.assignment.githubapidemo.business.repository.RepositoryDatabase;
import tw.assignment.githubapidemo.di.scopes.ApplicationScope;
import tw.assignment.githubapidemo.network.rest.GithubRetrofitAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {
    private static final String BASE_URL = "https://api.github.com/";
    private static final String DATABASE_NAME = "github_db";
    private final App mApp;

    public AppModule(App mainApplication) {
        mApp = mainApplication;
    }

    @Provides
    @ApplicationScope
    App getApplication() {
        return mApp;
    }

    @Provides
    @ApplicationScope
    RepositoryDatabase provideRoomRepositoryDatabase() {
        return Room.databaseBuilder(mApp.getApplicationContext(), RepositoryDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @ApplicationScope
    GithubRetrofitAPI provideBusinessService(Gson gson) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient()
                .newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(interceptor);
        OkHttpClient client = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppModule.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(GithubRetrofitAPI.class);
    }

    @Provides
    @ApplicationScope
    public Gson provideGson() {
        return new GsonBuilder()
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }
}
