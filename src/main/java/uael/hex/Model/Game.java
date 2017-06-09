package uael.hex.Model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

public class Game extends TimerTask {
    public final HexModel model;
    public Board board;
    private int current_color;
    private boolean playing = false;

    public Game(HexModel model, Player blue, Player red) {
        this.model = model;
        board = new Board(model.grid.getRow(), blue, red);
        board.players[0].setGame(this);
        board.players[0].setColor(0);
        board.players[1].setGame(this);
        board.players[1].setColor(1);
    }

    public void reset() {
        setCurrent_color(0);
        board.reset();
    }

    public void run() {
        if (!playing) {
            playing = true;
            board.players[getCurrentColor()].play();
            playing = false;
        }
    }

    boolean canPlay() {
        return !playing;
    }

    void play(int x, int y) {
        play(model.grid.getCell(x, y));
    }

    void play(MouseEvent e) {
        for (Cell c : model.grid) {
            if (c.contains(e.getX(), e.getY())) {
                play(c);
                return;
            }
        }
    }

    private void play(Cell cell) {
        int pos;

        if (cell.getBorder()) {
            return;
        }
        pos = cell.getX() * board.size + cell.getY();
        if (board.isToggled(pos) && cell.getColor() != Color.white) {
            return;
        }
        board.toggle(pos, getCurrentColor());
        cell.setColor(getCurrentColor() == 0 ? Color.BLUE : Color.RED);
        if (board.players[getCurrentColor()].win()) {
            model.setWinner(getCurrentColor() == 0 ? Color.BLUE : Color.RED);
            reset();
        } else {
            setCurrent_color(getCurrentColor() ^ 1);
        }
    }

    int getCurrentColor() {
        return current_color;
    }

    private void setCurrent_color(int current_color) {
        this.current_color = current_color;
    }
}
