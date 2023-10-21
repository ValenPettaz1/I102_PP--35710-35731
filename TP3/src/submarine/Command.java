package submarine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {

    private static List<Command> validCommands = new ArrayList<>(Arrays.asList(new Ascend(), new Descend(), new TurnLeft(), new TurnRight(), new MoveForward(), new DropCapsule()));

    public static void commandFor(Object commands, Nemo nemo) {
        commands.toString()
                .chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> validCommands.stream()
                        .filter(cmd -> cmd.applies(c))
                        .findFirst()
                        .get()
                        .executeMe(nemo));
    }

    public abstract boolean applies(Character commandKey);
    public abstract void executeMe(Nemo nemo);
}