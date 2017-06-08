package uael.hex.Model;

public interface PlayerMove {
    Move move(Player player, Board board);
    default void setGame(Game game) {}
}
