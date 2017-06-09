package uael.hex.Model;

public abstract class Player {
    public final State state;
    public int color;
    public Game game;

    public Player(int size) {
        this.state = new State(size);
    }

    public boolean win() {
        return state.win();
    }

    abstract void play();

    void setGame(Game game) {
        this.game = game;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
