package linea;

public class EndGame extends GameState{
    public EndGame() {}

    @Override
    public GameState nextState(Linea Game) {return new EndGame();}

    @Override
    public void playAsRed(Linea game, int columnIndex) {
        throw new RuntimeException("Ya terminó el juego");
    }

    @Override
    public void playAsBlue(Linea game, int columnIndex) {
        throw new RuntimeException("Ya terminó el juego");
    }

    @Override
    public boolean isEndGame() {
        return true;
    }
}
