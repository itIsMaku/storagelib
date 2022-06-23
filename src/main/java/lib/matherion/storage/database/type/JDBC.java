package lib.matherion.storage.database.type;

import com.github.jasync.sql.db.Connection;
import com.github.jasync.sql.db.mysql.MySQLConnectionBuilder;
import lib.matherion.storage.database.Database;

public class JDBC implements Database<Connection> {

    private final String url;
    private Connection connection;

    public JDBC(String url) {
        this.url = url;
    }

    public JDBC(Connection connection) {
        this.url = null;
        this.connection = connection;
    }

    @Override
    public boolean connect() {
        if (!isConnected()) {
            if (url != null) {
                connection = MySQLConnectionBuilder.createConnectionPool(url);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }

    @Override
    public void disconnect() {
        if (isConnected()) {
            connection.disconnect();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
