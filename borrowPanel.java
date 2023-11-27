package git.BookManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

class borrowPanel{
    private JPanel panelcard;
    private JPanel py;
    private JPanel p1;
    
    public JPanel getPanel(String[][] ztable) {
        CardLayout card = new CardLayout();
        this.panelcard = new JPanel(card);
        this.py = new JPanel(new BorderLayout());
        this.py.setOpaque(false);
        this.p1 = new JPanel();
        this.p1.setBorder(new TitledBorder(null, "借阅信息", 4, 2, null, Color.RED));
        String[] title = { "借书人", "书名", "状态", "借书时间", "还书时间" };
        DefaultTableModel book = new DefaultTableModel(ztable, (Object[])title);
        JTable table = new JTable(book);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(22);
        this.p1.add(scrollPane);
        this.p1.setOpaque(false);
        this.py.add(this.p1, "Center");
        this.panelcard.add(this.py);
        this.panelcard.setOpaque(false);
        return this.panelcard;
        }
}
