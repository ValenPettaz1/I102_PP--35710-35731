package submarine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NemoTests {

    @BeforeEach
    public void setUp() {
        nemo = new Nemo(1, 2, north());
    }

    @Test
    public void testNemoStartingPosition() {
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testNemoCanStartInAnyDirection() {
        nemo = new Nemo(3, 4, south());
        assertPositionAndDirection(nemo, 3, 4, 0, south());
    }

    @Test
    public void testNemoDoesNotMoveWithVoidStrings() {
        nemo.operate("");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testNemoDescends() {
        nemo.operate('d');
        assertPositionAndDirection(nemo, 1, 2, -1, north());
    }

    @Test
    public void testNemoAscends() {
        nemo.operate('d');
        nemo.operate('u');
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testNemoCannotFly() {
        nemo.operate('u');
        assertDepth(nemo, 0);
    }



    @Test
    public void testNemoTurnLeft() {
        nemo.operate('l');
        assertPositionAndDirection(nemo, 1, 2, 0, west());
    }

    @Test
    public void testNemoTurnRight() {
        nemo.operate('r');
        assertPositionAndDirection(nemo, 1, 2, 0, east());
    }



    @Test
    public void testNemoMoveForward() {
        nemo.operate('f');
        assertPositionAndDirection(nemo, 1, 3, 0, north());
    }

    @Test
    public void testNemoAcceptsMultipleOperationsByString() {
        nemo.operate("ddrffl");
        assertPositionAndDirection(nemo, 3, 2, -2, north());
    }

    @Test
    public void testSquareRightMovementReturnToInitialPosition(){
        nemo.operate("rfrfrfrf");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testRightLeftAndForwardOfNorth() {
        assertCardinals(east(), west(), new Coordinates(0, 1));
    }

    @Test
    public void testRightLeftAndForwardOfSouth() {
        nemo = new Nemo(0,0, south());
        assertCardinals(west(), east(), new Coordinates(0, -1));
    }

    @Test
    public void testRightLeftAndForwardOfEast() {
        nemo = new Nemo(0,0, east());
        assertCardinals(south(), north(), new Coordinates(1, 0));
    }

    @Test
    public void testRightLeftAndForwardOfWest() {
        nemo = new Nemo(0,0,west());
        assertCardinals(north(), south(), new Coordinates(-1, 0));
    }

    @Test
    public void testTurnRightFourTimesReturnTheSamePosition() {
        assertPositionAndDirection(nemo, 1, 2, 0, north());
        nemo.operate("rrrr");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testTurnLeftFourTimesReturnTheSamePosition() {
        assertPositionAndDirection(nemo, 1, 2, 0, north());
        nemo.operate("llll");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testSquareLeftMovementReturnToInitialPosition(){
        nemo.operate("lflflflf");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testNemoOnlyIgnoreUpCommandsWhenAreInTheSurface() {
        nemo.operate("uuudduuuududdu");
        assertDepth(nemo, -1);
    }

    @Test
    public void testNemoDropsCapsuleInSurface() {
        nemo.operate('m');
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testNemoDropsCapsuleInFirstLevel() {
        nemo.operate('d');
        nemo.operate('m');
        assertPositionAndDirection(nemo, 1, 2, -1, north());
    }

    @Test
    public void testNemoExplodesIfDropCapsuleInDepth() {
        nemo.operate('d');
        nemo.operate('d');
        assertNemoHasBeenDestroyed(() -> nemo.operate('m'));
    }

    @Test
    public void testNemoCanDropCapsuleManyTimes() {
        nemo.operate("mmmmm");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void testNemoExplodesIfDropCapsuleInDepthsByString() {
        assertNemoHasBeenDestroyed(() -> nemo.operate("dddm"));
    }

    @Test
    public void testNemoDescendsLowerThanFirstLevelThenAscendsAndDropCapsule() {
        nemo.operate("dddduuuum");
        assertPositionAndDirection(nemo, 1,2,0, north());
    }

    public void assertPositionAndDirection(Nemo nemo, int x, int y, int z, Cardinal direction) {
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
    private Cardinal north() {
        return new North();
    }

    private Cardinal south() {
        return new South();
    }

    private Cardinal east() {
        return new East();
    }

    private Cardinal west() {
        return new West();
    }
}


