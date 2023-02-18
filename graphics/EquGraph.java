package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EquGraph {

	public static void main(String[] args) {
		new EquGraph();
	}
	
	double xmin = -5.0, xmax = +5.0;
	double ymin = -5.0, ymax = +5.0;
	double xstep; //one pixel increases x by this value
	
	EquGraph() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);	
		window.setVisible(true);	
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		window.setSize(800,600);
		DrawingPanel panel = new DrawingPanel();
		window.add(panel);
	}

	private class DrawingPanel extends JPanel {
		int panW, panH;
		DrawingPanel() {

		}

		@Override
		public void paintComponent(Graphics g) {
			panW = this.getWidth();			
			panH = this.getHeight();
			xstep = (xmax-xmin)/(double)panW;
			
			drawAxes(g,Color.BLACK);
			
			for (double x = xmin; x < xmax; x += xstep) {				
				//equation comes here
				// y = 1/2 x^2 -1
				double y = 1/Math.sin(x);				
				plotPt(x,y,g, Color.BLUE);				

			}
		}
		
		void drawAxes(Graphics g, Color c) {
			g.setColor(c);
			int x = 0, y=0;
			//y-axis
			int px = (int) ((panW * (x - xmin)) / (xmax - xmin));
			g.drawLine(px,0,px,panH);
			//x-axis
			int py = (int) ((panH * (y - ymin)) / (ymax - ymin));
			py = panH - py; //to invert the y axis
			g.drawLine(0, py,panW,py);
		}
		
		void plotPt(double x, double y, Graphics g, Color c) {
			int px = (int) ((panW * (x - xmin)) / (xmax - xmin));
			int py = (int) ((panH * (y - ymin)) / (ymax - ymin));
			py = panH - py; //to invert the y axis
			
			g.setColor(c);
			g.fillRect(px, py, 1,1);
		}
	}
}