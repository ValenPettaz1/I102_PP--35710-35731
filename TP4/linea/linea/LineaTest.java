package linea;

import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setUp() {
        game = new Linea(4, 4, 'A');
    }

    @Test
    public void testNewBoardIsEmptyAndHasCorrectDimensions() {
        assertEquals("| - - - - |\n" +
                             "| - - - - |\n" +
                             "| - - - - |\n" +
                             "| - - - - |\n" +
                             "  1 2 3 4  \n", game.show());
    }

    @Test
    public void testRedAlwaysPlayFirst(){
        assertTrue(game.isRedTurn());
        assertFalse(game.isBlueTurn());
    }

    @Test
    public void testAfterRedIsBlueTurn(){
        game.playRedAt(1);
        assertTrue(game.isBlueTurn());
        assertFalse(game.isRedTurn());
    }

    @Test
    public void testRedCannotPlayTwice(){
        game.playRedAt(1);
        assertThrowsLike(() -> game.playRedAt(1), Linea.NoEsElTurnoDeRojo);
    }

    @Test
    public void testBlueCannotPlayTwice(){
        game.playRedAt(1);
        game.playBlueAt(1);
        assertThrowsLike(() -> game.playBlueAt(1), Linea.NoEsElTurnoDeAzul);
    }

    @Test
    public void testChipFallsToBottom(){
        game.playRedAt(1);
        assertEquals(game.getLastChipPlayed(), game.askForPoint(0,0));
    }

    @Test
    public void testChipCanBePlayedInTheFirstColumn(){
        game.playRedAt(1);
        assertEquals(game.getLastChipPlayed(), game.askForPoint(0,0));
    }

    @Test
    public void testChipCanBePlayedInTheLastColumn(){
        game.playRedAt(4);
        assertEquals(game.getLastChipPlayed(), game.askForPoint(3,0));
    }

    @Test
    public void testCannotPlayInFullColumn(){
        playAlternate(List.of(1,2,1,1,1), game);
        assertThrowsLike(() -> game.playBlueAt(1), Linea.NoSePuedeJugarEnEstaColumna);
    }

    @Test
    public void testCannotPlayInColumnAfterBounds(){
        assertThrowsLike(() -> game.playRedAt(5), Linea.NoSePuedeJugarEnEstaColumna);
    }

    @Test
    public void testCannotPlayInColumnBeforeBounds(){
        assertThrowsLike(() -> game.playRedAt(0), Linea.NoSePuedeJugarEnEstaColumna);
    }

    @Test void testInvalidModeThrowError(){
        assertThrowsLike(() -> new Linea(4,4,'D'), Linea.ModoNoValido);
    }

    @Test
    public void testRedWinInModeA(){
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(1);
        assertTrue(game.finished());
        assertEquals("Rojas", game.getLastColorPlayed());
    }

    @Test
    public void testBlueWinInModeA(){
        Linea game = boardForBlueWinInModeA();
        assertTrue(game.finished());
        assertEquals("Azules", game.getLastColorPlayed());
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
        game.playBlueAt(1);
        game.playRedAt(4);
        assertTrue(game.finished());
        assertEquals("Rojas", game.getLastColorPlayed());
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
        assertThrowsLike(() -> game.playRedAt(1), Linea.ElJuegoYaHaTerminado);
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

    private void playAlternate(List<Integer> columnIndexes, Linea game) {
        columnIndexes.forEach( index -> {
            if (game.isRedTurn()) {
                game.playRedAt(index);
            } else {
                game.playBlueAt(index);
            }
        });
    }

    private Linea game;
}