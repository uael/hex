package uael.hex.Model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

public class Game extends TimerTask {
    public final HexModel model;
    public Board board;
    public int current_color;
    private boolean playing = false;

    public Game(HexModel model, Player blue, Player red) {
        this.model = model;
        board = new Board(
            model.grid.getRow(),
            blue,
            red
        );
        board.players[0].setGame(this);
        board.players[0].setColor(0);
        board.players[1].setGame(this);
        board.players[1].setColor(1);
    }

    public void run() {
        if (!playing) {
            playing = true;
            board.players[current_color].play();
            playing = false;
        }
    }

    public boolean canPlay() {
        return !playing;
    }

    public boolean play(int x, int y) {
        int pos;

        pos = x * board.size + y;
        if (board.isToggled(pos)) {
            return false;
        }
        board.toggle(pos, current_color);
        Cell c = model.grid.getCell(x, y);
        c.setColor(current_color == 0 ? Color.BLUE : Color.RED);
        if (board.players[current_color].win()) {
            model.setWinner(current_color == 0 ? Color.BLUE : Color.RED);
            this.cancel();
            return true;
        }
        current_color ^= 1;
        return false;
    }

    public boolean play(Cell cell) {
        int pos;

        pos = cell.getX() * board.size + cell.getY();
        if (board.isToggled(pos)) {
            return false;
        }
        board.toggle(pos, current_color);
        cell.setColor(current_color == 0 ? Color.BLUE : Color.RED);
        if (board.players[current_color].win()) {
            model.setWinner(current_color == 0 ? Color.BLUE : Color.RED);
            this.cancel();
            return true;
        }
        current_color ^= 1;
        return false;
    }

    public boolean play(MouseEvent e) {
        for (Cell c : model.grid) {
            if (c.contains(e.getX(), e.getY())) {
                return play(c);
            }
        }
        return false;
    }
}
