package linea;

public class RedPlays extends GameState {


    public RedPlays() {super('X', "Rojas");}

    @Override
    public GameState getNextTurn(Linea game) {
        if (game.finished()){
            return new EndGame();
        }
        return new BluePlays();
    }

    @Override
    public void playAsRed(Linea game, int columnIndex) {
        playChip(game, columnIndex);
    }

    @Override
    public void playAsBlue(Linea game, int columnIndex) {
    throw new RuntimeException("No es el turno de azul");

    }




}
