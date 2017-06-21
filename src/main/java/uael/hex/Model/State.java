package uael.hex.Model;

import java.util.Arrays;

class State {
    int[] data;
    int size;

    State(int size) {
        this.size = size;
        reset();
    }

    void reset() {
        this.data = new int[size];
    }

    void toggle(int x, int y) {
        data[x] |= 1 << y;
    }

    boolean win() {
        int board[], shadow_board[] = new int[size];
        board = Arrays.copyOf(data, size);
        int row, row_num = 1, connections = 0, shadow_row = board[0];
        LinkState state = LinkState.LINK_DOWN;

        shadow_board[0] = board[0];
        board[0] = 0;
        while (true) {
            switch (state) {
                case LINK_DOWN: {
                    row = board[row_num];
                    connections = (row & shadow_row) | ((shadow_row >> 1) & row);
                    if (connections > 0) {
                        if (row_num == size - 1)
                            return true;
                        state = LinkState.CAPTURE;
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
                case LINK_UP: {
                    row = board[row_num];
                    connections = (row & shadow_row) | ((shadow_row << 1) & row);
                    if (connections > 0) {
                        state = LinkState.CAPTURE;
                    } else {
                        shadow_row = shadow_board[row_num];
                        row_num++;
                        state = LinkState.LINK_DOWN;
                    }
                    break;
                }
                case CAPTURE: {
                    int capture;

                    capture = connections;
                    board[row_num] ^= capture;
                    shadow_board[row_num] |= capture;
                    do {
                        capture >>= 1;
                        capture &= board[row_num];
                        board[row_num] ^= capture;
                        shadow_board[row_num] |= capture;
                    } while (capture > 0);
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
                        state = LinkState.LINK_UP;
                    } else {
                        row_num++;
                        state = LinkState.LINK_DOWN;
                    }
                    break;
                }
            }
        }
    }

    enum LinkState {
        LINK_DOWN,
        LINK_UP,
        CAPTURE
    }
}
