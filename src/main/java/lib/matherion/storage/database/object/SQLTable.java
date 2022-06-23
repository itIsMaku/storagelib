package lib.matherion.storage.database.object;

import lib.matherion.storage.data.DataUtil;
import lib.matherion.storage.database.type.JDBC;
import lib.matherion.storage.database.type.MySQL;

import java.util.HashMap;
import java.util.Map;

public class SQLTable {

    private final String name;
    private final Map<String, Class<?>> columns;

    public SQLTable(String name) {
        this(name, new HashMap<>());
    }

    public SQLTable(String name, Map<String, Class<?>> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public Map<String, Class<?>> getColumns() {
        return columns;
    }

    public void addColumn(String key, Class<?> value) {
        columns.put(key, value);
    }

    public <T, C> void update(String key, T value, SQLCondition<C> condition) {
        MySQL.getApi().query(
                name, "UPDATE {table} SET " + key + " = ? WHERE " + condition.getKey() + " = ?;", DataUtil.format(value), DataUtil.format(condition.getValue()));
    }

    public void create(JDBC jdbc) {
        if (jdbc instanceof MySQL) {
            StringBuilder columnsString = null;

            for (String key : columns.keySet()) {
                Class<?> type = columns.get(key);
                String addon = key;
                String simpleName = type.getSimpleName().toLowerCase();
                switch (simpleName) {
                    case "string":
                        addon = key + " varchar(512)";
                        break;
                    case "id":
                        addon = key + " int NOT NULL primary key AUTO_INCREMENT";
                        break;
                    case "timestamp":
                        addon = key + " TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP";
                        break;
                    case "integer":
                        addon = key + " int";
                        break;
                }
                if (columnsString == null) {
                    columnsString = new StringBuilder("(" + addon);
                } else {
                    columnsString.append(", ").append(addon);
                }
            }
            columnsString.append(")");
            MySQL.getApi().query(" ", "CREATE TABLE IF NOT EXISTS " + name + " " + columnsString + ";");

        }
    }

}
