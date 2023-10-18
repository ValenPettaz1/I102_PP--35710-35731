import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nemo {

    private Point position;
    private Cardinal direction;
    private List<DepthManager> depthLevels;
    private List<Command> validCommands = new ArrayList<>(Arrays.asList(new Ascend(), new Descend(), new TurnLeft(), new TurnRight(), new MoveForward(), new DropCapsule()));

    public Nemo(int xCoord, int yCoord, Cardinal direction) {
        this.position = new Point(xCoord, yCoord);
        this.direction = direction;
        this.depthLevels = new ArrayList<>(Arrays.asList(new Surface()));
    }

    public void operate(Character character) {this.operate(character.toString());}
    public void operate(String commands) {
        commands.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> validCommands.stream()
                        .filter(command -> command.applies(c))
                        .findFirst()
                        .ifPresent(command -> command.executeCommand(this))); //OJO ESTO
    }

    public Nemo ascend() {
        depthLevels.get(0).ascendMe(this);
        return this;
    }
    public Nemo descend() {
        depthLevels.get(0).descendMe(this);
        return this;
    }

    public Nemo turnRight() {
        direction = direction.getRight();
        return this;
    }
    public Nemo turnLeft() {
        direction = direction.getLeft();
        return this;
    }

    public Nemo moveForward() {
        position = position.add(direction.getFront());
        return this;
    }
    public Nemo dropCapsule() {
        depthLevels.get(0).capsuleHasBeenReleased();
        return this;
    }

    public Nemo addDepthLevel(DepthManager level) {
        depthLevels.add(0, level);
        return this;
    }
    public Nemo removeDepthLevel() {
        depthLevels.remove(0);
        return this;
    }

    public Point getPosition() {return position;}
    public Cardinal getDirection() {return direction;}
    public int getDepth(){return depthLevels.size() - 1;}
}
