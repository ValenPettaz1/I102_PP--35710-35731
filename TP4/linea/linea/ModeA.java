package linea;

import java.util.stream.IntStream;

public class ModeA extends Mode {
    private char mode;
    public ModeA(){
        this.mode = 'A';
    }

    @Override
    public boolean applies(char mode) {
        return this.mode == mode;
    }

    @Override
    public boolean checkFinish(Linea game) {
        char chip = game.getLastChipPlayed();
        return IntStream.rangeClosed(0, game.getHeight() - 1)
                .anyMatch(i -> IntStream.rangeClosed(0, game.getBase() - 4)
                        .anyMatch(j -> game.askForPoint(i, j) == chip &&
                                game.askForPoint(i, j + 1) == chip &&
                                game.askForPoint(i, j + 2) == chip &&
                                game.askForPoint(i, j + 3) == chip));
    }
}
