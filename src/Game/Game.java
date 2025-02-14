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

    public abstract void initGame(int columnSize, int lineSize);

    public abstract void initPlayer(Player [] players);

    public abstract void resetGame();

    public abstract Boolean playAction(Player player, int action);

    public abstract void undoAction();

    public abstract ArrayList<Integer> getPossibleMoves();

    public abstract void switchCurrentPlayer();

    public abstract Boolean endGame();

    // Nobody won
    public abstract Boolean drawGame();

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
