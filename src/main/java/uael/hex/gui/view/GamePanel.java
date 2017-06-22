package uael.hex.gui.view;

import uael.hex.Cell;
import uael.hex.gui.model.HexModel;

import javax.swing.*;
import java.awt.*;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class GamePanel extends JPanel {
    public JButton bReturn = new JButton("Retour menu");
    public HexModel model;
    JLabel currentPlayer = new JLabel("Empty");

    GamePanel(HexModel model) {
        super();
        this.model = model;
        currentPlayer.setFont(new Font(currentPlayer.getFont().getName(), Font.PLAIN, 50));
        add(currentPlayer);
        bReturn.setSize(125, 100);
        add(bReturn);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (model.getPlayer() == Color.BLUE)
            currentPlayer.setText("C'est au currentPlayer du bleu!");
        else
            currentPlayer.setText("C'est au currentPlayer du rouge!");

        for (Cell c : this.model.grid) {

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

}
