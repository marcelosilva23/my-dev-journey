package io.codeforall.kernelfc;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid {
    private static final int CELL_SIZE = 50;
    public static final int COLS = 15;
    public static final int ROWS = 15;
    public static final int PADDING = 10;
    private Cursor cursor;

    public Grid() {
        drawGrid();
        this.cursor = new Cursor();
    }

    void drawGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle cell = new Rectangle(getX(col), getY(row), CELL_SIZE, CELL_SIZE);
                //cell.setColor(Color.BLUE);
                cell.draw();
            }
        }
    }


    public static int getX(int col) {
        return PADDING + col * CELL_SIZE;
    }

    public int rowToY(int row) {
        return PADDING + CELL_SIZE * row;
    }


    public static int getY(int row) {
        return PADDING + row * CELL_SIZE;
    }

    public int colToX(int col) {
        return PADDING + CELL_SIZE * col;
    }
}
