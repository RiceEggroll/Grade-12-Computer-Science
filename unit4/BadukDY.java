package unit4;

//Daniel Yang
//A game of 9x9 go/baduk
//You can pass and resign on your turn
//Uses BFS for removing stones


import java.awt.BasicStroke;

//Daniel Yang
//Game of 10x10 Baduk
//Using BFS

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import unit4.GridBasedGameDY.DrawingPanel;

public class BadukDY {
	public static void main (String[]args) {
		new BadukDY();
	}

	//Size of board
	//Board is not scalable :(
	final static int SIZE = 9;
	final static int PANW = 594;
	final static int PANH = 594;

	//sizes of each box
	int boxW = PANW/SIZE;
	int boxH = PANH/SIZE;

	//WHite, BLack, eMpTy (this entire word is comprised of silent letters)
	final static int WH = 1;
	final static int BL = -1;
	final static int MT = 0; 
	//Sure I could have just used '0' for this but its just easier to use a variable
	
	boolean playerTurn; //white is true, black is false
	
	//counters to track the points
	//global since i need to be able to check the score at any time
	int white;
	int black;

	//Board arrays 
	static int[][] board = new int[SIZE][SIZE];
	boolean[][] checked = new boolean[SIZE][SIZE];
	
	//GUI stuff
	DrawingPanel panel;
	JLabel turn;
	JLabel score;
	JOptionPane options;
	
	BadukDY() {
		setUpGUI();
		init();
		//		board[4][2] = BL;
	}

	void init() { //Used to reset the game
		playerTurn = true; //white moves first
	
		//sets the values in the board to empty
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] =  MT;
			}
		}
		
		turn.setText("White Starts");
		score.setText("White: -   |   Black: -");
	}

	//GUI stuff
	void setUpGUI() {
		//declare variables
		JFrame window = new JFrame("Baduk");
		
		panel = new DrawingPanel();
		JPanel messagePanel = new JPanel();
		JPanel options = new JPanel();

		turn = new JLabel();
		score = new JLabel();
		JButton pass = new JButton("Pass");
		JButton resign = new JButton("Resign");
		
		//set events
		pass.setActionCommand("PASS");
		resign.setActionCommand("RESIGN");
		pass.addActionListener(new KL());
		resign.addActionListener(new KL());
		
		//adding the different components
		messagePanel.add(turn,BorderLayout.EAST);
		messagePanel.add(new JLabel("            "));
		messagePanel.add(score,BorderLayout.WEST);
		
		options.add(pass);
		options.add(resign);
		
		window.add(messagePanel, BorderLayout.NORTH);
		window.add(panel, BorderLayout.CENTER);
		window.add(options, BorderLayout.SOUTH);
		
		//JFrame stuff
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
			
			//space variable to prevent the stones from touching each other for an overall cleaner look
			int space = 3;


			//invisible board where the pieces are between the lines (used for debugging)
//			for (int i = 0; i < SIZE; i++) {
//				g.drawLine(i*boxW, 0, i*boxW, PANH);
//				g.drawLine(0, i*boxH, PANW, i*boxH);
//			}
			
			//the actual board in which the stones are going to be on the intersections
			g2.setStroke(new BasicStroke(3));
			g.setColor(Color.black);
			for (int i = 0; i < SIZE; i++) {
				g.drawLine(i*boxW+30, boxW/2, i*boxW+30, PANH-boxW/2-space);
				g.drawLine(boxH/2-space, i*boxH+30, PANH-boxH/2-space, i*boxH+30);
			}

			g2.setStroke(new BasicStroke(3));
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					if (board[row][col] == BL) { //Black stones
						g.setColor(Color.black);
						g.fillOval(col*boxW+space, row*boxH+space, 60-space*2, 60-space*2);
						g.setColor(Color.gray);
						g.drawOval(col*boxW+space, row*boxH+space, 60-space*2, 60-space*2);
					}
					if (board[row][col] == WH) { //White stones
						g.setColor(Color.white);
						g.fillOval(col*boxW+space, row*boxH+space, 60-space*2, 60-space*2);
						g.setColor(Color.gray);
						g.drawOval(col*boxW+space, row*boxH+space, 60-space*2, 60-space*2);
					}
				}
			}
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			int row = e.getY()/boxH;
			int col = e.getX()/boxW;

			drawStones(row, col); //handles the playerturns and setting values to the array
			checkEnd(); //checks if the game has ended
			checkBoard(); //if the game has not ended, checks if any stones are going to be removed
			changePanel(); //sets the text on the JLabels 
			this.repaint();
		}


		void drawStones(int row, int col) {
			//Sets a value to the array depending on whose turn it is then changes the turn
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

		void checkBoard() { //dennis helped me on this
			checked = new boolean[SIZE][SIZE];
			ArrayList<Point> remove;

			//loops through the board array, looking for an element with a value
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (board[i][j] !=MT && !checked[i][j])  {
						remove = checkNeighbours (i, j, board[i][j]);
						for (int k = 0; k < remove.size(); k++) {
							board[remove.get(k).x][remove.get(k).y] = MT;
						}
					}
				}
			}

		}

		ArrayList<Point> checkNeighbours(int row, int col, int colour) {
			//up, down, left, right to check neighbours
			int modifiers[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
			Queue<Point> queue = new LinkedList<>();
			ArrayList<Point> dead = new ArrayList<Point>();
			
			checked[row][col] = true;
			queue.add(new Point(row, col));
			
			//we know a clump is dead when there is no empty spaces around it
			//this means it is completely surrounded
			boolean isDead = true;
			while(!queue.isEmpty()) {
				Point current = queue.peek();
				if (isDead) dead.add(current);
				queue.remove();

				for (int i = 0; i < 4; i++) {
					int x = current.x + modifiers[i][0];
					int y = current.y + modifiers[i][1];
					
					if (x > SIZE - 1 || x < 0 || y > SIZE - 1 || y < 0) continue;
					//only mark the same colour as checked
					//we don't care about the opposite colour
					if (board[x][y] == colour && !checked[x][y]) {
						checked[x][y] = true;
						queue.add(new Point(x,y));
					}
					
					if (board[x][y] == MT) {
						isDead = false;
						dead.clear();
					}
				}
			}
			
			return dead;
		}
		
		//changes the text on panel and adds the counters
		public void changePanel() {
			//reset the counters since they can be removed
			white = 0;
			black = 0;
			
			//loops through the array, checking how many times white/black occurs
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) { 
					if (board[i][j] == WH) white++;
					if (board[i][j] == BL) black++;
				}
			}
			
			if (playerTurn) turn.setText("White's move");
			if(!playerTurn) turn.setText("Black's move");
			score.setText("White: "+white+" | Black: "+black);
		}
		
		public void checkEnd() {
			//loops through the array, if it does not find an empty space, 
			//it will know that the game has ended
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (board[i][j] == MT) return;
				}
			}
			endGame();
		}

		public void endGame() {
			//whoever scores more, wins, except in the case of a resignation
			if (white > black) {
				turn.setText("White Wins!");
				options.showMessageDialog(null, "White Wins! \nScore "+white+" - "+black, "Winner!", JOptionPane.PLAIN_MESSAGE);
			}
			if (black > white) {
				turn.setText("Black Wins!");
				options.showMessageDialog(null, "Black Wins! \nScore "+white+" - "+black, "Winner!", JOptionPane.PLAIN_MESSAGE);
			}
			init();
			this.repaint();
		}
		

		//Unused methods
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}


	}
	
	class KL implements ActionListener {

		//Buttons for pass/resign
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("PASS")) {
				playerTurn = !playerTurn;
				if (playerTurn) turn.setText("White's move");
				if(!playerTurn) turn.setText("Black's move");
			}
			
			//white resigns on his turn, black wins, vice versa
			if (e.getActionCommand().equals("RESIGN")) {
				if (playerTurn) {
					turn.setText("Black Wins!");
					options.showMessageDialog(null, "Black Wins by Resignation! \nScore "+white+" - "+black, "Winner!", JOptionPane.PLAIN_MESSAGE);
				}
				if (!playerTurn) {
					turn.setText("White Wins!");
					options.showMessageDialog(null, "White Wins by Resignation! \nScore "+white+" - "+black, "Winner!", JOptionPane.PLAIN_MESSAGE);	
				}
				init();
				panel.repaint();
			}
		}
		
	}
}
