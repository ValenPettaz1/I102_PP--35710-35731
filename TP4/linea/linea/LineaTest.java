package linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class LineaTest {

    @BeforeEach
    public void setUp() {
        game = new Linea(4, 4, 'A');
        gameB = new Linea(4, 4, 'B');
        gameC = new Linea(4, 4, 'C');
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
    public void testChipFallsToBottom(){
        game.playRedAt(1);
        assertEquals(game.getLastChipPlayed(), game.askForPoint(0,0));
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
        assertEquals("| - - - - |\n" +
                             "| - - - - |\n" +
                             "| - - - - |\n" +
                             "| X - - - |\n" +
                             "  1 2 3 4  \n", game.show());
    }

    @Test
    public void testBlueCannotPlayTwice(){
        game.playRedAt(1);
        game.playBlueAt(1);
        assertThrowsLike(() -> game.playBlueAt(1), Linea.NoEsElTurnoDeAzul);
        assertEquals("| - - - - |\n" +
                             "| - - - - |\n" +
                             "| O - - - |\n" +
                             "| X - - - |\n" +
                             "  1 2 3 4  \n", game.show());
    }

    @Test
    public void testChipCanBePlayedInTheFirstColumn(){
        game.playRedAt(1);
        assertEquals(game.getLastChipPlayed(), game.askForPoint(0,0));
        assertEquals("| - - - - |\n" +
                             "| - - - - |\n" +
                             "| - - - - |\n" +
                             "| X - - - |\n" +
                             "  1 2 3 4  \n", game.show());
    }

    @Test
    public void testChipCanBePlayedInTheLastColumn(){
        game.playRedAt(4);
        assertEquals(game.getLastChipPlayed(), game.askForPoint(3,0));
        assertEquals("| - - - - |\n" +
                             "| - - - - |\n" +
                             "| - - - - |\n" +
                             "| - - - X |\n" +
                             "  1 2 3 4  \n", game.show());
    }

    @Test
    public void testCannotPlayInFullColumn(){
        playAlternate(List.of(1,2,1,1,1), game);
        assertThrowsLike(() -> game.playBlueAt(1), Linea.NoSePuedeJugarEnEstaColumna);
        assertEquals("| X - - - |\n" +
                             "| O - - - |\n" +
                             "| X - - - |\n" +
                             "| X O - - |\n" +
                             "  1 2 3 4  \n", game.show());
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
    public void testRedVerticalWinInModeA(){
        playAlternate(List.of(1,2,1,2,1,2,1), game);
        assertTrue(game.finished());
        assertEquals("| X - - - |\n" +
                             "| X O - - |\n" +
                             "| X O - - |\n" +
                             "| X O - - |\n" +
                             "  1 2 3 4  \n" +
                             "Ganaron las Rojas", game.show());
    }

    @Test
    public void testRedHorizontalWinInModeA(){
        playAlternate(List.of(1,1,2,2,3,3,4), game);
        assertTrue(game.finished());
        assertEquals("| - - - - |\n" +
                             "| - - - - |\n" +
                             "| O O O - |\n" +
                             "| X X X X |\n" +
                             "  1 2 3 4  \n" +
                            "Ganaron las Rojas", game.show());
    }

    @Test
    public void testBlueWinInModeA(){
        playAlternate(List.of(4,2,1,2,1,2,1,2), game);
        assertTrue(game.finished());
        assertEquals("| - O - - |\n" +
                            "| X O - - |\n" +
                            "| X O - - |\n" +
                            "| X O - X |\n" +
                            "  1 2 3 4  \n" +
                            "Ganaron las Azules",game.show());
    }

    @Test
    public void testRedWinByRightDiagonalInModeB(){
        playAlternate(List.of(1,2,2,3,3,4,3,4,4,1,4), gameB);
        assertTrue(gameB.finished());
        assertEquals("| - - - X |\n" +
                             "| - - X X |\n" +
                             "| O X X O |\n" +
                             "| X O O O |\n" +
                             "  1 2 3 4  \n" +
                             "Ganaron las Rojas", gameB.show());
    }

    @Test
    public void testRedWinByLeftDiagonalInModeB(){
        playAlternate(List.of(4,3,3,1,2,2,2,4,1,1,1), gameB);
        assertTrue(gameB.finished());
        assertEquals(   "| X - - - |\n" +
                                "| O X - - |\n" +
                                "| X O X O |\n" +
                                "| O X O X |\n" +
                                "  1 2 3 4  \n" +
                                "Ganaron las Rojas",gameB.show());

    }

    @Test
    public void testBlueWinByRightDiagonalInModeB(){
        playAlternate(List.of(2,1,4,2,1,3,3,3,1,4,4,4), gameB);
        assertTrue(gameB.finished());
        assertEquals("| - - - O |\n" +
                             "| X - O X |\n" +
                             "| X O X O |\n" +
                             "| O X O X |\n" +
                             "  1 2 3 4  \n" +
                             "Ganaron las Azules", gameB.show());
    }

    @Test
    public void testBlueWinByLeftDiagonalInModeB(){
        playAlternate(List.of(3,4,2,3,2,2,1,1,1,1), gameB);
        assertTrue(gameB.finished());
        assertEquals("| O - - - |\n" +
                             "| X O - - |\n" +
                             "| O X O - |\n" +
                             "| X X X O |\n" +
                             "  1 2 3 4  \n" +
                             "Ganaron las Azules", gameB.show());
    }

    @Test
    public void testNotWinWithStraightLineInModeB(){
        playAlternate(List.of(1,2,1,2,1,2,1,2), gameB);
        assertFalse(gameB.finished());
        assertEquals("| X O - - |\n" +
                             "| X O - - |\n" +
                             "| X O - - |\n" +
                             "| X O - - |\n" +
                             "  1 2 3 4  \n" ,gameB.show());
    }

    @Test
    public void testRedWinInModeC(){
        playAlternate(List.of(1,2,2,3,3,4,3,4,4,1,4), gameC);
        assertTrue(gameC.finished());
        assertEquals("| - - - X |\n" +
                             "| - - X X |\n" +
                             "| O X X O |\n" +
                             "| X O O O |\n" +
                             "  1 2 3 4  \n" +
                             "Ganaron las Rojas", gameC.show());
    }

    @Test
    public void testBlueWinInModeC(){
        playAlternate(List.of(2,1,4,2,1,3,3,3,1,4,4,4), gameC);
        assertTrue(gameC.finished());
        assertEquals("| - - - O |\n" +
                             "| X - O X |\n" +
                             "| X O X O |\n" +
                             "| O X O X |\n" +
                             "  1 2 3 4  \n" +
                             "Ganaron las Azules", gameC.show());
    }

    @Test
    public void testDrawInModeA(){
        playAlternate(List.of(1,1,1,1,3,2,2,2,2,3,3,3,4,4,4,4), game);
        assertTrue(game.finished());
        assertEquals("| O X O O |\n"+
                             "| X O X X |\n"+
                             "| O X O O |\n"+
                             "| X O X X |\n"+
                             "  1 2 3 4  \n" +
                             "Empate", game.show());
    }
    @Test
    public void testDrawInModeB(){
        playAlternate(List.of(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4), gameB);
        assertTrue(gameB.finished());
        assertEquals(   "| O O O O |\n" +
                                "| X X X X |\n" +
                                "| O O O O |\n" +
                                "| X X X X |\n" +
                                "  1 2 3 4  \n" +
                                "Empate", gameB.show());
    }
    @Test
    public void testDrawInModeC(){
        playAlternate(List.of(1,3,4,2,1,4,2,2,3,1,1,3,4,4,3,2), gameC);
        assertTrue(gameC.finished());
        assertEquals("| X O X O |\n" +
                             "| O O O X |\n" +
                             "| X X X O |\n" +
                             "| X O O X |\n" +
                             "  1 2 3 4  \n" +
                             "Empate", gameC.show());
    }

    @Test
    public void testCannotPlayAfterFinishGame(){
        playAlternate(List.of(4,2,1,2,1,2,1,2), game);
        assertTrue(game.finished());
        assertThrowsLike(() -> game.playBlueAt(1), Linea.ElJuegoYaHaTerminado);
        assertEquals("| - O - - |\n" +
                "| X O - - |\n" +
                "| X O - - |\n" +
                "| X O - X |\n" +
                "  1 2 3 4  \n" +
                "Ganaron las Azules",game.show());
    }

    @Test
    public void testWinInAFullBoardDoesNotThrowDraw(){
        playAlternate(List.of(4,3,2,1,1,2,3,4,1,3,2,4,2,1,3,4), gameB);
        assertTrue(gameB.finished());
        assertEquals(   "| O X X O |\n" +
                                "| X X O O |\n" +
                                "| X O X O |\n" +
                                "| O X O X |\n" +
                                "  1 2 3 4  \n" +
                                "Ganaron las Azules",gameB.show());
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
    private Linea gameB;
    private Linea gameC;
}