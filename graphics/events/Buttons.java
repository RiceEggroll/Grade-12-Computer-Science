package graphics.events;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Buttons {
	public static void main(String[] args) {
		new Buttons();
	}

	JFrame window;
	JPanel panel;
	JButton btn1;
	JLabel count = new JLabel("0");
	int counter=0;
	
	Buttons(){
		panel = new JPanel();
		panel.add(count);
		btn1 = new JButton("Red");
		btn1.setActionCommand("R");
		JButton btn2 = new JButton("Blue");
		JButton btn3 = new JButton("Green");
		JButton btn4 = new JButton("DIE");
		
		btn1.addActionListener(new MyAL());
		btn1.addActionListener(new MoveAL());
		btn2.addActionListener(new MyAL());
		btn2.addActionListener(new MoveAL());
		btn3.addActionListener(new MyAL());
		btn3.addActionListener(new MoveAL());
		btn4.addActionListener(new MyAL());


		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);

		window = new JFrame("Events demo");
		window.setSize(500,400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		window.setVisible(true);
	}	

	//Inner class. Must implement ActionListener
	class MyAL implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals("R")) {
				panel.setBackground(Color.RED);
				btn1.setText("Bad choice");
			}
			if (e.getActionCommand().equals("Blue")) {
				panel.setBackground(Color.BLUE);
			}
			if (e.getActionCommand().equals("Green")) {
				panel.setBackground(Color.GREEN);
			}
			if (e.getActionCommand().equals("DIE")) {
				window.setTitle("Die");
				while(true) {
					panel.setBackground(Color.red);					
					window.setLocation((int)(700*Math.random()), (int)(700*Math.random()));
				}
			}
		}	
	}
	class MoveAL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			window.setLocation((int)(700*Math.random()), (int)(700*Math.random()));
			
			counter++;
			count.setText(""+counter);
		}
		
	}

}