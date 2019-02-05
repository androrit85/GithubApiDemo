package tw.assignment.githubapidemo.utils;

import android.arch.persistence.room.TypeConverter;
import tw.assignment.githubapidemo.network.model.Owner;


public class OwnerTypeConverter {

    @TypeConverter
    public static String fromOwner(Owner owner) {
        if (owner == null) {
            return (null);
        }
        return (String.format("%s,%s,%s", owner.getAvatarUrl(), owner.getFollowersUrl(), owner.getLogin()));
    }

    @TypeConverter
    public static Owner toOwner(String owner) {
        if (owner == null) {
            return (null);
        }
        String[] pieces = owner.split(",");
        Owner result = new Owner();
        result.setAvatarUrl(pieces[0]);
        result.setFollowersUrl(pieces[1]);
        result.setLogin(pieces[2]);
        return (result);
    }
}
