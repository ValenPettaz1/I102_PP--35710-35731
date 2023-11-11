package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GameState {
    public static List<GameState> validStates = new ArrayList<>(Arrays.asList(new RedPlays(), new BluePlays(), new EndByWin(), new EndByDraw()));

    public static GameState nextState(boolean anyWinner, boolean isDraw, String color) {
        return validStates.stream()
                .filter(s -> s.applies(anyWinner, isDraw, color))
                .findFirst().get();
    }

    public boolean isEndGame() {return false;}

    public abstract void checkRedTurn(Linea game);

    public abstract void checkBlueTurn(Linea game);

    public abstract boolean applies(boolean anyWinner, boolean isDraw, String color);

    public abstract String getEndGameMessage(Linea game);
}
