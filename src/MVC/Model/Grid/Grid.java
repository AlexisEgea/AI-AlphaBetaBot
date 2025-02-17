package MVC.Model.Grid;

public abstract class Grid<T>{
    protected int columnSize;
    protected int lineSize;
    protected T grid;

    public Grid(){
        this.columnSize = 0;
        this.lineSize = 0;
    }

    /**
     * Initializes the grid with specified dimensions.
     *
     * @param columnSize The number of columns in the grid
     * @param lineSize   The number of rows in the grid
     */
    public abstract void initGrid(int columnSize, int lineSize);

    /**
     * Getter: Retrieves the value from a cell in the grid at the specified position.
     *
     * @param column The column of the cell
     * @param line   The row of the cell
     * @return The value of the cell at that position
     */
    public abstract int getCell(int column, int line);

    /**
     * Setter: Modifies the value of a cell in the grid at a given position.
     *
     * @param column The column of the cell
     * @param line   The row of the cell
     * @param value  The new value to insert into the cell
     */
    public abstract void setCell(int column, int line, int value);

    /**
     * Getter: Retrieves the number of columns in the grid.
     *
     * @return The number of columns in the grid
     */
    public int getColumnSize() {
        return this.columnSize;
    }

    /**
     * Getter: Retrieves the number of rows in the grid.
     *
     * @return The number of rows in the grid
     */
    public int getLineSize() {
        return lineSize;
    }
}
