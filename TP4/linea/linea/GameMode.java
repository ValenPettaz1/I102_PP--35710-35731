package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GameMode {
    private static List<GameMode> validModes = new ArrayList<>(Arrays.asList(new ModeA(), new ModeB(), new ModeC()));

    public static boolean charForMode(char mode, Linea game) {
        return validModes.stream().anyMatch(m -> m.applies(mode) && m.checkFinish(game));
    }

    public abstract boolean applies(char mode);
    public abstract boolean checkFinish(Linea game);

}
