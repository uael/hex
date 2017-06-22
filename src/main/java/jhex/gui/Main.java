package jhex.gui;

import jhex.Game;
import jhex.Montecarlo;
import jhex.gui.view.HexView;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class Main {

    private Main(int size) {
        Game model = new Game(size, new Montecarlo(size, 2000), new Montecarlo(size, 2000));
        HexView view = new HexView(model, "HexGame - Abomnes - Bretheau - S3C");
        view.setVisible(true);

    }

    public static void main(String[] args) {
        new Main(9);
    }
}
