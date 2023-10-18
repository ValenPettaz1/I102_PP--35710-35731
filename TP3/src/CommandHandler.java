import java.util.function.Consumer;

public class CommandHandler {
    private Consumer<Nemo>[] handlers = new Consumer[128];

    public CommandHandler() {
        handlers['u'] = Nemo::ascend;
        handlers['d'] = Nemo::descend;
        handlers['l'] = Nemo::turnLeft;
        handlers['r'] = Nemo::turnRight;
        handlers['f'] = Nemo::moveFront;
        handlers['m'] = Nemo::dropCapsule;
    }

    public Nemo operateMe(char command, Nemo nemo) {
        handlers[command].accept(nemo);
        return nemo;
    }
}
