package uael.hex.Model;

import java.util.Arrays;

public class State {
    enum LinkState {
        LinkDown,
        LinkUp,
        Capture
    }
    public int[] data;
    private int size;
    private int length;

    public State(int size) {
        this.size = size;
        this.length = size * size;
        reset();
    }

    void reset() {
        this.data = new int[length];
    }

    void toggle(int row, int col) {
        data[row] |= 1 << col;
    }

    boolean win() {
        int board[], shadow_board[] = new int[length];

        board = Arrays.copyOf(data, length);
        int shadow_row = board[0];
        int row = 0;
        shadow_board[0] = board[0];
        board[0] = 0;

        LinkState state = LinkState.LinkDown;
        int row_num = 1;

        int connections = 0;
        while (true) {
            switch (state) {
                case LinkDown: {
                    row = board[row_num];
                    connections = (row & shadow_row) | ((shadow_row >> 1) & row);

                    if (connections > 0) {
                        if (row_num == size - 1)
                            return true;

                        state = LinkState.Capture;
                    } else {
                        shadow_row = shadow_board[row_num];
                        if (shadow_row > 0) {
                            row_num++;
                        } else {
                            return false;
                        }
                    }
                    break;
                }
                case LinkUp: {
                    row = board[row_num];
                    connections = (row & shadow_row) | ((shadow_row << 1) & row);
                    if (connections > 0) {
                        state = LinkState.Capture;
                    } else {
                        shadow_row = shadow_board[row_num];
                        row_num++;
                        state = LinkState.LinkDown;
                    }
                    break;
                }
                case Capture: {
                    int capture = 0;
                    capture = connections;

                    board[row_num] ^= capture;
                    shadow_board[row_num] |= capture;

                    // capture right bits
                    do {
                        capture >>= 1;
                        capture &= board[row_num];
                        board[row_num] ^= capture;
                        shadow_board[row_num] |= capture;
                    } while (capture > 0);

                    // capture left bits
                    capture = connections;
                    do {
                        capture <<= 1;
                        capture &= board[row_num];
                        board[row_num] ^= capture;
                        shadow_board[row_num] |= capture;
                    } while (capture > 0);

                    shadow_row = shadow_board[row_num];

                    if (board[row_num - 1] > 0) {
                        row_num--;
                        state = LinkState.LinkUp;
                    } else {
                        row_num++;
                        state = LinkState.LinkDown;
                    }
                    break;
                }
            }
        }
    }
}
