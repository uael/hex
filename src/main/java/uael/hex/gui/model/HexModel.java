package uael.hex.gui.model;

import java.awt.*;
import java.util.Observable;

/**
 * Abomnes Gauthier
 * Bretheau Yann
 * S3C
 */

public class HexModel extends Observable {

    public Grid grid; // La grille du jeu
    /**
     * *****************
     * <p>
     * Les attributs inGame et currentGame permettent de connaitre l'état du jeu.
     * L'attribut inGame permet de différencier le panel du menu et du jeu et l'attribut currentGame permet de savoir si une partie est en cours.
     * Grâce à l'attribut currentGame on peut proposer différente action dans le menu, comme une action de reset si une partie est en cours.
     * <p>
     * ******************
     */
    private boolean inGame; // Si on n'est sur le panel jeu
    private boolean currentGame; // Si une partie est en cours
    private Color player; //  Bleu ou Rouge selon le joueur
    private Color winner; // Blanche pour auncun gagnant, Bleu ou Rouge selon le gagnant
    private boolean victory; // 0 si un player gagne et 1 sinon

    /**
     * ***********
     * <p>
     * Constructeur
     * <p>
     * ************
     */
    public HexModel(int size) {
        this.inGame = false; // On affecte false pour dire qu'on commence sur le panel du menu
        this.currentGame = false; // On afecte false car aucune partie n'est lancé
        this.player = Color.BLUE; // On affecte false au player pour dire que le player bleu commence
        this.winner = Color.WHITE;
        this.victory = false; // On affecte false à la victory car auncun player n'a gagné
        this.grid = new Grid(size);
        grid.buildGrid(); // On construit la grille
    }

    /**
     * ***********
     * <p>
     * Accesseurs
     * <p>
     * ************
     */
    public boolean getInGame() {
        return this.inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
        setChanged();
        notifyObservers();
    }

    public boolean getCurrentGame() {
        return this.currentGame;
    }

    public void setCurrentGame(boolean currentGame) {
        this.currentGame = currentGame;
        setChanged();
        notifyObservers();
    }

    public Color getPlayer() {
        return this.player;
    }

    public void setPlayer(Color player) {
        this.player = player;
        setChanged();
        notifyObservers();
    }

    public Color getWinner() {
        return winner;
    }

    // Lorsqu'un player gagne on affecte true a victory et l'attribut gagnant prend le player qui gagne
    public void setWinner(Color winner) {
        this.winner = winner;
        this.victory = true;
        setChanged();
        notifyObservers();
    }

    // Il n'y a pas d'accesseur d'écriture pour l'attribut victory car la modification de la victoire se fait avec l'accesseur setWinner.
    // Si un player gagne alors on met automatiquement l'attribut victory a true.
    public boolean getVictory() {
        return victory;
    }

    /**
     * ***********
     * <p>
     * Méthode appelé au restart, elle reconstruit la grid et affecte les attributs comme dans le constructeur
     * <p>
     * ************
     */
    public void rebuild() {
        grid.clear();
        grid.buildGrid();
        this.inGame = false;
        this.currentGame = false;
        this.player = Color.BLUE;
        this.victory = false;
        setChanged();
        notifyObservers();
    }
}
