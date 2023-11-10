package linea;

public class EndByWin extends EndGame{
    public EndByWin() {}
    @Override
    public boolean applies(boolean anyWinner, boolean isDraw, String color) {
        return anyWinner;
    }

    @Override
    public String getEndGameMessage(Linea game) {
        return "Ganaron las " + game.getLastColorPlayed();
    }
}
