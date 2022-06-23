package lib.matherion.storage.properties;

import java.util.HashMap;
import java.util.Map;

public class PropertiesBuilder extends HashMap<String, Object> {

    public PropertiesBuilder put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public PropertiesBuilder put(Map<String, Object> map) {
        super.putAll(map);
        return this;
    }
}
