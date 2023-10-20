package submarine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NemoTests {

    private Cardinal north() { return new North(); }
    private Cardinal south() { return new South(); }
    private Cardinal east() { return new East(); }
    private Cardinal west() { return new West(); }

    @BeforeEach public void setUp(){
        nemo = new Nemo(1,2,  north());
    }

    @Test public void testNemoStartingPosition() {
        assertPosition(nemo, 1,2,0,  north());
    }

    @Test public void testNemoCanStartInAnyDirection(){
        nemo = new Nemo(3,4,south());
        assertPosition(nemo,3,4,0,south());
    }

    @Test public void testNemoDoesNotMoveWithVoidOperations() {
        nemo.operate("");
        assertPosition( nemo, 1, 2, 0, north());
    }

    @Test public void testNemoDescends() {
        nemo.operate('d');
        assertPosition(nemo, 1, 2, -1, north());
    }

    @Test public void testNemoCannotFly() {
        nemo.operate('u');
        assertDepth(nemo, 0);
    }


    @Test public void testNemoCannotFlyByString(){
        nemo.operate("uuudduuuududdu");
        assertDepth(nemo, -1);
    }

    @Test public void testNemoAscends() {
        nemo.operate('d');
        nemo.operate('u');
        assertPosition(nemo, 1, 2, 0, north());
    }

    @Test public void testCardinals(){
        assertCardinals(east(), west(), new Coordinates(1,0));
        nemo.operate('r');
        assertCardinals(south(), north(), new Coordinates(0,-1));
        nemo.operate('r');
        assertCardinals(west(), east(), new Coordinates(-1,0));
        nemo.operate('r');
        assertCardinals(north(), south(), new Coordinates(0,1));
        nemo.operate('r');
        assertCardinals(east(), west(), new Coordinates(1,0));
    }

    @Test public void testNemoTurnLeft(){
        nemo.operate('l');
        assertPosition(nemo, 1, 2, 0, west());
    }

    @Test public void testNemoTurnRight(){
        nemo.operate('r');
        assertPosition(nemo, 1, 2, 0, east());
    }

    @Test public void testNemoMoveForward(){
        nemo.operate('f');
        assertPosition(nemo, 1, 3, 0, north());
    }

    @Test public void testNemoAcceptsMultipleOperationsByString(){
        nemo.operate("ddrffl");
        assertPosition(nemo, 3, 2, -2, north());

    }



    @Test public void testTurnRightFourTimesReturnTheSamePosition(){
        assertPosition(nemo,1,2,0,north());
        nemo.operate("rrrr");
        assertPosition(nemo,1,2,0,north());
    }

    @Test public void testNemoDropsCapsuleInSurface(){
        nemo.operate('m');
        assertPosition(nemo, 1, 2, 0, north());
    }

    @Test public void testNemoDropsCapsuleInFirstLevel(){
        nemo.operate('d');
        nemo.operate('m');
        assertPosition(nemo, 1, 2, -1, north());
    }

    @Test public void testNemoCannotDropCapsuleInDepth(){
        nemo.operate('d');
        nemo.operate('d');
        assertNemoHasBeenDestroyed(() -> nemo.operate("m"));
    }

    @Test public void testNemoCanDropCapsuleManyTimes(){
        nemo.operate("mmmmm");
        assertPosition(nemo, 1, 2, 0, north());
    }

    @Test public void testNemoCannotDropCapsuleInDepthsByString(){
        assertNemoHasBeenDestroyed(() -> nemo.operate("dddmr"));
    }

    public void assertPosition(Nemo nemo, int x, int y, int z, Cardinal direction) {
        assertEquals(x, nemo.getPosition().getX());
        assertEquals(y, nemo.getPosition().getY());
        assertEquals(z, nemo.getDepth());
        assertEquals(direction, nemo.getDirection());
    }

    private void assertCardinals(Cardinal cardinalAtRight, Cardinal cardinalAtLeft, Coordinates vectorToMoveForward) {
        assertEquals(nemo.getDirection().getRight(), cardinalAtRight);
        assertEquals(nemo.getDirection().getLeft(), cardinalAtLeft);
        assertEquals(nemo.getDirection().getFront(), vectorToMoveForward);
    }

    private void assertNemoHasBeenDestroyed(Executable executable) {
        assertEquals(Nemo.NemoHasBeenDestoyed,
                assertThrows(Exception.class, executable)
                        .getMessage());
    }

    private void assertDepth(Nemo nemo, int depth) {
        assertEquals(depth, nemo.getDepth());
    }


    private Nemo nemo;
}


