package model;

import com.healthmarketscience.jackcess.*;
import gui.Main;
import gui.MainFrame;

import javax.swing.event.TableModelListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by user on 29.12.2014.
 */
public class TableModelFactory implements javax.swing.table.TableModel{

    private ArrayList <Object> row;
   private ArrayList<ColumnProperties> columnsProperties;
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private ArrayList <ArrayList<Object>> tableClone;
    private String tableName;
    private Database db;
    private class ColumnProperties {
        private String name;
        private Class clazz;
        private Boolean isForeignData;
        private HashMap<Integer,Object> foreignData;
        private String extTableName;
        private String extTableKey;
        private String extTableData;
    }

    public TableModelFactory(String tableName){
       this.tableName=tableName;
        try {
            db = DatabaseBuilder.open(new File(MainFrame.pathToData.toURI()));
            Table table = db.getTable(tableName);
            columnsProperties = getColumnsProperties(table);
            refreshExternalData();
            refreshData();
            db.close();
        } catch (Exception ex){
            System.out.println("database not found");
        }

    }

    private void refreshData() throws IOException {
        tableClone = new ArrayList<ArrayList<Object>>();
        Table table=db.getTable(tableName);
        for(Row row : table) {
            this.row = new ArrayList<Object>();

            for(Column column: table.getColumns() ) {
                Object val = row.get(column.getName());
                if (val == null) {
                  val = "Null";
                }
                this.row.add(val);
            }
            tableClone.add(this.row);
        }
    }

    private void refreshExternalData() throws IOException {

        for (ColumnProperties columnProperties:columnsProperties) {
            if (columnProperties.isForeignData) {
                Table extTable = db.getTable(columnProperties.extTableName);
                HashMap foreignData = new HashMap<Integer, Object>();
                for (Row row : extTable) {
                    foreignData.put(row.get(columnProperties.extTableKey), row.get(columnProperties.extTableData));
                }
                columnProperties.foreignData = foreignData;
            }
        }
    }

    private  ArrayList<ColumnProperties> getColumnsProperties(Table table) throws IOException {
        ArrayList<ColumnProperties> columnsProperties = new ArrayList<ColumnProperties>();
        for(Column column : table.getColumns()) {
            ColumnProperties columnProperties = new ColumnProperties();
            PropertyMap.Property property = column.getProperties().get("Caption");
            if (property==null) {
                columnProperties.name = column.getName();
            } else {
                columnProperties.name=property.getValue().toString();
            }

            property = column.getProperties().get("RowSourceType");
            if (property==null||!property.getValue().toString().equals("Table/Query")) {
                columnProperties.isForeignData = false;
                columnProperties.clazz = getColumnClass(column);
            } else {
                property=column.getProperties().get("RowSource");
                columnProperties.isForeignData = true;
                String[] foreignSource = getForeignSource(property.getValue().toString());
                columnProperties.extTableName=foreignSource[0];
                columnProperties.extTableKey=foreignSource[1];
                columnProperties.extTableData=foreignSource[3];
                Table extTable = db.getTable(columnProperties.extTableName);

                columnProperties.clazz = getColumnClass(extTable.getColumn(columnProperties.extTableData));
            }
         columnsProperties.add(columnProperties);
        }
        return columnsProperties;
    }

    private Class getColumnClass(Column column) {
        DataType s = column.getType();
        switch(s) {
            case TEXT:
                return String.class;
            case LONG:
                return Integer.class;
            case SHORT_DATE_TIME:
                return Date.class;
            default:
                return String.class;
        }
    }

    private String[] getForeignSource (String string){
        String temp = string.replaceFirst("SELECT DISTINCTROW ", "");
        temp = temp.replaceFirst(" FROM.*","");
       String tempArray[] = temp.split("[,.!?]+");
        for (int i=0; i<tempArray.length; i++){
            tempArray[i]=tempArray[i].replaceAll("[\\[\\]]","").trim();
        }
       return tempArray;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return columnsProperties.get(columnIndex).clazz;
    }

    public int getColumnCount() {
        return columnsProperties.size();
    }

    public String getColumnName(int columnIndex) {
        return columnsProperties.get(columnIndex).name;
    }
    public int getRowCount() {
        return tableClone.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
      Object result = tableClone.get(rowIndex).get(columnIndex);
        if (result.equals("Null")) return null;
        ColumnProperties columnProperties = columnsProperties.get(columnIndex);
        if (columnProperties.isForeignData) {
            return columnProperties.foreignData.get(result);
        }
        return result;
    }
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    }


}
