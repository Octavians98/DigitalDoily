import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
//This is my Gallery class where the drawing are saved
	public ImagePanel(){
	}
	public void addDrawing(JPanel drawing){

		if(this.getComponentCount()<12){
				this.add(drawing);
		
		}}
}





