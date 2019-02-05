package tw.assignment.githubapidemo.network.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import tw.assignment.githubapidemo.utils.RoomConverterNullString;

public class RepositoryDetails {
    @SerializedName("name") @Expose private String mName;
    @SerializedName("full_name") private String mFullName;
    @SerializedName("owner") @Expose private Owner mOwner;
    @SerializedName("description") @Expose private String mDescription;
    @SerializedName("stargazers_count") @Expose private int mStargazersCount;
    @SerializedName("watchers_count") @Expose private int mWatchersCount;
    @SerializedName("forks_count") @Expose private int mForksCount;
    @SerializedName("open_issues_count") @Expose private int mOpenIssuesCount;

    public String getName() {
        return mName;
    }

    public String getFullName() {
        return mFullName;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getStargazersCount() {
        return mStargazersCount;
    }

    public int getWatchersCount() {
        return mWatchersCount;
    }

    public int getForksCount() {
        return mForksCount;
    }

    public int getOpenIssuesCount() {
        return mOpenIssuesCount;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public void setOwner(Owner owner) {
        mOwner = owner;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setStargazersCount(int stargazersCount) {
        mStargazersCount = stargazersCount;
    }

    public void setWatchersCount(int watchersCount) {
        mWatchersCount = watchersCount;
    }

    public void setForksCount(int forksCount) {
        mForksCount = forksCount;
    }

    public void setOpenIssuesCount(int openIssuesCount) {
        mOpenIssuesCount = openIssuesCount;
    }
}
