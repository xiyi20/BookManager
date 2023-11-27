package git.BookManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

class BookPanel{
    private JPanel panelcard;
    private JPanel py;
    private JPanel p1;
    private JPanel p3;

    public JPanel getPanel(String[][] xtable) {
        final DBconnect d = new DBconnect();
        CardLayout card = new CardLayout();
        this.panelcard = new JPanel(card);
        this.py = new JPanel(new BorderLayout());
        this.p1 = new JPanel();
        this.p1.setBorder(new TitledBorder(null, "书籍查询", 4, 2, null, Color.RED));
        final JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("书籍名称");
        comboBox.addItem("书籍作者");
        final JTextField t15 = new JTextField(10);
        JButton bx = new JButton("查询");
        bx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (t15.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请检查是否空填");
                MyBookManager.message = 0;
                }
                else if (comboBox.getSelectedItem().equals("书籍名称")) {
                    MyBookManager.bookname = t15.getText();
                    MyBookManager.message = 1;
                    JOptionPane.showMessageDialog(null, "请刷新查看");
                } else {
                    MyBookManager.bookauthor = t15.getText();
                    MyBookManager.message = 2;
                    JOptionPane.showMessageDialog(null, "请刷新查看");
                } 
            }
        });

        this.p1.add(comboBox);
        this.p1.add(t15); this.p1.add(bx);
        this.p1.setOpaque(false);
        this.py.add(this.p1, "North");
        this.py.setOpaque(false);
        this.p3 = new JPanel();
        String[] title = { "编号", "书名", "类型", "作者", "价格", "库存", "状态" };
        DefaultTableModel book = new DefaultTableModel(xtable, (Object[])title);
        JTable table = new JTable(book);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(22);
        this.p3.add(scrollPane);
        JLabel t17 = new JLabel("编号:");
        final JTextField t18 = new JTextField(18);
        JLabel t19 = new JLabel("书名:");
        final JTextField t20 = new JTextField(18);
        JLabel t21 = new JLabel("作者:");
        final JTextField t22 = new JTextField(17);
        JLabel t23 = new JLabel("出版社:");
        final JTextField t24 = new JTextField(17);
        JLabel t25 = new JLabel("价格:");
        final JTextField t26 = new JTextField(18);
        JLabel t27 = new JLabel("库存:");
        final JTextField t28 = new JTextField(18);
        JLabel t29 = new JLabel("描述:");
        final JTextField t30 = new JTextField(39);
        JLabel t31 = new JLabel("类型");
        final JComboBox<String> comboBox1 = new JComboBox<>();
        comboBox1.addItem("小说");
        comboBox1.addItem("教材");
        comboBox1.addItem("科普");
        comboBox1.addItem("杂志");
        JLabel t32 = new JLabel("状态");
        final JComboBox<String> comboBox2 = new JComboBox<>();
        comboBox2.addItem("上架");
        comboBox2.addItem("下架");
        JButton b3 = new JButton("修改");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (t18.getText().isEmpty() || t20.getText().isEmpty() || t22.getText().isEmpty() || t24.getText().isEmpty() || 
                t26.getText().isEmpty() || t28.getText().isEmpty() || t30.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请检查是否空填");
                } else {
                    int bookid = Integer.parseInt(t18.getText());
                    String bookname = t20.getText();
                    String author = t22.getText();
                    String publish = t24.getText();
                    double price = Double.parseDouble(t26.getText());
                    int number = Integer.parseInt(t28.getText());
                    String remark = t30.getText();
                    int type = 0;
                    if (comboBox1.getSelectedItem().equals("小说")) { type = 1; }
                    else if (comboBox1.getSelectedItem().equals("教材")) { type = 2; }
                    else if (comboBox1.getSelectedItem().equals("科普")) { type = 3; }
                    else { type = 4; }
                    int status = 0;
                    if (comboBox2.getSelectedItem().equals("上架")) status = 1; 
                    if (d.status(1, bookid, "", 2)){ 
                        if (d.modifybook(bookid, bookname, type, author, publish, price, number, status, remark)){ 
                            JOptionPane.showMessageDialog(null, "修改成功");
                            MyBookManager.message = 0; 
                        }
                        else { 
                            JOptionPane.showMessageDialog(null, "修改失败"); 
                        }
                    }
                    else { 
                        JOptionPane.showMessageDialog(null, "该编号不存在"); 
                    }

                } 
            }
        });
        this.p3.add(t17); this.p3.add(t18); this.p3.add(t19); this.p3.add(t20); this.p3.add(t21); this.p3.add(t22);
        this.p3.add(t23); this.p3.add(t24); this.p3.add(t25); this.p3.add(t26); this.p3.add(t27); this.p3.add(t28); this.p3.add(t29);
        this.p3.add(t30); this.p3.add(t31); this.p3.add(comboBox1); this.p3.add(t32); this.p3.add(comboBox2); this.p3.add(b3);
        this.p3.setOpaque(false);
        this.py.add(this.p3, "Center");
        this.panelcard.add(this.py);
        this.panelcard.setOpaque(false);
        return this.panelcard;
    }
}