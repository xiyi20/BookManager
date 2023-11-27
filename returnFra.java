package git.BookManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


class returnFra{
  	public returnFra() {
		final DBconnect d = new DBconnect();
		bgPanel bgPanel = new bgPanel();
		JPanel backgroundPanel = bgPanel.getbgPanel(2);
		final JFrame f = new JFrame("用户界面");
		backgroundPanel.setLayout(new BorderLayout());
		f.setBounds(850, 200, 500, 610);
		JPanel px = new JPanel();
		px.setBorder(new TitledBorder(null, "借阅信息", 4, 2, null, Color.RED));
		JLabel lx = new JLabel("欢迎您," + MyBookManager.Username, 0);
		lx.setFont(new Font("黑体", 0, 30));
		px.add(lx); px.setOpaque(false);
		backgroundPanel.add(px, "North");
		final CardLayout card = new CardLayout();
		final JPanel panel = new JPanel(card);
		JPanel p1 = new JPanel();
		String[] title = { "编号", "书名", "状态", "借书时间", "还书时间" };
		DefaultTableModel book = new DefaultTableModel((Object[][])d.table(3, "", "", ""), (Object[])title);
		JTable table = new JTable(book);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(22);
		p1.add(scrollPane); p1.setOpaque(false);
		panel.add(p1, "默认");
		JPanel p2 = new JPanel();
		p2.setBorder(new TitledBorder(null, "还书", 4, 2, null, Color.RED));
		JButton b1 = new JButton("返回");
		b1.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
   				f.dispose();
				new UserHome();
 			}
        }); 
		JLabel t1 = new JLabel("编号:");
		final JTextField t2 = new JTextField(10);
		JButton b2 = new JButton("还书");
		b2.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
   				if (t2.getText().isEmpty()) {
     				JOptionPane.showMessageDialog(null, "请检查是否空填");
   				} else {
     				int id = Integer.parseInt(t2.getText());
     				if (d.status(0, id, "", 3)){ 
						if (d.returnbook(id)) {
							JOptionPane.showMessageDialog(null, "还书成功");
       					} else {
							JOptionPane.showMessageDialog(null, "还书失败,或已被归还");
       					}  
					}
     				else { JOptionPane.showMessageDialog(null, "没有借这本书"); }
				} 
				panel.removeAll();
   				JPanel p1 = new JPanel();
   				p1.setBorder(new TitledBorder(null, "借阅信息", 4, 2, null, Color.RED));
				String[] title = { "编号", "书名", "状态", "借书时间", "还书时间" };
				DefaultTableModel book = new DefaultTableModel((Object[][])d.table(3, "", "", ""), (Object[])title);
				JTable table = new JTable(book);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setVerticalScrollBarPolicy(22);
				p1.add(scrollPane); p1.setOpaque(false);
				panel.add(p1, "刷新");
				card.show(panel, "刷新");
				f.revalidate();
 			}
        });
		JButton b3 = new JButton("退出系统");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
   				System.exit(0);
 			}
        });
		p2.add(b1); p2.add(t1); p2.add(t2); p2.add(b2); p2.add(b3);
		p2.setOpaque(false);
		backgroundPanel.add(panel);
		backgroundPanel.add(p2, "South");
		f.setContentPane(backgroundPanel);
		f.setVisible(true);
		f.setDefaultCloseOperation(3);
  	}
}
