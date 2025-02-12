package Game;

import Player.Player;

import java.util.ArrayList;

public abstract class Game<T> {
    protected T grid;

    protected int columnSize;
    protected int lineSize;

    public Game(){
        this.columnSize = 0;
        this.lineSize = 0;
    }

    public abstract int getCell(int column, int line);

    public abstract void initGame(int xSize, int ySize);

    public abstract Boolean playAction(int playerId, int action);

    public abstract void undoAction();

    public abstract ArrayList<Integer> getPossibleMoves();

    public abstract Boolean endGame(Player[] players);

    public abstract Boolean isGridFull();

    public int getColumnSize() {
        return this.columnSize;
    }

    public int getLineSize() {
        return lineSize;
    }
}
