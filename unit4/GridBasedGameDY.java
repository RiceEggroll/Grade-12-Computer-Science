package unit4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridBasedGameDY {
	public static void main(String[] args) {
		new GridBasedGameDY();
	}

	final static int SIZE = 10;
	final static int PANW = 600;
	final static int PANH = 600;
	int space = 3;

	//WHite, BLack, eMpTy (this entire word is comprised of silent letters)
	final static int WH = 1;
	final static int BL = -1;
	final static int MT = 0;
	
	//Counters
	int white = 0;
	int black = 0;

	//sizes of each box
	static int boxW = PANW/SIZE;
	static int boxH = PANH/SIZE;

	boolean playerTurn = true; //white is true, black is false

	int[][] board = new int[SIZE][SIZE];

	boolean[][] checked = new boolean[SIZE][SIZE];
	boolean delete = true;



	GridBasedGameDY() {
		init();
		setUpGUI();

		//		board[4][2] = BL;
	}

	void init() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = MT;
			}
		}
	}

	void setUpGUI() {
		JFrame window = new JFrame("Baduk");
		DrawingPanel panel = new DrawingPanel();

		window.add(panel, BorderLayout.CENTER);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(600,0);
		window.setVisible(true);
	}

	class DrawingPanel extends JPanel implements MouseListener {
		DrawingPanel() {
			this.setBackground(new Color(255,211,154));
			this.setPreferredSize(new Dimension(PANW, PANH));
			this.addMouseListener(this);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			//invisible board where the pieces are between the lines
			//then make the actual board in which the stones are going to be on the intersections
			for (int i = 0; i < SIZE; i++) {
				g.drawLine(i*boxW, 0, i*boxW, PANH);
				g.drawLine(0, i*boxH, PANW, i*boxH);
			}
			
			white = 0;
			black = 0;
			
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					//Draw black or white stones here
					
					if (board[row][col] == BL) {
						black++;
						g.setColor(Color.black);
						g.fillOval(col*boxW+space, row*boxH+space, 60-space*2, 60-space*2);
					}
					if (board[row][col] == WH) {
						white++;
						g.setColor(Color.white);
						g.fillOval(col*boxW+space, row*boxH+space, 60-space*2, 60-space*2);
					}

				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			/*    
			 *    r1 r2 r3 r4 r5 ...
			 * c1
			 * c2
			 * c3
			 * c4
			 * c5
			 * ...
			 */

			int row = e.getY()/boxH;
			int col = e.getX()/boxW;

			drawStones(row, col);
			if (playerTurn) checkBoard(row,col,BL);
			if (!playerTurn) checkBoard(row,col,WH);
			playerTurn = !playerTurn;
			this.repaint();
		} 	

		void drawStones(int row, int col) {
			if (playerTurn && board[row][col] == MT) board[row][col] = WH;
			if (!playerTurn && board[row][col] == MT) board[row][col] = BL;
		}

		void checkBoard(int row, int col, int colour) {
			//I had dennis and web help me with checkBoard and checkNeighbour
			ArrayList<Point> remove = new ArrayList<Point>();
			checked = new boolean[SIZE][SIZE];
			
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (board[i][j] == colour && !checked[i][j]) remove = checkNeighbours(i, j, colour);					
				}
			}
			if (remove.isEmpty()) return;
			for (int i = 0; i < remove.size(); i++) {
				System.out.println(remove.get(i).x + " " + remove.get(i).y);
				board[remove.get(i).y][remove.get(i).x] = MT;
			}
			
		}
		
		ArrayList<Point> checkNeighbours (int row, int col, int colour) {
			//l r d u
			int modifiers[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
			
			Queue<Point> queue = new LinkedList<>();
			ArrayList<Point> dead = new ArrayList<Point>();
			checked[row][col] = true;
			queue.add(new Point(row, col));

			
			while(!queue.isEmpty()) {
				Point current = queue.peek();
				dead.add(current);
				queue.remove();
				
				for (int i = 0; i < 4; i++) {
					int y = current.y + modifiers[i][0];
					int x = current.x + modifiers[i][1];

					if (x > SIZE - 1 || x < 0 || y > SIZE - 1 || y < 0) continue;
					if (checked[y][x]) continue;
					checked[y][x] = true;
					
					if (board[y][x] == colour) {
						queue.add(new Point(y,x));
					}
					if (board[y][x] == MT) {
						System.out.println("Found empty");
						System.out.println(y + " " + x + '\n');
						dead.clear();
						return dead;
					}
				}
			}
			
			System.out.println("surrounded at "+queue.peek().y+" "+queue.peek().x);
			return dead;
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
