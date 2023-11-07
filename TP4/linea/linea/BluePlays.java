package linea;

public class BluePlays extends GameState {



    public BluePlays() {
        super('0', "Azules");
    }

    @Override
    public GameState getNextTurn(Linea game) {
        if (game.finished()){
            return new EndGame();
        }
        return new RedPlays();
    }

    @Override
    public void playAsRed(Linea game, int columnIndex) {
        throw new RuntimeException("No es el turno de rojo");

    }

    @Override
    public void playAsBlue(Linea game, int columnIndex) {
        playChip(game, columnIndex);

    }

}
