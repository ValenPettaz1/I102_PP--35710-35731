package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Mode {
    public static List<Mode> validModes = new ArrayList<>(Arrays.asList(new ModeA(), new ModeB(), new ModeC()));

    public static Mode charForMode(char mode) {
        return validModes.stream()
                .filter(m -> m.applies(mode))
                .findFirst().get();
    }

    public abstract boolean applies(char mode);
    public abstract boolean checkFinish(Linea game);
}