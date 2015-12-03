package eu.couste.common.reflexivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import eu.couste.common.datastructure.Table;


public class GetterExplorer {

    private Table table;

    public GetterExplorer() {
        this.table = new Table();
    }

    public void init(Object... objects) {
        for (Object o : objects) {
            this.initTable(o);
        }
    }

    public void initTable(Object object) {
        Class<?> classe = object.getClass();
        Method methods[] = classe.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterCount() == 0) {
                String methodName = method.getName();
                if (methodName.matches("get[A-Z][a-zA-Z]*")) {
                    table.addColumn(methodName.substring(3), method.getReturnType());
                }
            }
        }
    }

    public void addRow(Object... objects) {
        int rowIndex = this.table.newRow();
        for (Object o : objects) {
            this.addRow(rowIndex, o);
        }
    }

    public void addRow(int rowIndex, Object object) {
        Class<?> classe = object.getClass();
        Method methods[] = classe.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterCount() == 0) {
                String methodName = method.getName();
                if (methodName.matches("get[A-Z][a-zA-Z]*")) {
                    String key = methodName.substring(3);
                    Object value = null;
                    try {
                        value = method.invoke(object);
                    } catch (IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    this.table.addValue(rowIndex, key, value);
                }
            }
        }
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
