import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler {
    private List<Command> validCommands = new ArrayList<>(Arrays.asList(new Ascend(), new Descend(), new TurnLeft(), new TurnRight(), new MoveForward(), new DropCapsule()));

    public CommandHandler(char c, Nemo nemo) {
        validCommands.stream()
                .filter(command -> command.applies(c))
                .findFirst()
                .ifPresent(command -> command.executeCommand(nemo));
    }
}
