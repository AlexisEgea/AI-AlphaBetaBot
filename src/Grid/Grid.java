package Grid;

import static utils.Constant.EMPTY;

public abstract class Grid<T>{
    protected int columnSize;
    protected int lineSize;
    protected T grid;

    public Grid(){
        this.columnSize = 0;
        this.lineSize = 0;
    }

    public abstract void initGrid(int columnSize, int lineSize);

    public abstract int getCell(int column, int line);

    public abstract void setCell(int column, int line, int value);

    public int getColumnSize() {
        return this.columnSize;
    }

    public int getLineSize() {
        return lineSize;
    }

    public T getGrid(){
        return this.grid;
    }
}
