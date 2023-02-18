package unit4;

//Daniel Yang
//This program reads an image from a file and displays it
//It also plays around with the different parameters

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagesDY {

	public static void main(String[]args) {
		new ImagesDY();
	}

	ImagesDY() {
		JFrame window = new JFrame("feesh");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawingPanel panel = new DrawingPanel();
		window.add(panel);
		window.pack();	   
		window.setVisible(true);
		window.setResizable(false);

	}

	private class DrawingPanel extends JPanel{
		DrawingPanel() {
			this.setBackground(Color.white);
			this.setPreferredSize(new Dimension(600,600));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Image img = loadImage("fish.png");
			Image background = loadImage("underwater.jpg");

			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

			g.drawImage(img, 200, 200, 100, 80, null); //normal image
			g.drawImage(img, 200, 200, -100, 80, null); //flipped horizontally
			g.drawImage(img, 200, 200, 100, -80, null); //flipped vertically
			g.drawImage(img, 200, 200, -100, -80, null); //flipped both ways

			int width  = img.getWidth(null);
			int height = img.getHeight(null);
			
			g.drawImage(img, 300, 300, 400, 450, 0, 0, width/2, height, null); //first half of the fish
			g.drawImage(img, 400, 150, 500, 300, width/2, 0, width, height, null); //second half of the fish

		}

		private BufferedImage loadImage(String filename) {
			BufferedImage img = null;			
			try {
				img = ImageIO.read(new File(filename));
			} catch (IOException e) {
				System.out.println(e.toString());
				JOptionPane.showMessageDialog(null, "An image failed to load: " + filename , "ERROR", JOptionPane.ERROR_MESSAGE);
			}

			return img;
		}
	}
}