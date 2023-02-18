package unit4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Explode extends JFrame {
	public static void main(String[] args) {
		new Explode();
	}
	
	static final int SCRW = 600;
	static final int SCRH = 400;
	
	//The Sprite sheet has 7 rows of sprites, each consisting of 14 frames.
	//Each sprite is 64x64 pixels.
	static final int spriteW = 64;
	static final int spriteH = 64;
	static final int spriteMAXROW = 8;
	static final int spriteMAXFRAME = 14;	
	
	Image imgExplode = null;
	Image imgAsteroid = null;
	Rectangle asteroid;
	GrPanel panel;
	
	Explode() {
		this.setTitle("Asteroid");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setSize(SCRW, SCRH);
		this.setLocationRelativeTo(null);

		panel = new GrPanel();
		panel.setBackground(Color.BLACK); 
		panel.addMouseListener(panel);
		this.add(panel,BorderLayout.CENTER);
		
		imgExplode = loadImage("explosions.png");

		asteroid = new Rectangle (-1,-1, spriteW, spriteH);
		imgAsteroid = loadImage("green_asteroid.png");  //this should be an image of size spriteW x spriteH
		placeAsteroid();

		this.setVisible(true);
	}

	/* This method loads an Image object from a file.
	 * It assumes that the file is in a resource folder that's in the classpath. */
	Image loadImage(String filename) {
		Image image = null;	
		URL imageURL = this.getClass().getResource(filename);
		if (imageURL != null) {
			ImageIcon icon = new ImageIcon(imageURL);
			image = icon.getImage();
		} else { 
			JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return image;
	}

		
	void placeAsteroid() {
		/* (SCREEN WIDTH - width of image - 2*25 border) + 25 (border on left)		 */
		asteroid.x = (int)(Math.random()*(SCRW-2*spriteW)) + spriteW;
		asteroid.y = (int)(Math.random()*(SCRH-2*spriteH)) + spriteH;
		this.setTitle("Asteroid"); //set title back to normal
	}


	private class GrPanel extends JPanel implements MouseListener {

		private boolean explosionRunning = false;
		private int exploNum = 0; //row of explosion
		private int frame = 0;  //column
				
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			/*******
			drawImage parameters:
			img = image
			x, y = location of top left corner of image
			width, height = place image in this rectangle  (scaled to fit?)

			bcolor
			imageobserver: notify when image has been completely drawn

			drawImage(Image img,
				int dx1,int dy1,int dx2,int dy2,
				int sx1,int sy1,int sx2,int sy2,ImageObserver observer)
			dx = destination rectangle coords (x1,y1)-(x2,y2)  NOT like drawRect()
			sx = source rectangle coords
			The subimage is scaled and flipped as needed to preserve those mappings.

			******/

			if (explosionRunning) {
				//sprite width = 64, height = 64
				g.drawImage(imgExplode,							
					asteroid.x, asteroid.y, asteroid.x + asteroid.width, asteroid.y + asteroid.height,  //destination
					frame * spriteW, exploNum * spriteH, (frame+1) * spriteW, (exploNum+1) * spriteH,						
					null);
			} else {				
				g.drawImage(imgAsteroid, 
					asteroid.x, asteroid.y, asteroid.x + asteroid.width, asteroid.y + asteroid.height,  //destination
					0,0, asteroid.width, asteroid.height,												//source
					null);				
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (explosionRunning) return;
			if (asteroid.contains(e.getPoint())) runExplosion();			
		}

		
		void runExplosion() {
			
			//set title of frame
			Explode.this.setTitle("Asteroid explosion style " + exploNum);
						
			//here we define the TimerListener class and Timer...
			//This is an inner class in a method inside another inner class!
			class MyTimerListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					panel.repaint();
					frame++;
					
					//stop the timer from inside the ActionListener: use the event object to get the source (and cast it)
					if (frame == spriteMAXFRAME) { 
						((Timer)e.getSource()).stop();
						explosionRunning = false;			
						frame = 0;
						exploNum++;
						if (exploNum == spriteMAXROW) exploNum = 0;
						placeAsteroid();
						panel.repaint();
					}
				}
			}
						
			Timer myTimer = new Timer(80, new MyTimerListener());   //Here we specify how fast the animation goes
			explosionRunning = true;
			myTimer.start();	
		}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}

	}
}

