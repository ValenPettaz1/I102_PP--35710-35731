package linea;

public abstract class GameState {

    private char chip;
    private String color;

    public GameState(){}

    public GameState(char chip, String color) {
        this.chip = chip;
        this.color = color;
    }

    public boolean checkFinish(Linea game){
        return false;
    }

    public void updateGame(Linea game) {
        game.setTurn(getNextTurn(game));
        game.setCountPlayed(game.getCountPlayed() + 1);
        game.setLastChipPlayed(getChip());
        game.setLastColorPlayed(getColor());
    }

    public abstract void playAsRed(Linea game, int columnIndex);
    public abstract void playAsBlue(Linea game, int columnIndex);
    public abstract GameState getNextTurn(Linea game);

    protected void playChip(Linea game, int columnIndex){
        if (game.isOnBounds(columnIndex) && game.columnHasSpace(columnIndex)) {
            game.getBoard().get(columnIndex).add(getChip());
            updateGame(game);
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }

    public char getChip() {
        return chip;
    }

    public String getColor() {
        return color;
    }
}
