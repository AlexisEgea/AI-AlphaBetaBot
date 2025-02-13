package MVC.View;

import Game.Game;
import MVC.Controller.Controller;
import Player.Player;
import Player.HumanPlayer;

import javax.swing.*;
import java.awt.*;


import static utils.Constant.*;

public class View extends JFrame {
	private Game game;

	private CirclePanel[][] grid;
	private JPanel gridPanel;

	private JLabel currentPlayerLabel;

	private JButton[] buttons;

	private Controller controller;


	public View(Game game) {
		super("Connect Four");
		this.game = game;

		// Setup the main window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLayout(new BorderLayout());

		this.getContentPane().setBackground(Color.RED);

		// Panel for the game grid
		this.gridPanel = new JPanel();
		this.gridPanel.setLayout(new GridLayout(this.game.getGrid().getLineSize(), this.game.getGrid().getColumnSize()));

		// Creating the game grid
		this.grid = new CirclePanel[this.game.getGrid().getColumnSize()][this.game.getGrid().getLineSize()];
		for (int line = 0; line < this.game.getGrid().getLineSize(); line++) {
			for (int column = 0; column < this.game.getGrid().getColumnSize(); column++) {
				CirclePanel cell = new CirclePanel(Color.WHITE);
				this.grid[column][line] = cell;
				this.gridPanel.add(cell);
			}
		}

		// Panel for the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, this.game.getGrid().getColumnSize()));

		this.buttons = new JButton[this.game.getGrid().getColumnSize()];
		for (int i = 0; i < this.game.getGrid().getColumnSize(); i++) {
			JButton button = new JButton(String.valueOf(i + 1));
			button.setFont(new Font("Arial", Font.BOLD, 20));
			button.setBackground(Color.LIGHT_GRAY);
			this.buttons[i] = button;
			buttonPanel.add(button);
		}

		this.currentPlayerLabel = new JLabel("Player 1's turn (RED)", SwingConstants.CENTER);
		this.currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		this.currentPlayerLabel.setForeground(Color.BLACK);
		this.currentPlayerLabel.setBackground(Color.RED);

		this.add(currentPlayerLabel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(gridPanel, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void setController(Controller controller) {
		this.controller = controller;
		for (int i = 0; i < this.game.getGrid().getColumnSize(); i++) {
			this.buttons[i].addActionListener(new PlayActionListener(controller));
		}
	}

	public void update(){
		for (int x = 0; x < this.controller.getGame().getGrid().getColumnSize(); x++) {
			for (int y = 0; y < this.controller.getGame().getGrid().getLineSize(); y++) {
				this.changeGridColor(x, y, this.controller.getGame().getGrid().getCell(x, y));
			}
		}

		this.updateCurrentPlayer(this.controller.getCurrentPlayer().getPlayerId());
		if(this.controller.getCurrentPlayer() instanceof HumanPlayer)
			this.enablePlayerInput(true);
		else
			this.enablePlayerInput(false);
	}

	public void updateEndGame(){
		int winner = this.controller.getGame().getWinner();
		String message = "";
		if(winner != 0)
			message = "Player " + winner + " WIN !!!";
		else
			message = "Egality, Nobody Won";
		this.endGame(winner, message);
	}

	public void changeGridColor(int x, int y, int color) {
		switch (color) {
			case RED -> this.grid[x][y].changeColor(Color.RED);
			case YELLOW -> this.grid[x][y].changeColor(Color.YELLOW);
			default -> this.grid[x][y].changeColor(Color.WHITE);
		}
	}

	public void updateCurrentPlayer(int playerId) {
		if (playerId == RED) {
			this.getContentPane().setBackground(Color.RED);
			this.currentPlayerLabel.setText("Player 1's turn (RED)");
		} else {
			this.getContentPane().setBackground(Color.YELLOW);
			this.currentPlayerLabel.setText("Player 2's turn (YELLOW)");
		}
	}

	public void enablePlayerInput(boolean enable) {
		for (JButton button : buttons) {
			button.setEnabled(enable);
		}
	}

	public void endGame(int playerId, String message) {
		if (playerId == RED)
			this.currentPlayerLabel.setText("Player 1 (RED) WIN !!!");
		else
			this.currentPlayerLabel.setText("Player 2 (YELLOW) WIN !!!");
		this.getContentPane().setBackground(Color.GREEN);
		this.enablePlayerInput(false);

		JOptionPane.showMessageDialog(this, "End of the game. Thank you for taking the time to play :)", message, JOptionPane.INFORMATION_MESSAGE);
	}

	private class CirclePanel extends JPanel {
		private Color color;

		public CirclePanel(Color color) {
			this.color = color;
			this.setPreferredSize(new Dimension(80, 80));
			this.setBackground(Color.BLUE);
		}

		public void changeColor(Color color) {
			this.color = color;
			this.repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(color);
			int size = Math.min(getWidth(), getHeight()) - 10;
			g.fillOval(5, 5, size, size);
		}
	}
}

