package gui;

import model.TableModelFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by user on 06.01.2015.
 */
public class ClaimsForm  extends JInternalFrame{
    private int scale=1;
    private final String L = SpringLayout.WEST;
    private final String  R= SpringLayout.EAST;
    private final String U = SpringLayout.NORTH;
    private final String D = SpringLayout.SOUTH;
    public ClaimsForm (){
        super("Заявки",true,true,true,true);
        getContentPane().setLayout(new BorderLayout());
        SpringLayout sl = new SpringLayout();
        JPanel jPanel=new JPanel(sl);

        JLabel label11 = new JLabel("Заявитель");
        jPanel.add(label11);
        String temp[] ={"ghhjkkkkk","hgggggggggg","and company"};
        JComboBox text11 = new JComboBox();
        text11.setBackground(Color.WHITE);
        jPanel.add(text11);

        JLabel label12 = new JLabel("Код заявки");
        jPanel.add(label12);
        JTextField text12 = new JTextField("1234");
        jPanel.add(text12);
        JTextField text13 = new JTextField("Смитт");
        jPanel.add(text13);

        JLabel label21 = new JLabel("№ п/п");
        jPanel.add(label21);
        JTextField text21 = new JTextField("0");
        jPanel.add(text21);

        JLabel label22 = new JLabel("№Год");
        jPanel.add(label22);
        JTextField text22 = new JTextField("0");
        jPanel.add(text22);

        JLabel label23 = new JLabel("№/месяц");
        jPanel.add(label23);
        JTextField text23 = new JTextField("0");
        jPanel.add(text23);

        JLabel label24 = new JLabel("<html>Расписка/<BR>факс:</html>");
        jPanel.add(label24);
        JTextField text24 = new JTextField(" ");
        jPanel.add(text24);

        JLabel label25 = new JLabel("№ Заявки");
        jPanel.add(label25);
        JTextField text25 = new JTextField("02333222");
        jPanel.add(text25);

        JLabel label26 = new JLabel("внутр_№");
        jPanel.add(label26);
        JTextField text26 = new JTextField(" ");
        jPanel.add(text26);

        JLabel label27 = new JLabel("Приоритет");
        jPanel.add(label27);
        JTextField text27 = new JTextField("11.11.12");
        jPanel.add(text27);

        JLabel label31 = new JLabel("Описание");
        jPanel.add(label31);
        JTextField text31 = new JTextField("");
        jPanel.add(text31);

        JLabel label32 = new JLabel("Тип");
        jPanel.add(label32);
        JTextField text32 = new JTextField("");
        jPanel.add(text32);

        JLabel label33 = new JLabel("Решение");
        jPanel.add(label33);
        JTextField text33 = new JTextField("11.11.12");
        jPanel.add(text33);

     sl.putConstraint(R,jPanel,1100,L,jPanel);
      sl.putConstraint(D,jPanel,400,U,jPanel);

        sl.putConstraint(L,label11,3,L,jPanel);
        sl.putConstraint(U,label11,4,U,jPanel);

        sl.putConstraint(R,text11,550,L,text11);
       sl.putConstraint(L,text11,75,L,jPanel);
        sl.putConstraint(U,text11,3,U,jPanel);
        sl.putConstraint(D,text11,20,U,text11);

        sl.putConstraint(L,label12,744,L,jPanel);
        sl.putConstraint(U,label12,4,U,jPanel);

        sl.putConstraint(R,text12,90,L,text12);
        sl.putConstraint(L,text12,810,L,jPanel);
        sl.putConstraint(U,text12,3,U,jPanel);

        sl.putConstraint(R,text13,110,L,text13);
        sl.putConstraint(L,text13,930,L,jPanel);
        sl.putConstraint(U,text13,3,U,jPanel);

        sl.putConstraint(L,label21,5,L,jPanel);
        sl.putConstraint(U,label21,27,U,jPanel);

        sl.putConstraint(R,text21,33,L,text21);
        sl.putConstraint(L,text21,75,L,jPanel);
        sl.putConstraint(U,text21,26,U,jPanel);

        sl.putConstraint(L,label22,110,L,jPanel);
        sl.putConstraint(U,label22,27,U,jPanel);

        sl.putConstraint(R,text22,33,L,text22);
        sl.putConstraint(L,text22,150,L,jPanel);
        sl.putConstraint(U,text22,26,U,jPanel);

        sl.putConstraint(L,label23,186,L,jPanel);
        sl.putConstraint(U,label23,27,U,jPanel);

        sl.putConstraint(R,text23,46,L,text23);
        sl.putConstraint(L,text23,240,L,jPanel);
        sl.putConstraint(U,text23,26,U,jPanel);

        sl.putConstraint(L,label24,290,L,jPanel);
        sl.putConstraint(U,label24,23,U,jPanel);

        sl.putConstraint(R,text24,90,L,text24);
        sl.putConstraint(L,text24,355,L,jPanel);
        sl.putConstraint(U,text24,26,U,jPanel);

        sl.putConstraint(L,label25,450,L,jPanel);
        sl.putConstraint(U,label25,27,U,jPanel);

        sl.putConstraint(R,text25,80,L,text25);
        sl.putConstraint(L,text25,510,L,jPanel);
        sl.putConstraint(U,text25,26,U,jPanel);

        sl.putConstraint(L,label26,595,L,jPanel);
        sl.putConstraint(U,label26,27,U,jPanel);

        sl.putConstraint(R,text26,80,L,text26);
        sl.putConstraint(L,text26,648,L,jPanel);
        sl.putConstraint(U,text26,26,U,jPanel);

        sl.putConstraint(L,label27,744,L,jPanel);
        sl.putConstraint(U,label27,27,U,jPanel);

        sl.putConstraint(R,text27,90,L,text27);
        sl.putConstraint(L,text27,810,L,jPanel);
        sl.putConstraint(U,text27,26,U,jPanel);

        sl.putConstraint(L,label31,3,L,jPanel);
        sl.putConstraint(U,label31,67,U,jPanel);

        sl.putConstraint(R,text31,550,L,text31);
        sl.putConstraint(L,text31,75,L,jPanel);
        sl.putConstraint(U,text31,66,U,jPanel);

        sl.putConstraint(L,label32,635,L,jPanel);
        sl.putConstraint(U,label32,67,U,jPanel);

        sl.putConstraint(R,text32,60,L,text32);
        sl.putConstraint(L,text32,667,L,jPanel);
        sl.putConstraint(U,text32,66,U,jPanel);

        sl.putConstraint(L,label33,744,L,jPanel);
        sl.putConstraint(U,label33,67,U,jPanel);

        sl.putConstraint(R,text33,90,L,text33);
        sl.putConstraint(L,text33,810,L,jPanel);
        sl.putConstraint(U,text33,66,U,jPanel);

       //set border to all textfields
       Font font=new Font(Font.SANS_SERIF,Font.PLAIN,11);
        for (Component component:jPanel.getComponents()) {
            if (component.getClass() == text12.getClass()) {
                JTextField text = (JTextField) component;
                text.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

            }
            if (component.getClass() == label11.getClass()) {
                JLabel label = (JLabel) component;
                label.setFont(font);

            }
        }
        JPanel jPanel1= new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(jPanel);
        JScrollPane jScrollPane = new JScrollPane(jPanel1);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jScrollPane, BorderLayout.CENTER);
        setVisible(true);
        setPreferredSize(new Dimension(1000, 500));

        pack();

    }

}
