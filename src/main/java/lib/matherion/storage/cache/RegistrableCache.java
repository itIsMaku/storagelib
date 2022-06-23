package lib.matherion.storage.cache;

public interface RegistrableCache<K, V> {

    V register(K key);

    V unregister(K key);

    void refresh();
}