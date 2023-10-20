package submarine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nemo {
    public static String NemoHasBeenDestoyed = "El submarino fue destruido, probablemente por exceso de chocolate";

    private Coordinates position;
    private Cardinal direction;
    private List<DepthManager> depthLevels;

    public Nemo(int xCoord, int yCoord, Cardinal direction) {
        this.position = new Coordinates(xCoord, yCoord);
        this.direction = direction;
        this.depthLevels = new ArrayList<>(Arrays.asList(new Surface()));
    }

    public void operate(Object commands) {
        Command.commandFor(commands, this);
    }

    public void ascend() {
        depthLevels.get(0).ascendMe(this);
    }

    public void descend() {
        depthLevels.get(0).descendMe(this);
    }

    public void turnRight() {
        direction = direction.getRight();
    }

    public void turnLeft() {
        direction = direction.getLeft();
    }

    public void moveForward() {
        position = position.add(direction.getFront());
    }

    public void dropCapsule() {
        depthLevels.get(0).capsuleHasBeenReleased();
    }

    public void addDepthLevel(DepthManager level) {
        depthLevels.add(0, level);
    }

    public void removeDepthLevel() {
        depthLevels.remove(0);
    }

    public Coordinates getPosition() {
        return position;
    }

    public Cardinal getDirection() {
        return direction;
    }

    public int getDepth() {
        return (depthLevels.size() - 1) * (-1);
    }
}