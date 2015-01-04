package gui;

import model.TableModelFactory;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by user on 04.01.2015.
 */
public class TableFrame extends JInternalFrame {

    public TableFrame (String tableName, JDesktopPane desktopPane) {
      super(tableName,true,true,true,true);
        getContentPane().setLayout(new BorderLayout());
        TableModel model = new TableModelFactory(tableName);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(jScrollPane,BorderLayout.CENTER );
        setVisible(true);
        setPreferredSize(new Dimension(640, 480));
       desktopPane.add(this);
        pack();
    }
}
