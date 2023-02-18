package graphics;

/* Spinning lines.
 * Rotation method: rotating graphics object
 * Lines: multiple variables, not objects.
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

public class SpinningLines extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new SpinningLines();
	}

	DrawingPanel panel;
	static int panW = 425;
	static int panH = 450;
	
	//lines
	double x1, y1, x2, y2;
	double a1, b1, a2, b2;
	double c1, d1, c2, d2;
	
	double angle = 0.0;

	SpinningLines(){

		//right line
		x1 = 212;
		x2 = 355;
		y1 = 40;
		y2 = 280;

		//left line
		a1 = 212;
		b1 = 40;
		a2 = 70;
		b2 = 280;

		//bottom line
		c1 = 70;
		d1 = 280;
		c2 = 355;
		d2 = 280;

		//Set up window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new DrawingPanel();
		this.add(panel);
		this.pack();
		this.setVisible(true);

		Timer timer = new Timer(30, this);
		timer.start();
	}

	
	class DrawingPanel extends JPanel{

		double cx = (x1+x2)/2.0;
		double cy = (y1+y2)/2.0;

		double ca = (a1+a2)/2.0;
		double cb = (b1+b2)/2.0;

		double cc = (c1+c2)/2.0;
		double cd = (d1+d2)/2.0;
		
		DrawingPanel(){
			this.setBackground(new Color(0,0,0,20));
			this.setPreferredSize(new Dimension(panW, panH));
		}

		@Override
		public void paintComponent(Graphics g) {			
			super.paintComponent(g); //paint background

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(3));
			
			//drawing lines
			g2.setColor(Color.GREEN);
			g2.rotate(angle,cx,cy);
			g2.drawLine( (int)x1, (int)y1, (int)x2,  (int)y2);
			g2.rotate(-angle,cx,cy);
			g2.setColor(Color.BLUE);
			g2.rotate(angle,ca,cb);
			g.drawLine( (int)a1, (int)b1, (int)a2,  (int)b2);
			g2.rotate(-angle,ca,cb);
			g2.setColor(Color.YELLOW);
			g2.rotate(angle,cc,cd);
			g.drawLine( (int)c1, (int)d1, (int)c2,  (int)d2);
			g2.rotate(-angle,cc,cd);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		angle = angle + 0.05;
		panel.repaint();
	}
}
