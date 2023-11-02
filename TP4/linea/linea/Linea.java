package linea;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Linea {
    private int base;
    private int height;
    private char mode;
    private char lastChipPlayed;
    private static String lastColorPlayed;

    private ArrayList<ArrayList<Character>> board = new ArrayList<>();

    public Linea(int base, int height, char mode) {
        for (int i = 0; i < base; i++) {
            board.add(new ArrayList<>());
        }
        this.base = base;
        this.height = height;
        this.mode = mode;
    }

    public void playRedAt(int columnIndex) {
        if (isOnBounds(columnIndex) && columnHasSpace(columnIndex)) {
            board.get(columnIndex).add('X');
            lastChipPlayed = 'X';
            lastColorPlayed = "Rojas";
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }

    public void playBlueAt(int columnIndex) {
        if (isOnBounds(columnIndex) && columnHasSpace(columnIndex)) {
            board.get(columnIndex).add('O');
            lastChipPlayed = 'O';
            lastColorPlayed = "Azules";
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }

    public boolean finished() {
        char chip = getLastChipPlayed();
        if (mode == 'A' || mode == 'C'){
            for (int i = 0; i < getHeight(); i++) {
                for (int j = 0; j < getBase(); j++) {
                    if (askForPoint(i,j) == chip){
                        if (askForPoint(i,j+1)== chip){
                            if (askForPoint(i,j+2) == chip){
                                if (askForPoint(i,j+3) == chip){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (mode == 'B' || mode == 'C'){
            for (int i = 0; i < getBase(); i++) {
                for (int j = 0; j < getHeight(); j++) {
                    if (askForPoint(i,j) == chip){
                        if (askForPoint(i+1,j)== chip){
                            if (askForPoint(i+2,j) == chip){
                                if (askForPoint(i + 3,j) == chip){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (mode == 'C'){
            for (int i = -getHeight()+1; i <= getBase()-3; i++){
                for (int j = 0; j <= getHeight()-3; j++) {
                    if (askForPoint(i, j) == chip) {
                        if (askForPoint(i + 1, j + 1) == chip) {
                            if (askForPoint(i + 2, j + 2) == chip) {
                                if (askForPoint(i + 3, j + 3) == chip) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = getBase() + getHeight() -1; i >= 3 ; i--){
                for (int j = 0; j <= getHeight()-3; j++) {
                    if (askForPoint(i, j) == chip) {
                        if (askForPoint(i -1, j + 1) == chip) {
                            if (askForPoint(i - 2, j + 2) == chip) {
                                if (askForPoint(i - 3, j + 3) == chip) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public String show() {
        return IntStream.rangeClosed(1, getHeight())
                .mapToObj(i -> getHeight() - i)
                .map(rowIndex -> "| " +
                        IntStream.range(0, getBase())
                                .mapToObj(columnIndex -> askForPoint(columnIndex, rowIndex) + " ")
                                .collect(Collectors.joining()) +
                        "|\n")
                .collect(Collectors.joining()) +
                "| " +
                IntStream.rangeClosed(0, getBase() - 1)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")) +
                " |\n";
    }

    private Character askForPoint(int columnIndex, int rowIndex) {
        if (isOnBounds(columnIndex) && rowIndex >= 0 && rowIndex < board.get(columnIndex).size()) {
            return board.get(columnIndex).get(rowIndex);
        }
        return '-';
    }

    private boolean columnHasSpace(int columnIndex) {
        return (columnIndex >= 0 && board.get(columnIndex).size() < getHeight());
    }

    private boolean isOnBounds(int columnIndex) {
        return (columnIndex < getBase() && columnIndex >= 0);
    }

    public int getBase() {
        return base;
    }
    public int getHeight() {
        return height;
    }
    public char getLastChipPlayed(){return lastChipPlayed;}
    public static String getLastColorPlayed() {return lastColorPlayed;}
}
