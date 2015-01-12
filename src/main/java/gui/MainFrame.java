package gui;


import com.healthmarketscience.jackcess.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;


/**
 * C reated by user on 23.12.2014.
 */
public class MainFrame implements ActionListener ,MouseListener{
    JFrame jfrm;
    JList jlist;
    Object[] tableNames = null;
    JDesktopPane desktopPane;
    Database db=null;
    public static String pathToData;
    public MainFrame() {

       pathToData= "FIP.mdb";

        UIManager.put("Table.gridColor", Color.gray);
        try {
            FileChannel channel = new RandomAccessFile(pathToData,"rw").getChannel();
            db =  new DatabaseBuilder().setChannel(channel).setReadOnly(true).open();


//FileLock lock =channel.tryLock();
            tableNames = db.getTableNames().toArray();
//lock.release();

           //db.close();
            channel.close();
            System.out.println(channel.isOpen());



        } catch (Exception ex) {
            System.out.println(ex);
            tableNames =new String[] {"чтото неправильно с путями к БД"};

        } finally {try {db.close();}catch (IOException ioex){System.out.println(ioex);}}

        jfrm = new JFrame("Access Viewer");

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
        desktopPane.add(new ClaimsForm());

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
            JInternalFrame iframe =new TableFrame(name);
           desktopPane.add(iframe);
            iframe.toFront();
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





