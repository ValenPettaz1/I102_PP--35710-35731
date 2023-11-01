package linea;

import java.util.ArrayList;
import java.util.List;

public class Linea {
    private int base;
    private int height;
    private char mode;

    private ArrayList<ArrayList<Character>> board = new ArrayList<>();

    public Linea(int base, int height, char mode) {
        for (int i = 0; i < base; i++) {
            board.add(new ArrayList<>());
        }
        this.base = base;
        this.height = height;
        this.mode = mode;
    }

    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }

    public void playRedAt(int columnIndex) {
        if (isOnBounds(columnIndex) && columnHasSpace(columnIndex)) {
            board.get(columnIndex).add('X');
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }

    public void playBlueAt(int columnIndex) {
        if (isOnBounds(columnIndex) && columnHasSpace(columnIndex)) {
            board.get(columnIndex).add('O');
        } else {
            throw new RuntimeException("No se puede jugar en esta columna");
        }
    }

    public boolean finished() {
        // Implementa la lógica para determinar si el juego ha terminado
        return false;
    }

    public String show() {
        StringBuilder result = new StringBuilder();

        for (int i = getHeight() - 1; i >= 0; i--) {
            result.append("| ");
            for (int j = 0; isOnBounds(j); j++) {
                if (board.get(j).size() > i) {
                    result.append(board.get(j).get(i)).append(" ");
                } else {
                    result.append("- ");
                }
            }
            result.append("|\n");
        }
        result.append("| ");
        for (int i = 0; isOnBounds(i); i++) {
            result.append(i).append(" ");
        }
        result.append("|\n");

        return result.toString();
    }

    private String askForThisPoint(int rowIndex, int columnIndex){
        return"A";
    }

    private boolean columnHasSpace(int columnIndex) {
        return board.get(columnIndex).size() < getHeight();
    }

    private boolean isOnBounds(int columnIndex) {
        return columnIndex < getBase();
    }
}
