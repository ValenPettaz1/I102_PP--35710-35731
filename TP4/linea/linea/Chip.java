package linea;

public class Chip {
    private char chip;
    private String color;

    public Chip(char chip, String color) {
        this.chip = chip;
        this.color = color;
    }

    public void playMe(Linea game, int columnIndex){
        if (game.isOnBounds(columnIndex) && game.columnHasSpace(columnIndex)) {
            game.getBoard().get(columnIndex).add(chip);
            game.setLastChipPlayed(chip);
            game.setLastColorPlayed(color);
            game.setCountPlayed(game.getCountPlayed() + 1);

        } else {
            throw new RuntimeException(Linea.NoSePuedeJugarEnEstaColumna);
        }
    }
}
