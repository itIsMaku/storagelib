package lib.matherion.storage.database.object;

public class SQLCondition<T> {

    private final String key;
    private final T value;

    public SQLCondition(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}