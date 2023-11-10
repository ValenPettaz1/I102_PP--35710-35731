package linea;

public abstract class EndGame extends GameState{

    @Override
    public void checkRedTurn(Linea game) {throw new RuntimeException("Ya terminó el juego");}
    @Override
    public void checkBlueTurn(Linea game) {throw new RuntimeException("Ya terminó el juego");}

    @Override
    public boolean isEndGame() {
        return true;
    }
}
