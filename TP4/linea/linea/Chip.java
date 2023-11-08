package linea;

public class Chip {
    public static String NoSePuedeJugarEnEstaColumna = "No se puede jugar en esta columna";
    private char chip;
    private String color;

    public Chip(char chip, String color) {
        this.chip = chip;
        this.color = color;
    }

    public void playMe(Linea game, int columnIndex){
        if (game.isOnBounds(columnIndex) && game.columnHasSpace(columnIndex)) {
            game.getBoard().get(columnIndex).add(chip);
            GameState newTurn = game.getTurn().nextState(game);
            game.setTurn(newTurn);
        } else {
            throw new RuntimeException(NoSePuedeJugarEnEstaColumna);
        }
    }

    public void updateChip(Linea game){
        game.setLastChipPlayed(chip);
        game.setLastColorPlayed(color);
        game.setCountPlayed(game.getCountPlayed() + 1);
    }
    public char getChip() {
        return chip;
    }

    public String getColor() {
        return color;
    }
}
