package linea;

public class BluePlays extends GameState {

    public static String NoEsElTurnoDeRojo = "No es el turno de rojo";

    public BluePlays() {}

    @Override
    public void checkRedTurn(Linea game) {throw new RuntimeException(NoEsElTurnoDeRojo);}
    @Override
    public void checkBlueTurn(Linea game) {}

    @Override
    public GameState nextState(Linea game) {
        if (game.getMode().checkWinner(game)){
            return new EndGame();
        }
        return new RedPlays();
    }

}
