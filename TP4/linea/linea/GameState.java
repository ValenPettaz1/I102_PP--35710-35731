package linea;

public abstract class GameState {
    private char chip;
    private String color;
    public GameState(char chip, String color) {
        this.chip = chip;
        this.color = color;
    }
    public abstract void setTurn(Linea game);
    public abstract void playAt(Linea game, int columnIndex);
    public abstract void changeTurn(Linea game);

    public void updateGame(Linea game){
        game.setCountPlayed(game.getCountPlayed() + 1);
        game.setLastChipPlayed(getChip());
        game.setLastColorPlayed(getColor());
    }

    /*{
        if (game.isRedTurn() && getColor() == "Azules") {
            throw new RuntimeException("No es el turno de jugar");
        }
        if (game.isBlueTurn() && getColor() == "Rojas") {
            throw new RuntimeException("No es el turno de jugar");
        }
        if (game.isOnBounds(columnIndex) && game.columnHasSpace(columnIndex)) {
            game.getBoard().get(columnIndex).add(getChip());
            updateGame(game);
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }*/
    public char getChip() {return chip;}
    public String getColor() {return color;}

}
