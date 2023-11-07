package linea;

public abstract class GameState {
    void play(Linea game, int columnIndex);
    String getColor();
    void cambiarTurno(Linea game);
}
