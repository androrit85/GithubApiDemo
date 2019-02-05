package tw.assignment.githubapidemo.network.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import tw.assignment.githubapidemo.utils.OwnerTypeConverter;
import tw.assignment.githubapidemo.utils.RoomConverterNullString;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "repository")
public class Repository {
    @PrimaryKey(autoGenerate = true) @SerializedName("id") @TypeConverters(RoomConverterNullString.class) private int mId;
    @SerializedName("name") @Expose @TypeConverters(RoomConverterNullString.class) private String mName;
    @SerializedName("full_name") @Expose @TypeConverters(RoomConverterNullString.class) private String mFullName;
    @SerializedName("owner") @Expose @TypeConverters(OwnerTypeConverter.class) private Owner mOwner;
    @SerializedName("html_url") @TypeConverters(RoomConverterNullString.class) private String mHtmlUrl;
    @SerializedName("description") @Expose @TypeConverters(RoomConverterNullString.class) private String mDescription;
    @SerializedName("url") @Expose @TypeConverters(RoomConverterNullString.class) private String mUrl;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getFullName() {
        return mFullName;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setId(int id) {
        mId = id;
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

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
    }
}
