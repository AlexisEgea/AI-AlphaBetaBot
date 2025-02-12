package Game;

import Player.Player;
import java.util.Stack;
import java.util.ArrayList;

import static utils.Constant.*;

public class ConnectFour extends Game<int[][]> {
    private Stack<int[]> moveHistory; // Stack to store move history

    public ConnectFour(int columnSize, int lineSize) {
        super();
        this.initGame(columnSize, lineSize);
    }

    @Override
    public int getCell(int column, int line) {
        return this.grid[column][line];
    }

    @Override
    public void initGame(int columnSize, int lineSize) {
        this.columnSize = columnSize;
        this.lineSize = lineSize;
        this.grid = new int[this.columnSize][this.lineSize];
        this.moveHistory = new Stack<>();

        for (int i = 0; i < this.columnSize; i++) {
            for (int j = 0; j < this.lineSize; j++) {
                this.grid[i][j] = EMPTY;
            }
        }
    }

    @Override
    public Boolean playAction(int playerId, int action){
        action--;
        if (action >= 0 && action < this.columnSize) {
            if (this.isColumnFull(action)){
                System.out.println("Error Size Column: Column " + action + " is full");
                return false;
            }

            for (int line = this.lineSize - 1; line >= 0; line--) {
                if (this.grid[action][line] == EMPTY) {
                    this.grid[action][line] = playerId;
                    moveHistory.push(new int[]{action, line});
                    System.out.println("Player ID " + playerId + " Action: " + action);
                    System.out.println(this);

                    return true;
                }
            }
        } else {
            action++;
            System.out.println("Error: Action " + action + " out of bound -> " + this.columnSize);
            return false;
        }
        return false;
    }

    @Override
    public void undoAction() {
        int[] lastMove = moveHistory.pop();
        this.grid[lastMove[0]][lastMove[1]] = EMPTY;
    }

    @Override
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        for (int column = 0; column < this.columnSize; column++) {
            if (!isColumnFull(column)) {
                possibleMoves.add(column + 1);
            }
        }
        return possibleMoves;
    }

    public Boolean isColumnFull(int column) {
        return this.grid[column][0] != EMPTY;
    }

    @Override
    public Boolean endGame(Player[] players) {
        Boolean end;
        for(Player player : players) {
            end = this.searchFourPiece(player.getPlayerId());
            if (end) {
                System.out.println("Player " + player.getPlayerId() + " win the game!");
                return true;
            }
        }

        if (this.isGridFull()) {
            System.out.println("Grid FULL, nobody wins the game 0_0");
            return true;
        }

        return false;
    }

    public Boolean searchFourPiece(int playerId) {
        for (int x = 0; x < this.columnSize; x++) {
            for (int y = 0; y < this.lineSize; y++) {
                if (this.grid[x][y] == playerId) {
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

            if (newX < 0 || newX >= this.columnSize || newY < 0 || newY >= this.lineSize) {
                return false;
            }

            if (this.grid[newX][newY] != playerId) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean isGridFull() {
        for (int column = 0; column < this.columnSize; column++) {
            for (int line = 0; line < this.lineSize; line++) {
                if (this.grid[column][line] == EMPTY)
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < lineSize; j++) {
            for (int i = 0; i < columnSize; i++) {
                if (grid[i][j] == EMPTY) {
                    sb.append("· ");
                } else if (grid[i][j] == RED) {
                    sb.append("R ");
                } else if (grid[i][j] == YELLOW) {
                    sb.append("Y ");
                }
            }
            sb.append("\n");
        }

        for (int i = 0; i < columnSize; i++) {
            sb.append(i + 1).append(" ");
        }

        return sb.toString();
    }
}
