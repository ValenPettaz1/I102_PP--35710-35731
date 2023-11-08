package linea;


public class ModeC extends Mode {
    private char mode;
    public ModeC(){
        this.mode = 'C';
    }

    @Override
    public boolean checkWinner(Linea game) {
        return verticalCheck(game) || horizontalCheck(game) || rightDiagonalCheck(game) || leftDiagonalCheck(game);
    }

    @Override
    public boolean applies(char mode) {
        return true;
    }

}
