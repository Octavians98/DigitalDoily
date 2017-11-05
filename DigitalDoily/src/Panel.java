import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;




public class Panel extends JPanel {

	public static Integer nlines;
	private Color color;
	private boolean ref;
	private int size;
	private ArrayList<Line> lines;
	private boolean visibility;
	

	public Panel(){
		super();
		color = Color.white;
		nlines = 12;
		ref = false;
		size =5;
		lines = new ArrayList<Line>();
		visibility = true;
		this.setBackground(Color.BLACK);
		start();




	}
	private void start(){
		//Drawing the lines
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Line ln = new Line(color, size, ref);
				ln.add(e.getPoint());
				


				lines.add(ln);
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e) {
				lines.get(lines.size()-1).add(e.getPoint());
				repaint();}
		});
	}



	public void clear(){
		lines = new ArrayList<Line>();
		repaint();
	}


	public void undo(){
		if(lines.isEmpty()==false){
			lines.remove(lines.size()-1);
			repaint();
		}
	}
	//I`m using this method to change the number of the sectors
	public void split(int nr){
		this.nlines = nr;
		repaint();
	}
	public void penSize(int n){
		this.size = n;

	}
	public void changeColor(Color c){
		this.color = c;
	}
	public void changeBackGround(Color bc){
		this.setBackground(bc);
	}
	public Color returnColor(){
		return this.color;
	}
//If the sectors are visible or not
	public void changeVisibility(boolean vis){
		this.visibility =vis;
		repaint();
	}
	public void changeReflection(boolean r){
		this.ref = r;
		repaint();
	}
	public Panel save(){
		return this;
	}
	//Should save all the images in the local storage in the JPG format but from a reason or another it only save the first one
	public void saveImage(){
		int n=1;
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D im = image.createGraphics();
		paint(im);
		try{
			ImageIO.write(image, "jpg", new File("image"+n+".jpg"));
		}catch (Exception e){
			e.printStackTrace();
		}
		n++;
	}

//Drawing the doily
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(hints);
		g2.translate(this.getWidth()/2, this.getHeight()/2);
		if(visibility==true){for(int i=0; i<nlines; ++i){
			g2.drawLine(0, 0, 0, this.getHeight());
			g2.rotate(Math.PI*2/nlines);
		}}
		for(int i=0; i<nlines; i++){
			for (int j=0; j<lines.size(); j++){
				Line line = lines.get(j);
				boolean reflect = line.Reflect();
				ArrayList<Point> points = line.returnPoints();
				g2.setColor(line.returnColor());
				g2.setStroke(new BasicStroke(line.returnSize()));

				for(int l=0; l<points.size()-1; l++){
					Point p = points.get(l);
					Point q = points.get(l + 1);
					g.drawLine(p.x-this.getWidth()/2, p.y-this.getHeight()/2,q.x-this.getWidth()/2, q.y-this.getHeight()/2);
					//I use this to reflect the line that I have just drew
					if(reflect==true){
						g2.drawLine(-p.x+this.getWidth()/2, p.y-this.getHeight()/2, -q.x+this.getWidth()/2, q.y-this.getHeight()/2);}
				}}
			g2.rotate(Math.PI*2/nlines);}
	}}


