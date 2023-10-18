import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {

    public abstract boolean applies(Character commandKey);
    public abstract void executeCommand(Nemo nemo);
}

class Ascend extends Command{
    private char key = 'u';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeCommand(Nemo nemo) {
        nemo.ascend();
    }
}

class Descend extends Command{
    private char key = 'd';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeCommand(Nemo nemo) {
        nemo.descend();
    }
}

class TurnLeft extends Command{
    private char key = 'l';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeCommand(Nemo nemo) {
        nemo.turnLeft();
    }
}

class TurnRight extends Command{
    private char key = 'r';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeCommand(Nemo nemo) {
        nemo.turnRight();
    }
}

class MoveForward extends Command{
    private char key = 'f';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeCommand(Nemo nemo) {
        nemo.moveFront();
    }
}

class DropCapsule extends Command{
    private char key = 'm';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeCommand(Nemo nemo) {
        nemo.dropCapsule();
    }
}