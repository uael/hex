package uael.hex.gui.model;

import uael.hex.gui.model.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class Grid extends ArrayList<Cell> {

    private int row;
    private int length;

    public Grid(int nbColumns) {
        super((nbColumns + 1) * (nbColumns + 1));
        row = nbColumns;
        this.length = row * row;
    }

    public void buildGrid() {
        for (int k = 0; k < length; k++) {
            this.add(new Cell(k / row, k % row, Color.WHITE, false));
        }
        for (int x = -1; x <= row; x++) {
            for (int y = -1; y <= row; y++) {
                if (x == -1 && y >= 0)
                    add(new Cell(x, y, Color.BLUE, true));
                else if (x == row && y < row)
                    add(new Cell(x, y, Color.BLUE, true));
                else if (y == -1 && x >= 0)
                    add(new Cell(x, y, Color.RED, true));
                else if (y == row && x < row)
                    add(new Cell(x, y, Color.RED, true));
            }
        }
    }

    public int getRow() {
        return row;
    }

    public Cell getCell(int i, int j) {
        int idx = i * row + j;

        if (idx < 0 || idx >= length) {
            return null;
        }
        return this.get(i * row + j);
    }

    public void setPast(Cell cell) {
        cell.setPast(true);
    }

    public boolean getPast(Cell cell) {
        return cell.getPast();
    }

    public void resetPast() {
        for (Cell c : this)
            c.setPast(false);
    }
}
