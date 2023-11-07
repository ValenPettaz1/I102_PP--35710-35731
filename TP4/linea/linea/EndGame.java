package linea;

public class EndGame extends GameState{
    public EndGame() {}

    @Override
    public GameState getNextTurn(Linea Game) {
        return new EndGame();
    }

    @Override
    public boolean checkFinish(Linea game){
        return true;
    }

    @Override
    public void playAsRed(Linea game, int columnIndex) {
        throw new RuntimeException("Ya terminó el juego");
    }

    @Override
    public void playAsBlue(Linea game, int columnIndex) {
        throw new RuntimeException("Ya terminó el juego");
    }
}
