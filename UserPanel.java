package git.BookManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

class UserPanel{
	private JPanel panelcard;
	private JPanel py;
	private JPanel p1;
	private JPanel p3;
  
  	public JPanel getPanel(String[][] ytable) {
		final DBconnect d = new DBconnect();
		CardLayout card = new CardLayout();
		this.panelcard = new JPanel(card);
		this.py = new JPanel(new BorderLayout());
		this.py.setOpaque(false);
		this.p1 = new JPanel();
		this.p1.setBorder(new TitledBorder(null, "用户查询", 4, 2, null, Color.RED));
		JLabel t1 = new JLabel("用户名:");
		final JTextField t2 = new JTextField(10);
		JButton bx = new JButton("查询");
		bx.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
				if (t2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请检查是否空填");
				} else { 
					MyBookManager.username = t2.getText();
					MyBookManager.message = 4;
					JOptionPane.showMessageDialog(null, "请刷新查看");
				} 
 			}
        });
    	this.p1.add(t1); this.p1.add(t2); this.p1.add(bx);
		this.p1.setOpaque(false);
		this.py.add(this.p1, "North");
		this.p3 = new JPanel();
		String[] title = { "编号", "用户名", "密码", "性别", "电话" };
		DefaultTableModel book = new DefaultTableModel(ytable,title);

		JLabel t3 = new JLabel("编号:");
		final JTextField t4 = new JTextField(11);
		JLabel t5 = new JLabel("用户名:");
		final JTextField t6 = new JTextField(13);
		JLabel t7 = new JLabel("密码:");
		final JTextField t8 = new JTextField(20);
		JLabel t9 = new JLabel("性别:");
		final JTextField t10 = new JTextField(11);
		JLabel t11 = new JLabel("手机号:");
		final JTextField t12 = new JTextField(20);

		JTable table = new JTable(book);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int row=table.getSelectedRow();
				t4.setText(table.getValueAt(row, 0).toString());
				t6.setText(table.getValueAt(row, 1).toString());
				t8.setText(table.getValueAt(row, 2).toString());
				t10.setText(table.getValueAt(row, 3).toString());
				t12.setText(table.getValueAt(row, 4).toString());
			}
		});
		table.setSize(500, 50);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(22);
		this.p3.add(scrollPane);
		this.p3.setBorder(new TitledBorder(null, "用户编辑", 4, 2, null, Color.RED));
		
		JButton b3 = new JButton("修改");
		b3.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
				if (t4.getText().isEmpty() || t6.getText().isEmpty() || t8.getText().isEmpty() || t10.getText().isEmpty() || t12.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请检查是否空填");
				} else {
					int id = Integer.parseInt(t4.getText());
					String username = t6.getText();
					String password = t8.getText();
					String sex = t10.getText();
					String phone = t12.getText();
					if (d.status(id, 0, "", 4)){ 
						if (d.modifyuser(username, password, sex, phone, id)) {
							JOptionPane.showMessageDialog(null, "修改成功,请刷新");
							MyBookManager.message = 3;
						} else {
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					}
					else { JOptionPane.showMessageDialog(null, "没有此用户"); }
				} 
			}
        });
		JButton b4=new JButton("重置");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				t4.setText("");
				t6.setText("");
				t8.setText("");
				t10.setText("");
				t12.setText("");
			}
		});
		this.p3.add(t3); this.p3.add(t4);
		this.p3.add(t5); this.p3.add(t6);
		this.p3.add(t9); this.p3.add(t10);
		this.p3.add(t7); this.p3.add(t8);
		this.p3.add(t11); this.p3.add(t12);
		this.p3.add(b3); this.p3.add(b4);
		this.p3.setOpaque(false);
		this.py.add(this.p3, "Center");
		this.panelcard.add(this.py);
		this.panelcard.setOpaque(false);
		return this.panelcard;
  	}
}
