package uael.hex;

import uael.hex.Controller.CustomController;
import uael.hex.Model.Game;
import uael.hex.Model.HexModel;
import uael.hex.Model.Montecarlo;
import uael.hex.Model.RealPlayer;
import uael.hex.View.HexView;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class Main {

    private Main(int size) {
        //Creation du model
        HexModel model = new HexModel(size);
        //Creation de la vue
        HexView view = new HexView(model, "HexGame - Abomnes - Bretheau - S3C");
        //Creation du controller
        CustomController controller = new CustomController(
            model,
            view,
            new Game(model, new Montecarlo(size, 2000), new Montecarlo(size, 2000))
        );

    }

    public static void main(String[] args) {
        new Main(9);
    }
}
