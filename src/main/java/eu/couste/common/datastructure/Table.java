package eu.couste.common.datastructure;

import java.util.*;


public class Table {

    private List<String> columnsName;
    private Map<String, Class<?>> columns;
    private List<Map<String, Object>> rows;

    public Table() {
        this.columns = new HashMap<>();
        this.rows = new ArrayList<>();
    }

    public void addColumn(String variableName, Class<?> type) {
        this.columns.put(variableName, type);
        this.columnsName.add(variableName);
    }

    public int newRow() {
        this.rows.add(new HashMap<>());
        return this.rows.size() - 1;
    }

    public void addValue(int rowIndex, String key, Object value) {
        this.rows.get(rowIndex).put(key, value);
    }

    @Override
    public String toString() {
        String table = columns.toString();
        for (Map<String, Object> m : rows) {
            table += "\n";
            table += m;
        }
        return table;
    }
}
