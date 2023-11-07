package linea;

public abstract class GameState {

    private char chip;
    private String color;

    public GameState(char chip, String color) {
        this.chip = chip;
        this.color = color;
    }

    public void updateGame(Linea game) {
        game.setCountPlayed(game.getCountPlayed() + 1);
        game.setLastChipPlayed(getChip());
        game.setLastColorPlayed(getColor());
        game.setTurn(getNextTurn());
    }

    public abstract void playAsRed(Linea game, int columnIndex);
    public abstract void playAsBlue(Linea game, int columnIndex);
    public abstract GameState getNextTurn();


    public char getChip() {
        return chip;
    }

    public String getColor() {
        return color;
    }
}
