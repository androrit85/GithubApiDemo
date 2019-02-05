package tw.assignment.githubapidemo.business.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import tw.assignment.githubapidemo.network.model.Owner;
import tw.assignment.githubapidemo.network.model.Repository;

@Database(entities = {Repository.class, Owner.class}, version = 1, exportSchema = false)
public abstract class RepositoryDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();

}
