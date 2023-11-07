package linea;

public class RedTurn extends GameState{

    public RedTurn(Linea game) {
        super(game);
    }

    @Override
    public void setTurn(String color) {
        if (color == "Azules") {
            throw new RuntimeException("No es el turno de jugar");
        }
        getGame().setState(new BlueTurn(getGame()));
    }
}
