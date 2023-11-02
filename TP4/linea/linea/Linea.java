package linea;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return IntStream.rangeClosed(1, getHeight())
                .mapToObj(i -> getHeight() - i)
                .map(rowIndex -> "| " +
                        IntStream.range(0, getBase())
                                .mapToObj(columnIndex -> askForPoint(rowIndex, columnIndex) + " ")
                                .collect(Collectors.joining()) +
                        "|\n")
                .collect(Collectors.joining()) +
                "| " +
                IntStream.rangeClosed(0, getBase() - 1)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")) +
                " |\n";
    }




    private Character askForPoint(int rowIndex, int columnIndex) {
        if (isOnBounds(columnIndex) && rowIndex < board.get(columnIndex).size()) {
            return board.get(columnIndex).get(rowIndex);
        }
        return '-';
    }

    private boolean columnHasSpace(int columnIndex) {
        return board.get(columnIndex).size() < getHeight();
    }

    private boolean isOnBounds(int columnIndex) {
        return columnIndex < getBase();
    }
}
