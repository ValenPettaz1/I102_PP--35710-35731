package linea;

public class BluePlays extends GameState {

    public BluePlays() {}

    @Override
    public void checkRedTurn(Linea game) {throw new RuntimeException(Linea.NoEsElTurnoDeRojo);}
    @Override
    public void checkBlueTurn(Linea game) {}

    @Override
    public boolean applies(boolean anyWinner, boolean isDraw, String color) {
        return !anyWinner && !isDraw && color.equals("Azules");
    }

    @Override
    public String getEndGameMessage(Linea game) {return "";}

}
