package git.BookManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


class UserHome{
    public UserHome() {
		final DBconnect d = new DBconnect();
		bgPanel bgPanel = new bgPanel();
		JPanel backgroudPanel = bgPanel.getbgPanel(2);
		final JFrame f = new JFrame("图书馆");
		f.setBounds(850, 200, 500, 603);
		backgroudPanel.setLayout(new BorderLayout());
		final CardLayout card = new CardLayout();
		final JPanel panel = new JPanel(card);
		JPanel p1 = new JPanel();
		p1.setBorder(new TitledBorder(null, "书籍信息", 4, 2, null, Color.RED));
		final JComboBox<String> combobox = new JComboBox<>();
		combobox.addItem("书籍名称"); combobox.addItem("书籍作者");
		final JTextField t1 = new JTextField(10);

		JLabel t2 = new JLabel("编号:");
		final JTextField t3 = new JTextField(10);
		JLabel t4 = new JLabel("书名:");
		final JTextField t5 = new JTextField(10);

		JButton b1 = new JButton("查询");
		b1.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
   				if (t1.getText().isEmpty()) {
					panel.removeAll();
					JPanel px = new JPanel();
					String[] title = { "编号", "书名", "类型", "作者", "描述" };
					DefaultTableModel book = new DefaultTableModel(d.table(0, "", "", ""),title);
					JTable table = new JTable(book);
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setVerticalScrollBarPolicy(22);
					px.add(scrollPane);
					panel.add(px, "default");
     				card.show(panel, "default");
					f.revalidate();
				}
   				else if (combobox.getSelectedItem().equals("书籍名称")) {
					panel.removeAll();
					JPanel px = new JPanel();
					String[] title = { "编号", "书名", "类型", "作者", "描述" };
					DefaultTableModel book = new DefaultTableModel(d.table(1, t1.getText(), "", ""),title);
					JTable table = new JTable(book);
					table.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e){
							int row=table.getSelectedRow();
							t3.setText(table.getValueAt(row, 0).toString());
							t5.setText(table.getValueAt(row, 1).toString());
						}
					});
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setVerticalScrollBarPolicy(22);
					px.add(scrollPane);
					panel.add(px, "名称");
					card.show(panel, "名称");
					f.revalidate();
   				}
   				else if (combobox.getSelectedItem().equals("书籍作者")) {
					panel.removeAll();
					JPanel px = new JPanel();
					String[] title = { "编号", "书名", "类型", "作者", "描述" };
					DefaultTableModel book = new DefaultTableModel(d.table(2, "", t1.getText(), ""),title);
					JTable table = new JTable(book);
					table.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e){
							int row=table.getSelectedRow();
							t3.setText(table.getValueAt(row, 0).toString());
							t5.setText(table.getValueAt(row, 1).toString());
						}
					});
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setVerticalScrollBarPolicy(22);
					px.add(scrollPane);
					panel.add(px, "作者");
					card.show(panel, "作者");
					f.revalidate();
   				} 
 			}
        });
		p1.add(combobox); p1.add(t1); p1.add(b1);
		p1.setOpaque(false);

		JPanel px = new JPanel();
		String[] title = { "编号", "书名", "类型", "作者", "描述" };
		DefaultTableModel book = new DefaultTableModel(d.table(0, "", "", ""),title);
		JTable table = new JTable(book);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int row=table.getSelectedRow();
				t3.setText(table.getValueAt(row, 0).toString());
				t5.setText(table.getValueAt(row, 1).toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(22);
		px.add(scrollPane);
		panel.add(px);

		JPanel p2 = new JPanel();
		p2.setBorder(new TitledBorder(null, "借书", 4, 2, null, Color.RED));
		
		JButton b2 = new JButton("借书");
		b2.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
   				if (t3.getText().isEmpty() || t5.getText().isEmpty()) {
     				JOptionPane.showMessageDialog(null, "请检查是否空填");
   				} else {
					int user_id = MyBookManager.Id;
					int book_id = Integer.parseInt(t3.getText());
					String book_name = t5.getText();
					if (d.status(user_id, book_id, book_name, 0)){ 
						JOptionPane.showMessageDialog(null, "书已被借,请先归还");
        			}
     				else if (d.status(user_id, book_id, book_name, 1)){ 
						if (d.borrowbook(user_id, MyBookManager.Username, book_id, book_name)) { JOptionPane.showMessageDialog(null, "借书成功,请及时归还"); }
       					else { JOptionPane.showMessageDialog(null, "借书失败"); }
        			}
     				else { JOptionPane.showMessageDialog(null, "不存在此书,请检查编号或书名"); }
				} 
 			}
        });
    	JButton b3 = new JButton("去还书");
    	b3.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
   				f.dispose();
				new returnFra();
 			}
        });
		p2.add(t2); p2.add(t3); p2.add(t4);
		p2.add(t5); p2.add(b2); p2.add(b3);
		p2.setOpaque(false);
		backgroudPanel.add(p1, "North");
		backgroudPanel.add(panel, "Center");
		backgroudPanel.add(p2, "South");
		f.setVisible(true);
		f.setContentPane(backgroudPanel);
		f.setDefaultCloseOperation(3);
  	}
}
