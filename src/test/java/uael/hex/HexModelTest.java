package uael.hex;

import uael.hex.gui.model.Cell;
import uael.hex.gui.model.HexModel;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class HexModelTest {

    //On définit le model pour les tests
    HexModel modelTest = new HexModel(9);

    @Test
    public void testChangePlayer() {
        modelTest.setPlayer(Color.BLUE);
        Color playerTest = modelTest.getPlayer();

        Assert.assertEquals(playerTest, Color.BLUE);
    }

    @Test
    public void testSetWinnerBlue() {
        modelTest.setWinner(Color.BLUE);
        Color winnerTest = modelTest.getWinner();

        Assert.assertEquals(winnerTest, Color.BLUE);
    }

    @Test
    public void testSetWinnerRed() {
        modelTest.setWinner(Color.RED);
        Color winnerTest = modelTest.getWinner();

        Assert.assertEquals(winnerTest, Color.RED);
    }
}