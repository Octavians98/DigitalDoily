import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gallery extends JFrame {
public BufferedImage bi;
	public Gallery(Panel panel){
		this.setLayout(new FlowLayout(0,0,FlowLayout.CENTER));
		bi = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		panel.paint(g2);
	}

	public void saveImage(JPanel p){
		this.add(p);
		}
}
