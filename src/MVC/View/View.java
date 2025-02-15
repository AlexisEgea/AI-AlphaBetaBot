package MVC.View;

import Game.ConnectFourGame;
import MVC.Controller.Controller;
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

	public void update(){
		int [] test = ((ConnectFourGame) this.controller.getGame()).getLastMove();
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

	public void changeGridColor(int x, int y, int color) {
		switch (color) {
			case RED -> this.grid[x][y].changeColor(Color.RED);
			case YELLOW -> this.grid[x][y].changeColor(Color.YELLOW);
			default -> this.grid[x][y].changeColor(Color.WHITE);
		}
	}

	public void changeGridColorWitHighlighted(int x, int y, int color) {
		switch (color) {
			case RED -> this.grid[x][y].changeColor(Color.RED, true);
			case YELLOW -> this.grid[x][y].changeColor(Color.YELLOW, true);
			default -> this.grid[x][y].changeColor(Color.WHITE, true);
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
		for (JButton button : this.buttons)
			button.setEnabled(enable);
	}

	public void enableBotInput(boolean enable) {
		if(this.buttonBot != null)
			this.buttonBot.setEnabled(enable);
	}

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

	public class CirclePanel extends JPanel {
		private Color color;
		private boolean highlighted;

		public CirclePanel(Color color) {
			this.color = color;
			this.highlighted = false;
			this.setPreferredSize(new Dimension(80, 80));
			this.setBackground(Color.BLUE);
		}

		public void changeColor(Color color) {
			this.color = color;
			this.highlighted = false;
			this.repaint();
		}

		public void changeColor(Color color, Boolean highlighted) {
			this.color = color;
			this.highlighted = highlighted;
			this.repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.setColor(color);
			int size = Math.min(getWidth(), getHeight()) - 10;
			g.fillOval(5, 5, size, size);

			if (highlighted) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.GREEN);
				g2.setStroke(new BasicStroke(3));
				g2.drawOval(5, 5, size, size);
			}
		}
	}

}

