package submarine;

public class MoveForward extends Command {
    private char key = 'f';

    public boolean applies(Character commandKey) {return commandKey.equals(key);}

    public void executeMe(Nemo nemo) {nemo.moveForward();}
}