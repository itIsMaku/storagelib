package lib.matherion.storage.cache;

public interface Cache<T> {

    boolean connect();

    boolean isConnected();

    void disconnect();

    T getHandler();
}