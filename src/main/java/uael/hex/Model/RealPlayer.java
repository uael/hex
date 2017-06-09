package uael.hex.Model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RealPlayer extends Player implements MouseListener {
    public RealPlayer(int size) {
        super(size);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.canPlay() && game.getCurrentColor() == color) {
            game.play(e);
        }
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

    @Override
    void play() {
    }
}
