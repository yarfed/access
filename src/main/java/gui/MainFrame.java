package gui;


import javax.swing.*;
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
        JDesktopPane desktopPane = new JDesktopPane();

        new TableFrame("Заказчики",desktopPane);
        new TableFrame("Заявки",desktopPane);
        new TableFrame("Пошлины",desktopPane);



        jfrm.add(desktopPane);
        // желательные размеры окна
        jfrm.setPreferredSize(new Dimension(1024, 768));


        // завершить приложение при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();

        // устанавливаем желательные размеры
        jfrm.setVisible(true);


    }
    public void actionPerformed(ActionEvent event){

        String command=event.getActionCommand();
        System.out.println(command);
        if (command.equals("Open")) {

                }
            }
        }







