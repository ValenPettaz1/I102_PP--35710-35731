package linea;

public class EndGame extends GameState{
    public EndGame() {}

    @Override
    public void checkRedTurn(Linea game) {throw new RuntimeException("Ya terminó el juego");}
    @Override
    public void checkBlueTurn(Linea game) {throw new RuntimeException("Ya terminó el juego");}

    @Override
    public GameState nextState(Linea Game) {throw new RuntimeException("Ya terminó el juego");}

    @Override
    public boolean isEndGame() {
        return true;
    }
}
