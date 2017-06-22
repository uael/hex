package jhex.gui.view;

import jhex.Game;
import jhex.GameEvent;
import jhex.gui.model.Cell;
import jhex.gui.model.Grid;

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

public class GamePanel extends JPanel implements Observer {
    private Grid grid;
    private Game game;

    private JButton bMenu = new JButton("Menu");
    private JLabel currentPlayer = new JLabel("");

    GamePanel(Game game) {
        super();
        this.game = game;
        game.addObserver(this);
        this.grid = new Grid(game.board.size);

        currentPlayer.setFont(new Font(currentPlayer.getFont().getName(), Font.PLAIN, 50));
        add(currentPlayer);

        bMenu.setSize(125, 100);
        bMenu.addActionListener(this::onMenuClick);
        add(bMenu);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(70, 90);
        for (Cell c : this.grid) {
            Graphics2D g2 = (Graphics2D) g;
            //Cell
            g2.setColor(c.getColor());
            g2.fillPolygon(c);
            //Bordure
            Stroke s;
            if (c.getBorder())
                s = new BasicStroke(4f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f);
            else
                s = new BasicStroke(1.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f);
            g2.setStroke(s);
            Color cBordure = new Color(37, 36, 37);
            g2.setColor(cBordure);
            g2.drawPolygon(c);

            revalidate();
            repaint();
        }
    }

    private void onMenuClick(ActionEvent e) {
        game.pause();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Game && arg instanceof GameEvent) {
            Game game = (Game) o;
            GameEvent gameEvent = (GameEvent) arg;

            switch (gameEvent) {
                case ON_START:
                    for (int i = 0; i < grid.length; ++i) {
                        grid.get(i).setColor(Color.WHITE);
                    }
                case ON_TURN:
                    if (game.getCurrentColor() == 0) {
                        currentPlayer.setText("Blue player turn!");
                    } else {
                        currentPlayer.setText("Red player turn!");
                    }
                    break;
                case ON_PLAY:
                    Cell c = grid.getCell(game.getLastMove().x, game.getLastMove().y);
                    switch (game.getLastMove().color) {
                        case 0:
                            c.setColor(Color.BLUE);
                            break;
                        case 1:
                            c.setColor(Color.RED);
                            break;
                        default:
                            break;
                    }
                    break;
            }
        }
    }
}
