package project.book;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
	
class LoginFra{
	public LoginFra() {
   		final DBconnect d = new DBconnect();
		bgPanel bgPanel = new bgPanel();
		JPanel backgroundPanel = bgPanel.getbgPanel(1);
		final JFrame f = new JFrame("用户登录");
		f.setLayout(new GridLayout(5, 1));
		f.setBounds(850, 200, 450, 400);
		JLabel tx = new JLabel("用户登录", 0);
		backgroundPanel.add(tx);
		tx.setFont(new Font("黑体", 0, 24));
		JPanel p1 = new JPanel();
		JLabel t1 = new JLabel("用户名:");
		final JTextField t2 = new JTextField(15);
		p1.add(t1); p1.add(t2);
		p1.setOpaque(false);
		JPanel p2 = new JPanel();
		JLabel t3 = new JLabel("密码:");
		final JTextField t4 = new JPasswordField(16);
		p2.add(t3); p2.add(t4);
		p2.setOpaque(false);
		JPanel p3 = new JPanel();
		JLabel t5 = new JLabel("权限:");
		final JComboBox<String> combobox = new JComboBox<>();
		combobox.addItem("用户");
		combobox.addItem("管理员");
		p3.add(t5); p3.add(combobox);
		p3.setOpaque(false);
		JPanel p4 = new JPanel();
		JButton b1 = new JButton("登录");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = t2.getText();
				String pw = t4.getText();
 				if (combobox.getSelectedItem() == "用户") {
   					int role = 0;
   					if (d.login(name, pw, role, false) != 0){ 
						MyBookManager.Id = d.login(name, pw, role, false);
						MyBookManager.Username = name;
						JOptionPane.showMessageDialog(null, "用户登录成功!");
						f.dispose(); 
						new UserHome();
					}
   					else{ 
						JOptionPane.showMessageDialog(null, "账号或密码错误!"); 
					} 
 				} 
				else if (combobox.getSelectedItem() == "管理员") {
   					int role = 1;
   					if (d.login(name, pw, role, true) != 0) {
						MyBookManager.Id = d.login(name, pw, role, true);
						MyBookManager.Username = name;
						JOptionPane.showMessageDialog(null, "管理员登录成功!");
						f.dispose();
						new RootHome();
   					} else {
						JOptionPane.showMessageDialog(null, "账号或密码或权限错误!");
   					} 
 				}  
			}
  		});
   		JButton b2 = new JButton("前往注册页面");
   		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
 				f.dispose();
				new RegistFra();
			}
  		});
		p4.add(b1); p4.add(b2);
		p4.setOpaque(false);
		backgroundPanel.add(p1); backgroundPanel.add(p2);
		backgroundPanel.add(p3); backgroundPanel.add(p4);
		f.setContentPane(backgroundPanel);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
 	}
}
