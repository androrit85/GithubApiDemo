package tw.assignment.githubapidemo.network.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import tw.assignment.githubapidemo.utils.RoomConverterNullString;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "owner")
public class Owner {
    @PrimaryKey(autoGenerate = true) private int mId;
    @SerializedName("login") @Expose @TypeConverters(RoomConverterNullString.class) private String mLogin;
    @SerializedName("avatar_url") @Expose @TypeConverters(RoomConverterNullString.class) private String mAvatarUrl;
    @SerializedName("followers_url") @TypeConverters(RoomConverterNullString.class) private String mFollowersUrl;

    public String getLogin() {
        return mLogin;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getFollowersUrl() {
        return mFollowersUrl;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        mFollowersUrl = followersUrl;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }
}
