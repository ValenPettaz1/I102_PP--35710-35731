package linea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
Enunciado
Se nos pide desarrollar la lógica de juego del '4 en línea'. (https://www.epasatiempos.es/juego-4-en-raya.php)
El espacio de juego se define al iniciar, junto con la variante de triunfo.
La variante de triunfo puede ser,
- 'A' solo 4 en línea verticales u horizontales.
- 'B' solo 4 en línea diagonales
- 'C' 4 en línea en cualquier orientación.
Inician el juego las Rojas y alternan los turnos a partir de ahí
El juego puede terminar por triunfo o por empate. Una vez terminado no se puede seguir colocando fichas

Es requisito cumplir con todos los criterios vistos a lo largo de la cursada.

Se ofrece una pequeña interfaz de línea de comandos para correr por consola:
Debe respetarse el protocolo definido para Linea, el constructor y los mensajes playBlueAt, playRedAt, finished y show.
 */

public class LineaTest {

    @Test //OJO, test que expone atributos internos de la solución.
    public void testNewBoardHasCorrectDimensions() {
        Linea game = new Linea( 7, 6, 'A');
        assertEquals( 7, game.getBase() );
        assertEquals( 6, game.getHeight() );
    }

    @Test
    public void testNewBoardIsEmpty() { //VER! Estamos chequeando contra el tablero
        Linea game = new Linea(4, 4, 'A');
        assertEquals("| - - - - |\n" +
                            "| - - - - |\n" +
                             "| - - - - |\n" +
                             "| - - - - |\n" +
                             "| 1 2 3 4 |\n", game.show());
    }

    @Test
    public void testRedAlwaysPlayFirst(){
        Linea game = new Linea (4, 4, 'A');
        assertTrue(game.isRedTurn());
        assertFalse(game.isBlueTurn());
    }

    @Test
    public void testAfterRedIsBlueTurn(){
        Linea game = new Linea (4, 4, 'A');
        game.playRedAt(1);
        assertTrue(game.isBlueTurn());
        assertFalse(game.isRedTurn());
    }

    @Test
    public void testRedCannotPlayTwice(){
        Linea game = new Linea (4,4, 'A');
        game.playRedAt(1);
        assertThrowsLike(() -> game.playRedAt(1), "No es el turno de rojo");
    }

    @Test
    public void testBlueCannotPlayTwice(){
        Linea game = new Linea (4,4, 'A');
        game.playRedAt(1);
        game.playBlueAt(1);
        assertThrowsLike(() -> game.playBlueAt(1), RedPlays.NoEsElTurnoDeAzul);
    }

    @Test
    public void testChipFallsToBottom(){
        Linea game = new Linea (4,4, 'A');
        game.playRedAt(1);
        assertEquals('X', game.askForPoint(0,0));
    }

    @Test
    public void testCannotPlayInFullColumn(){
        Linea game = new Linea (4,4, 'A');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        assertThrowsLike(() -> game.playBlueAt(1), Chip.NoSePuedeJugarEnEstaColumna);
    }

    @Test
    public void testCannotPlayInColumnOutOfBounds(){
        Linea game = new Linea (4,4, 'A');
        assertThrowsLike(() -> game.playRedAt(5), Chip.NoSePuedeJugarEnEstaColumna);
    }

    @Test
    public void testRedWinInModeA(){
        Linea game = new Linea (4,4, 'A');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        assertTrue(game.finished());
        assertEquals("Rojas", game.getMatchResult());
    }

    @Test
    public void testBlueWinInModeA(){
        Linea game = boardForBlueWinInModeA();
        assertTrue(game.finished());
        assertEquals("Azules", game.getMatchResult());
    }

    @Test
    public void testRedWinByRightDiagonalInModeB(){
        Linea game = new Linea (4,4, 'B');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(4);
        assertTrue(game.finished());
        assertEquals("Rojas", game.getMatchResult());
    }

    @Test
    public void testRedWinByLeftDiagonalInModeB(){
    }

    @Test
    public void testBlueWinByRightDiagonalInModeB(){}

    @Test
    public void testBlueWinByLeftDiagonalInModeB(){}

    @Test
    public void testNotWinWithStraightLineInModeB(){}

    @Test
    public void testRedWinInModeC(){}

    @Test
    public void testBlueWinInModeC(){}

    @Test
    public void testDrawInModeA(){}
    @Test
    public void testDrawInModeB(){}
    @Test
    public void testDrawInModeC(){}
    @Test
    public void testCannotPlayAfterFinishGame(){
        Linea game = boardForBlueWinInModeA();
        assertTrue(game.finished());
        assertThrowsLike(() -> game.playRedAt(1), "El juego ya terminó");
    }

    private Linea boardForBlueWinInModeA() {
        Linea game = new Linea (4,4, 'A');
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(4);
        game.playBlueAt(2);
        return game;
    }

    private void assertThrowsLike(Executable executable, String message ) {
        assertEquals( message, assertThrows( Exception.class, executable ).getMessage() );
    }

    /*private void playAlternate(List<List<Integer>> columnIndexes, Linea game) {
        columnIndexes.forEach(par -> {
            game.playRedAt(par.get(0));
            game.playBlueAt(par.get(1));
        });
    }*/
}