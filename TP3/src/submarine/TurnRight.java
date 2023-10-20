package submarine;

public class TurnRight extends Command {
    private char key = 'r';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeMe(Nemo nemo) {
        nemo.turnRight();
    }
}