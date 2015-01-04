package gui;

import model.TableModelRead;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * C reated by user on 23.12.2014.
 */
public class MainFrame implements ActionListener{
    JFrame jfrm;

    public MainFrame() {
        jfrm = new JFrame("Opus");
        //Container c = jfrm.getContentPane(); // клиентская область окна
       // c.setLayout(new BorderLayout()); // выбираем компоновщик
        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiClose = new JMenuItem("Close");
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmb.add(jmFile);

        jfrm.setJMenuBar(jmb);
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this );

        JInternalFrame jfrmInt = new JInternalFrame("internal",true,true,true,true);
        jfrmInt.getContentPane().setLayout(new BorderLayout());
        TableModel model = new TableModelRead("Заявки");
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JDesktopPane desktopPane = new JDesktopPane();

        jfrmInt.add(jScrollPane,BorderLayout.CENTER );
        desktopPane.add(jfrmInt);
        jfrm.add(desktopPane);
        // желательные размеры окна
        jfrm.setPreferredSize(new Dimension(1024, 768));
        jfrmInt.setPreferredSize(new Dimension(640, 480));

        // завершить приложение при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrmInt.pack();
        // устанавливаем желательные размеры
        jfrm.setVisible(true);
        jfrmInt.setVisible(true);
        // отображаем окно


    }
    public void actionPerformed(ActionEvent event){

        String command=event.getActionCommand();
        System.out.println(command);
        if (command.equals("Open")) {



                }
            }
        }







