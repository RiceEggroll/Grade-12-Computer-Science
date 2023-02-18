package graphics;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GridAndTimer {
	public static void main(String[]args) {
		new GridAndTimer().run();
	}
	
	JPanel panel; 
	Timer timer;
	
	GridAndTimer() {
		JFrame window = new JFrame();
		window.setSize(800,700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null); // centers the window
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,3)); // 5 rows 3 columns
		panel.setBackground(Color.LIGHT_GRAY);
		
		window.add(panel);
		window.setVisible(true);
		
		timer = new Timer(1, new TimerListener()); // first value is ms
	}
	
	void run() {
		timer.start();
	}
	
	class TimerListener implements ActionListener{
		int num = 1;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			panel.add(new JButton(""+num));
			panel.validate();
			num++;
			if (num < 15) {
				timer.stop();
			}
		}
		
	}
}