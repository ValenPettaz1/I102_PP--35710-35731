package linea;

import org.junit.jupiter.api.Test;

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

    @Test
    public void testNewBoardHasCorrectDimensions() {
        Linea game = new Linea( 7, 6, 'A');
        assertEquals( 7, game.getBase() );
        assertEquals( 6, game.getHeight() );
    }

    @Test
    public void testNewBoardIsEmpty() { //VER! Estamos chequeando contra el tablero
        Linea game = new Linea(4, 3, 'A');
        assertEquals("| - - - - |\n" +
                             "| - - - - |\n" +
                             "| - - - - |\n" +
                             "| 0 1 2 3 |\n", game.show());
    }

    @Test
    public void testRedAlwaysPlayFirst(){
        Linea game = new Linea (4, 3, 'A');
        assertTrue(game.isRedTurn());
        assertFalse(game.isBlueTurn());
    }

    @Test
    public void testAfterRedIsBlueTurn(){
        Linea game = new Linea (4, 3, 'A');
        game.playRedAt(0);
        assertTrue(game.isBlueTurn());
        assertFalse(game.isRedTurn());
    }
    
}