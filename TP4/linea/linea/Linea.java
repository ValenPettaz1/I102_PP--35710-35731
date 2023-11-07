package linea;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Linea {
    private int base;
    private int height;
    private Mode mode;
    private GameState turn;
    private int countPlayed = 0;
    private char lastChipPlayed;
    private String lastColorPlayed;
    private ArrayList<ArrayList<Character>> board;

    public Linea(int base, int height, char charMode) {
        this.base = base;
        this.height = height;
        this.board = Stream.generate(ArrayList<Character>::new)
                .limit(base)
                .collect(Collectors.toCollection(ArrayList::new));
        this.mode = Mode.charForMode(charMode);
        this.turn = new RedPlays();
    }

    public void playRedAt(int columnIndex) {
        getTurn().playAsRed(this, columnIndex - 1);
    }

    public void playBlueAt(int columnIndex) {
        getTurn().playAsBlue(this, columnIndex - 1);
    }

    public boolean finished() {
        if (getCountPlayed() == getBase() * getHeight()) {
            setLastColorPlayed("Empate");
            return true;
        }
        return mode.checkWinner(this);
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
                IntStream.rangeClosed(1, getBase())
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")) + " |\n";


        String winner = finished() ? "Ganador: " + getMatchResult() : "";
        return boardString + columnNumbers;
    }

    public Character askForPoint(int columnIndex, int rowIndex) {
        if (isOnBoard(columnIndex, rowIndex)) {
            return board.get(columnIndex).get(rowIndex);
        }
        return '-';
    }

    private boolean isOnBoard(int columnIndex, int rowIndex) {
        return isOnBounds(columnIndex) && rowIndex >= 0 && rowIndex < board.get(columnIndex).size();
    }

    public boolean columnHasSpace(int columnIndex) {
        return columnIndex >= 0 && board.get(columnIndex).size() < getHeight();
    }

    public boolean isOnBounds(int columnIndex) {
        return columnIndex >= 0 && columnIndex < getBase();
    }

    public void setTurn(GameState newTurn) {
        this.turn = newTurn;
    }

    public void setCountPlayed(int countPlayed) {
        this.countPlayed = countPlayed;
    }

    public void setLastChipPlayed(char lastChipPlayed) {
        this.lastChipPlayed = lastChipPlayed;
    }

    public void setLastColorPlayed(String lastColorPlayed) {
        this.lastColorPlayed = lastColorPlayed;
    }

    public boolean isRedTurn() {
        return getTurn() instanceof RedPlays;
    }

    public boolean isBlueTurn() {
        return getTurn() instanceof BluePlays;
    }

    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }

    public GameState getTurn() {
        return turn;
    }

    public int getCountPlayed() {
        return countPlayed;
    }

    public char getLastChipPlayed() {
        return lastChipPlayed;
    }

    public String getLastColorPlayed() {
        return lastColorPlayed;
    }

    public ArrayList<ArrayList<Character>> getBoard() {
        return board;
    }

    public String getMatchResult() {
        return getLastColorPlayed();
    }
}
