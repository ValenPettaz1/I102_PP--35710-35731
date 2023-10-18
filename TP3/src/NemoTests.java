import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*; //OJO con el asterisco.


public class NemoTests {

    private North North = new North();
    private South South = new South();
    private East East = new East();
    private West West = new West();

    @BeforeEach public void setUp(){
        nemo = new Nemo(1,2, new North());
    }

    @Test public void testNemoStartingPosition() {
        assertPosition(nemo, 1,2,0, new North());
    }

    @Test public void testNemoCanStartInAnyDirection(){
        nemo = new Nemo(3,4,South);
        assertPosition(nemo,3,4,0,South);
    }

    @Test public void testNemoDoesNotMoveWithVoidOperations() {
        nemo.operate("");
        assertPosition( nemo, 1, 2, 0, North);
    }

    @Test public void testNemoDescends() {
        nemo.operate('d');
        assertPosition(nemo, 1, 2, -1, North);
    }

    @Test public void testNemoCannotFly() {
        nemo.operate('u');
        assertPosition(nemo, 1, 2, 0, North);
    }

    @Test public void testNemoAscends() {
        nemo.operate('d');
        nemo.operate('u');
        assertPosition(nemo, 1, 2, 0, North);
    }

    @Test public void testNemoTurnLeft(){
        nemo.operate('l');
        assertPosition(nemo, 1, 2, 0, West);
    }

    @Test public void testNemoTurnRight(){
        nemo.operate('r');
        assertPosition(nemo, 1, 2, 0, East);
    }



    @Test public void testNemoMoveForward(){
        nemo.operate('f');
        assertPosition(nemo, 1, 3, 0, North);
    }

    @Test public void testNemoAcceptsMultipleOperationsByString(){
        nemo.operate("ddrffl");
        assertPosition(nemo, 3, 2, -2, North);

    }

    @Test public void testTurnRightFourTimesReturnTheSamePosition(){
        assertPosition(nemo,1,2,0,North);
        nemo.operate("rrrr");
        assertPosition(nemo,1,2,0,North);
    }

    @Test public void testNemoDropsCapsuleInSurface(){
        nemo.operate('m');
        assertPosition(nemo, 1, 2, 0, North);
    }

    @Test public void testNemoDropsCapsuleInFirstLevel(){
        nemo.operate('d');
        nemo.operate('m');
        assertPosition(nemo, 1, 2, -1, North);
    }

    @Test public void testNemoCannotDropCapsuleInDepth(){
        nemo.operate('d');
        nemo.operate('d');
        assertThrowsLike(() -> nemo.operate("m"), DepthManager.NemoHasBeenDestoyed);
    }

    @Test public void testNemoCanDropCapsuleManyTimes(){
        nemo.operate("mmmmm");
        assertPosition(nemo, 1, 2, 0, North);
    }

    @Test public void testNemoCannotDropCapsuleInDepthsByString(){
        assertThrowsLike(() -> nemo.operate("dddmr"), DepthManager.NemoHasBeenDestoyed);
    }

    public void assertPosition(Nemo nemo, int x, int y, int z, Cardinal direction) {
        assertEquals(x, nemo.getPosition().getX());
        assertEquals(y, nemo.getPosition().getY());
        assertEquals(z, nemo.getDepth());
        assertEquals(direction, nemo.getDirection());
    }

    private void assertThrowsLike(Executable executable, String message) {
        assertEquals(message,
                assertThrows(Exception.class, executable)
                        .getMessage());
    }

    private Nemo nemo;
}


