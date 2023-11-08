package linea;

public class BluePlays extends GameState {

    public static String NoEsElTurnoDeRojo = "No es el turno de rojo";

    public BluePlays() {}

    @Override
    public GameState nextState(Linea game) {
        if (game.getMode().checkWinner(game)){
            return new EndGame();
        }
        return new RedPlays();
    }

    @Override
    public void playAsRed(Linea game, int columnIndex) {
        throw new RuntimeException(NoEsElTurnoDeRojo);

    }

    @Override
    public void playAsBlue(Linea game, int columnIndex) {
        new Chip('O', "Azules").playMe(game, columnIndex);
    }

}
