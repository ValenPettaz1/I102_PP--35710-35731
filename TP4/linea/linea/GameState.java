package linea;

public abstract class GameState {

    public abstract void checkRedTurn(Linea game);
    public abstract void checkBlueTurn(Linea game);
    public abstract GameState nextState(Linea game);

    public boolean isEndGame() {
        return false;
    }
}
