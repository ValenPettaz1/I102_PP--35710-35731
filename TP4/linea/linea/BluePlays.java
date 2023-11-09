package linea;

public class BluePlays extends GameState {

    public static String NoEsElTurnoDeRojo = "No es el turno de rojo";

    public BluePlays() {}

    @Override
    public void checkRedTurn(Linea game) {throw new RuntimeException(NoEsElTurnoDeRojo);}
    @Override
    public void checkBlueTurn(Linea game) {}

    @Override
    public boolean applies(boolean endGame, String color) {
        return !endGame && color.equals("Azules");
    }

}
