package lib.matherion.storage.cache.external;

import lib.matherion.storage.cache.Cache;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis implements Cache<Jedis> {

    private final Jedis jedis;
    private final JedisPool jedisPool;
    private final String password;

    public Redis(String ip, int port, String password) {
        this.password = password;
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(3);
        jedisPoolConfig.setMaxWaitMillis(10000);
        this.jedisPool = new JedisPool(jedisPoolConfig, ip, port);
        this.jedis = jedisPool.getResource();
    }

    public Redis(Jedis jedis, String password) {
        this.jedis = jedis;
        this.jedisPool = jedis.
        this.password = password;
    }

    @Override
    public boolean connect() {
        if (!isConnected()) {
            jedis.connect();
            jedis.auth(password);
            return true;
        }
        return false;
    }

    @Override
    public boolean isConnected() {
        return jedis.isConnected();
    }

    @Override
    public void disconnect() {
        if (isConnected()) {
            jedis.disconnect();
        }
    }

    @Override
    public Jedis getHandler() {
        return jedis;
    }

}