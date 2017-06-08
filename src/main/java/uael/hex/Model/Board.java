package uael.hex.Model;

public class Board {
    public Player players[] = new Player[2];
    public int freecells_c, freecells_idx[], freecells[];
    public int size, length;

    public Board(int size, Player blue, Player red) {
        this.size = size;
        this.length = size * size;
        this.players[0] = blue;
        this.players[1] = red;
        this.freecells = new int[length];
        this.freecells_idx = new int[length];

        for (int i = 0; i < length; ++i) {
            freecells_idx[i] = freecells[i] = i;
        }
        freecells_c = length;
    }

    public boolean isToggled(int idx) {
        int x = idx / size;
        int y = idx % size;

        return (((players[0].state.data[x] >> y) & 1) |
                ((players[0].state.data[y] >> x) & 1)) > 0;
    }

    public void toggle(int idx, int color) {
        int tmp, x, y, i;

        if (color > 0) {
            x = idx % size;
            y = idx / size;
        }
        else {
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
