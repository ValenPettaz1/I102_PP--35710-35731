package linea;

public class BluePlays extends GameState {



    public BluePlays() {
        super('0', "Blue");
    }

    @Override
    public GameState getNextTurn() {
        return new RedPlays();
    }

    @Override
    public void playAsRed(Linea game, int columnIndex) {
        throw new RuntimeException("No es el turno de rojo");

    }

    @Override
    public void playAsBlue(Linea game, int columnIndex) {
        if (game.isOnBounds(columnIndex) && game.columnHasSpace(columnIndex)) {
            game.getBoard().get(columnIndex).add(getChip());
            updateGame(game);
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }

    }

}
