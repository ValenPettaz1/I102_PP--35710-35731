package linea;

public abstract class GameState {

    /*public void updateGame(Linea game) {
        if (game.finished()){
            game.setTurn(new EndGame());
        }
        else{
            game.setTurn(getNextTurn(game));
        }
    }*/

    public abstract void playAsRed(Linea game, int columnIndex);
    public abstract void playAsBlue(Linea game, int columnIndex);
    public abstract GameState nextState(Linea game);

    public boolean isEndGame() {
        return false;
    }

    public GameState checkWin(Linea game){
        if (game.getMode().checkWinner(game)){
            return new EndGame();
        }
        return nextState(game);
    }
}
