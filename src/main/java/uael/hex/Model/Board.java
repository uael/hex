package uael.hex.Model;

public class Board {
    public Player players[] = new Player[2];
    private int freecells_idx[];
    int size, freecells_c, freecells[];
    private int length;

    Board(int size, Player blue, Player red) {
        this.size = size;
        this.length = size * size;
        this.players[0] = blue;
        this.players[1] = red;
        this.freecells = new int[length];
        this.freecells_idx = new int[length];
        reset();
    }

    void reset() {
        players[0].state.reset();
        players[1].state.reset();
        for (int i = 0; i < length; ++i) {
            freecells_idx[i] = freecells[i] = i;
        }
        freecells_c = length;
    }

    boolean isToggled(int idx) {
        int x = idx / size;
        int y = idx % size;

        return (((players[0].state.data[x] >> y) & 1) |
                ((players[1].state.data[y] >> x) & 1)) > 0;
    }

    void toggle(int idx, int color) {
        int tmp, x, y, i;

        if (color > 0) {
            x = idx % size;
            y = idx / size;
        } else {
            x = idx / size;
            y = idx % size;
        }
        players[color].state.data[x] |= 1 << y;
        i = freecells_idx[idx];
        tmp = freecells[i];
        freecells[i] = freecells[--freecells_c];
        freecells[freecells_c] = tmp;
        tmp = freecells_idx[i];
        freecells_idx[i] = freecells_idx[freecells_c];
        freecells_idx[freecells_c] = tmp;
    }
}
