package graphics;

/* Spinning lines #2.
 * Rotation method: rotating graphics object
 * Lines: objects created in inner class
 * by Mr. Harwood
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpinningLines2 extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new SpinningLines2();
	}

	DrawingPanel panel;
	static int panW = 425;
	static int panH = 450;

	double angle = 0.0;

	//right line
	Line line1 = new Line(212,40,355,280);
	//left line
	Line line2 = new Line(212,40,70,280);
	//bottom line
	Line line3 = new Line(70,280,355,280);

	SpinningLines2(){
		//Set up window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new DrawingPanel();
		this.add(panel);
		this.pack();
		this.setVisible(true);

		Timer timer = new Timer(30, this);
		timer.start();
	}

	class Line {
		double x1,y1,x2,y2;
		double cx,cy;
		
		Line(double a, double b, double c, double d) {
			x1=a; y1=b; x2=c; y2= d;
			cx = (x1+x2)/2.0;
			cy = (y1+y2)/2.0;
		}
		
		void draw(Graphics2D g2) {
			g2.rotate(angle,this.cx,this.cy);
			g2.drawLine( (int)this.x1, (int)this.y1, (int)this.x2,  (int)this.y2);
			g2.rotate(-angle,this.cx,this.cy);
		}
	}

	//inner class
	class DrawingPanel extends JPanel{

		DrawingPanel(){
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(panW, panH));
		}

		@Override
		public void paintComponent(Graphics g) {			
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.GREEN);

			line1.draw(g2);
			line2.draw(g2);
			line3.draw(g2);
		}		
	}

	//event listener for timer (main class)
	@Override
	public void actionPerformed(ActionEvent e) {
		angle = angle + 0.05;
		panel.repaint();
	}
}