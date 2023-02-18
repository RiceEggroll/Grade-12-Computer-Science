package graphics.events;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

//Web Nguyen
//'22, June 13
//The Events program

public class EventsWeb {
	//Global variables
	JFrame frame = new JFrame("Build your own MiG");
    ChangeDisplay onClick = new ChangeDisplay();
    JPanel panel;
    JMenuBar menuBar = new JMenuBar();
    JMenu menuSelect = new JMenu("Select a MiG");    
    JMenuItem MiG15Item = new JMenuItem("MiG-15");
    JMenuItem MiG21Item = new JMenuItem("MiG-21");
	JMenuItem aboutItem = new JMenuItem("About MiGs");
	
	JLabel cost = new JLabel("Low");
	JLabel titleLabel;
    JLabel yearLabel;
    JLabel weaponLabel;
    JLabel engineLabel;

    JTextField yearField;
    
    JCheckBox w1;
    JCheckBox w2;
    JCheckBox w3;
    JCheckBox w4;
    
    ButtonGroup radioGroup;
    JRadioButton e1;
    JRadioButton e2;
    
    JLabel MiGPicLabel;
	String picFile;
	
	JButton clear = new JButton("Clear");
        
    
	public static void main(String[] args) {
		new EventsWeb();
	}
	
	//Frame constructor
	EventsWeb() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(buildDefault(), BorderLayout.NORTH);
		
		frame.setJMenuBar(menuBar);  
		frame.setSize(300, 200);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	//Builds the panel and menu
	private JPanel buildDefault() {
		panel = new JPanel();
		
	    menuBar.add(menuSelect);
	    menuSelect.add(MiG15Item);
	    menuSelect.add(MiG21Item);
		menuSelect.add(aboutItem);
	    
        MiG15Item.addActionListener(onClick);
        MiG21Item.addActionListener(onClick);
		aboutItem.addActionListener(onClick);

		return panel;
	}
	
	//adds components to the panel
	private void buildMiG() {
		
		MiGPicLabel = new JLabel(new ImageIcon(loadImage(picFile, 200, 200)));
		
	    radioGroup.add(e1);
	    radioGroup.add(e2);

		cost.setForeground(new Color(0, 102, 0));

	    panel.setLayout(new MigLayout());
	    panel.add(titleLabel, "span, center");
		panel.add(new JLabel("Price:"), "left");
		panel.add(cost, "left, span");
	    panel.add(MiGPicLabel, "span, center, gapbottom 10");

	    panel.add(yearLabel, "align label");
	    panel.add(yearField, "wrap");

	    panel.add(weaponLabel, "align label");
	    panel.add(w1);
	    panel.add(w2);
	    if(w4 == null) {
		    panel.add(w3, "wrap");
	    } else {
		    panel.add(w3);
		    panel.add(w4, "wrap");
	    }

	    panel.add(engineLabel);
	    panel.add(e1);
	    panel.add(e2, "wrap");
	    
	    panel.add(clear, "span, center, wrap");

        clear.addActionListener(onClick);
    }

	private Image loadImage(String file, int w, int h) {
		Image pic;
	    try {
	        pic = ImageIO.read(new File(file));
	        pic = pic.getScaledInstance(w, h, Image.SCALE_DEFAULT);
			return pic;
	    } catch(IOException e) {
	    	e.printStackTrace();
	    }
		return null;
	}
		
	//Sets all the labels to the MiG-15 option
	private void setToMiG15() {
		titleLabel = new JLabel("Build your own MiG-15!");
	    yearLabel = new JLabel("Year Built:");
	    weaponLabel = new JLabel(" Weapons:");
	    engineLabel = new JLabel("     Engine:");

	    yearField = new JTextField("1949",3);
	    
	    w1 = new JCheckBox("NR-23 Autocannon");
	    w2 = new JCheckBox("100 kg Bombs");
	    w3 = new JCheckBox("Unguided Rockets");
	    w4 = null;
	    
	    radioGroup = new ButtonGroup();
	    e1 = new JRadioButton("Rolls-Royce Nene");
	    e2 = new JRadioButton("Klimov VK-1");
	    picFile = "mig15.jpg";

		w1.addItemListener(onClick);
		w2.addItemListener(onClick);
		w3.addItemListener(onClick);
	}
	
		//Sets all the labels to the MiG-21 option
	private void setToMiG21() {
		titleLabel = new JLabel("Build your own MiG-21!");
	    yearLabel = new JLabel("Year Built:");
	    weaponLabel = new JLabel(" Weapons:");
	    engineLabel = new JLabel("     Engine:");

	    yearField = new JTextField("1959",3);
	    
	    w1 = new JCheckBox("GSh-23 Autocannon");
	    w2 = new JCheckBox("500 kg Bombs");
	    w3 = new JCheckBox("UB-16-57 Rocket Pods");
	    w4 = new JCheckBox("K-13 Missiles");
	    
	    radioGroup = new ButtonGroup();
	    e1 = new JRadioButton("Mikulin AM-9B");
	    e2 = new JRadioButton("Tumansky R-11");
	    picFile = "boss.png";

		w1.addItemListener(onClick);
		w2.addItemListener(onClick);
		w3.addItemListener(onClick);
		w4.addItemListener(onClick);
	}
	
	//Listens to the menu items, checkbox buttons, and buttons
	class ChangeDisplay implements ActionListener, ItemListener {
		int costCounter = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			//Listens to a change in MiG via menu
			if(e.getSource() == MiG15Item) {
				changePanel(1);
			}
			if(e.getSource() == MiG21Item) {
				changePanel(2);
			}

			//For the clear button
			if(e.getActionCommand().equals("Clear")) {
				radioGroup.clearSelection();
				w1.setSelected(false);
				w2.setSelected(false);
				w3.setSelected(false);
				if(w4 != null) w4.setSelected(false);			
			}

			//Creates an info frame
			if(e.getSource() == aboutItem) {
				aboutFrame();
			}
		}

		//Listens for changes in checkbox selection
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) costCounter++;
			if (e.getStateChange() == ItemEvent.DESELECTED) costCounter--;
			costChange();
		}

		//repaints the panel to the newly selected MiG
		private void changePanel(int mig) {
			panel.removeAll();
			if(mig == 1) {
				costCounter = -2;
				setToMiG15();
			}
			if(mig == 2) {
				costCounter = 1;
				setToMiG21();
			}

			buildMiG();
			frame.pack();
			panel.revalidate();
			panel.repaint();
			costChange();
		}

		private void costChange() {
			if(costCounter == -2) {
				cost.setForeground(new Color(0, 102, 0));
				cost.setText("Very Low");
			}
			if(costCounter == -1 || costCounter == 0) {
				cost.setForeground(new Color(0, 102, 0));
				cost.setText("Low");
			}
			if(costCounter == 1 || costCounter == 2) {
				cost.setForeground(new Color(204,204,0));
				cost.setText("Medium");
			}
			if(costCounter == 3 || costCounter == 4) {
				cost.setForeground(new Color(255, 34, 34));
				cost.setText("High");
			}
			if(costCounter == 5) {
				cost.setForeground(new Color(139, 0, 0));
				cost.setText("Very High");
			}	
		}
	}

	//A new info frame
	void aboutFrame() {
		JFrame aFrame = new JFrame();
		JPanel aPanel = new JPanel();
		aFrame.setSize(800, 330);

		aPanel.setLayout(new MigLayout());
		aPanel.add(new JLabel(new ImageIcon(loadImage("Mikhail_Gurevich.jpg", 200, 200))));
		aPanel.add(new JLabel(new ImageIcon(loadImage("Artem_Mikoyan.jpg", 300, 200))), "wrap");

		aPanel.add(new JLabel("The MiG series of aircraft were first developed in the 1940's, with the creation of the MiG 15 in 1947: the world's first modern jet plane."), "span");
		aPanel.add(new JLabel("The planes are produced by the Russian corperation Mikoyan, and are named after Artem Mikoyan and Mikhail Gurevich, whose last"), "span");
		aPanel.add(new JLabel("names are the namesakes of the series: M(ikoyan) i (\"and\" in Russian) G(urevich)."), "span");

		aFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aFrame.add(aPanel, BorderLayout.NORTH);
		aFrame.setResizable(true);
		aFrame.setLocationRelativeTo(null);
		aFrame.setVisible(true);
	}
}