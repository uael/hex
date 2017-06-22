package uael.hex.gui.model;

import java.awt.*;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class Cell extends Polygon {

    static final int ECART_ORIGIN = 50;
    static final int ECART_CELLULE = 62;

    private int x;
    private int y;
    private Color color;
    private boolean border;
    private boolean past = false;

    /**
     * ***********
     * <p>
     * Constructeur
     * <p>
     * ************
     */
    public Cell(int x, int y, Color color, boolean border) {
        this.x = y;
        this.y = x;
        this.color = color;
        this.border = border;

        double arc = (Math.PI * 2) / 6;
        for (int i = 0; i <= 6; i++) {
            int pX = (int) Math.round((((this.x + (this.y / 2.0)) * ECART_CELLULE) + ECART_ORIGIN) + 30 * Math.cos(arc * i + 100));
            int pY = (int) Math.round(((this.y * (ECART_CELLULE - 10)) + (ECART_ORIGIN * 2)) + 30 * Math.sin(arc * i + 100));
            addPoint(pX, pY);
        }
    }

    public int getX() {
        return this.y;
    }

    public int getY() {
        return this.x;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getBorder() {
        return this.border;
    }

    public boolean getPast() {
        return past;
    }

    public void setPast(boolean past) {
        this.past = past;
    }
}
