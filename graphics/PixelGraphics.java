package graphics;

import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelGraphics {

	public static void main(String[] args) {
		new PixelGraphics();
	}
	
	PixelGraphics() {
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
			
			g.setColor(Color.red);
			for (int x = 0; x < panW; x++) {
				for (int y = 0; y < panH; y++) {
					
					int r = (y^50)%256;
					int gr = (x^50)%256;
					int b = (x*y)%256;
					
					Color c = new Color(r,gr,b);
					g.setColor(c);
					g.drawLine(x, y, x, y);
				}
			}
		}
	}
}
