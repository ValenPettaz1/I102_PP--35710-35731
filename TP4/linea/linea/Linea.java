package linea;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Linea {

    public static String NoSePuedeJugarEnEstaColumna = "No se puede jugar en esta columna";
    public static String ModoNoValido = "Modo inválido";
    public static String NoEsElTurnoDeAzul = "No es el turno de azul";
    public static String NoEsElTurnoDeRojo = "No es el turno de rojo";
    public static String ElJuegoYaHaTerminado = "Ya terminó el juego";

    private int base;
    private int height;
    private Mode mode;
    private GameState gameState;
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
        this.gameState = new RedPlays();
    }


    public void playRedAt(int columnIndex) {
        playAt(columnIndex, () -> gameState.checkRedTurn(this), new Chip('X', "Rojas"), "Azules");
    }

    public void playBlueAt(int columnIndex) {
        playAt(columnIndex, () -> gameState.checkBlueTurn(this), new Chip('O', "Azules"), "Rojas");
    }

    public void playAt(int columnIndex, Runnable checkTurn, Chip chip, String color) {
        checkTurn.run();
        chip.playMe(this, columnIndex - 1);
        setGameState(GameState.nextState(mode.checkWinner(this), mode.checkDraw(this), color));
    }


    public boolean finished() {
        return gameState.isEndGame();
    }


    public String show() {
        String boardString = IntStream.rangeClosed(1, getHeight())
                .mapToObj(i -> getHeight() - i)
                .map(rowIndex -> "| " +
                        IntStream.range(0, getBase())
                                .mapToObj(columnIndex -> askForPoint(columnIndex, rowIndex) + " ")
                                .collect(Collectors.joining()) + "|\n")
                .collect(Collectors.joining());

        String columnNumbers = "  " +
                IntStream.rangeClosed(1, getBase())
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")) + "  \n";

        String endGame = gameState.getEndGameMessage(this);

        return boardString + columnNumbers + endGame;
    }


    public Character askForPoint(int columnIndex, int rowIndex) {
        if (isOnBounds(columnIndex) && rowIndex >= 0 && rowIndex < board.get(columnIndex).size()) {
            return board.get(columnIndex).get(rowIndex);
        }
        return '-';
    }

    public boolean columnHasSpace(int columnIndex) {
        return board.get(columnIndex).size() < getHeight();
    }

    public boolean isOnBounds(int columnIndex) {
        return columnIndex >= 0 && columnIndex < getBase();
    }

    public boolean isRedTurn() {return gameState instanceof RedPlays;}

    public boolean isBlueTurn() {
        return gameState instanceof BluePlays;
    }


    public void setGameState(GameState newState) {this.gameState = newState;}
    public void setCountPlayed(int countPlayed) {
        this.countPlayed = countPlayed;
    }
    public void setLastChipPlayed(char lastChipPlayed) {
        this.lastChipPlayed = lastChipPlayed;
    }
    public void setLastColorPlayed(String lastColorPlayed) {
        this.lastColorPlayed = lastColorPlayed;
    }


    public int getBase() {return base;}
    public int getHeight() {return height;}
    public int getCountPlayed() {
        return countPlayed;
    }
    public char getLastChipPlayed() {return lastChipPlayed;}
    public String getLastColorPlayed() {
        return lastColorPlayed;
    }
    public ArrayList<ArrayList<Character>> getBoard() {return board;}
}
