package jhex.gui.view;

import jhex.Game;
import jhex.GameEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class HexView extends JFrame implements Observer {

    public MenuPanel pMenu;
    public GamePanel pGame;
    public VictoryFrame pVictory;

    public HexView(Game game, String title) {
        super(title);
        this.pMenu = new MenuPanel(game);
        this.pGame = new GamePanel(game);
        this.pVictory = new VictoryFrame(game);
        game.addObserver(this);
        pMenu.bQuit.addActionListener(this::onQuitClick);

        setBounds(500, 400, 900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().removeAll();
        getContentPane().add(pMenu);
    }

    private void onQuitClick(ActionEvent e) {
        dispose();
        System.exit(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Game && arg instanceof GameEvent) {
            GameEvent gameEvent = (GameEvent) arg;

            switch (gameEvent) {
                case ON_STOP:
                case ON_PAUSE:
                    getContentPane().removeAll();
                    getContentPane().add(pMenu);
                    break;
                case ON_START:
                case ON_RESUME:
                    getContentPane().removeAll();
                    getContentPane().add(pGame);
                    break;
                default:
                    break;
            }
            this.revalidate();
            this.repaint();
        }
    }
}
