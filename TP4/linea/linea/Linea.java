package linea;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Linea {
    private int base;
    private int height;
    private char mode;
    private char lastChipPlayed;
    private static String lastColorPlayed;
    private String turn;


    private ArrayList<ArrayList<Character>> board;

    public Linea(int base, int height, char mode) {
        this.base = base;
        this.height = height;
        this.mode = mode;
        this.board = Stream.generate(ArrayList<Character>::new)
                .limit(base)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void playRedAt(int columnIndex) {
        Chip redChip = new Red();
        redChip.playAt(this, columnIndex);
        lastChipPlayed = redChip.getChip();
        lastColorPlayed = redChip.getColor();
    }

    public void playBlueAt(int columnIndex) {
        Chip blueChip = new Blue();
        blueChip.playAt(this, columnIndex);
        lastChipPlayed = blueChip.getChip();
        lastColorPlayed = blueChip.getColor();
    }

    public boolean finished() { //OJO: por ahora C solo chequea diagonales
        return GameMode.charForMode(mode, this);
    }

    public String show() {
        String boardString = IntStream.rangeClosed(1, getHeight())
                .mapToObj(i -> getHeight() - i)
                .map(rowIndex -> "| " +
                    IntStream.range(0, getBase())
                            .mapToObj(columnIndex -> askForPoint(columnIndex, rowIndex) + " ")
                            .collect(Collectors.joining()) + "|\n")
                .collect(Collectors.joining());

        String columnNumbers = "| " +
                IntStream.rangeClosed(0, getBase() - 1)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")) + " |\n";

        return boardString + columnNumbers;
    }

    public Character askForPoint(int columnIndex, int rowIndex) {
        if (isOnBounds(columnIndex) && rowIndex >= 0 && rowIndex < board.get(columnIndex).size()) {
            return board.get(columnIndex).get(rowIndex);
        }
        return '-';
    }

    public boolean columnHasSpace(int columnIndex) {
        return columnIndex >= 0 && board.get(columnIndex).size() < getHeight();
    }

    public boolean isOnBounds(int columnIndex) {
        return columnIndex >= 0 && columnIndex < getBase();
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public boolean isRedTurn() {
        return getTurn()== "red" ;
    }

    public boolean isBlueTurn() {
        return getTurn()== "blue";
    }

    public String getTurn() {
        return turn;
    }

    public int getBase() {return base;}
    public int getHeight() {return height;}
    public char getLastChipPlayed(){return lastChipPlayed;}
    public static String getLastColorPlayed() {return lastColorPlayed;}
    public ArrayList<ArrayList<Character>> getBoard() {return board;}


}
