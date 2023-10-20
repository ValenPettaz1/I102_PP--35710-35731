package submarine;

public abstract class Command {

    public abstract boolean applies(Character commandKey);
    public abstract void executeMe(Nemo nemo);
}