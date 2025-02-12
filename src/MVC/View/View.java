package MVC.View;

import Game.Game;
import MVC.Controller.Controller;
import Player.Player;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setLayout(new BorderLayout());

		getContentPane().setBackground(Color.RED);

		// Panel for the game grid
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(this.game.getGrid().getLineSize(), this.game.getGrid().getColumnSize()));

		// Creating the game grid
		grid = new CirclePanel[this.game.getGrid().getColumnSize()][this.game.getGrid().getLineSize()];
		for (int line = 0; line < this.game.getGrid().getLineSize(); line++) {
			for (int column = 0; column < this.game.getGrid().getColumnSize(); column++) {
				CirclePanel cell = new CirclePanel(Color.WHITE);
				grid[column][line] = cell;
				gridPanel.add(cell);
			}
		}

		// Panel for the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, this.game.getGrid().getColumnSize()));

		buttons = new JButton[this.game.getGrid().getColumnSize()];

		for (int i = 0; i < this.game.getGrid().getColumnSize(); i++) {
			JButton button = new JButton(String.valueOf(i + 1));
			button.setFont(new Font("Arial", Font.BOLD, 20));
			button.setBackground(Color.LIGHT_GRAY);
			buttons[i] = button;
			buttonPanel.add(button);
		}

		currentPlayerLabel = new JLabel("Player 1's turn (RED)", SwingConstants.CENTER);
		currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		currentPlayerLabel.setForeground(Color.BLACK);
		currentPlayerLabel.setBackground(Color.RED);

		add(currentPlayerLabel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		add(gridPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	public void setController(Controller controller) {
		this.controller = controller;
		for (int i = 0; i < this.game.getGrid().getColumnSize(); i++) {
			buttons[i].addActionListener(new PlayActionListener(controller));
		}
	}

	public void update(){
		for (int x = 0; x < this.controller.getGame().getGrid().getColumnSize(); x++) {
			for (int y = 0; y < this.controller.getGame().getGrid().getLineSize(); y++) {
				this.changeGridColor(x, y, this.controller.getGame().getGrid().getCell(x, y));
			}
		}


		if(this.controller.getGame().endGame(new Player[]{this.controller.getPlayer1(), this.controller.getPlayer2()}))
			this.endGame("Game Over!");
		else
			this.updateCurrentPlayer(this.controller.getCurrentPlayer().getPlayerId());
	}


	public void changeGridColor(int x, int y, int color) {
		switch (color) {
			case RED -> grid[x][y].changeColor(Color.RED);
			case YELLOW -> grid[x][y].changeColor(Color.YELLOW);
			default -> grid[x][y].changeColor(Color.WHITE);
		}
		this.grid[x][y].revalidate();
		this.grid[x][y].repaint();
	}

	public void updateCurrentPlayer(int playerId) {
		if (playerId == RED) {
			getContentPane().setBackground(Color.RED);
			currentPlayerLabel.setText("Player 1's turn (RED)");
			enablePlayerInput(true);
		} else {
			getContentPane().setBackground(Color.YELLOW);
			currentPlayerLabel.setText("Player 2's turn (YELLOW)");
			enablePlayerInput(false);
		}
	}

	public void enablePlayerInput(boolean enable) {
		for (JButton button : buttons) {
			button.setEnabled(enable);
		}
	}

	public void endGame(String message) {
		JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
		dispose(); // Ferme la fenêtre
	}

	private class CirclePanel extends JPanel {
		private Color color;

		public CirclePanel(Color color) {
			this.color = color;
			setPreferredSize(new Dimension(80, 80));
			setBackground(Color.BLUE);
		}

		public void changeColor(Color color) {
			this.color = color;
			repaint();
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

