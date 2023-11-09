package linea;

public class RedPlays extends GameState {

    public static String NoEsElTurnoDeAzul = "No es el turno de azul";

    public RedPlays() {}

    @Override
    public void checkRedTurn(Linea game) {}
    @Override
    public void checkBlueTurn(Linea game) {throw new RuntimeException(NoEsElTurnoDeAzul);}

    @Override
    public GameState nextState(Linea game) {
        if (game.getMode().checkWinner(game)){
            return new EndGame();
        }
        return new BluePlays();
    }
}
