package linea;

public abstract class EndGame extends GameState{

    @Override
    public void checkRedTurn(Linea game) {throw new RuntimeException(Linea.ElJuegoYaHaTerminado);}
    @Override
    public void checkBlueTurn(Linea game) {throw new RuntimeException(Linea.ElJuegoYaHaTerminado);}

    @Override
    public boolean isEndGame() {
        return true;
    }
}
