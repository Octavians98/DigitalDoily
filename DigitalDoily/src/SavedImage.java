import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;
//This is where i`m saving the drawing
public class SavedImage extends JPanel {
	BufferedImage image;
	
	public SavedImage(Panel pen){
		
		int w = pen.getWidth();
		int h = pen.getHeight();
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		pen.paint(g);
		this.setPreferredSize(new Dimension(150, 150));
	}

	 @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         
             Graphics2D g2d = (Graphics2D) g.create();
             int x = this.getWidth();
             int y = this.getHeight();
             //I`m resizing the JPanel which contains the drawing
             Image i = image.getScaledInstance(x, x, Image.SCALE_DEFAULT);
             g2d.drawImage(i, 0, 0, null);
             g2d.dispose();
            
           
         
     }

 }
