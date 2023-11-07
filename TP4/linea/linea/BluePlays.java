package linea;
import java.util.function.Consumer;


public class BluePlays extends GameState{
    private Consumer<Linea> setTurno = game -> game.setTurn(new RedPlays());

    @Override
    public void play(Linea game, int columnIndex) {
        game.playRedAt(columnIndex);
        setTurn().accept(game);
    }
}
