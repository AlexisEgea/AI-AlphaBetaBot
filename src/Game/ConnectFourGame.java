package Game;

import Grid.ConnectFourGrid;

import Player.Player;

import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;

import static utils.Constant.*;

public class ConnectFourGame extends Game {
    private Stack<int[]> moveHistory; // Stack to store move history

    public ConnectFourGame() {
        super();
        this.initGame(7, 6);
    }

    @Override
    public void initGame(int columnSize, int lineSize) {
         this.grid = new ConnectFourGrid(columnSize, lineSize);
         this.grid.initGrid(this.grid.getColumnSize(), this.grid.getLineSize());
         this.moveHistory = new Stack<>();
    }

    @Override
    public void initPlayer(Player [] players){
        this.players = new ArrayList<>();
        this.players.addAll(Arrays.asList(players));
        this.currentPlayer = this.players.getFirst();
    }

    @Override
    public void resetGame() {
        this.initGame(this.grid.getColumnSize(), this.grid.getLineSize());
        Player [] players = new Player[this.players.size()];
        players[0] = this.players.getLast();
        players[1] = this.players.getFirst();
        players[0].setPlayerId(RED);
        players[1].setPlayerId(YELLOW);
        this.initPlayer(players);
        this.winner = null;
    }

    @Override
    public void switchCurrentPlayer(){
        int currentPlayerId = (this.currentPlayer.getPlayerId() + 1) % this.players.size();
        if(currentPlayerId == this.players.get(0).getPlayerId())
            currentPlayer = this.players.get(0);
        else
            currentPlayer = this.players.get(1);
    }

    @Override
    public Boolean playAction(Player player, int action){
        action--;
        if (action >= 0 && action < this.grid.getColumnSize()) {
            if (((ConnectFourGrid) this.grid).isColumnFull(action)){
                System.out.println("Error Size Column: Column " + action + " is full");
                return false;
            }

            for (int line = this.grid.getLineSize() - 1; line >= 0; line--) {
                if (this.grid.getCell(action, line) == EMPTY) {
                    this.grid.setCell(action, line, player.getPlayerId());
                    moveHistory.push(new int[]{action, line});
                    System.out.println("Player ID " + player.getPlayerId() + " Action: " + action);
                    System.out.println(this.getGrid());
                    return true;
                }
            }
        } else {
            action++;
            System.out.println("Error: Action " + action + " out of bound -> " + this.grid.getColumnSize());
            return false;
        }
        return false;
    }

    @Override
    public void undoAction() {
        int[] lastMove = moveHistory.pop();
        this.grid.setCell(lastMove[0], lastMove[1], EMPTY);
    }

    public int[] getLastMove() {
        System.out.println(Arrays.toString(this.moveHistory.peek()));
        return this.moveHistory.peek();
    }

    @Override
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        for (int column = 0; column < this.grid.getColumnSize(); column++) {
            if (!((ConnectFourGrid) this.grid).isColumnFull(column)) {
                possibleMoves.add(column + 1);
            }
        }
        return possibleMoves;
    }

    @Override
    public Boolean endGame() {
        Boolean end;
        for(Player player : this.players) {
            end = this.searchFourPiece(player.getPlayerId());
            if (end) {
                System.out.println("Player " + player.getPlayerId() + " win the game!");
                this.winner = player;
                return true;
            }
        }

        if (this.drawGame()) {
            System.out.println("Grid FULL, nobody wins the game 0_0");
            this.winner = null; // If nobody win the game, the winner id is 0
            return true;
        }
        return false;
    }

    public Boolean searchFourPiece(int playerId) {
        for (int x = 0; x < this.grid.getColumnSize(); x++) {
            for (int y = 0; y < this.grid.getLineSize(); y++) {
                if (this.grid.getCell(x, y) == playerId) {
                    if (checkDirection(x, y, 1, 0, playerId) || //      →
                            checkDirection(x, y, 0, 1, playerId) || //  ↓
                            checkDirection(x, y, 1, 1, playerId) || //  ↘
                            checkDirection(x, y, 1, -1, playerId))  //  ↗
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Boolean checkDirection(int x, int y, int dx, int dy, int playerId) {
        for (int i = 0; i < 4; i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;

            if (newX < 0 || newX >= this.grid.getColumnSize() || newY < 0 || newY >= this.grid.getLineSize())
                return false;

            if (this.grid.getCell(newX, newY) != playerId)
                return false;
        }
        return true;
    }

    @Override
    public Boolean drawGame(){
        return ((ConnectFourGrid) this.grid).isGridFull();
    }
}
