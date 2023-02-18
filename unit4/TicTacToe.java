package unit4;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe {
	public static void main (String[]args) {
		new TicTacToe();
	}
	final static int SIZE = 3; //size of grid

	final static int XX = 1;
	final static int OO = -1;
	final static int EMPTY = 0; //is this necessary?
	final static int sp = 5; //pixels of space for X and O

	//player turn (either bool or int works)
	boolean playerTurn = true; //if true: X's turn, if false: O's turn
	boolean playerWin; //works the same way as playerTurn (X is true, O is false)
	boolean tie = false;
	int winsX = 0, winsO = 0;	
	int[][] board = new int [SIZE][SIZE];
	
	//GUI stuff
	JLabel message = new JLabel(""); //who's turn is it?
	JLabel wins = new JLabel("X: " + winsX + ", O: " + winsO); //How many wins?
	JOptionPane options = new JOptionPane();

	//To setup the game
	TicTacToe() {
		init();
		createAndShowGUI();

		//		board[0][1] = OO;
		//		board[1][2] = XX; debugging
	}

	//will be used to clear/reset board
	void init() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				board[row][col] = EMPTY;
			}
		}
		playerTurn = true;
		tie = false;
		
		message.setText("X Starts");
		wins.setText("X: " + winsX + ", O: " + winsO);
	}

	void createAndShowGUI() {
		JFrame window = new JFrame("TicTacToe");
		JPanel messagesPanel = new JPanel();
		messagesPanel.add(message);
		messagesPanel.add(new JLabel(" | "));
		messagesPanel.add(wins);
	
		message.setText("X Starts");

		messagesPanel.setBackground(new Color(255,255,140));		

		DrawingPanel panel = new DrawingPanel();

		window.add(messagesPanel, BorderLayout.NORTH);
		window.add(panel, BorderLayout.CENTER);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(500,500);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	class DrawingPanel extends JPanel implements MouseListener {

		int panW, panH; //size of panel
		int boxW, boxH; //size of a box

		DrawingPanel() {
			this.setBackground(new Color(240,240,240));
			this.addMouseListener(this);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			initGraphics();

			g.setColor(Color.GRAY);
			for (int i = 0; i < SIZE; i++) {
				g.drawLine(i*boxW, 0, i*boxW, panH);
				g.drawLine(0, i*boxH, panW, i*boxH);
			}

			// draw X or O
			g2.setStroke(new BasicStroke(3));

			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					if (board[row][col] == OO) {
						g.setColor(Color.blue);
						g.drawOval(col*boxW+sp, row*boxH+sp, boxW-sp*2, boxH-sp*2);
					}
					if (board[row][col] == XX) {
						g.setColor(Color.red);
						g.drawLine(col*boxW+sp, row*boxH+sp, col*boxW+boxW-sp, row*boxH+boxH-sp);
						g.drawLine(col*boxW+boxW-sp, row*boxH+sp, col*boxW+sp, row*boxH+boxH-sp);
					}
				}
			}
			repaint();
		}


		void initGraphics() { 
			panW = this.getSize().width;
			panH = this.getSize().height;
			boxW = (int) (panW/SIZE+0.5);
			boxH = (int) (panH/SIZE+0.5);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			int row = mouseY / boxH;
			int col = mouseX / boxW;

			drawXO(row, col);
			winCon();
			tieCheck();
		}

		void drawXO(int row, int col) {
			if (playerTurn && board[row][col] == EMPTY) {
				board[row][col] = XX;
				message.setText("O Turn");

				playerTurn = false;
			}
			if (!playerTurn && board[row][col] == EMPTY) {
				board[row][col] = OO;
				message.setText("X Turn");

				playerTurn = true;
			}
		}
		
		void winCon() {
			int total1 = 0;
			int total2 = 0;
			//horizontal and vertical
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					total1 += board[row][col];
					total2 += board[col][row];

					if (total1 == SIZE || total2 == SIZE) {
						playerWin = true;
						gameEnd();
						return;
					}
					if (total1 == -SIZE || total2 == -SIZE) {
						playerWin = false;
						gameEnd();
						return;
					}
				}
				total1 = 0;
				total2 = 0;
			}

			//diagonal top left to bottom right
			//the variable "i" represents both row and col
			for (int i = 0; i < SIZE; i++) {
				total1 += board[i][i];
				total2 += board[i][SIZE-i-1];

			}
			if (total1 == SIZE || total2 == SIZE) {
				playerWin = true;
				gameEnd();
				return;
			}
			if (total1 == -SIZE || total2 == -SIZE) {
				playerWin = false;
				gameEnd();
				return;
			}
			return;
		}

		void gameEnd() {

			
			if (playerWin && !tie) {
				winsX++;
				options.showMessageDialog(null, "X Wins!", "Winner!", JOptionPane.PLAIN_MESSAGE);

			}
			if (!playerWin && !tie) {
				winsO++;
				options.showMessageDialog(null, "O Wins!", "Winner!", JOptionPane.PLAIN_MESSAGE);
			}
			if (tie) {
				options.showMessageDialog(null, "Tie!", "No winner :(", JOptionPane.PLAIN_MESSAGE);
			}
			init();
		}
		
		void tieCheck() {
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					if (board[row][col] == EMPTY) {
						return;
					}
				}
			}
			tie = true;
			gameEnd();
		}

		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}

	}
}
