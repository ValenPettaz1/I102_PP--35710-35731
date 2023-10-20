package submarine;

public class DropCapsule extends Command {
    private char key = 'm';

    public boolean applies(Character commandKey) {return commandKey.equals(key);}

    public void executeMe(Nemo nemo) {nemo.dropCapsule();}
}