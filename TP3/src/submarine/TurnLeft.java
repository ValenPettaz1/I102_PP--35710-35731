package submarine;

public class TurnLeft extends Command {
    private char key = 'l';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeMe(Nemo nemo) {
        nemo.turnLeft();
    }
}