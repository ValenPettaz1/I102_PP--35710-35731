package linea;

import java.util.function.Consumer;

public class RedPlays extends GameState {

//    private Consumer<Linea> changeTurnToBlue = game -> game.setTurn(new BluePlays());

    public RedPlays() {super('X', "Red");}

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
        throw new RuntimeException("Rojo no puede jugar dos veces");

    }

    @Override
    protected void changeToBlue(Linea game) {
        game.setTurn(new BluePlays());

    }

    @Override
    protected void nextChange(Linea game) {
        if (game.getTurn() instanceof RedPlays){
            changeToBlue(game);
        }
        else{
            changeToRed(game);
        }
    }
}
