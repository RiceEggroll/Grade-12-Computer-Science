package unit4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovingRectangle {

	public static void main(String[] args) {
		new MovingRectangle();
	}

	JFrame window;
	JPanel panel;
	Rectangle player;
	static int panW = 800, panH = 700;
	
	MovingRectangle() {
		player = new Rectangle(panW/2,panH/2, 100, 60);
		
		window = new JFrame("Moving Rectangle");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawingPanel();
		window.add(panel); //the panel will control the size
		window.pack();	   //therefore we need to pack
		window.setVisible(true);
	}
	
	private class DrawingPanel extends JPanel implements KeyListener{
		DrawingPanel() {
			this.setBackground(Color.white);
			this.setPreferredSize(new Dimension(panW,panH));
			this.addKeyListener(this);
			this.setFocusable(true); //need something like this to get focus
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.green);
			g.fillRect(player.x, player.y, player.width, player.height);
		}


		@Override
		public void keyPressed(KeyEvent e) {
			//WASD
			if (e.getKeyCode() == 'W' && player.y > 0)player.y-=5;
			if (e.getKeyCode() == 'A' && player.x > 0)player.x-=5;
			if (e.getKeyCode() == 'S' && player.y + player.height < panH)player.y+=5;
			if (e.getKeyCode() == 'D' && player.x + player.width < panW)player.x+=5;

			//Arrows
			if (e.getKeyCode() == 37 && player.x > 0)player.x-=5;
			if (e.getKeyCode() == 38 && player.y > 0)player.y-=5;
			if (e.getKeyCode() == 39 && player.x + player.width < panW)player.x+=5;
			if (e.getKeyCode() == 40 && player.y + player.height < panH)player.y+=5;
			
			
			
			this.repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) { //slow
		}

	}
	
}
