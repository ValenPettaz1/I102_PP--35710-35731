package submarine;

public class Descend extends Command {
    private char key = 'd';

    public boolean applies(Character commandKey) {return commandKey.equals(key);}

    public void executeMe(Nemo nemo) {nemo.descend();}
}
