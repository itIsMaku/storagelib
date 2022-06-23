package lib.matherion.storage.database;

public interface Database<T> {

    boolean connect();

    void disconnect();

    boolean isConnected();

    T getConnection();
}
