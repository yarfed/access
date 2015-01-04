package model;

import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 28.12.2014.
 */
public class TableModelImpl implements javax.swing.table.TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private DaoAccess tableDao;

    public TableModelImpl(DaoAccess tableDao) {
        this.tableDao= tableDao;
    }
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }
    public Class<?> getColumnClass(int columnIndex) {
        return tableDao.getColumnClass(columnIndex);
    }
    public int getColumnCount() {
        return tableDao.getColumnCount();
    }
    public String getColumnName(int columnIndex) {
      return tableDao.getColumnName(columnIndex);
    }
    public int getRowCount() {
        return tableDao.getRowCount();
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
   Object val = tableDao.getValueAt(rowIndex,columnIndex);
     if (val==null) {return null;}
     if (val.equals("Null")) {return null;}
        return val;
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    }

}
