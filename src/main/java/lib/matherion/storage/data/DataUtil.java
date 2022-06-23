package lib.matherion.storage.data;

import com.google.gson.Gson;

public class DataUtil {

    public static Object format(Object object) {
        if (object instanceof DataSerializable) {
            return serialize(object);
        }
        return object;
    }

    public static String serialize(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        return new Gson().fromJson(json, tClass);
    }

}
