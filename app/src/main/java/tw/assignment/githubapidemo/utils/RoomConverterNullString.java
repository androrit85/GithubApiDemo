package tw.assignment.githubapidemo.utils;

import android.arch.persistence.room.TypeConverter;

public class RoomConverterNullString {
    @TypeConverter
    public static String fromNullToString(String value) {
        if (value == null) {
            return "";
        } else {
            return value;
        }
    }
}
