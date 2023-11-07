package linea;
import java.util.function.Consumer;


public class BluePlays extends GameState{

//    private Consumer<Linea> changeTurnToRed = game -> game.setTurn(new RedPlays());

    public BluePlays() {super('0',"Blue");}

    @Override
    public void playAt(Linea game, int columnIndex) {
        if (game.isOnBounds(columnIndex) && game.columnHasSpace(columnIndex)) {
            game.getBoard().get(columnIndex).add(getChip());
            updateGame(game);
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }


    @Override
    public void changeTurn(Linea game) {
        nextChange(game);
    }

    @Override
    protected void changeToRed(Linea game) {
        game.setTurn(new RedPlays());
    }

    @Override
    protected void changeToBlue(Linea game) {
        throw new RuntimeException("Azul no puede jugar dos veces");

    }

    @Override
    protected void nextChange(Linea game) {
        if (game.getTurn() instanceof BluePlays){
            changeToRed(game);
        }
        else{
            changeToBlue(game);
        }
    }
}
