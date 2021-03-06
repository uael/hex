package jhex;

public abstract class Player {
    final State state;
    protected int color;
    Game game;

    Player(int size) {
        this.state = new State(size);
    }

    boolean win() {
        return state.win();
    }

    abstract void onTurnStart();

    void setGame(Game game) {
        this.game = game;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
