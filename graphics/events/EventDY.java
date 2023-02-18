package graphics.events;

//Daniel Yang
//A mini-game where the user clicks a button as fast as they can

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

import net.miginfocom.swing.MigLayout;

public class EventDY {
	public static void main(String[]args) {
		new EventDY();
	}

	/**different components here**/
	//JFrames
	JFrame frame, exitFrame;
	
	JPanel panel;

	//JLabels
	JLabel title, time, counter, cps;

	//JButtons
	JButton startBtn = new JButton("Click to start");
	JButton clickBtn = new JButton("CLICK!");
	JButton exitYes = new JButton("Yes");
	JButton exitNo = new JButton("No");

	JSlider timeSlide;

	//JMenu stuff
	JMenuBar menubar = new JMenuBar();
	JMenu menu = new JMenu("Menu");

	JMenuItem mainMI = new JMenuItem("Main");
	JMenuItem settingsMI = new JMenuItem("Settings");
	JMenuItem infoMI = new JMenuItem("Info");
	JMenuItem exitMI = new JMenuItem("Exit");

	//to make things look nicer
	Color bg = new Color(225, 247, 241);
	Font titleFont = new Font("Sans Serif", Font.BOLD, 20);

	//Timer needed to stop the game
	Timer timer;

	boolean active = false;
	int clicks = 0;
	int playTime = 10;

	EventDY() {
		//Adding everything to the menu
		menubar.add(menu);
		menu.add(mainMI);
		menu.add(settingsMI);
		menu.add(infoMI);
		menu.add(exitMI);

		//Menu actionlisteners
		MenuChange onClick = new MenuChange();
		mainMI.addActionListener(onClick);
		settingsMI.addActionListener(onClick);
		infoMI.addActionListener(onClick);
		exitMI.addActionListener(onClick);

		//Button actionlisteners
		ButtonClick btnClick = new ButtonClick();
		startBtn.addActionListener(btnClick);
		clickBtn.addActionListener(btnClick);
		exitYes.addActionListener(btnClick);
		exitNo.addActionListener(btnClick);

		//Slider changelistener
		sliderChange onSlide = new sliderChange();
		timeSlide = new JSlider(0,10,30,10);
		timeSlide.setBackground(bg);
		timeSlide.setMajorTickSpacing(10);
		timeSlide.setMinorTickSpacing(5);
		timeSlide.setPaintTicks(true);
		timeSlide.setPaintLabels(true);
		timeSlide.setSnapToTicks(true);
		timeSlide.addChangeListener(onSlide);

		panel = new JPanel();

		//Set up frame
		frame = new JFrame("Events");
		frame.setJMenuBar(menubar);
		frame.setSize(500,400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);

		//Goes into the main menu
		changePanel(1);
	}

	void mainMenu() {
		//Set up the panel
		panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 3, insets 10"));
		panel.setBackground(bg);
		frame.add(panel);

		//JLabel for the title
		title = new JLabel("Click");
		title.setFont(titleFont);
		panel.add(title,"align left, wrap");

		time = new JLabel("Time: "+ playTime);
		cps = new JLabel("Last Game CPS: " + clicks/playTime);
		panel.add(time,"align left");
		panel.add(cps,"align left, wrap");

		//JButton
		panel.add(startBtn,"grow");

	}

	void settings() {
		//Set up the frame
		panel = new JPanel();
		frame.add(panel);
		panel.setBackground(bg);

		//Panel setup
		panel.setLayout(new MigLayout("wrap 2, insets 10"));

		//JLabels
		title = new JLabel ("Settings");
		title.setFont(titleFont);
		panel.add(title,"align left, wrap");

		panel.add(new JLabel("Play Time: "));
		panel.add(timeSlide);
		panel.add(new JLabel("Current play time: "+playTime));

	}

	void info() {
		//Set up the frame
		panel = new JPanel();
		frame.add(panel);
		panel.setBackground(bg);

		panel.setLayout(new MigLayout("wrap 1, insets 10"));

		title = new JLabel("Info");
		title.setFont(titleFont);
		panel.add(title,"align left");

		panel.add(new JLabel("Made by Daniel Yang"));
	}

	void exit() {
		panel = new JPanel();
		panel.setBackground(bg);
		panel.add(new JLabel("----->"));
		frame.add(panel);
		
		//set up the exit frame that pops up
		exitFrame = new JFrame("Exit :(");
		exitFrame.setSize(500,400);
		exitFrame.setResizable(false);
		exitFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		exitFrame.setLocationRelativeTo(null);
		exitFrame.setVisible(true);
		
		//set up the exit panel, labels, and buttons
		JPanel exitPanel = new JPanel();
		JLabel exitLabel = new JLabel("Exit?");
		exitLabel.setFont(titleFont);
		exitPanel.setBackground(bg);
		exitPanel.setLayout(new MigLayout("wrap 2, insets 10"));
		exitPanel.add(exitLabel,"wrap");
		exitPanel.add(exitYes);
		exitPanel.add(exitNo);
		
		exitFrame.add(exitPanel);
		exitFrame.pack();
	}
	
	void changePanel(int choice) {
		//method with switch statements to change the panels with the menuitems
		frame.remove(panel);
		switch (choice) {
		case 1:
			mainMenu();
			break;	
		case 2:
			settings();
			break;
		case 3:
			info();
			break;
		case 4:
			exit();
			break;
		}
		frame.pack();
		panel.revalidate();
		panel.repaint();
	}
	
	class MenuChange implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {	
			//Button listener for the menuitems that calls the changePanel method
			if (e.getSource() == mainMI) {
				changePanel(1);
			}
			if (e.getSource() == settingsMI) {
				changePanel(2);
			}
			if (e.getSource() == infoMI) {
				changePanel(3);
			}
			if (e.getSource() == exitMI) {
				changePanel(4);
			}
		}
	}
	
	class ButtonClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Button listeners for the game
			if (e.getSource().equals(startBtn)) {				
				panel.remove(startBtn);
				panel.add(clickBtn);
				
				counter = new JLabel(""+clicks);
				panel.add(counter);

				timer = new Timer(playTime * 1000, new GameTimer());
				timer.start();

				frame.pack();
				panel.revalidate();
				panel.repaint();
				active = true;
			}
			if (e.getSource().equals(clickBtn)) {
				if (active) {
					clicks++;
					counter.setText(""+clicks);
				}
			}
			
			//Button listeners for the exit frame
			if (e.getSource().equals(exitYes)) {
				System.exit(0);
			}
			if (e.getSource().equals(exitNo)) {
				changePanel(1);
				exitFrame.dispose();
			}
		}
	}

	class GameTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		//Timer to stop the game
			try {
				active = false;
				timer.stop();
				clickBtn.setText("STOP");
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			//reset the game
			changePanel(1);
		}
	}

	class sliderChange implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			//slider used to change the time limit
			if (e.getSource() == timeSlide) {
				playTime = (int) ((JSlider) e.getSource()).getValue();
				changePanel(2);
			}
		}		
	}
}
