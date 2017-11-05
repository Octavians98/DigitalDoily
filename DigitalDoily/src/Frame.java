import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Frame extends JFrame {
	private Panel panel;
	private JLabel label;
	private ImagePanel gallery;	
	public Frame(){
		setTitle("Digital Doily");
		setSize(1500,1000);

		setVisible(true);
		myActions();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);



	}
	//Here is where i`m adding all the buttons and their functionality
	public void myActions(){
		gallery = new ImagePanel();
		JPanel doilyPanel = new JPanel();
		doilyPanel.setLayout(new GridLayout(1,2));
		panel = new Panel();
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				panel.clear();
			}
		});

		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.undo();

			}
		});
		JSlider nrOfSectors = new JSlider(JSlider.HORIZONTAL,2,40,12);
		nrOfSectors.setMajorTickSpacing(5);
		nrOfSectors.setMinorTickSpacing(1);
		nrOfSectors.setPaintTicks(true);
		nrOfSectors.setPaintLabels(true);
		int nr = nrOfSectors.getValue();
		nrOfSectors.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source =(JSlider)e.getSource();
				int nr = (int)source.getValue();
				panel.split(nr);

			}
		});
		JLabel sectorLabel = new JLabel("Number of sectors ");
		JSlider pensize = new JSlider(JSlider.HORIZONTAL,0,30,5);
		pensize.setMajorTickSpacing(5);
		pensize.setMinorTickSpacing(1);
		pensize.setPaintTicks(true);
		pensize.setPaintLabels(true);
		pensize.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source2 = (JSlider)e.getSource();
				int n = (int)source2.getValue();
				panel.penSize(n);

			}
		});
		JLabel penLabel = new JLabel("Select pen size");
		JButton colorpan = new JButton("Press me");
		colorpan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Color initialColor = panel.returnColor();
				Color color = JColorChooser.showDialog(colorpan, "Pick a color", initialColor);
				panel.changeColor(color);

			}
		});

		JLabel colorLabel = new JLabel("Select your color");
		JToggleButton toggleSectors = new JToggleButton("Sectors");
		toggleSectors.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(toggleSectors.isSelected()){
					panel.changeVisibility(false);
				} else {
					panel.changeVisibility(true);
				}

			}
		});
		JLabel toggleLabel = new JLabel("Turn ON/OFF");
		JToggleButton refelction = new JToggleButton("Reflection");
		refelction.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(refelction.isSelected()){
					panel.changeReflection(true);
				} else {
					panel.changeReflection(false);
				}

			}
		});
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {


				panel.saveImage();

				SavedImage si = new SavedImage(panel.save());
				gallery.addDrawing(si);
				gallery.revalidate();



			}});
		JLabel backgroundColorLabel = new JLabel("Change the background color");
		JButton backgroundColorChooser = new JButton("Press me");
		backgroundColorChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				Color backgroundColor = JColorChooser.showDialog(colorpan, "Pick a color", Color.BLACK);
				panel.setBackground(backgroundColor);

			}
		});
		//Here is where i`m organizing my JFrame
		JPanel sectorsSlider = new JPanel();
		sectorsSlider.add(sectorLabel);
		sectorsSlider.add(nrOfSectors);
		JPanel penSizeSlider = new JPanel();
		penSizeSlider.add(penLabel);
		penSizeSlider.add(pensize);
		JPanel drawingMenu =new JPanel();
		drawingMenu.setLayout(new FlowLayout());
		drawingMenu.add(undo);
		drawingMenu.add(clear);
		drawingMenu.add(save);
		JPanel menuAndGalleryPanel = new JPanel();
		menuAndGalleryPanel.setLayout(new GridLayout(2, 1));
		JPanel p5 = new JPanel();
		p5.add(toggleLabel);
		p5.add(toggleSectors);
		p5.add(refelction);
		drawingMenu.add(p5);
		JPanel p4 = new JPanel();
		p4.add(colorLabel);
		p4.add(colorpan);
		JPanel p7 = new JPanel();
		p7.add(backgroundColorLabel);
		p7.add(backgroundColorChooser);
		JPanel galleryPanel = new JPanel();
		galleryPanel.setLayout(new BoxLayout(galleryPanel,BoxLayout.Y_AXIS));


		galleryPanel.add(gallery);
		drawingMenu.add(sectorsSlider);
		drawingMenu.add(penSizeSlider);
		drawingMenu.add(p4);
		drawingMenu.add(p7);
		menuAndGalleryPanel.add(drawingMenu);
		menuAndGalleryPanel.add(galleryPanel);
		doilyPanel.add(menuAndGalleryPanel);
		doilyPanel.add(panel);


		this.add(doilyPanel);


	}

	

	public static void main(String[] args){
		Frame f = new Frame();

	}



}
