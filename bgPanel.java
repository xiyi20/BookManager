package git.BookManager;
 
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class bgPanel{
    public JPanel getbgPanel(final int x) {
        JPanel backgroundPanel = new JPanel(new GridLayout(5, 1)){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String adress = "";
                if (x == 1) { adress = "java\\project\\book\\image\\bg1.jpg"; }
                else if (x == 2) { adress = "java\\project\\book\\image\\bg2.jpg"; }
                else if (x == 3) { adress = "java\\project\\book\\image\\bg3.jpg"; }
                ImageIcon imageIcon;
                try {
                    imageIcon = new ImageIcon(getClass().getResource(adress));
                } catch (Exception e) {
                    imageIcon = new ImageIcon(adress);
                }
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        return backgroundPanel;
    }
}
