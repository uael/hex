package jhex.gui.view;

import jhex.Game;
import jhex.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class MenuPanel extends JPanel implements Observer {
    private static final int bWidth = 175;
    private static final int bHeight = 100;
    public JButton bPlay = new JButton("Play");
    public JButton bQuit = new JButton("Quit");
    public JButton bRestart = new JButton("Restart");
    private Game game;
    private JLabel title = new JLabel("HexGame");

    MenuPanel(Game game) {
        super();
        this.game = game;
        game.addObserver(this);
        //Fond
        this.setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Box box1 = new Box(BoxLayout.X_AXIS);
        Box box2 = new Box(BoxLayout.X_AXIS);
        Box box3 = new Box(BoxLayout.X_AXIS);
        Box box4 = new Box(BoxLayout.X_AXIS);
        Box box5 = new Box(BoxLayout.X_AXIS);
        //Titre
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 100));
        box1.add(title, BorderLayout.NORTH);
        box2.add(new JSeparator());

        JPanel bPanel = new JPanel();
        bPanel.setBackground(Color.WHITE);
        //Button

        bPlay.setSize(bWidth, bHeight);
        bRestart.setSize(bWidth, bHeight);
        bQuit.setSize(bWidth, bHeight);

        bPanel.add(bPlay, BorderLayout.NORTH);
        bPanel.add(bRestart, BorderLayout.CENTER);
        bPanel.add(bQuit, BorderLayout.SOUTH);

        box3.add(bPanel);
        box4.add(new JSeparator());
        box5.add(new JLabel("2015 - 2016"));

        add(box1);
        add(box2);
        add(box3);
        add(box4);
        add(box5);

        bPlay.addActionListener(this::onPlayClick);
        bRestart.addActionListener(this::onRestartClick);
        bQuit.addActionListener(this::onQuitClick);
        bRestart.setVisible(false);
    }

    private void onPlayClick(ActionEvent e) {
        if (game.isPaused()) {
            game.resume();
        } else {
            game.start();
        }
    }

    private void onRestartClick(ActionEvent e) {
        game.restart();
    }

    private void onQuitClick(ActionEvent e) {
        game.stop();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Game && arg instanceof GameEvent) {
            GameEvent gameEvent = (GameEvent) arg;

            switch (gameEvent) {
                case ON_STOP:
                    bPlay.setText("Jouer");
                    bRestart.setVisible(false);
                    break;
                case ON_PAUSE:
                    bPlay.setText("Reprendre");
                    bRestart.setVisible(true);
                    break;
            }
        }
    }
}
