package uael.hex.Model;

import java.awt.*;

public class Player {
    public final Color color;
    public final State state;
    public final PlayerMove move;

    public Player(int size, Color color, PlayerMove move) {
        this.color = color;
        this.state = new State(size);
        this.move = move;
    }

    public boolean win() {
        return state.win();
    }
}
