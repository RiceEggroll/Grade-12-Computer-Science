package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MandelBrot extends JPanel{

	public static void main(String[] args) {
		new MandelBrot();
	}

	//constants
	static int PANW = 1000;
	static int PANH = 800;
	static int MAXITER = 255;

	//globals
	double xmin = -2.5;
	double xmax = +1.0;
	double ymin = -1.0;
	double ymax = +1.0;
	double xstep = (xmax - xmin) / PANW; //x increment for one pixel
	double ystep = (ymax - ymin) / PANH;

	MandelBrot() {
		//		If you don't want to make JFrame a global variable, use a return statement
		JFrame w = SetupGUI();
		w.setVisible(true);
		//		SetupGUI().setVisible(true); also works
	}

	JFrame SetupGUI() {
		JFrame window = new JFrame("MandelBrot");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		Set properties of JPanel
		this.setPreferredSize(new Dimension(PANW, PANH));
		window.add(this);
		window.pack();
		window.setLocationRelativeTo(null);

		return window;
	}

	@Override //This method runs upon "setVisible(true)"
	protected void paintComponent(Graphics g) {
		//go through each point
		for (double x = xmin; x < xmax; x+= xstep) {
			for (double y = ymin; y < ymax; y+= ystep) {
				Color clr = Color.white;
				int n = iterate(x,y);

				if (n == 0) clr = Color.black;
				else {
					clr = Color.getHSBColor(n/255.0f,1.0f,1.0f);
				}
				plotPoint(x,y,g,clr);
			}
		}
	}

	int iterate(double cReal, double cImag) {
		double zReal = 0.0;
		double zImag = 0.0;
		double temp  = 0.0;

		int i = 0;

		while (i++ <= MAXITER) {
			//		Calculate the next z
			temp = zReal*zReal - zImag*zImag + cReal;
			zImag = 2 * zReal*zImag + cImag;
			zReal = temp;

			//		If z^2 is more than 4, then it's close to infinity
			if (zReal*zReal + zImag*zImag > 4) break;
		}

		//		Ternary operator - if statement with true : false

		//		If have not gotten past 4 after 255 iterations,
		//		then return 0. Otherwise return the number of iterations
		//		that it took to get past 4
		return (i > MAXITER) ? 0 : i;
	}
	
	void plotPoint(double x, double y, Graphics g, Color c) {
		int px = (int) ((PANW * (x - xmin)) / (xmax - xmin));
		int py = (int) ((PANH * (y - ymin)) / (ymax - ymin));
		py = PANH - py; //to invert the y axis
		
		g.setColor(c);
		g.fillRect(px, py, 1,1);
	}
}
