package linea;

import java.util.ArrayList;

public abstract class Chip {
    private char chip;
    private String color;
    public Chip(char chip, String color) {
        this.chip = chip;
        this.color = color;
    }
    public abstract void playAt(Linea game, int columnIndex);
    public char getChip() {return chip;}
    public String getColor() {return color;}

}
