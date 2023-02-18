package unit4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationGameExample implements ActionListener {

	public static void main(String[] args) {
		new AnimationGameExample();
	}

	static final int MAXENEMIES = 50;

	JFrame window;
	JPanel panel;
	Player player;
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	MyKL mainKL = new MyKL();
	static int panW = 800, panH = 800;
	Timer timerMovePlayer;
	Timer timerMoveEnemy;

	AnimationGameExample() {
		//Make game objects
		player = new Player(panW/2,panH/2, 60, 60);
		for(int i = 0; i < MAXENEMIES; i++) {
			enemyList.add(new Enemy());
		}

		//GUI setup
		window = new JFrame("Animation gmae");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawingPanel();
		window.add(panel); //the panel will control the size
		window.pack();	   //therefore we need to pack
		window.setVisible(true);

		Timer timerMovePlayer = new Timer(1,this);
		Timer timerMoveEnemy = new Timer(10, new EnemyAL());
		timerMovePlayer.start();
		timerMoveEnemy.start();
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
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			//player
			g2.setStroke(new BasicStroke(3));
			g.setColor(Color.blue);
			g.fillRect(player.x, player.y, player.width, player.height);

			//healthbar
			g.setColor(Color.red);
			g.fillRect(player.x, player.y-20, player.width, 10);
			g.setColor(Color.green);
			g.fillRect(player.x, player.y-20, player.width*player.health/100, 10);
			g.setColor(Color.gray);
			g.drawRect(player.x, player.y-20, player.width, 10);

			//enemies
			g.setColor(Color.red);
			for(Enemy e : enemyList) {
				g.drawOval(e.x, e.y, e.width, e.height);
			}

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

	//This moves all enemies
	class EnemyAL implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(Enemy enemy : enemyList) {
				enemy.y += enemy.speed;
			}

			for(Enemy enemy : enemyList) {
				if(enemy.y > panH) {
					enemyList.remove(enemy);
					break;
				}
			}

			for(int i = 0; i < enemyList.size(); i++) {
				Enemy en = enemyList.get(i);
				if (en.intersects(player)) {
					player.health -= en.damage;
					enemyList.remove(i);
					i--;
				}
			}
			checkWin();
		}

		//Determines if the game is over
		//called from EnemyAL
		void checkWin() {
			if (player.health <= 0) {
				timerMovePlayer.stop();
				timerMoveEnemy.stop();
			}

			if(enemyList.size() == 0) {
				System.out.println("You win!");
				panel.repaint();
				timerMovePlayer.stop();
				timerMoveEnemy.stop();
			}
		}
	}
}
