package jhex;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Observable {
    public Board board;
    private int current_color, winner = -1;
    private boolean playing = false;
    private GameStatus status;
    private Move lastMove = null;
    private Task task = new Task();
    private Timer timer = new Timer();


    public Game(int size, Player blue, Player red) {
        board = new Board(size, blue, red);
        board.players[0].setGame(this);
        board.players[0].setColor(0);
        board.players[1].setGame(this);
        board.players[1].setColor(1);
    }

    public void stop() {
        if (isRunning()) {
            setStatus(GameStatus.STOPED);
            timer.purge();
            timer.cancel();
            timer = new Timer();
            task.cancel();
            task = new Task();
        }
    }

    public void restart() {
        stop();
        start();
    }

    public void start() {
        if (!isRunning()) {
            board.reset();
            setCurrentColor(0);
            timer.schedule(task, 0L, 1000L);
            setStatus(GameStatus.RUNNING);
        }
    }

    public void pause() {
        if (isRunning()) {
            setStatus(GameStatus.PAUSED);
            timer.purge();
            timer.cancel();
            timer = new Timer();
            task.cancel();
            task = new Task();
        }
    }

    public void resume() {
        if (isPaused()) {
            setStatus(GameStatus.RUNNING);
            timer.schedule(task, 0L, 1000L);
        }
    }

    boolean canPlay() {
        return isRunning() && !playing;
    }

    void play(int x, int y) {
        play(new Move(x, y, current_color));
    }

    private void play(Move cell) {
        int pos;

        pos = cell.x * board.size + cell.y;
        if (board.isToggled(pos)) {
            return;
        }
        board.toggle(pos, getCurrentColor());
        setLastMove(cell);
        if (board.players[getCurrentColor()].win()) {
            setWinner(getCurrentColor());
            stop();
        } else {
            setCurrentColor(getCurrentColor() ^ 1);
        }
    }

    public int getCurrentColor() {
        return current_color;
    }

    private void setCurrentColor(int current_color) {
        this.current_color = current_color;
        setChanged();
        notifyObservers(GameEvent.ON_TURN);
    }

    public Move getLastMove() {
        return lastMove;
    }

    private void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
        setChanged();
        if (lastMove != null) {
            notifyObservers(GameEvent.ON_PLAY);
        }
    }

    public int getWinner() {
        return winner;
    }

    private void setWinner(int winner) {
        this.winner = winner;
        setChanged();
        if (winner >= 0) {
            notifyObservers(GameEvent.ON_WIN);
        }
    }

    private void setStatus(GameStatus status) {
        GameStatus lastStatus = this.status;
        this.status = status;
        setChanged();
        switch (status) {
            case RUNNING:
                notifyObservers(lastStatus == GameStatus.PAUSED ? GameEvent.ON_RESUME : GameEvent.ON_START);
                break;
            case STOPED:
                notifyObservers(GameEvent.ON_STOP);
                break;
            case PAUSED:
                notifyObservers(GameEvent.ON_PAUSE);
                break;
        }
    }

    private boolean isRunning() {
        return status == GameStatus.RUNNING || status == GameStatus.PAUSED;
    }

    public boolean isPaused() {
        return status == GameStatus.PAUSED;
    }

    private class Task extends TimerTask {
        @Override
        public void run() {
            if (canPlay()) {
                playing = true;
                board.players[getCurrentColor()].onTurnStart();
                playing = false;
            }
        }
    }
}
