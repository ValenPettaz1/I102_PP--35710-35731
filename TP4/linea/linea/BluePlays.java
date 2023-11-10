package linea;

public class BluePlays extends GameState {

    public static String NoEsElTurnoDeRojo = "No es el turno de rojo";

    public BluePlays() {}

    @Override
    public void checkRedTurn(Linea game) {throw new RuntimeException(NoEsElTurnoDeRojo);}
    @Override
    public void checkBlueTurn(Linea game) {}

    @Override
    public boolean applies(boolean anyWinner, boolean isDraw, String color) {
        return !anyWinner && !isDraw && color.equals("Azules");
    }

    @Override
    public String getEndGameMessage(Linea game) {return "";}

}
