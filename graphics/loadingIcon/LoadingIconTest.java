package graphics.loadingIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LoadingIconTest extends JFrame{
	public static void main(String[]args) {
		new LoadingIconTest();
	}

	final static int PANW = 800;
	final static int PANH = 800;

	DrawingPanel panel= new DrawingPanel();
	
	Circle circle1 = new Circle(400,200,30);
	Circle circle2 = new Circle(200,400,30);
	Circle circle3 = new Circle(600,400,30);
	Circle circle4 = new Circle(400,600,30);
	Circle rotationpoint = new Circle(PANW/2,PANH/2,10);
	Circle[] circles = {circle1, circle2,circle3,circle4};

	Color[] colours = {Color.green,Color.red,Color.blue};
	
	int timerSpeed = 0;
	Timer timer;
	int tick = 0;
	double angle = 0.05;
	
	LoadingIconTest() {
		this.setSize(PANW,PANH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		panel.setPreferredSize(new Dimension(PANW,PANH));
		this.add(panel);
		this.setVisible(true);
		timer = new Timer(timerSpeed, new MyTimer());
		timer.start();
	}

	class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setBackground(Color.white);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.setColor(Color.white);
			g.fillOval(rotationpoint.x, rotationpoint.y, rotationpoint.width, rotationpoint.height);
			
			g.setColor(Color.black);
			g2.rotate(angle,rotationpoint.x,rotationpoint.y);
			for (int i = 0; i < circles.length; i++) 
				g.fillOval(circles[i].x,circles[i].y,circles[i].width,circles[i].height);
			
	
		}
	}

	class MyTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			for (int i = 0; i < circles.length; i++) {
				circles[i].oscillate(tick);
				circles[i].move();
				circles[i].reCalc();
				}
			rotationpoint.move();
			rotationpoint.reCalc();
            
			angle = angle + 0.05;
			tick++;
			panel.repaint();
		}
	}
}
