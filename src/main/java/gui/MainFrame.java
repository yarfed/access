package gui;

import dao.DatabaseActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * C reated by user on 23.12.2014.
 */
public class MainFrame implements ActionListener{


    public MainFrame() {
        JFrame jfrm = new JFrame("Opus");
        Container c = jfrm.getContentPane(); // клиентская область окна
        c.setLayout(new FlowLayout()); // выбираем компоновщик

        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiClose = new JMenuItem("Close");
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmb.add(jmFile);
        JLabel label1=new JLabel("its empty now ");

        jfrm.add(label1);
        jfrm.setJMenuBar(jmb);
        jmiOpen.addActionListener(this );
        jmiClose.addActionListener(this );

//test first  commit


        // желательные размеры окна
        jfrm.setPreferredSize(new Dimension(640, 480));
        // завершить приложение при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack(); // устанавливаем желательные размеры
        jfrm.setVisible(true); // отображаем окно


    }
    public void actionPerformed(ActionEvent event){
        String command=event.getActionCommand();
        System.out.println(command);
        if (command.equals("Open")) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                Main.pathToData = file.getAbsolutePath().toString();
                if (DatabaseActions.open(Main.pathToData)) {

                }
                else {
                }


            }

        }

    }




}
