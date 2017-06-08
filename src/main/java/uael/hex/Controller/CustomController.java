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

        /**
         * Action dans la fenêtre du menu avec le jeu lancé
         */
        else if (!model.getInGame() && model.getCurrentGame()) {
            // Si on clique sur le bouton play on retourne au jeu et on continue la partie en cours
            if (e.getSource() == view.pMenu.bPlay) {
                model.setInGame(true);
                model.setCurrentGame(true);
                timer.purge();
                timer.schedule(game, 0L, 1000L);
            } else if (e.getSource() == view.pMenu.bReset) {
                // Si on clique sur le bouton reset on relance une nouvelle partie
                model.rebuild();
                model.setInGame(true);
                model.setCurrentGame(true);
                timer.purge();
                timer.schedule(game, 0L, 1000L);
            } else if (e.getSource() == view.pMenu.bQuit) {
                // Si on clique sur le bouton quitter on quitte le jeu
                view.dispose();
            }
        }

        /**
         * Action dans la fenêtre de jeu
         */
        else if (model.getInGame() && model.getCurrentGame()) {
            // Si on clique sur le bouton retour on retourne au menu et la partie est toujours en cours, c'est à dire qu'on pourra y revenir
            // en appuyant sur play dans le menu
            if (e.getSource() == view.pGame.bReturn) {
                model.setInGame(false);
                model.setCurrentGame(true);
                view.pVictory.setVisible(false);
                timer.purge();
            }
            // Si on clique sur le bouton retour de la fenêtre de victoire on retourne au menu, ce qui arrête la partie en cours puisqu'on a eu un gagant
            if (e.getSource() == view.pVictory.panel.bReturn) {
                view.pVictory.setVisible(false);
                model.rebuild();
                timer.purge();
            }
        }
    }
}
