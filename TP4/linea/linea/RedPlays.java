package linea;

public class RedPlays extends GameState {


    public static String NoEsElTurnoDeAzul = "No es el turno de azul";

    public RedPlays() {}

    @Override
    public GameState nextState(Linea game) {
        if (game.getMode().checkWinner(game)){
            return new EndGame();
        }
        return new BluePlays();
    }

    @Override
    public void playAsRed(Linea game, int columnIndex) {
        new Chip('X', "Rojas").playMe(game, columnIndex);
    }

    @Override
    public void playAsBlue(Linea game, int columnIndex) {
    throw new RuntimeException(NoEsElTurnoDeAzul);

    }




}
