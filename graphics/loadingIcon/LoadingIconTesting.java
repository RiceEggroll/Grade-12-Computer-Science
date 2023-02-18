package graphics.loadingIcon;

import java.awt.Color;
import java.awt.Component;
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


//have a square with a line that rotates inside the square
//http://static.demilked.com/wp-content/uploads/2016/06/gif-animations-replace-loading-screen-3.gif
//have an invisible line with circles that go around the perimeter of the square
//rotate 90 degrees

public class LoadingIconTesting extends JFrame {
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
	
//	Color red = new Color(245, 44, 37);
//	Color orange = new Color (252, 117, 33);
//	Color yellow = new Color (255, 248, 43);
//	Color green = new Color (81, 255, 33);
//	Color blue = new Color (80, 65, 250);
//	Color colours[] = {red, orange, yellow, green, blue};
	
	double angle;
	double wholeAngle = 0.01;
	int radianDivision = 64;
	boolean divide = true;
	
	int rotation = 0;
	boolean rotate = true;
	boolean rotationPoint = false; 	//circle1 and circle5 are the only ones that are gonna be the rotation points
	//circle1 is false, circle5 is true

	DrawingPanel panel= new DrawingPanel();

	int timerSpeed = 10;
	Timer timer;

	LoadingIconTesting() {
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
			this.setBackground(Color.gray);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
//			g.setColor(new Color(100,0,144));
			g.drawRect(Rect.x, Rect.y, Rect.width, Rect.height);
			
			g2.setPaint(new GradientPaint(PANW/2,180,new Color(136, 17, 247),PANW/2,620,new Color(8, 255, 115)));
			g2.rotate(angle, arrCircle[0].x+(arrCircle[0].width/2), arrCircle[0].y+arrCircle[0].height/2);
			for (int i = 0; i < arrCircle.length; i++) {
				g2.rotate(wholeAngle,PANW/2,PANH/2);
				g.fillOval(arrCircle[i].x,arrCircle[i].y,arrCircle[i].width,arrCircle[i].height);
			}
		}
	}

	class MyTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			wholeAngle += 0.01;
			if (rotate) angle += Math.PI/radianDivision;

			if (angle > (Math.PI/2)) {
				angle = Math.PI/2;
				rotate = false;
				rotation++;
				if (rotation == 4) rotation = 0;

				setValues();
			}

			panel.repaint();
		}

		void setValues() {
			//Variables to set the x/y values 
			int xValue;
			int yValue;

			switch(rotation) {
			case 0:
				xValue = 230;
				yValue = 180;
				for (int i = 0; i < arrCircle.length; i++) {
					arrCircle[i].x = xValue;
					arrCircle[i].y = yValue;
					xValue += 100;
				}
	
				if (divide) radianDivision /= 2;
				if (!divide) radianDivision *= 2;
				if (radianDivision == 2) divide = false; 
				if (radianDivision == 128) divide = true;
				
				break;
			
			case 1:
				xValue = 230;
				yValue = 580;
				for (int i = 0; i < arrCircle.length; i++) {
					arrCircle[i].x = xValue;
					arrCircle[i].y = yValue;
					yValue -= 100;
				}
				break;
				
			case 2:
				xValue = 630;
				yValue = 580;
				for (int i = 0; i < arrCircle.length; i++) {
					arrCircle[i].x = xValue;
					arrCircle[i].y = yValue;
					xValue -= 100;
				}
				break;
				
			case 3:
				xValue = 630;
				yValue = 180;
				for (int i = 0; i < arrCircle.length; i++) {
					arrCircle[i].x = xValue;
					arrCircle[i].y = yValue;
					yValue += 100;
				}
				break;
			}
			

			angle = 0;
			rotate = true;
		}
	}
}