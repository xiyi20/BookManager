package project.book;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


class RootHome{
    public RootHome() {
        final DBconnect d = new DBconnect();
        final BookPanel bookpanel = new BookPanel();
        final UserPanel userpanel = new UserPanel();
        bgPanel bgPanel = new bgPanel();
        JPanel backgroundPanel = bgPanel.getbgPanel(3);
        final borrowPanel borrowpanel = new borrowPanel();
        JFrame f = new JFrame("管理员界面");
        f.setBounds(850, 100, 500, 733);
        backgroundPanel.setLayout(new BorderLayout());
        final CardLayout card = new CardLayout();
        final JPanel panel = new JPanel(card);
        panel.setOpaque(false);
        JPanel p1 = new JPanel();
        p1.setSize(250, 100);
        p1.setBorder(new TitledBorder(null, "书籍添加", 4, 2, null, Color.RED));
        JLabel t1 = new JLabel("书名:");
        final JTextField t2 = new JTextField(40);
        JLabel t3 = new JLabel("作者:");
        final JTextField t4 = new JTextField(40);
        JLabel t5 = new JLabel("出版社:");
        final JTextField t6 = new JTextField(40);
        JLabel t7 = new JLabel("价格:");
        final JTextField t8 = new JTextField(40);
        JLabel t9 = new JLabel("库存:");
        final JTextField t10 = new JTextField(40);
        JLabel t11 = new JLabel("类型:");
        final JComboBox<String> t12 = new JComboBox<>();
        t12.addItem("小说"); t12.addItem("教材");
        t12.addItem("科普"); t12.addItem("杂志");
        JLabel t13 = new JLabel("概述:");
        final JTextField t14 = new JTextField(40);
        JButton b1 = new JButton("添加");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = t2.getText(); int type = 0;
                String author = t4.getText(), publish = t6.getText();
                String remark = t14.getText();
                if (t12.getSelectedItem().toString() == "小说") { type = 1; }
                else if (t12.getSelectedItem().toString() == "教材") { type = 2; }
                else if (t12.getSelectedItem().toString() == "科普") { type = 3; }
                else if (t12.getSelectedItem().toString() == "杂志") { type = 4; }
                if (name.isEmpty() || author.isEmpty() || publish.isEmpty() || t8.getText().isEmpty() || t10.getText().isEmpty() || remark.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请检查是否空填!");
                } else {
                    double price = Double.parseDouble(t8.getText());
                    int number = Integer.parseInt(t10.getText());
                    if (d.addbook(name, type, author, publish, price, number, remark)) {
                        JOptionPane.showMessageDialog(null, "添加成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败");
                    } 
                }  
            }
        });
        JButton b2 = new JButton("重置");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t2.setText(""); t4.setText(""); t6.setText("");
                t8.setText(""); t10.setText(""); t14.setText("");
            }
        });
        p1.add(t1); p1.add(t2); p1.add(t3); p1.add(t4); p1.add(t5);
        p1.add(t6); p1.add(t7); p1.add(t8); p1.add(t9); p1.add(t10);
        p1.add(t13); p1.add(t14); p1.add(t11); p1.add(t12); p1.add(b1); p1.add(b2);
        p1.setOpaque(false);
        panel.add(p1, "书籍添加");
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("书籍管理");
        JMenuItem mi1 = new JMenuItem("书籍添加");
        m1.add(mi1);
        mi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(panel, "书籍添加");
            }
        });
        JMenuItem mi2 = new JMenuItem("书籍修改");
        m1.add(mi2);
        mi2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.add(bookpanel.getPanel(d.table(6, "", "", "")), "书籍修改");
                card.show(panel, "书籍修改");
            }
        });
        JMenu m2 = new JMenu("用户管理");
        JMenuItem mi3 = new JMenuItem("用户信息");
        m2.add(mi3);
        mi3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.add(userpanel.getPanel(d.table(7, "", "", "")), "用户修改");
                card.show(panel, "用户修改");
            }
        });
        JMenuItem mi4 = new JMenuItem("借阅信息");
        m2.add(mi4);
        mi4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.add(borrowpanel.getPanel(d.table(9, "", "", "")), "借阅信息");
                card.show(panel, "借阅信息");
            }
        });
        JMenu m3 = new JMenu("刷新");
        JMenuItem mi5 = new JMenuItem("刷新");
        m3.add(mi5);
        mi5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (MyBookManager.message == 0) {
                    panel.add(bookpanel.getPanel(d.table(6, "", "", "")), "书籍修改");
                    card.show(panel, "书籍修改");
                }
                else if (MyBookManager.message == 1) {
                    panel.add(bookpanel.getPanel(d.table(4, MyBookManager.bookname, "", "")), "书名查询");
                    card.show(panel, "书名查询");
                    MyBookManager.message = 0;
                }
                else if (MyBookManager.message == 2) {
                    panel.add(bookpanel.getPanel(d.table(5, "", MyBookManager.bookauthor, "")), "作者查询");
                    card.show(panel, "作者查询");
                    MyBookManager.message = 0;
                }
                else if (MyBookManager.message == 3) {
                    panel.add(userpanel.getPanel(d.table(7, "", "", "")), "用户修改");
                    card.show(panel, "用户修改");
                    MyBookManager.message = 0;
                }
                else if (MyBookManager.message == 4) {
                    panel.add(userpanel.getPanel(d.table(8, "", "", MyBookManager.username)), "用户查询");
                    card.show(panel, "用户查询");
                    MyBookManager.message = 0;
                } 
            }
        });
        JMenu m4 = new JMenu("退出系统");
        JMenuItem mi6 = new JMenuItem("退出");
        m4.add(mi6);
        mi6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mb.add(m1); mb.add(m2); mb.add(m3); mb.add(m4);
        mb.setOpaque(false);
        backgroundPanel.add(panel, "Center");
        f.setJMenuBar(mb);
        f.setContentPane(backgroundPanel);
        f.setVisible(true);
        f.setDefaultCloseOperation(3);
 }
}
