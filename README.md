# StorageLib

Lib for storage, which using Matherion

## Database
Creating JDBC instance and connection (use method connect()):
`JDBC jdbc = new JDBC(String url, String username, String password`

Creating MySQL instance is similar, but use different parameters. 
`MySQL mysql = new MySQL(String ip, int port, String database, String username, String password, boolean useSSL, boolean autoReconnect)`
MySQL has unique method `query(String table, String sql, Object... objects)` what returns List of SQLRow. In **String sql** use **{table}** for replacing table name.

You can also create own JDBC implementation by extending your class by JDBC class or create own database handler by implementing class Database<T>.

Every class what implements Database<T> has this methods:

`
    boolean connect();
    void disconnect();
    boolean isConnected();
    T getConnection();`

### Other objects
Lib has another objects like SQLCondition or SQLTable for better usage.

## Cache
Creating Redis instance is similar, but you should use different parameters.
`Redis redis = new Redis(String address, int port, String password)`

Also after creating new instance, you should use method `connect()`.

You can again create own Cache implementation by implementing class Cache<T>. 
Cache<T> has this methods:
`   
    boolean connect();
    boolean isConnected();
    void disconnect();
    T getHandler();`
