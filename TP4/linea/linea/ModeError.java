package linea;

public class ModeError extends Mode {

    @Override
    public boolean checkWinner(Linea game) {
        return false;
    }

    @Override
    public boolean applies(char mode) {
        throw new RuntimeException(Linea.ModoNoValido);
    }
}