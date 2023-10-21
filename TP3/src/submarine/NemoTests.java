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
    public void test01NemoStartingPosition() {
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void test02NemoCanStartInAnyDirection() {
        nemo = new Nemo(3, 4, south());
        assertPositionAndDirection(nemo, 3, 4, 0, south());
    }

    @Test
    public void test03NemoDoesNotMoveWithVoidStrings() {
        nemo.operate("");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void test04NemoDescendsFromSurfaceToFirstLevel() {
        nemo.operate('d');
        assertDepth(nemo, -1);
    }

    @Test
    public void test05NemoDescendsFromSurfaceToFirstLevelAndThenLower() {
        nemo.operate('d');
        nemo.operate('d');
        assertDepth(nemo, -2);
    }

    @Test
    public void test06NemoCannotFly() {
        nemo.operate('u');
        assertDepth(nemo, 0);
    }

    @Test
    public void test07RightLeftAndForwardOfNorth() {
        assertCardinals(east(), west(), new Coordinates(0, 1));
    }

    @Test
    public void test08RightLeftAndForwardOfSouth() {
        nemo = new Nemo(0,0, south());
        assertCardinals(west(), east(), new Coordinates(0, -1));
    }

    @Test
    public void test09RightLeftAndForwardOfEast() {
        nemo = new Nemo(0,0, east());
        assertCardinals(south(), north(), new Coordinates(1, 0));
    }

    @Test
    public void test10RightLeftAndForwardOfWest() {
        nemo = new Nemo(0,0,west());
        assertCardinals(north(), south(), new Coordinates(-1, 0));
    }

    @Test
    public void test11NemoTurnLeft() {
        nemo.operate('l');
       assertDirection(nemo, west());
    }

    @Test
    public void test12NemoTurnRight() {
        nemo.operate('r');
        assertDirection(nemo, east());
    }

    @Test
    public void test13NemoMoveForward() {
        nemo.operate('f');
        assertPositionAndDirection(nemo, 1, 3, 0, north());
    }

    @Test
    public void test14NemoAcceptsMultipleOperationsByString() {
        nemo.operate("drfl");
        assertPositionAndDirection(nemo, 2, 2, -1, north());
    }

    @Test
    public void test15NemoAscendsFromFirstLevelToSurface() {
        nemo.operate("du");
        assertDepth(nemo, 0);
    }

    @Test
    public void test16NemoAscendsFromLowerLevelToFirstLevel() {
        nemo.operate("ddu");
        assertDepth(nemo, -1);
    }

    @Test
    public void test17NemoDescendsInLowerLevels() {
        nemo.operate("dddddd");
        assertDepth(nemo, -6);
    }

    @Test
    public void test18NemoAscendsInLowerLevels() {
        nemo.operate("ddddddu");
        assertDepth(nemo, -5);
    }

    @Test
    public void test19TurnRightFourTimesReturnTheSamePosition() {
        assertPositionAndDirection(nemo, 1, 2, 0, north());
        nemo.operate("rrrr");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void test20TurnLeftFourTimesReturnTheSamePosition() {
        assertPositionAndDirection(nemo, 1, 2, 0, north());
        nemo.operate("llll");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void test21SquareRightMovementReturnToInitialPosition(){
        nemo.operate("rfrfrfrf");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void test22SquareLeftMovementReturnToInitialPosition(){
        nemo.operate("lflflflf");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void test23NemoOnlyIgnoreUpCommandsWhenAreInTheSurface() {
        nemo.operate("uuudduuuududdu");
        assertDepth(nemo, -1);
    }

    @Test
    public void test24NemoDropsCapsuleInSurface() {
        nemo.operate('m');
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void test25NemoDropsCapsuleInFirstLevel() {
        nemo.operate("dm");
        assertPositionAndDirection(nemo, 1, 2, -1, north());
    }

    @Test
    public void test26NemoExplodesIfDropCapsuleInLowerLevels() {
        nemo.operate("dd");
        assertNemoHasBeenDestroyed(() -> nemo.operate('m'));
    }

    @Test
    public void test27NemoCanDropCapsuleManyTimes() {
        nemo.operate("mmmmm");
        assertPositionAndDirection(nemo, 1, 2, 0, north());
    }

    @Test
    public void test28NemoExplodesIfDropCapsuleInDepthsByString() {
        assertNemoHasBeenDestroyed(() -> nemo.operate("dddm"));
    }

    @Test
    public void test29NemoDescendsLowerThanFirstLevelThenAscendsAndDropCapsule() {
        nemo.operate("dddduuuum");
        assertPositionAndDirection(nemo, 1,2,0, north());
    }

    @Test
    public void test30NemoDropCapsuleManyByAStringWithOtherCommands() {
        nemo.operate("mdmlfrrffmmmduuf");
        assertPositionAndDirection(nemo, 3, 2, 0, east());
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

    private void assertDirection(Nemo nemo, Cardinal direction) {
        assertEquals(direction, nemo.getDirection());
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


