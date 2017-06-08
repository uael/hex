package uael.hex.Model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RealPlayer implements MouseListener, PlayerMove {
    private Game game;

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.play(e);
    }

    @Override
    public Move move(Player player, Board board) {
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
