package model;

import com.healthmarketscience.jackcess.*;

import javax.swing.event.TableModelListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by user on 29.12.2014.
 */
public class TableModelRead implements javax.swing.table.TableModel{
   private String tableName;
    private ArrayList <Object> row;
   private ArrayList<ColumnProperties> columnsProperties;
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private ArrayList <ArrayList<Object>> tableClone;

    public TableModelRead(String tableName){
        this.tableName=tableName;
        try {
            Database db = DatabaseBuilder.open(new File("FIP.mdb"));
            Table table = db.getTable(tableName);

            columnsProperties = new ArrayList<ColumnProperties>();
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
                    HashMap foreignData = new HashMap<Integer,Object>();
                    columnProperties.clazz = getColumnClass(extTable.getColumn(columnProperties.extTableData));
                    for (Row row:extTable){
                       foreignData.put(row.get(columnProperties.extTableKey),row.get(columnProperties.extTableData));
                    }
                    columnProperties.foreignData=foreignData;
                }

             columnsProperties.add(columnProperties);
            }

            tableClone = new ArrayList<ArrayList<Object>>();
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




        } catch (IOException ex){
            System.out.println("database not found");
        }

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

    private class ColumnProperties {
    private String name;
    private Class clazz;
    private Boolean isForeignData;
    private HashMap<Integer,Object> foreignData;
    private String extTableName;
    private String extTableKey;
    private String extTableData;

}
}
