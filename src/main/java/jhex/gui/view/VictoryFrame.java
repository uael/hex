package jhex.gui.view;

import jhex.GameEvent;
import jhex.Game;

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

public class VictoryFrame implements Observer {

    private static final int Width = 600;
    private static final int Height = 150;
    public VictoryPanel panel;
    private JFrame frame;

    VictoryFrame(Game game) {
        game.addObserver(this);
        panel = new VictoryPanel();
        frame = new JFrame("Victoire!");
        frame.setBounds(400, 50, Width, Height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.setVisible(false);
        panel.bReturn.addActionListener(VictoryFrame.this::onReturnClick);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Game && arg instanceof GameEvent) {
            Game game = (Game) o;
            GameEvent gameEvent = (GameEvent) arg;

            if (gameEvent == GameEvent.ON_WIN && !frame.isVisible()) {
                if (game.getWinner() == 0) {
                    panel.setBackground(Color.BLUE);
                    panel.title.setText("Le joueur Bleu à gagné!");
                } else {
                    panel.setBackground(Color.RED);
                    panel.title.setText("Le joueur Rouge à gagné!");
                }
                frame.setVisible(true);
            }
        }
    }

    private void onReturnClick(ActionEvent e) {
        frame.setVisible(false);
    }

    public class VictoryPanel extends JPanel {

        JButton bReturn = new JButton("Retour");
        JLabel title = new JLabel("Un joueur à gagné!");

        VictoryPanel() {
            this.setBackground(Color.WHITE);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            Box box1 = new Box(BoxLayout.X_AXIS);
            Box box2 = new Box(BoxLayout.X_AXIS);
            //Box 1
            title.setForeground(Color.WHITE);
            title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
            box1.add(title);
            //Box 2
            bReturn.setSize(150, 50);
            box2.add(bReturn);

            add(box1);
            add(box2);
        }
    }
}
