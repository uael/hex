package uael.hex.Model;

import java.util.Arrays;
import java.util.Random;

public class Montecarlo extends Player {
    private Random random = new Random();

    public Montecarlo(int size) {
        super(size);
    }

    @Override
    void play() {
        int i, p, k, moves, free_nodes_count = game.board.freecells_c, nodes[];
        int j, max_wins;
        State s = new State(game.board.size);
        Move win_pos, free_nodes[] = new Move[free_nodes_count], free_nodes_copy[];

        nodes = Arrays.copyOf(game.board.freecells, free_nodes_count);
        for (i = 0; i < free_nodes_count; ++i) {
            int id = nodes[i], x, y;
            if (color > 0) {
                x = id % game.board.size;
                y = id / game.board.size;
            } else {
                x = id / game.board.size;
                y = id % game.board.size;
            }
            free_nodes[i] = new Move(x, y);
        }
        free_nodes_copy = Arrays.copyOf(free_nodes, free_nodes_count);
        max_wins = 0;
        win_pos = free_nodes_copy[0];
        moves = (free_nodes_count - 2) / 2 + color;
        for (p = 0; p < free_nodes_count; ++p) {
            int wins = 0, possible_wins = 1000;
            Move pos = free_nodes_copy[p];

            for (j = 0; j < 1000; ++j) {
                s.data = Arrays.copyOf(state.data, game.board.length);
                s.toggle(pos.x, pos.y);
                for (k = 0; k < moves; ++k) {
                    int kpos;
                    Move id, tmp;

                    kpos = (random.nextInt((free_nodes_count - 1 - k)) + 1);
                    id = free_nodes[kpos];
                    tmp = free_nodes[kpos];
                    free_nodes[kpos] = free_nodes[free_nodes_count - 1 - k];
                    free_nodes[free_nodes_count - 1 - k] = tmp;

                    if (pos.x == id.x && pos.y == id.y) {
                        ++k;
                        continue;
                    }
                    s.toggle(id.x, id.y);
                }
                if (s.win()) {
                    ++wins;
                } else {
                    possible_wins--;
                }
                if (possible_wins < max_wins) {
                    break;
                }
            }
            if (possible_wins >= max_wins && wins > max_wins) {
                win_pos = pos;
                max_wins = wins;
            }
        }
        if (color > 0) {
            game.play(win_pos.y, win_pos.x);
        } else {
            game.play(win_pos.x, win_pos.y);
        }
    }
}
