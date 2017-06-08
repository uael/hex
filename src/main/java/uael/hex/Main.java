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

    public Main() {
        //Creation du model
        HexModel model = new HexModel();
        //Creation de la vue
        HexView view = new HexView(model, "HexGame - Abomnes - Bretheau - S3C");
        //Creation du controller
        CustomController controller = new CustomController(model, view, new Game(model, new Montecarlo(model.grid.getRow()), new RealPlayer(model.grid.getRow())));

    }

    public static void main(String[] args) {
        new Main();
    }
}
