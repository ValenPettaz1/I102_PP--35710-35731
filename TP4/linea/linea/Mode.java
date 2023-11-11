package linea;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Mode {
    public static List<Mode> validModes = new ArrayList<>(Arrays.asList(new ModeA(), new ModeB(), new ModeC(), new ModeError()));

    public static Mode charForMode(char mode) {
        return validModes.stream()
                .filter(m -> m.applies(mode))
                .findFirst().get();
    }

    public static boolean linealCheck(Linea game, int StreamStart, int StreamEnd, int subStreamStart, int subStreamEnd, Point increment) {
        char chip = game.getLastChipPlayed();
        return IntStream.rangeClosed(StreamStart, StreamEnd)
                .anyMatch(i -> IntStream.rangeClosed(subStreamStart, subStreamEnd)
                        .anyMatch(j -> IntStream.rangeClosed(0, 3)
                                .allMatch(k -> game.askForPoint(i + k * increment.x, j + k * increment.y) == chip)));
    }

    public abstract boolean checkWinner(Linea game);

    public abstract boolean applies(char mode);

    public boolean verticalCheck(Linea game) {
        return linealCheck(game, 0, game.getHeight(), 0, game.getBase(), new Point(1, 0));
    }

    public boolean horizontalCheck(Linea game) {
        return linealCheck(game, 0, game.getBase(), 0, game.getHeight(), new Point(0, 1));
    }

    public boolean rightDiagonalCheck(Linea game) {
        return linealCheck(game, -game.getHeight(), game.getBase(),
                0, game.getHeight(), new Point(1, 1));
    }

    public boolean leftDiagonalCheck(Linea game) {
        return linealCheck(game, 0, game.getBase() + game.getHeight(),
                0, game.getHeight(), new Point(-1, 1));
    }

    public boolean checkDraw(Linea linea) {
        return linea.getCountPlayed() == linea.getBase() * linea.getHeight();
    }
}