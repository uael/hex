package jhex;

import jhex.gui.model.Cell;
import jhex.gui.model.Grid;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class GridTest {

    // On définit la grid pour les tests
    Grid gridTest = new Grid(8);

    // Ce test permet également de tester la méthode getCell
    @Test
    public void testBuildGrid() {
        // On récupère les cellules pour tester leur couleurs pour voir si la création de la grid fonctionne
        Cell c1 = gridTest.getCell(5, 5);
        Color couleur1 = c1.getColor();

        Cell c2 = gridTest.getCell(0, 3);
        Color couleur2 = c2.getColor();

        Cell c3 = gridTest.getCell(3, 0);
        Color couleur3 = c3.getColor();

        assertEquals(couleur1, Color.WHITE);
        assertEquals(couleur2, Color.BLUE);
        assertEquals(couleur3, Color.RED);
    }
}