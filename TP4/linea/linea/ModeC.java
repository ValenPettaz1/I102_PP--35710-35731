package linea;

import java.util.stream.IntStream;

public class ModeC extends GameMode{
    private char mode;
    public ModeC(){
        this.mode = 'C';
    }

    @Override
    public boolean applies(char mode) {
        return false;
    }

    @Override
    public boolean checkFinish(Linea game) {
        char chip = game.getLastChipPlayed();
        boolean rightDiagonal = IntStream.rangeClosed(-game.getHeight() + 1, game.getBase() - 3)
                .anyMatch(i -> IntStream.rangeClosed(0, game.getHeight() - 3)
                        .anyMatch(j -> game.askForPoint(i, j) == chip &&
                                game.askForPoint(i + 1, j + 1) == chip &&
                                game.askForPoint(i + 2, j + 2) == chip &&
                                game.askForPoint(i + 3, j + 3) == chip));

        boolean leftDiagonal = IntStream.rangeClosed(game.getBase() + game.getHeight() - 1, 3)
                .anyMatch(i -> IntStream.rangeClosed(0, game.getHeight() - 3)
                        .anyMatch(j -> game.askForPoint(i, j) == chip &&
                                game.askForPoint(i - 1, j + 1) == chip &&
                                game.askForPoint(i - 2, j + 2) == chip &&
                                game.askForPoint(i - 3, j + 3) == chip));

        return rightDiagonal || leftDiagonal || verticalCheck(game) || horizontalCheck(game);
    }

    private boolean verticalCheck(Linea game){
        return new ModeA().checkFinish(game);
    }

    private boolean horizontalCheck(Linea game){
        return new ModeB().checkFinish(game);
    }
}
