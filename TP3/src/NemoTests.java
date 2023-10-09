import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;


public class NemoTests {

    @BeforeEach public void setUp(){
        nemo = new Nemo(1,2);
    }

    @Test public void test01StartingNemoPosition() {
        assertPosition(nemo, 1,2,0,0);
    }

    @Test public void test02NemoDoesNotMoveWithVoidOperations() {
        nemo.operate("");
        assertPosition( nemo, 1, 2, 0, 0);
    }

    @Test public void test03NemoDescends() {
        nemo.operate("d");
        assertPosition(nemo, 1, 2, -1, 0);
    }

    @Test public void test04NemoAscends() {
        nemo.operate("u");
        assertPosition(nemo, 1, 2, 0, 0);
    }

    @Test public void test05NemoTurnLeft(){
        nemo.operate("l");
        assertPosition(nemo, 1, 2, 0, 90);
    }

    @Test public void test06NemoTurnRight(){
        nemo.operate("r");
        assertPosition(nemo, 1, 2, 0, -90);
    }

    @Test public void test07NemoMoveForward(){
        nemo.operate("f");
        assertPosition(nemo, 2, 2, 0, 0);
    }

    @Test public void test08NemoDropsCapsuleInSurface(){
        nemo.operate("m");
        assertPosition(nemo, 1, 2, 0, 0);
    }

    @Test public void test09NemoCannotDropCapsuleInDepth(){
        nemo.operate("d");
        nemo.operate("d");
        assertThrowsLike(() -> nemo.operate("m"), "No se puede liberar la cápsula");
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


