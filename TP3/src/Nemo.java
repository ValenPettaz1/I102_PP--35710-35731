
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Nemo {

    private Point position;
    private Cardinal direction;
    private int depth;
    private CommandHandler commandHandler = new CommandHandler();
    public List<Command> validCommands = new ArrayList<>(Arrays.asList(new Ascend(), new Descend(), new TurnLeft(), new TurnRight(), new MoveForward(), new DropCapsule()));
    private List<DepthManager> depthLevels = new ArrayList<>(Arrays.asList(new Surface()));

    public Nemo(int xCoord, int yCoord, Cardinal direction) {
        this.position = new Point(xCoord, yCoord);
        this.direction = direction;
        this.depth = -1 * (depthLevels.size() - 1);
    }

    public void operate(){}

    public void operate(Character character) {
        this.operate(character.toString());
    }

    public void operate(String commands) {
        commands.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> validCommands.stream()
                        .filter(command -> command.applies(c))
                        .findFirst()
                        .ifPresent(command -> command.executeCommand(this)));
                /*.forEach(c -> commandHandler.operateMe(c,this));*/
    }

    public Nemo ascend() {
        depthLevels.get(0).ascendMe(this);
        depth = -1 * (depthLevels.size() - 1);
        return this;
    }
    public Nemo descend() {
        depthLevels.get(0).descendMe(this);
        depth = -1 * (depthLevels.size() - 1);
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

    public Nemo moveFront() {
        position = position.add(direction.getFront());
        return this;
    }
    public Nemo dropCapsule() {
        depthLevels.get(0).capsuleHasBeenReleased();
        return this;
    }

    public Nemo addDepthLevel(DepthManager level) {
        depthLevels.add(0,level);
        return this;
    }
    public Nemo removeDepthLevel() {
        depthLevels.remove(0);
        return this;
    }

    public Point getPosition() {return position;}
    public Cardinal getDirection() {return direction;}
    public int getDepth(){return depth;}
}
