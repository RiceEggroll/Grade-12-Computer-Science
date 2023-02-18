package graphics.loadingIcon;

//Daniel Yang
//This loading icon is a line of circles that rotate upon itself
//and outlines a square

//beware: lots of hardcoding because rotate() works weirdly

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class LoadingIconDY extends JFrame {
	public static void main(String[]args) {
		new LoadingIconDY();
	}

	final static int PANW = 900;
	final static int PANH = 900;

	Rectangle Rect = new Rectangle(PANW/2-200,PANH/2-250,400,400);
	Circles circle1 = new Circles(250,200,20);
	Circles circle2 = new Circles(350,200,20);
	Circles circle3 = new Circles(450,200,20);
	Circles circle4 = new Circles(550,200,20);
	Circles circle5 = new Circles(650,200,20);
	Circles arrCircle[] = {circle1, circle2, circle3, circle4, circle5};

	//247, 54, 54
	int red = 255;
	int green = 54;
	int blue = 54;
	boolean colourReverse = false;

	double angle;
	int radianDivision = 350;

	int rotation = 0;

	DrawingPanel panel= new DrawingPanel();

	int timerSpeed = 5;
	Timer timer;

	LoadingIconDY() {
		this.setSize(PANW,PANH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.add(panel);
		this.setVisible(true);
		this.setResizable(false);

		timer = new Timer(timerSpeed, new MyTimer());
		timer.start();
	}

	class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setBackground(new Color(206, 245, 220));
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setPaint(new GradientPaint(PANW/2,180,new Color(136, 17, 247),PANW/2,620,new Color(8, 255, 115))); 
			g.drawRect(Rect.x, Rect.y, Rect.width, Rect.height);

			g2.rotate(angle, arrCircle[0].x+(arrCircle[0].width/2), arrCircle[0].y+arrCircle[0].height/2);
//			247, 54, 54
			g.setColor(new Color(red,green,blue));

			for (int i = 0; i < arrCircle.length; i++) {
				g.fillOval(arrCircle[i].x,arrCircle[i].y,arrCircle[i].width,arrCircle[i].height);
			}
		}
	}

	class MyTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			//Adds the rotation
			angle += Math.PI/radianDivision;
				
			//Acceleration
			if (angle >= Math.PI/32) radianDivision = 128;
			if (angle >= 3*Math.PI/32) radianDivision = 64;
			if (angle >= 3*Math.PI/8) radianDivision = 128;
			if (angle >= 7*Math.PI/16) radianDivision = 350;

			changeColour();
			
			//Stops rotation once it goes 90 degrees (PI/2)
			if (angle > (Math.PI/2)) {
				angle = Math.PI/2;

				rotation++;
				setValues();
				
				//Reverse the colour
				if (colourReverse) colourReverse = false;
				else colourReverse = true;
			}

			panel.repaint();
		}

		void changeColour () {
			if (!colourReverse) {
				red -= 3;
				green += 2;
				blue += 3;
			}
			if (colourReverse) {
				red += 3;
				green -= 2;
				blue -= 3;
			}
		}
		
		void setValues() {
			//Variables to set the x/y values 
			int xValue = 0;
			int yValue = 0;
			Circles.resetPosition(rotation, arrCircle, xValue, yValue);

			//Reset the angle
			angle = 0;
		}
	}
}