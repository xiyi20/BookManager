package project.book;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class RegistFra{
  	public RegistFra() {
    	final DBconnect d = new DBconnect();
    	bgPanel bgPanel = new bgPanel();
		JPanel backgroundPanel = bgPanel.getbgPanel(1);
		backgroundPanel.setLayout(new GridLayout(7, 1));
		final JFrame f = new JFrame("用户注册");
		f.setBounds(850, 200, 450, 400);
		JLabel t1 = new JLabel("用户注册", 0);
		backgroundPanel.add(t1);
		t1.setFont(new Font("黑体", 0, 24));
		JPanel p1 = new JPanel();
		JLabel t2 = new JLabel("用户名:");
		final JTextField t3 = new JTextField(15);
		p1.add(t2); p1.add(t3);
		p1.setOpaque(false);
		backgroundPanel.add(p1);
		JPanel p2 = new JPanel();
		JLabel t4 = new JLabel("密码:");
		final JTextField t5 = new JPasswordField(16);
		p2.add(t4); p2.add(t5);
		p2.setOpaque(false);
		backgroundPanel.add(p2);
		JPanel p3 = new JPanel();
		JLabel t6 = new JLabel("手机号:");
		final JTextField t7 = new JTextField(15);
		p3.add(t6); p3.add(t7);
		p3.setOpaque(false);
		backgroundPanel.add(p3);
		JPanel p4 = new JPanel();
		JLabel t8 = new JLabel("性别:");
		ButtonGroup g = new ButtonGroup();
		final JRadioButton man = new JRadioButton("男");
		man.setOpaque(false);
		final JRadioButton woman = new JRadioButton("女");
		woman.setOpaque(false);
		g.add(woman); g.add(man);
		p4.add(t8); p4.add(man); p4.add(woman);
		p4.setOpaque(false);
		backgroundPanel.add(p4);
		final CaptchaPanel px = new CaptchaPanel();
		px.setOpaque(false);
		JPanel p5 = new JPanel(new FlowLayout(2));
		JLabel t9 = new JLabel("验证码:");
		final JTextField t10 = new JTextField(4);
		p5.add(t9); p5.add(t10);
		p5.setOpaque(false);
		JPanel p6 = new JPanel(new GridLayout(1, 2));
		p6.add(p5); p6.add(px);
		p6.setOpaque(false);
		backgroundPanel.add(p6);
		JPanel p7 = new JPanel();
		JButton b1 = new JButton("注册");
		b1.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
				String catpcha = px.getcaptcha();
				String name = t3.getText();
				String pw = t5.getText();
				String phone = t7.getText();
				String sex = "";
   				if (man.isSelected()) { sex = man.getText(); }
   				else if (woman.isSelected()) { sex = woman.getText(); }
    			if (name.isEmpty() || pw.isEmpty() || phone.isEmpty()) { JOptionPane.showMessageDialog(null, "请检查是否空填"); }
   				else if (catpcha.equals(t10.getText())){ 
					if (d.regist(name, pw, phone, sex)) { JOptionPane.showMessageDialog(null, "注册成功,请登录"); }
     				else { JOptionPane.showMessageDialog(null, "已存在该用户,请登录"); }
      			}
   				else{ 
					px.repaint();
     				catpcha = px.getcaptcha();
     				JOptionPane.showMessageDialog(null, "验证码错误,请注意大小写"); 
				}
			}
        });
   		JButton b2 = new JButton("前往登录页面");
    	b2.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
   				f.dispose();
				new LoginFra();
 			}
        });
    
		p7.add(b1); p7.add(b2);
		p7.setOpaque(false);
		backgroundPanel.add(p7);
		f.setContentPane(backgroundPanel);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
  	}
}
