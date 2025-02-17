package Game;

import Grid.Grid;
import Player.Player;

import java.util.ArrayList;

public abstract class Game {
    protected Grid grid;

    protected ArrayList<Player> players;
    protected Player currentPlayer;
    protected Player winner;

    public Game(){
        this.players = new ArrayList<>();
        this.currentPlayer = null;
        this.winner = null;
    }

    /**
     * Initializes the game by setting up mainly the grid depending on the game + any other needed variables.
     *
     * @param columnSize The number of columns in the grid
     * @param lineSize   The number of rows in the grid
     */
    public abstract void initGame(int columnSize, int lineSize);

    /**
     * Initializes the players participating in the game.
     *
     * @param players An array of Player objects representing the players
     */
    public abstract void initPlayer(Player [] players);

    /**
     * Restarts the game and alternates the order of play between players.
     */
    public abstract void resetGame();

    /**
     * Executes an action for the given player.
     *
     * @param player The player making the action
     * @param action The action chosen by the player
     * @return true if the action is valid and successfully played, false otherwise
     */
    public abstract Boolean playAction(Player player, int action);

    /**
     * Reverts the last action made and restoring the previous game state.
     */
    public abstract void undoAction();

    /**
     * Retrieves a list of possible actions the current player can make.
     *
     * @return An ArrayList of integers representing valid actions
     */
    public abstract ArrayList<Integer> getPossibleActions();

    /**
     * Switches the current player to the next one in the turn order.
     */
    public abstract void switchCurrentPlayer();

    /**
     * Determines whether the game has ended.
     *
     * @return true if the game is over (win or draw), false otherwise
     */
    public abstract Boolean endGame();

    /**
     * Checks if the game ended in a draw (i.e., no winner).
     *
     * @return true if the game is a draw, false otherwise
     */    public abstract Boolean drawGame();

    public Grid getGrid() {
        return this.grid;
    }

    public Player getWinner() {
        return this.winner;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }
}
