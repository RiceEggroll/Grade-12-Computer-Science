package graphics;

//Daniel Yang
//April 20, 2022
//This program uses many of the different functions of Graphics and draws a very nice picture
//This picture may or may not be of a guy dying of a heat stroke

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing_DY extends JFrame{

	public static void main(String[] args) {
		new Drawing_DY();
	}

	final static int PANW = 800;
	final static int PANH = 600;
	Color blue = new Color(0, 140, 255);
	
	Drawing_DY() {
		this.setSize(PANW,PANH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("A very nice picture");

		DrawingPanel panel = new DrawingPanel();
		//panel.setBackground(Color.CYAN);
		this.add(panel);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


	class DrawingPanel extends JPanel {

		DrawingPanel() {
			this.setBackground(blue);
		}

		//All drawing happens here
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			//			AntiAliasing
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			//			Sun
			g.setColor(new Color(246, 255, 0));
			g.fillOval(50, 50, 100, 100);

			//			Sun radiance
			g.setColor(new Color(246, 255, 0, 127));
			g.fillOval(30, 30, 140, 140);

			//			Sunray that kills the guy
			Polygon sunray = new Polygon();
			sunray.addPoint(75, 75);
			sunray.addPoint(400, 500);
			sunray.addPoint(550, 500);
			g.setColor(new Color(246, 255, 0, 200));
			g.fillPolygon(sunray);

			g.setColor(new Color(91, 219, 0));
			g.fillRect(0, 500, 800, 100);

			//g.setColor(new Color(27, 178, 224,150));
			g.setColor(Color.BLUE);
			g.fillArc(520,500,10,10,0,-180);
			//Inside for head
			g.setColor(Color.white);
			g.fillOval(500, 480, 50, 50);

			//Outline for head
			g2.setStroke(new BasicStroke(3));
			g.setColor(Color.BLACK);
			g.drawOval(500, 480, 50, 50);

			//Sweat
			g.setColor(blue);
			Polygon sweat = new Polygon();
			sweat.addPoint(515, 498);
			sweat.addPoint(521,498);
			sweat.addPoint(518, 490);
			g.fillPolygon(sweat);
			g.fillArc(515,495,6,6,0,-180);

			sweat.reset();
			sweat.addPoint(540, 508);
			sweat.addPoint(546,508);
			sweat.addPoint(543, 500);
			g.fillPolygon(sweat);
			g.fillArc(540, 505, 6, 6, 0, -180);

			g.setColor(Color.BLACK);
			//Body
			g.fillRect(400, 505, 100, 3);
			//Arms
			g.fillRect(470, 470, 3, 70);

			Polygon leg1 = new Polygon();
			//Bottom leg
			leg1.addPoint(400, 505);
			leg1.addPoint(350, 550);
			leg1.addPoint(350, 553);
			leg1.addPoint(403, 505);
			g.fillPolygon(leg1);

			//Top legs
			Polygon leg2 = new Polygon();
			leg2.addPoint(400, 505);
			leg2.addPoint(330, 500);
			leg2.addPoint(330, 503);
			leg2.addPoint(400, 508);
			g.fillPolygon(leg2);


			g.setFont(new Font("Arial", Font.PLAIN, 18));
			g.drawString("i am have heatstroke", 590, 400);

			g2.setStroke(new BasicStroke(2));
			g.drawLine(550, 480, 615, 410);

			clouds(g, (int)(Math.random()*500)+200);
			clouds(g, (int)(Math.random()*500)+200);

			//Text after the clouds so that it does not get blocked
			g.setColor(Color.black);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			g.drawString("summer is dangerous!!!", 350, 150);
		}

		void clouds(Graphics g, int randomCloud) {
			//A random cloud that never goes into the sun because it wouldn't make sense for a sunray to go through a cloud
			g.setColor(new Color (255,255,255,190));
			g.fillOval(randomCloud, 95, 70, 70);
			g.fillOval(randomCloud+30, 83, 60, 60);
			g.fillOval(randomCloud-20, 98, 60, 60);
			g.fillOval(randomCloud-2, 80, 50, 50);
			g.fillOval(randomCloud+35, 107, 65, 65);
		}
	}
}
