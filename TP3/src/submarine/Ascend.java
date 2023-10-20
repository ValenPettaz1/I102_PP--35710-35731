package submarine;

public class Ascend extends Command {
    private char key = 'u';

    public boolean applies(Character commandKey) {
        return commandKey.equals(key);
    }

    public void executeMe(Nemo nemo) {
        nemo.ascend();
    }
}
