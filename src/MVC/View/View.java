package MVC.View;

import Game.ConnectFourGame;
import MVC.Controller.Controller;
import MVC.View.Panel.CirclePanel;
import Player.Player;
import Player.HumanPlayer;

import javax.swing.*;
import java.awt.*;

import static utils.Constant.*;

public class View extends JFrame {
	private CirclePanel[][] grid;
	private JPanel gridPanel;
	private JLabel currentPlayerLabel;
	private JButton[] buttons;
	private JButton buttonBot;

	private Controller controller;

	public View(Controller controller) {
		super("Connect Four");
		this.controller = controller;

		// Setup the main window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setLayout(new BorderLayout());

		this.getContentPane().setBackground(Color.RED);

		// Panel for the game grid
		this.gridPanel = new JPanel();
		this.gridPanel.setLayout(new GridLayout(this.controller.getGame().getGrid().getLineSize(), this.controller.getGame().getGrid().getColumnSize()));

		// Creating the game grid
		this.grid = new CirclePanel[this.controller.getGame().getGrid().getColumnSize()][this.controller.getGame().getGrid().getLineSize()];
		for (int line = 0; line < this.controller.getGame().getGrid().getLineSize(); line++) {
			for (int column = 0; column < this.controller.getGame().getGrid().getColumnSize(); column++) {
				CirclePanel cell = new CirclePanel(Color.WHITE);
				this.grid[column][line] = cell;
				this.gridPanel.add(cell);
			}
		}

		// Panel for buttons representing actions to click
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, this.controller.getGame().getGrid().getColumnSize()));
		this.buttons = new JButton[this.controller.getGame().getGrid().getColumnSize()];
		for (int i = 0; i < this.controller.getGame().getGrid().getColumnSize(); i++) {
			JButton button = new JButton(String.valueOf(i + 1));
			button.setFont(new Font("Arial", Font.BOLD, 20));
			button.setBackground(Color.LIGHT_GRAY);
			this.buttons[i] = button;
			this.buttons[i].addActionListener(new PlayHumanActionListener(this.controller));
			buttonPanel.add(button);
		}

		// Row to specify which player have to play
		this.currentPlayerLabel = new JLabel("Player 1's turn (RED)", SwingConstants.CENTER);
		this.currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		this.currentPlayerLabel.setForeground(Color.BLACK);
		this.currentPlayerLabel.setBackground(Color.RED);

		// If one player is a bot, a button for the bot is added to perform an action for that player
		if (!(this.controller.getGame().getPlayers().getFirst() instanceof HumanPlayer)
				|| !(this.controller.getGame().getPlayers().getLast() instanceof HumanPlayer)) {
			this.buttonBot = new JButton("Bot Play");
			this.buttonBot.setFont(new Font("Arial", Font.BOLD, 20));
			this.buttonBot.setBackground(Color.LIGHT_GRAY);
			this.buttonBot.addActionListener(new PlayBotActionListener(this.controller));

			JPanel buttonBotPanel = new JPanel();
			buttonBotPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			buttonBotPanel.add(this.buttonBot);

			JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new BorderLayout());
			controlPanel.add(buttonPanel, BorderLayout.NORTH);
			controlPanel.add(buttonBotPanel, BorderLayout.SOUTH);

			this.add(controlPanel, BorderLayout.SOUTH);

			if(this.controller.getGame().getCurrentPlayer() instanceof HumanPlayer){
				this.enablePlayerInput(true);
				this.enableBotInput(false);
			} else {
				this.enablePlayerInput(false);
				this.enableBotInput(true);
			}
		} else {
			this.add(buttonPanel, BorderLayout.SOUTH);
		}

		this.add(currentPlayerLabel, BorderLayout.NORTH);
		this.add(gridPanel, BorderLayout.CENTER);

		this.setVisible(true);
	}

	/**
	 * Updates the game grid and current player display after each action.
	 * This method is triggered after each action to refresh the user interface.
	 */
	public void update(){
		int [] test = ((ConnectFourGame) this.controller.getGame()).getLastAction();
		for (int x = 0; x < this.controller.getGame().getGrid().getColumnSize(); x++) {
			for (int y = 0; y < this.controller.getGame().getGrid().getLineSize(); y++) {
				this.changeGridColor(x, y, this.controller.getGame().getGrid().getCell(x, y));
			}
		}
		// Add a highlighted to recognize easily the last played action
		this.changeGridColorWitHighlighted(test[0], test[1], this.controller.getGame().getGrid().getCell(test[0], test[1]));

		this.updateCurrentPlayer(this.controller.getGame().getCurrentPlayer().getPlayerId());
		if(this.controller.getGame().getCurrentPlayer() instanceof HumanPlayer){
			this.enablePlayerInput(true);
			this.enableBotInput(false);
		}
		else {
			this.enablePlayerInput(false);
			this.enableBotInput(true);
		}
	}

	/**
	 * Updates the game view to show the final result after the game has ended.
	 * Displays the winner (if any) or a message for a draw.
	 */
	public void updateEndGame(){
		Player winner = this.controller.getGame().getWinner();
		String message = "";
		if(winner != null){
			message = "Player " + winner.getPlayerId() + " WIN !!!";
			this.endGame(winner.getPlayerId(), message);
		} else {
			message = "Egality, Nobody Won";
			this.endGame(0, message);
		}
    }

	/**
	 * Changes the color of a specific grid cell based on the player's action.
	 *
	 * @param x The x-coordinate (column) of the cell.
	 * @param y The y-coordinate (row) of the cell.
	 * @param color The color associated with the player's piece.
	 */
	public void changeGridColor(int x, int y, int color) {
		switch (color) {
			case RED -> this.grid[x][y].changeColor(Color.RED);
			case YELLOW -> this.grid[x][y].changeColor(Color.YELLOW);
			default -> this.grid[x][y].changeColor(Color.WHITE);
		}
	}

	/**
	 * Changes the color of a grid cell and highlights it for the last played action.
	 *
	 * @param x The x-coordinate (column) of the cell.
	 * @param y The y-coordinate (row) of the cell.
	 * @param color The color to apply to the cell.
	 */
	public void changeGridColorWitHighlighted(int x, int y, int color) {
		switch (color) {
			case RED -> this.grid[x][y].changeColor(Color.RED, true);
			case YELLOW -> this.grid[x][y].changeColor(Color.YELLOW, true);
			default -> this.grid[x][y].changeColor(Color.WHITE, true);
		}
	}

	/**
	 * Updates the label displaying the current player's turn based on the player ID.
	 *
	 * @param playerId The ID of the current player (RED or YELLOW).
	 */
	public void updateCurrentPlayer(int playerId) {
		if (playerId == RED) {
			this.getContentPane().setBackground(Color.RED);
			this.currentPlayerLabel.setText("Player 1's turn (RED)");
		} else {
			this.getContentPane().setBackground(Color.YELLOW);
			this.currentPlayerLabel.setText("Player 2's turn (YELLOW)");
		}
	}

	/**
	 * Enables or disables input for the player based on the current state of the game.
	 *
	 * @param enable Whether to enable (true) or disable (false) the player input buttons.
	 */
	public void enablePlayerInput(boolean enable) {
		for (JButton button : this.buttons)
			button.setEnabled(enable);
	}

	/**
	 * Enables or disables input for the bot based on the current state of the game.
	 *
	 * @param enable Whether to enable (true) or disable (false) the bot input button.
	 */
	public void enableBotInput(boolean enable) {
		if(this.buttonBot != null)
			this.buttonBot.setEnabled(enable);
	}

	/**
	 * Displays the final result, and prevents further player actions.
	 *
	 * @param playerId The ID of the winning player (0 if no winner).
	 * @param message The message to display in the end-of-game dialog box.
	 */
	public void endGame(int playerId, String message) {
		if(playerId == RED)
			this.currentPlayerLabel.setText("Player 1 (RED) WIN !!!");
		if(playerId == YELLOW)
			this.currentPlayerLabel.setText("Player 2 (YELLOW) WIN !!!");
		else
			this.currentPlayerLabel.setText("Nobody won this game");
		this.getContentPane().setBackground(Color.GREEN);
		this.enablePlayerInput(false);

		JOptionPane.showMessageDialog(this, "End of the game. Thank you for taking the time to play :)", message, JOptionPane.INFORMATION_MESSAGE);
	}
}

