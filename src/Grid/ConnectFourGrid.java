package Grid;

import static utils.Constant.*;

public class ConnectFourGrid extends Grid<int[][]> {
    public ConnectFourGrid(int columnSize, int lineSize){
        super();
        this.columnSize = columnSize;
        this.lineSize = lineSize;
        this.initGrid(this.columnSize, this.lineSize);
    }

    @Override
    public void initGrid(int columnSize, int lineSize) {
        this.columnSize = columnSize;
        this.lineSize = lineSize;
        this.grid = new int[this.columnSize][this.lineSize];

        for (int i = 0; i < this.columnSize; i++) {
            for (int j = 0; j < this.lineSize; j++) {
                this.grid[i][j] = EMPTY;
            }
        }
    }

    @Override
    public int[][] getGrid() {
        return this.grid;
    }

    @Override
    public int getCell(int column, int line) {
        return this.grid[column][line];
    }

    @Override
    public void setCell(int column, int line, int value) {
        this.grid[column][line] = value;
    }

    public Boolean isColumnFull(int column) {
        return this.grid[column][0] != EMPTY;
    }

    public Boolean isGridFull() {
        for (int column = 0; column < this.columnSize; column++) {
            for (int line = 0; line < this.lineSize; line++) {
                if (this.grid[column][line] == EMPTY)
                    return false;
            }
        }
        return true;
    }

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
