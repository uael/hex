package uael.hex.View;

import uael.hex.Model.HexModel;

import javax.swing.*;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class VictoryFrame {

    private static final int Width = 600;
    private static final int Height = 150;
    public VictoryPanel panel;
    private JFrame frame;

    VictoryFrame(HexModel model) {
        panel = new VictoryPanel(model);
        frame = new JFrame("Victoire!");
        frame.setBounds(400, 50, Width, Height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(panel);

        frame.setVisible(false);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
