package lib.matherion.storage.database.type;

import com.github.jasync.sql.db.Connection;
import com.github.jasync.sql.db.QueryResult;
import com.github.jasync.sql.db.RowData;
import lib.matherion.storage.properties.PropertiesBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MySQL extends JDBC {

    private static MySQL instance;

    public MySQL(String address, int port, String database, String username, String password, boolean useSSL, boolean autoReconnect) {
        super("jdbc:mysql://" + address + ":" + port + "/" + database + "?autoReconnect=" + autoReconnect + "&useSSL=" + useSSL + "&username=" + username + "&password=" + password);
        instance = this;
    }

    /**
     *
     *  Valid properties: address, port, database, username, password, ssl, autoreconnect
     *
     */
    public MySQL(PropertiesBuilder propertiesBuilder) {
        this(
                (String) propertiesBuilder.get("address"),
                (int) propertiesBuilder.get("port"),
                (String) propertiesBuilder.get("database"),
                (String) propertiesBuilder.get("username"),
                (String) propertiesBuilder.get("password"),
                (boolean) propertiesBuilder.get("ssl"),
                (boolean) propertiesBuilder.get("autoreconnect")
        );
    }

    public MySQL(Connection connection) {
        super(connection);
        instance = this;
    }

    public List<RowData> query(String table, String sql, Object... objects) {
        sql = sql.replace("{table}", table);
        try {
            CompletableFuture<QueryResult> st = getConnection().sendPreparedStatement(sql, Arrays.asList(objects));
            return st.get().getRows();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static MySQL getApi() {
        return instance;
    }
}
