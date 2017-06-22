package jhex;

public class Move {
    public int x;
    public int y;
    public int color;

    Move(int x, int y, int current_color) {
        this.x = x;
        this.y = y;
        this.color = current_color;
    }

    Move(int x, int y) {
        this(x, y, -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (x != move.x) return false;
        return y == move.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
