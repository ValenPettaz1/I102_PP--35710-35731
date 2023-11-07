package linea;

public class RedPlays extends GameState {


    public RedPlays() {super('X', "Red");}

    @Override
    public GameState getNextTurn() {
        return new BluePlays();
    }

    @Override
    public void playAsRed(Linea game, int columnIndex) {
        if (game.isOnBounds(columnIndex) && game.columnHasSpace(columnIndex)) {
            game.getBoard().get(columnIndex).add(getChip());
            updateGame(game);
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }

    @Override
    public void playAsBlue(Linea game, int columnIndex) {
    throw new RuntimeException("No es el turno de azul");

    }




}
