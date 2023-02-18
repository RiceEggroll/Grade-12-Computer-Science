package unit4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MovingRectangle2 implements ActionListener {

	public static void main(String[] args) {
		new MovingRectangle2();
	}

	JFrame window;
	JPanel panel;
	Rectangle player;
	MyKL mainKL = new MyKL();
	static int panW = 800, panH = 500;

	MovingRectangle2() {
		player = new Rectangle(panW/2,panH/2, 100, 60);

		window = new JFrame("Moving Rectangle");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawingPanel();
		window.add(panel); //the panel will control the size
		window.pack();	   //therefore we need to pack
		window.setVisible(true);
		Timer timer = new Timer(1,this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (mainKL.isKeyDown('W') && player.y > 0)player.y-=5;
		if (mainKL.isKeyDown('A') && player.x > 0)player.x-=5;
		if (mainKL.isKeyDown('S') && player.y + player.height < panH)player.y+=5;
		if (mainKL.isKeyDown('D') && player.x + player.width < panW)player.x+=5;	
		
		panel.repaint();
	}

	private class DrawingPanel extends JPanel{
		DrawingPanel() {
			this.setBackground(Color.white);
			this.setPreferredSize(new Dimension(panW,panH));
			this.addKeyListener(mainKL);
			this.setFocusable(true); //need something like this to get focus
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.green);
			g.fillRect(player.x, player.y, player.width, player.height);
		}
	} //end of drawing class
	
	class MyKL implements KeyListener {
		private boolean[] keys = new boolean[256];

		boolean isKeyDown(int n) {
			return keys[n];
		}
		@Override
		public void keyPressed(KeyEvent e) {
			keys[e.getKeyCode()] = true;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			keys[e.getKeyCode()] = false;
		}

		@Override
		public void keyTyped(KeyEvent e) {} //slow
	}
}

