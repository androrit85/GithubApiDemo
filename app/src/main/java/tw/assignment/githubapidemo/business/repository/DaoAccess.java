package tw.assignment.githubapidemo.business.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import tw.assignment.githubapidemo.network.model.Repository;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoAccess {

    @Insert(onConflict = REPLACE)
    void addAllRepositories(List<Repository> repositoryList);

    @Query("SELECT * FROM repository")
    Flowable<List<Repository>> getAllCachedRepositories();

    @Query("DELETE FROM repository")
    public void deleteAllRepositories();
}
