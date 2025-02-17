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
    public int getCell(int column, int line) {
        return this.grid[column][line];
    }

    @Override
    public void setCell(int column, int line, int value) {
        this.grid[column][line] = value;
    }

    /**
     * Checks if a column is full. A column is considered full if its topmost row is not EMPTY.
     *
     * @param column The column to be checked
     * @return True if the column is full, false otherwise
     */
    public Boolean isColumnFull(int column) {
        return this.grid[column][0] != EMPTY;
    }

    /**
     * Checks if the connect four grid is full. A grid is full if no top column cells contain the EMPTY value.
     *
     * @return True if the grid is full, false otherwise
     */
    public Boolean isGridFull() {
        for (int column = 0; column < this.columnSize; column++) {
            if (this.grid[column][0] == EMPTY) {
                    return false;
            }
        }
        return true;
    }

    /**
     * Converts the ConnectFour grid into a string representation.
     * This method creates a human-readable string version of the grid.
     * The string uses:
     * - "·" to represent empty cells
     * - "R" to represent red pieces
     * - "Y" to represent yellow pieces
     *
     * @return A string representation of the grid
     */
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
