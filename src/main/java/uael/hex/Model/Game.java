package uael.hex.Model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

public class Game extends TimerTask {
    public final HexModel model;
    public Board board;
    public int current_color;

    public Game(HexModel model, PlayerMove blue, PlayerMove red) {
        this.model = model;
        board = new Board(
            model.grid.getRow(),
            new Player(model.grid.getRow(), Color.BLUE, blue),
            new Player(model.grid.getRow(), Color.RED, red)
        );
        board.players[0].move.setGame(this);
        board.players[1].move.setGame(this);
    }

    public void run() {
        Player player;
        Move move;

        player = board.players[current_color];
        move = player.move.move(player, board);

        if (move != null) {
            play(move);
        }
    }

    public boolean play(Move move) {
        int pos;
        boolean victory;

        pos = move.x * board.size + move.y;
        if (board.isToggled(pos)) {
            return false;
        }
        board.toggle(pos, current_color);
        if (model != null) {
            Cell c = model.grid.getCell(move.x, move.y);
            c.setColor(current_color == 0 ? Color.BLUE : Color.RED);
        }
        victory = board.players[current_color].win();
        current_color ^= 1;
        return victory;
    }

    public boolean play(Cell cell) {
        int pos;
        boolean victory;

        pos = cell.getX() * board.size + cell.getY();
        if (board.isToggled(pos)) {
            return false;
        }
        board.toggle(pos, current_color);
        cell.setColor(current_color == 0 ? Color.BLUE : Color.RED);
        victory = board.players[current_color].win();
        current_color ^= 1;
        return victory;
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
