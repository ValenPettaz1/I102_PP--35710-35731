
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Nemo {

    private Point position;
    private Cardinal direction;
    private List<Depth> depthLevels = new ArrayList<>(Arrays.asList(new Surface()));

    public Nemo(int xCoord, int yCoord, Cardinal direction) {
        this.position = new Point(xCoord, yCoord, 0);
        this.direction = direction;
    }

    public void operate(){}

    public void operate(Character character) {
        this.operate(character.toString());
    }

    public void operate(String commands) {
        commands.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> new Command(c).operateMe(this));
    }

    public Nemo ascend() {
        position = depthLevels.get(0).ascendMe(this);
        return this;
    }
    public Nemo descend() {
        position = depthLevels.get(0).descendMe(this);
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
        depthLevels.get(0).dropCapsule(); //OJO COMO DISTRIBUIMOS ESTA RESPONSABILIDAD
        return this;
    }

    public Nemo addDepthLevel(Depth level) {
        depthLevels.add(0,level);
        return this;
    }
    public Nemo removeDepthLevel() {
        depthLevels.remove(0);
        return this;
    }

    public Point getPosition() {return position;}
    public Cardinal getDirection() {return direction;}
}
