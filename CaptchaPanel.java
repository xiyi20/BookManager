package git.BookManager;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
 

class CaptchaPanel extends JPanel{
	public String generateCaptcha() {
		char[] chars="0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Random random=new Random();
		StringBuilder captchaBuilder = new StringBuilder();
		for (int i=0;i<4;i++) {
			int pos=random.nextInt(chars.length);
			captchaBuilder.append(chars[pos]);
		} 
 		return captchaBuilder.toString();
   	}
   	String captcha=generateCaptcha();
   	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width=80;
		int height=40;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width-1, height-1);
		g.setFont(new Font("宋体", 1, 30));
		g.setColor(Color.BLUE);
		this.captcha = generateCaptcha();
 		g.drawString(this.captcha, 8, 30);
   	}
   	public String getcaptcha() {
 		return this.captcha;
   	}
}