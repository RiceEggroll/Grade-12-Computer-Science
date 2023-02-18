package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoadingIconBall {
	public static void main(String[]args) {
		new LoadingIconBall();
	}

	//constants
	int PANW = 1200;
	int PANH = 700;
	
	Ball ball = new Ball();
	DrawingPanel panel = new DrawingPanel();

	LoadingIconBall(){
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);	
		window.setVisible(true);	
		window.setSize(PANW,PANH);
		window.add(panel);
	}

	class DrawingPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillOval(ball.x, ball.y, 30, 30);
			ball.move();
		}
	}

	class Ball {
		int x,y;	//position
		int vx, vy;	//speed

		Ball(){
			//Do something in the constructor to set the position and speeds.
			x = 0;
			y = 0;
			vx = 1;
			vy = 1;
		}

		void move() {
			x += vx;
			y += vy;

			panel.repaint();
			//needs to bounce off edges
		
			if (x == PANW-40) vx = -1;
			if (y == PANH-60) vy = -1;
			if (x == 0) vx = 1;
			if (y == 0) vy = 1;
			//needs to check for collisions
		}
	}
}
