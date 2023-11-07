package linea;
import java.util.function.Consumer;

public class RedPlays extends GameState{

    private Consumer<Linea> changeTurn = game -> game.setTurn(new BluePlays());

    public RedPlays(char chip, String color) {
        super(chip, color);
    }


    @Override
    public void changeTurn(Linea game) {
        changeTurn.accept(game);
    }

    @Override
    public void playAt(Linea game, int columnIndex) {
        setTurn.accept(game);
        if (game.isOnBounds(columnIndex) && game.columnHasSpace(columnIndex)) {
            game.getBoard().get(columnIndex).add(getChip());
            updateGame(game);
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }

}
