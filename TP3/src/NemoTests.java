import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;


public class NemoTests {

    @BeforeEach public void setUp(){
        nemo = new Nemo(1,2);
    }

    @Test public void testNemoStartingPosition() {
        assertPosition(nemo, 1,2,0,0);
    }

    @Test public void testNemoDoesNotMoveWithVoidOperations() {
        nemo.operate("");
        assertPosition( nemo, 1, 2, 0, 0);
    }

    @Test public void testNemoDescends() {
        nemo.operate("d");
        assertPosition(nemo, 1, 2, -1, 0);
    }

    @Test public void testNemoAscends() {
        nemo.operate("d");
        nemo.operate("u");
        assertPosition(nemo, 1, 2, 0, 0);
    }

    @Test public void testNemoCannotFly() {
        nemo.operate("u");
        assertPosition(nemo, 1, 2, 0, 0);
    }

    @Test public void testNemoTurnLeft(){
        nemo.operate("l");
        assertPosition(nemo, 1, 2, 0, 90);
    }

    @Test public void testNemoTurnRight(){
        nemo.operate("r");
        assertPosition(nemo, 1, 2, 0, -90);
    }

    @Test public void testNemoMoveForward(){
        nemo.operate("f");
        assertPosition(nemo, 2, 2, 0, 0);
    }

    @Test public void testNemoHasCapsuleWhenCreated(){
        assertTrue(nemo.hasCapsule());
    }

    @Test public void testNemoDropsCapsuleInSurface(){
        nemo.operate("m");
        assertFalse(nemo.hasCapsule());
        assertPosition(nemo, 1, 2, 0, 0);
    }

    @Test public void testNemoDropsCapsuleInFirstLevel(){
        nemo.operate("d");
        nemo.operate("m");
        assertFalse(nemo.hasCapsule());
        assertPosition(nemo, 1, 2, -1, 0);
    }

    @Test public void testNemoCannotDropCapsuleInDepth(){
        nemo.operate("d");
        nemo.operate("d");
        assertThrowsLike(() -> nemo.operate("m"), "No se puede liberar la cápsula debajo del primer nivel");
    }

    @Test public void testNemoCannotDropCapsuleTwice(){
        nemo.operate("m");
        assertFalse(nemo.hasCapsule());
        assertThrowsLike(() -> nemo.operate("m"), "La capsula ya fue lanzada");
    }

    @Test public void testNemoAcceptsMultipleOperationsByString(){
        nemo.operate("ddrffl");
        assertPosition(nemo, 2, 3, -2, 90);
    }



    public void assertPosition(Nemo nemo, int x, int y, int z, int direction) {
        assertEquals(x, nemo.getX());
        assertEquals(y, nemo.getY());
        assertEquals(z, nemo.getZ());
        assertEquals(direction, nemo.getDirection());
    }

    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message,
                assertThrows(Exception.class, executable)
                        .getMessage());
    }

    private Nemo nemo;



}


