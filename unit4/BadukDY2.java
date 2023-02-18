package unit4;

//Daniel Yang
//Game of 10x10 Baduk
//Using recursion, but there are a bunch of bugs

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

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BadukDY2 {
	public static void main (String[]args) {
		new BadukDY2();
	}

	final static int SIZE = 10;
	final static int PANW = 600;
	final static int PANH = 600;
	int space = 3;

	//sizes of each box
	static int boxW = PANW/SIZE;
	static int boxH = PANH/SIZE;

	//WHite, BLack, eMpTy (this entire word is comprised of silent letters)
	final static int WH = 1;
	final static int BL = -1;
	final static int MT = 0;

	//Counters
	int white = 0;
	int black = 0;

	boolean playerTurn = true; //white is true, black is false

	int[][] board = new int[SIZE][SIZE];

	boolean[][] checked = new boolean[SIZE][SIZE];
	ArrayList<Point> deletion = new ArrayList<Point>();
	boolean delete = true;


	BadukDY2() {
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
		window.setResizable(false);
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

			//invisible board where the pieces are between the lines to help with debugging
			for (int i = 0; i < SIZE; i++) {
				g.drawLine(i*boxW, 0, i*boxW, PANH);
				g.drawLine(0, i*boxH, PANW, i*boxH);
			}
			
			//the actual board in which the stones are going to be on the intersections
//			g2.setStroke(new BasicStroke(3));
//			for (int i = 0; i < SIZE; i++) {
//				g.drawLine(i*boxW+30, 0, i*boxW+30, PANH);
//				g.drawLine(0, i*boxH+30, PANW, i*boxH+30);
//			}

			//counters reset to zero since pieces can be removed
			white = 0;
			black = 0;

			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					//Draw black or white stones

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
			int row = e.getY()/boxH;
			int col = e.getX()/boxW;

			drawStones(row, col);
			//Checks the board to see if any stones are removed
			checkBoard();
			this.repaint();
		}

		void drawStones(int row, int col) {
			//white plays, black's turn and vice versa
			if (playerTurn && board[row][col] == MT) {
				board[row][col] = WH;
				playerTurn = !playerTurn;
				return;
			}
			if (!playerTurn && board[row][col] == MT) {
				board[row][col] = BL;
				playerTurn = !playerTurn;
			}
		}

		void checkBoard() {
			

			//loops through the entire board, looking for a non-empty space
			//then it takes the value of that space, which is the colour
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					delete = true;
					deletion.clear();
					checked = new boolean[SIZE][SIZE];
					if (board[i][j] != MT) checkNeighbours(i,j,board[i][j]);
					if (delete) deleteStones(deletion);
				}
			}
		}
		
		void checkNeighbours (int row, int col, int colour) {
			//add the point to deletion (stones that will be removed) and checked (prevent infinite loops)
			deletion.add(new Point(row,col));
			checked[row][col] = true;
			
			
			//prevents out of bounds
			if (row-1 > 0) {
				if (board[row-1][col] == colour && !checked[row-1][col]) checkNeighbours(row-1,col,colour);
				if (board[row-1][col] == MT) {
					delete = false;
					deletion.clear();
					return;
				}
			}
			//prevents out of bounds
			if (row+1 < SIZE-1) {
				if (board[row+1][col] == colour && !checked[row+1][col]) checkNeighbours(row+1,col,colour);
				if (board[row+1][col] == MT) {
					delete = false;
					deletion.clear();
					return;
				}
			}
			//prevents out of bounds
			if (col - 1 > 0) {
				if (board[row][col-1] == colour && !checked[row][col-1]) checkNeighbours(row,col-1,colour);
				if (board[row][col-1] == MT) {
					delete = false;
					deletion.clear();
					return;
				}
			}
			//prevents out of bounds
			if (col + 1 < SIZE-1) {
				if (board[row][col+1] == colour && !checked[row][col+1]) checkNeighbours(row,col+1,colour);
				if (board[row][col+1] == MT) {
					delete = false;
					deletion.clear();
					return;
				}
			}
		}

		void deleteStones (ArrayList<Point> deletion) {
			for (int i = 0; i < deletion.size(); i++) {
				int row = deletion.get(i).x;
				int col = deletion.get(i).y;
				board[row][col] = MT;
			}
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
