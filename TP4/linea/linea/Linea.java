package linea;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Linea {
    private int base;
    private int height;
    private Mode mode;
    private String turn;
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
    }

    public void playRedAt(int columnIndex) {
        new GameState(this).setTurn("red");
        new Chip('X', "Rojas").playAt(this, columnIndex - 1);
    }

    public void playBlueAt(int columnIndex) {
        new Chip('O', "Azules").playAt(this, columnIndex - 1);
        setTurn("red");
    }

    public boolean finished() {
        if (getCountPlayed() == getBase() * getHeight()) {
            setLastColorPlayed("Empate");
            return true;
        }
        return mode.checkFinish(this);
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
    public void setCountPlayed(int countPlayed) {this.countPlayed = countPlayed;}
    public void setLastChipPlayed(char lastChipPlayed) {this.lastChipPlayed = lastChipPlayed;}
    public void setLastColorPlayed(String lastColorPlayed) {this.lastColorPlayed = lastColorPlayed;}

    public boolean isRedTurn() {return Objects.equals(getTurn(), "red");}

    public boolean isBlueTurn() {
        return Objects.equals(getTurn(), "blue");
    }

    public int getBase() {return base;}
    public int getHeight() {return height;}
    public String getTurn() {
        return turn;
    }
    public int getCountPlayed() {return countPlayed;}
    public char getLastChipPlayed(){return lastChipPlayed;}
    public String getLastColorPlayed() {return lastColorPlayed;}
    public ArrayList<ArrayList<Character>> getBoard() {return board;}

    public String getMatchResult() {
        return getLastColorPlayed();
    }
}
