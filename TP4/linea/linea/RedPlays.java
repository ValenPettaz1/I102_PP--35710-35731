package linea;

public class RedPlays extends GameState {

    public static String NoEsElTurnoDeAzul = "No es el turno de azul";

    public RedPlays() {}

    @Override
    public void checkRedTurn(Linea game) {}
    @Override
    public void checkBlueTurn(Linea game) {throw new RuntimeException(NoEsElTurnoDeAzul);}

    @Override
    public boolean applies(boolean anyWinner, boolean isDraw, String color) {
        return !anyWinner && !isDraw && color.equals("Rojas");
    }

    @Override
    public String getEndGameMessage(Linea game) {return "";}
}
