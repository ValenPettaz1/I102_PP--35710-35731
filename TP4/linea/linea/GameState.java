package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GameState {
    public static List<GameState> validStates = new ArrayList<>(Arrays.asList(new RedPlays(), new BluePlays(), new EndGame()));

    public static GameState nextState(boolean endGame, String color) {
        return validStates.stream()
                .filter(s -> s.applies(endGame, color))
                .findFirst().get();
    }

    public abstract void checkRedTurn(Linea game);
    public abstract void checkBlueTurn(Linea game);

    public abstract boolean applies(boolean endGame, String color);

    public boolean isEndGame() {
        return false;
    }
}
