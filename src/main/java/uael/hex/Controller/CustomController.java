package uael.hex.Controller;

import uael.hex.Model.*;
import uael.hex.View.HexView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;

public class CustomController implements ActionListener {
    private HexModel model;
    private HexView view;
    private Game game;
    private Timer timer = new Timer();

    public CustomController(HexModel model, HexView view, Game game) {
        this.model = model;
        this.view = view;
        this.game = game;

        view.pMenu.bPlay.addActionListener(this);
        view.pMenu.bReset.addActionListener(this);
        view.pMenu.bQuit.addActionListener(this);
        view.pGame.bReturn.addActionListener(this);
        view.pVictory.panel.bReturn.addActionListener(this);

        if (game.board.players[0] instanceof MouseListener) {
            view.pGame.addMouseListener((MouseListener) game.board.players[0]);
        }
        if (game.board.players[1] instanceof MouseListener) {
            view.pGame.addMouseListener((MouseListener) game.board.players[1]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Action dans la fenêtre du menu sans que le jeu sois lancé
         */
        if (!model.getInGame() && !model.getCurrentGame()) {
            // Si on clique sur le bouton play le jeu se lance
            if (e.getSource() == view.pMenu.bPlay) {
                model.setInGame(true);
                model.setCurrentGame(true);
                timer.purge();
                timer.schedule(game, 0L, 1000L);
            } else if (e.getSource() == view.pMenu.bQuit) {
                // Si on clique sur le bouton quitter on quitte le jeu
                view.dispose();
                timer.purge();
            }
        }
    }
}
