package uael.hex.Model;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Montecarlo implements PlayerMove {
    private Random random = new Random();

    @Override
    public Move move(Player player, Board board) {
        int i, p, k, moves, free_nodes_count = board.freecells_c, nodes[];
        int j, max_wins;
        State state = new State(board.size);
        Move win_pos, free_nodes[] = new Move[free_nodes_count], free_nodes_copy[];
        int color = player.color == Color.BLUE ? 0 : 1;

        nodes = Arrays.copyOf(board.freecells, free_nodes_count);
        for (i = 0; i < free_nodes_count; ++i) {
            int id = nodes[i], x, y;
            if (color > 0) {
                x = id % board.size;
                y = id / board.size;
            } else {
                x = id / board.size;
                y = id % board.size;
            }
            free_nodes[i] = new Move(x, y);
        }
        free_nodes_copy = Arrays.copyOf(free_nodes, free_nodes_count);
        max_wins = 0;
        win_pos = free_nodes_copy[0];
        moves = (free_nodes_count - 2) / 2 + color;
        for (p = 0; p < free_nodes_count; ++p) {
            int wins = 0, possible_wins = 100;
            Move pos = free_nodes_copy[p];

            for (j = 0; j < 100; ++j) {
                state.data = Arrays.copyOf(board.players[color].state.data, board.length);
                state.toogle(pos.x, pos.y);
                for (k = 0; k < moves; ++k) {
                    int kpos;
                    Move id, tmp;

                    kpos = (random.nextInt((free_nodes_count - 1 - k)) + 1);
                    id = free_nodes[kpos];
                    tmp = free_nodes[kpos];
                    free_nodes[kpos] = free_nodes[free_nodes_count - 1 - k];
                    free_nodes[free_nodes_count - 1 - k] = tmp;

                    if (pos.v == id.v) {
                        ++k;
                        continue;
                    }
                    state.toogle(id.x, id.y);
                }
                if (state.win()) {
                    ++wins;
                } else {
                    possible_wins--;
                }
                if (possible_wins < max_wins) {
                    break;
                }
            }
            if (possible_wins < max_wins) {
                continue;
            }
            if (wins > max_wins) {
                win_pos = pos;
                max_wins = wins;
            }
        }
        if (color > 0)
            return new Move(win_pos.y, win_pos.x);
        return win_pos;
    }
}
