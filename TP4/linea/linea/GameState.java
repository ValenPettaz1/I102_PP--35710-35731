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
    }

    public abstract void playAt(Linea game, int columnIndex);

    public abstract void changeTurn(Linea game);

    protected abstract void changeToRed(Linea game);
    protected abstract void changeToBlue(Linea game);

    protected abstract void nextChange(Linea game);

    public char getChip() {
        return chip;
    }

    public String getColor() {
        return color;
    }
}
