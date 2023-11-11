package linea;

public class EndByDraw extends EndGame {
    public EndByDraw() {}

    @Override
    public boolean applies(boolean anyWinner, boolean isDraw, String color) {
        return isDraw;
    }

    @Override
    public String getEndGameMessage(Linea game) {
        return "Empate";
    }
}
