package gui;


import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import model.TableModelFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;


/**
 * C reated by user on 23.12.2014.
 */
public class MainFrame implements ActionListener ,MouseListener{
    JFrame jfrm;
    JList jlist;
    Object[] tableNames = null;
    JDesktopPane desktopPane;
    public MainFrame() {
        //init

        try {
            Database db = DatabaseBuilder.open(new File("FIP.mdb"));
            tableNames = db.getTableNames().toArray();
            db.close();

        } catch (IOException ex) {
            System.out.println("database not found");
        }

        jfrm = new JFrame("Opus");

        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiClose = new JMenuItem("Close");
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmb.add(jmFile);

        jfrm.setJMenuBar(jmb);
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        desktopPane = new JDesktopPane();

        //  new TableFrame("Заказчики",desktopPane);
        //  new TableFrame("Заявки",desktopPane);
        // new TableFrame("Пошлины",desktopPane);
        JInternalFrame tablesFrm = new JInternalFrame("all Tables", true, true, true, true);
        tablesFrm.getContentPane().setLayout(new BorderLayout());
         jlist= new JList(tableNames);
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setLayoutOrientation(JList.VERTICAL_WRAP);
        jlist.addMouseListener(this);
        JScrollPane jScrollPane = new JScrollPane(jlist);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablesFrm.add(jScrollPane, BorderLayout.CENTER);
        tablesFrm.setVisible(true);
        tablesFrm.setPreferredSize(new Dimension(700, 500));
        desktopPane.add(tablesFrm);
        tablesFrm.pack();


        jfrm.add(desktopPane);
        // желательные размеры окна
        jfrm.setPreferredSize(new Dimension(1024, 768));


        // завершить приложение при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();

        // устанавливаем желательные размеры
        jfrm.setVisible(true);


    }

    public void actionPerformed(ActionEvent event) {

        String command = event.getActionCommand();
        System.out.println(command);
        if (command.equals("Open")) {

        }
    }
    public void mouseClicked(MouseEvent event) {
        if(event.getClickCount()==1) {
            // single click  - срабатывает при любых кликах
        }

        if(event.getClickCount()==2) {
            int index = jlist.getSelectedIndex();
            String name = tableNames[index].toString();
           new TableFrame(name,desktopPane);
        }
    }
    public void mousePressed(MouseEvent event) {
    }
    public void mouseReleased(MouseEvent event) {
    }
    public void mouseEntered(MouseEvent event) {
    }
    public void mouseExited(MouseEvent event) {
    }


}





