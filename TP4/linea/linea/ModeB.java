package linea;

import java.util.stream.IntStream;

public class ModeB extends GameMode{
    private char mode;
    public ModeB(){
        this.mode = 'B';
    }

    @Override
    public boolean applies(char mode) {
        return false;
    }

    @Override
    public boolean checkFinish(Linea game) {
        char chip = game.getLastChipPlayed();
        return IntStream.rangeClosed(0, game.getBase() - 4)
                .anyMatch(i -> IntStream.rangeClosed(0, game.getHeight() - 1)
                        .anyMatch(j -> game.askForPoint(i, j) == chip &&
                                game.askForPoint(i + 1, j) == chip &&
                                game.askForPoint(i + 2, j) == chip &&
                                game.askForPoint(i + 3, j) == chip));
    }
}
