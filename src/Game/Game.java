package Game;

import Grid.Grid;
import Player.Player;

import java.util.ArrayList;

public abstract class Game {
    protected Grid grid;
    protected int winner;

    public Game(){
        this.winner = -1;
    }

    public abstract void initGame(int columnSize, int lineSize);

    public abstract Boolean playAction(Player player, int action);

    public abstract void undoAction();

    public abstract ArrayList<Integer> getPossibleMoves();

    public abstract Boolean endGame(Player[] players);

    // Nobody won
    public abstract Boolean drawGame();

    public Grid getGrid() {
        return this.grid;
    }

    public int getWinner() {
        return winner;
    }
}
